package com.example.automatas.service;

import com.example.automatas.model.AFD;

import org.springframework.stereotype.Service;

@Service
public class ValidationService {

    public boolean validar(AFD afd,
                           String cadena) {

        String estadoActual =
                afd.getEstadoInicial();

        for (char simbolo :
                cadena.toCharArray()) {

            estadoActual =
                    afd.obtenerTransicion(
                            estadoActual,
                            String.valueOf(simbolo)
                    );

            if (estadoActual == null) {
                return false;
            }
        }

        return afd.getEstadosFinales()
                .contains(estadoActual);
    }
}