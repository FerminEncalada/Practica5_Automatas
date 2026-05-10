package com.example.automatas.service;

import com.example.automatas.model.AFD;
import com.example.automatas.model.ValidationResult;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ValidationService {

    public ValidationResult validar(AFD afd,
                                    String cadena) {

        List<String> recorrido =
                new ArrayList<>();

        String estadoActual =
                afd.getEstadoInicial();

        recorrido.add(
                "Inicio → " + estadoActual
        );

        for (char simbolo :
                cadena.toCharArray()) {

            estadoActual =
                    afd.obtenerTransicion(
                            estadoActual,
                            String.valueOf(simbolo)
                    );

            if (estadoActual == null) {

                recorrido.add(
                        simbolo +
                        " → ERROR"
                );

                return new ValidationResult(
                        false,
                        recorrido
                );
            }

            recorrido.add(
                    simbolo +
                    " → " +
                    estadoActual
            );
        }

        boolean aceptada =
                afd.getEstadosFinales()
                        .contains(estadoActual);

        return new ValidationResult(
                aceptada,
                recorrido
        );
    }
}