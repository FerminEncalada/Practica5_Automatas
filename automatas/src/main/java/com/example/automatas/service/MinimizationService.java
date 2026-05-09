package com.example.automatas.service;
import com.example.automatas.model.AFD;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MinimizationService {

    public AFD minimizar(AFD afd) {

        List<String> estados =
                new ArrayList<>(afd.getEstados());

        Set<String> finales =
                afd.getEstadosFinales();

        Map<String, Boolean> tabla =
                new HashMap<>();

        /*
         * Paso 1:
         * Marcar estados distinguibles
         */

        for (int i = 0; i < estados.size(); i++) {

            for (int j = i + 1;
                 j < estados.size();
                 j++) {

                String e1 = estados.get(i);
                String e2 = estados.get(j);

                String clave = generarClave(e1, e2);

                boolean distinguible =
                        finales.contains(e1)
                        != finales.contains(e2);

                tabla.put(clave, distinguible);
            }
        }

        /*
         * Paso 2:
         * Revisar transiciones
         */

        boolean cambio;

        do {

            cambio = false;

            for (int i = 0; i < estados.size(); i++) {

                for (int j = i + 1;
                     j < estados.size();
                     j++) {

                    String e1 = estados.get(i);
                    String e2 = estados.get(j);

                    String clave =
                            generarClave(e1, e2);

                    if (tabla.get(clave)) {
                        continue;
                    }

                    for (String simbolo :
                            afd.getAlfabeto()) {

                        String destino1 =
                                afd.obtenerTransicion(
                                        e1,
                                        simbolo
                                );

                        String destino2 =
                                afd.obtenerTransicion(
                                        e2,
                                        simbolo
                                );

                        if (destino1 == null
                                || destino2 == null) {

                            continue;
                        }

                        String claveDestino =
                                generarClave(
                                        destino1,
                                        destino2
                                );

                        if (tabla.getOrDefault(
                                claveDestino,
                                false)) {

                            tabla.put(clave, true);

                            cambio = true;

                            break;
                        }
                    }
                }
            }

        } while (cambio);

        /*
         * Paso 3:
         * Encontrar equivalentes
         */

        Map<String, String> representante =
                new HashMap<>();

        for (String estado : estados) {
            representante.put(estado, estado);
        }

        for (int i = 0; i < estados.size(); i++) {

            for (int j = i + 1;
                 j < estados.size();
                 j++) {

                String e1 = estados.get(i);
                String e2 = estados.get(j);

                String clave =
                        generarClave(e1, e2);

                if (!tabla.get(clave)) {

                    representante.put(e2, e1);
                }
            }
        }

        /*
         * Paso 4:
         * Construir nuevo AFD
         */

        Set<String> nuevosEstados =
                new HashSet<>(
                        representante.values()
                );

        Set<String> nuevosFinales =
                new HashSet<>();

        for (String estado : finales) {

            nuevosFinales.add(
                    representante.get(estado)
            );
        }

        String nuevoInicial =
                representante.get(
                        afd.getEstadoInicial()
                );

        AFD minimizado =
                new AFD(
                        nuevosEstados,
                        afd.getAlfabeto(),
                        nuevoInicial,
                        nuevosFinales
                );

        for (String estado :
                afd.getEstados()) {

            for (String simbolo :
                    afd.getAlfabeto()) {

                String destino =
                        afd.obtenerTransicion(
                                estado,
                                simbolo
                        );

                if (destino == null) {
                    continue;
                }

                minimizado.agregarTransicion(
                        representante.get(estado),
                        simbolo,
                        representante.get(destino)
                );
            }
        }

        return minimizado;
    }

    private String generarClave(String a,
                                String b) {

        if (a.compareTo(b) < 0) {
            return a + "-" + b;
        }

        return b + "-" + a;
    }
}