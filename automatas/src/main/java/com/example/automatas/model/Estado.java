package com.example.automatas.model;

public class Estado {

    private String nombre;
    private boolean aceptacion;

    public Estado(String nombre, boolean aceptacion) {
        this.nombre = nombre;
        this.aceptacion = aceptacion;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isAceptacion() {
        return aceptacion;
    }
}