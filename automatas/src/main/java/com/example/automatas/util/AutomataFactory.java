// AutomataFactory.java — versión corregida y alineada con el reporte técnico

package com.example.automatas.util;

import com.example.automatas.model.AFND;
import java.util.*;

public class AutomataFactory {

    /**
     * Autómata 1 — Validación de Protocolo de Telemetría IoT
     *
     * Lenguaje: HDR (TEMP | HUM)* CRC  →  H(T|U)*C
     *
     * Q  = {q0, q1, q2, q3, q4}
     * Σ  = {H, T, U, C}
     * q0 = estado inicial
     * F  = {q4}
     *
     * δ(q0, H) = {q1}
     * δ(q1, T) = {q2, q3}
     * δ(q1, U) = {q2}
     * δ(q1, C) = {q4}
     * δ(q2, T) = {q2}
     * δ(q2, U) = {q2}
     * δ(q2, C) = {q4}
     * δ(q3, C) = {q4}
     */
    public static AFND crearAutomataIoT() {

        Set<String> estados  = new HashSet<>(Arrays.asList("q0","q1","q2","q3","q4"));
        Set<String> alfabeto = new HashSet<>(Arrays.asList("H","T","U","C"));
        Set<String> finales  = new HashSet<>(Arrays.asList("q4"));

        AFND afnd = new AFND(estados, alfabeto, "q0", finales);

        afnd.agregarTransicion("q0", "H", "q1");

        afnd.agregarTransicion("q1", "T", "q2");
        afnd.agregarTransicion("q1", "T", "q3");
        afnd.agregarTransicion("q1", "U", "q2");
        afnd.agregarTransicion("q1", "C", "q4");

        afnd.agregarTransicion("q2", "T", "q2");
        afnd.agregarTransicion("q2", "U", "q2");
        afnd.agregarTransicion("q2", "C", "q4");

        afnd.agregarTransicion("q3", "C", "q4");

        return afnd;
    }

    /**
     * Autómata 2 — Reconocimiento de Secuencias Genéticas (Bioinformática)
     *
     * Lenguaje: KGX*F
     *
     * Q  = {q0, q1, q2, q3}
     * Σ  = {K, G, X, F}
     * q0 = estado inicial
     * F  = {q3}
     *
     * δ(q0, K) = {q1}
     * δ(q0, F) = {q0}
     * δ(q0, G) = {q0}
     * δ(q0, X) = {q0}
     * δ(q1, G) = {q2}
     * δ(q2, K) = {q2}
     * δ(q2, G) = {q2}
     * δ(q2, X) = {q2}
     * δ(q2, F) = {q3}
     */
    public static AFND crearAutomataGenetico() {

        Set<String> estados  = new HashSet<>(Arrays.asList("q0","q1","q2","q3"));
        Set<String> alfabeto = new HashSet<>(Arrays.asList("K","G","X","F"));
        Set<String> finales  = new HashSet<>(Arrays.asList("q3"));

        AFND afnd = new AFND(estados, alfabeto, "q0", finales);

        afnd.agregarTransicion("q0", "K", "q1");
        afnd.agregarTransicion("q0", "F", "q0");
        afnd.agregarTransicion("q0", "G", "q0");
        afnd.agregarTransicion("q0", "X", "q0");

        afnd.agregarTransicion("q1", "G", "q2");

        afnd.agregarTransicion("q2", "K", "q2");
        afnd.agregarTransicion("q2", "G", "q2");
        afnd.agregarTransicion("q2", "X", "q2");
        afnd.agregarTransicion("q2", "F", "q3");

        return afnd;
    }

    /**
     * Autómata 3 — Analizador de Comportamiento de Usuario (E-commerce)
     *
     * Lenguaje: HOME SEARCH+ CART  →  HS+C
     *
     * Q  = {q0, q1, q2, q3}
     * Σ  = {H, S, C}
     * q0 = estado inicial
     * F  = {q3}
     *
     * δ(q0, H) = {q1}
     * δ(q1, S) = {q1, q2}   ← no-determinismo: q1 para seguir buscando, q2 para ir al carrito
     * δ(q2, C) = {q3}
     */
    public static AFND crearAutomataEcommerce() {

        Set<String> estados  = new HashSet<>(Arrays.asList("q0","q1","q2","q3"));
        Set<String> alfabeto = new HashSet<>(Arrays.asList("H","S","C"));
        Set<String> finales  = new HashSet<>(Arrays.asList("q3"));

        AFND afnd = new AFND(estados, alfabeto, "q0", finales);

        afnd.agregarTransicion("q0", "H", "q1");

        afnd.agregarTransicion("q1", "S", "q1");
        afnd.agregarTransicion("q1", "S", "q2");

        afnd.agregarTransicion("q2", "C", "q3");

        return afnd;
    }
}