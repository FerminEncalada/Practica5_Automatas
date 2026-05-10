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

        model.addAttribute("automata", "iot");
        agregarDatosVisuales("iot", model);

        return "index";
    }

    @PostMapping("/validar")
    public String validar(@RequestParam String automata,
                          @RequestParam String cadena,
                          Model model) {

        AFND afnd = afndService.obtenerAutomata(automata);

        ValidationResult resultadoAFND =
                afndValidationService.validar(afnd, cadena);

        AFD afd = subsetService.convertir(afnd);

        ValidationResult resultadoAFD =
                validationService.validar(afd, cadena);

        AFD afdMinimizado = minimizationService.minimizar(afd);

        ValidationResult resultadoMinimizado =
                validationService.validar(afdMinimizado, cadena);

        boolean equivalentes =
                resultadoAFND.isAceptada() == resultadoAFD.isAceptada()
                && resultadoAFD.isAceptada() == resultadoMinimizado.isAceptada();

        model.addAttribute("automata",            automata);
        model.addAttribute("cadena",              cadena);
        model.addAttribute("resultadoAFND",       resultadoAFND);
        model.addAttribute("resultadoAFD",        resultadoAFD);
        model.addAttribute("resultadoMinimizado", resultadoMinimizado);
        model.addAttribute("equivalentes",        equivalentes);

        agregarDatosVisuales(automata, model);

        return "index";
    }

    private void agregarDatosVisuales(String automata, Model model) {

        String alfabeto;
        String ejemplos;

        switch (automata) {

            case "iot":
                alfabeto = "Σ = {H, T, U, C}  —  H: Header, T: Temperature sensor, U: Humidity sensor, C: Close";
                ejemplos = "HTC,  HUC,  HTTC,  HUUC,  HTUC,  HUTC,  HTTTC,  HUUUC,  HTUUC,  HTTUC,  HTUUTC,  HTUTUTC";
                break;

            case "genetico":
                alfabeto = "Σ = {K, G, X, F}  —  K: Start codon, G: Gene marker, X: Repeat unit, F: Finish codon";
                ejemplos = "KGF,  KGXF,  KGXXF,  KGXXXF,  KGXXXXF,  KGXXXXXF,  KGXXXXXXF,  KGXXXXXXXF,  KGXXXXXXXXF,  KGXXXXXXXXXF,  KGXXXXXXXXXXF,  KGXXXXXXXXXXXF";
                break;

            default:
                alfabeto = "Σ = {H, S, C}  —  H: Header/inicio de orden, S: Step/paso de proceso, C: Confirmation/confirmación";
                ejemplos = "HC,  HSC,  HSSC,  HSSSC,  HSSSSC,  HSSSSSC,  HSSSSSSC,  HSSSSSSSC,  HSSSSC,  HSSSSSC,  HSSSSSSSC,  HSSSSSSSSC";
                break;
        }

        model.addAttribute("alfabeto", alfabeto);
        model.addAttribute("ejemplos", ejemplos);
    }
}