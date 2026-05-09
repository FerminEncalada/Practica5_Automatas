package com.example.automatas.util;

import com.example.automatas.model.AFND;

import java.util.*;

public class AutomataFactory {

    public static AFND crearAutomataIoT() {

        Set<String> estados =
                Set.of("q0", "q1", "q2", "qf");

        Set<String> alfabeto =
                Set.of("H", "T", "U", "C");

        Set<String> finales =
                Set.of("qf");

        AFND afnd = new AFND(
                estados,
                alfabeto,
                "q0",
                finales
        );

        afnd.agregarTransicion("q0", "H", "q1");

        afnd.agregarTransicion("q1", "T", "q1");
        afnd.agregarTransicion("q1", "T", "q2");

        afnd.agregarTransicion("q1", "U", "q1");
        afnd.agregarTransicion("q1", "U", "q2");

        afnd.agregarTransicion("q2", "C", "qf");

        return afnd;
    }

    public static AFND crearAutomataGenetico() {

        Set<String> estados =
                Set.of("q0", "q1", "q2", "q3", "qf");

        Set<String> alfabeto =
                Set.of("K", "G", "X", "F");

        Set<String> finales =
                Set.of("qf");

        AFND afnd = new AFND(
                estados,
                alfabeto,
                "q0",
                finales
        );

        afnd.agregarTransicion("q0", "K", "q1");

        afnd.agregarTransicion("q1", "G", "q2");

        afnd.agregarTransicion("q2", "X", "q2");
        afnd.agregarTransicion("q2", "X", "q3");

        afnd.agregarTransicion("q2", "F", "qf");
        afnd.agregarTransicion("q3", "F", "qf");

        return afnd;
    }

    public static AFND crearAutomataEcommerce() {

        Set<String> estados =
                Set.of("q0", "q1", "q2", "qf");

        Set<String> alfabeto =
                Set.of("H", "S", "C");

        Set<String> finales =
                Set.of("qf");

        AFND afnd = new AFND(
                estados,
                alfabeto,
                "q0",
                finales
        );

        afnd.agregarTransicion("q0", "H", "q1");

        afnd.agregarTransicion("q1", "S", "q1");
        afnd.agregarTransicion("q1", "S", "q2");

        afnd.agregarTransicion("q2", "C", "qf");

        return afnd;
    }
}