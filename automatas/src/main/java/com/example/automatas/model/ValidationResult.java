package com.example.automatas.model;

import java.util.List;

public class ValidationResult {

    private final boolean aceptada;

    private final List<String> recorrido;

    public ValidationResult(boolean aceptada,
                            List<String> recorrido) {

        this.aceptada = aceptada;
        this.recorrido = recorrido;
    }

    public boolean isAceptada() {
        return aceptada;
    }

    public List<String> getRecorrido() {
        return recorrido;
    }
}