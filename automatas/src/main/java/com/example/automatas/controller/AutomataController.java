package com.example.automatas.controller;

import com.example.automatas.model.AFD;
import com.example.automatas.model.AFND;

import com.example.automatas.service.*;

import com.example.automatas.util.AutomataFactory;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

@Controller
public class AutomataController {

    @Autowired
    private SubsetConstructionService subsetService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @PostMapping("/validar")
    public String validar(@RequestParam String automata,
                          @RequestParam String cadena,
                          Model model) {

        AFND afnd;

        switch (automata) {

            case "iot":
                afnd = AutomataFactory
                        .crearAutomataIoT();
                break;

            case "genetico":
                afnd = AutomataFactory
                        .crearAutomataGenetico();
                break;

            default:
                afnd = AutomataFactory
                        .crearAutomataEcommerce();
                break;
        }

        AFD afd =
                subsetService.convertir(afnd);

        boolean resultado =
                validationService
                        .validar(afd, cadena);

        model.addAttribute(
                "resultado",
                resultado
        );

        return "index";
    }
}