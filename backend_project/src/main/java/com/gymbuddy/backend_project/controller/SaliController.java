package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.dto.SaliDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.service.SaliService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping
public class SaliController {
    private final SaliService saliService;

    public SaliController(SaliService saliService){
        this.saliService = saliService;
    }

    @GetMapping("/sali")
    String getAllNutritionisti(Model model)
    {
        List<SalaFitness> listaSali=saliService.findAllSali();
        model.addAttribute("sali", listaSali);
        return "sali";
    }

    @GetMapping("/adauga_sala")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        SaliDto sali = new SaliDto();
        model.addAttribute("sali", sali);
        return "adaugare_sala";
    }

}
