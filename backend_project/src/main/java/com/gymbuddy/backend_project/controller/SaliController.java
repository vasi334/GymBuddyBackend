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

/**
 Controller de tip REST(clasa Bean) ce se ocupa de handluirea requesturilor ce sunt trimise
 la adresa URL "../sali"(plecand de la localhost:...)
 */

@Controller
@RequestMapping("/gyms")
public class SaliController {

    private final SaliService saliService;
    /**
     * Depdendency injection
     * @param saliService-SaliService
     */

    @Autowired
    public SaliController(SaliService saliService) {
        this.saliService = saliService;
    }

    /**
     * @return Multimea de informatii dorite ale salilor existente, prezentate la pagina dorita
     * Numele, adresa, adresa web
     */
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

    /**
     *
     * @param salaFitnessRequest - Sala de fitness pe care dorim să o adaugam
     * @return Raspunsul vizavi de adaugarea cu succes a salii
     * sau esuarea adaugarii acesteia(fie informatiile
     * prezentate sunt deja preluate de catre o alta sala, fie etc)
     */
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

    /**
     *
     * @param id - Id-ul salii de fitness pe care dorim sa o stergem
     * @return Raspunsul vizavi de stergerea cu succes a salii(A existat
     * o sala fitness de Id dorit) sau absenta salii de fitness de Id dorit ce dorim
     * sa o stergem
     */
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