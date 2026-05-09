package com.example.automatas.model;

import java.util.*;

public class AFND {

    /*
     * Q = conjunto de estados
     */
    private final Set<String> estados;

    /*
     * Σ = alfabeto
     */
    private final Set<String> alfabeto;

    /*
     * δ = función de transición
     *
     * Estado -> símbolo -> conjunto de destinos
     */
    private final Map<String, Map<String, Set<String>>> transiciones;

    /*
     * q0 = estado inicial
     */
    private final String estadoInicial;

    /*
     * F = estados finales
     */
    private final Set<String> estadosFinales;

    public AFND(Set<String> estados,
                Set<String> alfabeto,
                String estadoInicial,
                Set<String> estadosFinales) {

        this.estados = estados;
        this.alfabeto = alfabeto;
        this.estadoInicial = estadoInicial;
        this.estadosFinales = estadosFinales;

        this.transiciones = new HashMap<>();
    }

    public void agregarTransicion(String origen,
                                  String simbolo,
                                  String destino) {

        transiciones.putIfAbsent(
                origen,
                new HashMap<>()
        );

        transiciones.get(origen)
                .putIfAbsent(
                        simbolo,
                        new HashSet<>()
                );

        transiciones.get(origen)
                .get(simbolo)
                .add(destino);
    }

    public Set<String> obtenerTransiciones(String estado,
                                           String simbolo) {

        if (!transiciones.containsKey(estado)) {
            return new HashSet<>();
        }

        return transiciones.get(estado)
                .getOrDefault(
                        simbolo,
                        new HashSet<>()
                );
    }

    public Set<String> getEstados() {
        return estados;
    }

    public Set<String> getAlfabeto() {
        return alfabeto;
    }

    public String getEstadoInicial() {
        return estadoInicial;
    }

    public Set<String> getEstadosFinales() {
        return estadosFinales;
    }

    public Map<String, Map<String, Set<String>>> getTransiciones() {
        return transiciones;
    }
}