package com.gymbuddy.backend_project.controller;
import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.dto.SalaDto;
import com.gymbuddy.backend_project.entity.Sala;
import com.gymbuddy.backend_project.service.SalaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class SalaController {
    private SalaService salaService;
    @Autowired
    public SalaController(SalaService salaService) {
        this.salaService = salaService;
    }

    @GetMapping("/sali")
    String getAllSali(Model model)
    {
        List<Sala> listaSali=salaService.findAllSali();
        model.addAttribute("sali", listaSali);
        return "sali";
    }

    @GetMapping("/sali/adaugare_sala")
    public String showRegistrationForm(Model model){
        SalaDto sala = new SalaDto();
        model.addAttribute("sala", sala);
        return "adaugare_sala";
    }

}
