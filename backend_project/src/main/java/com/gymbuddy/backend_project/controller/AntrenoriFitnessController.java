package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.service.AntrenoriFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
Controller de tip REST(clasa Bean) ce se ocupa de handluirea requesturilor ce sunt trimise
la adresa URL "../antrenori"(plecand de la localhost:...)
 */
@Controller
public class AntrenoriFitnessController {

    private final AntrenoriFitnessService antrenoriFitnessService;

    /**
     * Depdendency injection
     * @param antrenoriFitnessService-AntrenoriFitnessService
     */
    @Autowired
    public AntrenoriFitnessController(AntrenoriFitnessService antrenoriFitnessService){
        this.antrenoriFitnessService = antrenoriFitnessService;
    }

    /**
     * @return Multimea de informatii doritre(DTO) ale antrenorilor existenti, prezentati la pagia dorita
     * Numele, prenumele, informatii de contact, ETC
     */
    @GetMapping("/antrenori")
    public String findAllAntrenoriFitness(Model model) {
        List<AntrenorFitnessDTO> listaAntrenori = antrenoriFitnessService.findAllAntrenoriFitness();
        model.addAttribute("antrenori", listaAntrenori);
        return "antrenori"; // This should be the name of your Thymeleaf template (antrenori.html)
    }


    /**
     * Salvam informatiile unui antrenor(Daca putem)
     * @param antrenorFitnessDTO-AntrenorFitnessDTO
     */
    @PostMapping
    public void addNewAntrenor(@RequestBody AntrenorFitnessDTO antrenorFitnessDTO){
        this.antrenoriFitnessService.save(antrenorFitnessDTO);
    }
}
