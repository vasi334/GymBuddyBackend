package com.gymbuddy.backend_project.controller;
import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.service.SaliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/gyms")
public class SaliController {

    private final SaliService saliService;

    @Autowired
    public SaliController(SaliService saliService) {
        this.saliService = saliService;
    }

    @GetMapping
    public String getAllSali(Model model) {
        List<SalaFitness> sali = saliService.getAllSali();
        model.addAttribute("sali", sali);
        return "gyms";
    }

    @GetMapping("/add_sali_fitness")
    public String showAddSalaPage(){
        return "add_sali_fitness";
    }

    @PostMapping("/add_sali_fitness")
    public ResponseEntity<String> createSalaFitness(@RequestBody SalaFitness salaFitnessRequest){
        try {
            SalaFitness salaFitness = new SalaFitness();
            salaFitness.setNume(salaFitnessRequest.getNume());
            salaFitness.setAdresa(salaFitnessRequest.getAdresa());
            salaFitness.setWebAdresa(salaFitnessRequest.getWebAdresa());
            saliService.createSala(salaFitness);
            return new ResponseEntity<>("Sala Fitness salvata cu succes!", HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>("Eroare la salvarea salii!",HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public String getSalaById(@PathVariable Long id, Model model) {
        SalaFitness sala = saliService.getSalaById(id);
        model.addAttribute("sala", sala);
        return "detalii_sala";
    }

    @PostMapping
    public String createSala(@ModelAttribute SalaFitness sala) {
        saliService.createSala(sala);
        return "redirect:/sali";
    }

    @PutMapping("/{id}")
    public String updateSala(@PathVariable Long id, @ModelAttribute SalaFitness updatedSala) {
        saliService.updateSala(id, updatedSala);
        return "redirect:/sali";
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteSala(@PathVariable Long id) {
        try {
            saliService.deleteSala(id);
            return new ResponseEntity<>("Sala a fost ștearsa cu succes!", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la ștergerea sălii.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search")
    public String getSaliByNume(@RequestParam String nume, Model model) {
        List<SalaFitness> sali = saliService.getSaliByNume(nume);
        model.addAttribute("sali", sali);
        return "sali_fitness";
    }
}