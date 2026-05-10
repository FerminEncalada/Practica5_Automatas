package com.example.automatas.controller;

import com.example.automatas.model.AFD;
import com.example.automatas.model.AFND;
import com.example.automatas.model.ValidationResult;

import com.example.automatas.service.AFNDService;
import com.example.automatas.service.AFNDValidationService;
import com.example.automatas.service.MinimizationService;
import com.example.automatas.service.SubsetConstructionService;
import com.example.automatas.service.ValidationService;

import org.springframework.beans.factory.annotation.Autowired;
 
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AutomataController {

    @Autowired
    private AFNDService afndService;

    @Autowired
    private AFNDValidationService afndValidationService;

    @Autowired
    private SubsetConstructionService subsetService;

    @Autowired
    private MinimizationService minimizationService;

    @Autowired
    private ValidationService validationService;

    @GetMapping("/")
    public String inicio(Model model) {

        model.addAttribute(
                "automata",
                "iot"
        );

        model.addAttribute(
                "alfabeto",
                "Σ = {H, T, U, C}"
        );

        model.addAttribute(
                "ejemplos",
                "HTC, HUC, HTTC, HUUC, HTUC"
        );

        return "index";
    }

    @PostMapping("/validar")
    public String validar(@RequestParam String automata,
                          @RequestParam String cadena,
                          Model model) {

        /*
         * Obtener AFND
         */
        AFND afnd =
                afndService.obtenerAutomata(
                        automata
                );

        /*
         * Validación AFND
         */
        ValidationResult resultadoAFND =
                afndValidationService
                        .validar(
                                afnd,
                                cadena
                        );

        /*
         * Conversión AFND -> AFD
         */
        AFD afd =
                subsetService.convertir(
                        afnd
                );

        /*
         * Validación AFD
         */
        ValidationResult resultadoAFD =
                validationService
                        .validar(
                                afd,
                                cadena
                        );

        /*
         * Minimización
         */
        AFD afdMinimizado =
                minimizationService
                        .minimizar(
                                afd
                        );

        /*
         * Validación AFD mínimo
         */
        ValidationResult resultadoMinimizado =
                validationService
                        .validar(
                                afdMinimizado,
                                cadena
                        );

        /*
         * Equivalencia
         */
        boolean equivalentes =
                resultadoAFND.isAceptada()
                ==
                resultadoAFD.isAceptada()
                &&
                resultadoAFD.isAceptada()
                ==
                resultadoMinimizado.isAceptada();

        /*
         * Datos visuales
         */
        String alfabeto;
        String ejemplos;

        switch (automata) {

            case "iot":

                alfabeto =
                        "Σ = {H, T, U, C}";

                ejemplos =
                        "HTC, HUC, HTTC, HUUC, HTUC";

                break;

            case "genetico":

                alfabeto =
                        "Σ = {K, G, X, F}";

                ejemplos =
                        "KGF, KGXF, KGXXF, KGXXXF";

                break;

            default:

                alfabeto =
                        "Σ = {H, S, C}";

                ejemplos =
                        "HC, HSC, HSSC, HSSSC";
        }

        /*
         * Enviar datos
         */
        model.addAttribute(
                "automata",
                automata
        );

        model.addAttribute(
                "cadena",
                cadena
        );

        model.addAttribute(
                "alfabeto",
                alfabeto
        );

        model.addAttribute(
                "ejemplos",
                ejemplos
        );

        model.addAttribute(
                "resultadoAFND",
                resultadoAFND
        );

        model.addAttribute(
                "resultadoAFD",
                resultadoAFD
        );

        model.addAttribute(
                "resultadoMinimizado",
                resultadoMinimizado
        );

        model.addAttribute(
                "equivalentes",
                equivalentes
        );

        return "index";
    }
}