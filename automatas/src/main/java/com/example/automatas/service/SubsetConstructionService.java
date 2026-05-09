package com.example.automatas.service;

import com.example.automatas.model.AFD;
import com.example.automatas.model.AFND;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SubsetConstructionService {

    public AFD convertir(AFND afnd) {

        Set<String> estadosAFD = new HashSet<>();

        Set<String> finalesAFD = new HashSet<>();

        Queue<Set<String>> cola = new LinkedList<>();

        Map<Set<String>, String> nombres =
                new HashMap<>();

        Set<String> inicial =
                Set.of(afnd.getEstadoInicial());

        nombres.put(inicial, "A");

        cola.add(inicial);

        estadosAFD.add("A");

        AFD afd = new AFD(
                estadosAFD,
                afnd.getAlfabeto(),
                "A",
                finalesAFD
        );

        char nombreActual = 'B';

        while (!cola.isEmpty()) {

            Set<String> actual = cola.poll();

            String nombreEstado =
                    nombres.get(actual);

            for (String simbolo :
                    afnd.getAlfabeto()) {

                Set<String> nuevo =
                        new HashSet<>();

                for (String estado : actual) {

                    nuevo.addAll(
                            afnd.obtenerTransiciones(
                                    estado,
                                    simbolo
                            )
                    );
                }

                if (nuevo.isEmpty()) {
                    continue;
                }

                if (!nombres.containsKey(nuevo)) {

                    nombres.put(
                            nuevo,
                            String.valueOf(nombreActual++)
                    );

                    cola.add(nuevo);

                    estadosAFD.add(
                            nombres.get(nuevo)
                    );
                }

                afd.agregarTransicion(
                        nombreEstado,
                        simbolo,
                        nombres.get(nuevo)
                );

                for (String estado : nuevo) {

                    if (afnd.getEstadosFinales()
                            .contains(estado)) {

                        finalesAFD.add(
                                nombres.get(nuevo)
                        );
                    }
                }
            }
        }

        return afd;
    }
}