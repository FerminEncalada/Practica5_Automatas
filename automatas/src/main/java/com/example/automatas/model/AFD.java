package com.example.automatas.model;

import java.util.*;

public class AFD {

    private final Set<String> estados;
    private final Set<String> alfabeto;

    private final Map<String, Map<String, String>> transiciones;

    private final String estadoInicial;

    private final Set<String> estadosFinales;

    public AFD(Set<String> estados,
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

        transiciones.putIfAbsent(origen, new HashMap<>());

        transiciones.get(origen)
                .put(simbolo, destino);
    }

    public String obtenerTransicion(String estado,
                                    String simbolo) {

        if (!transiciones.containsKey(estado)) {
            return null;
        }

        return transiciones.get(estado)
                .get(simbolo);
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

    public Map<String, Map<String, String>> getTransiciones() {
        return transiciones;
    }
}