package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.service.AntrenoriFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
Controller de tip REST(clasa Bean) ce se ocupa de handluirea requesturilor ce sunt trimise
la adresa URL "../antrenori"(plecand de la localhost:...)
 */
@RestController
@RequestMapping("/antrenori")
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
     */
    @GetMapping
    public List<AntrenorFitnessDTO> findAllAntrenoriFitness(){
        return antrenoriFitnessService.findAllAntrenoriFitness();
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