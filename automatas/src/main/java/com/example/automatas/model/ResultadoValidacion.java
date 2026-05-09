package com.example.automatas.model;

public class ResultadoValidacion {

    private String cadena;
    private boolean aceptada;
    private String automata;

    public ResultadoValidacion(String cadena,
                               boolean aceptada,
                               String automata) {

        this.cadena = cadena;
        this.aceptada = aceptada;
        this.automata = automata;
    }

    public String getCadena() {
        return cadena;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public String getAutomata() {
        return automata;
    }
}