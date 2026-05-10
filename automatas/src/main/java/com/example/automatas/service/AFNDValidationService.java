package com.example.automatas.service;

import com.example.automatas.model.AFND;
import com.example.automatas.model.ValidationResult;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AFNDValidationService {

    public ValidationResult validar(AFND afnd,
                                    String cadena) {

        List<String> recorrido =
                new ArrayList<>();

        Set<String> estadosActuales =
                new HashSet<>();

        estadosActuales.add(
                afnd.getEstadoInicial()
        );

        recorrido.add(
                "Inicio → " +
                estadosActuales
        );

        for (char simbolo :
                cadena.toCharArray()) {

            Set<String> nuevosEstados =
                    new HashSet<>();

            for (String estado :
                    estadosActuales) {

                nuevosEstados.addAll(
                        afnd.obtenerTransiciones(
                                estado,
                                String.valueOf(simbolo)
                        )
                );
            }

            estadosActuales = nuevosEstados;

            recorrido.add(
                    simbolo +
                    " → " +
                    estadosActuales
            );

            if (estadosActuales.isEmpty()) {

                return new ValidationResult(
                        false,
                        recorrido
                );
            }
        }

        boolean aceptada = false;

        for (String estado :
                estadosActuales) {

            if (afnd.getEstadosFinales()
                    .contains(estado)) {

                aceptada = true;

                break;
            }
        }

        return new ValidationResult(
                aceptada,
                recorrido
        );
    }
}