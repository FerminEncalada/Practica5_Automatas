package com.example.automatas.service;

import com.example.automatas.model.AFND;
import com.example.automatas.util.AutomataFactory;

import org.springframework.stereotype.Service;

@Service
public class AFNDService {

    public AFND obtenerAutomata(String tipo) {

        switch (tipo) {

            case "iot":
                return AutomataFactory
                        .crearAutomataIoT();

            case "genetico":
                return AutomataFactory
                        .crearAutomataGenetico();

            default:
                return AutomataFactory
                        .crearAutomataEcommerce();
        }
    }
}