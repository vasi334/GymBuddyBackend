package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.service.AntrenoriFitnessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        return "antrenori";
    }
    @GetMapping("/antrenori/adauga_antrenor")
    public String showAddAntrenorPage(){
        return "add_antrenor";
    }
    @PostMapping("/antrenori/adauga_antrenor")
    public ResponseEntity<String> createAntrenor(@RequestBody AntrenorFitness antrenorRequest) {
        try {
            AntrenorFitness antrenorFitnessToBeSaved = new AntrenorFitness();
            antrenorFitnessToBeSaved.setFirstName(antrenorRequest.getFirstName());
            antrenorFitnessToBeSaved.setLastName(antrenorRequest.getLastName());
            antrenorFitnessToBeSaved.setContactInformation(antrenorRequest.getContactInformation());

            antrenoriFitnessService.save(antrenorFitnessToBeSaved);

            return new ResponseEntity<>("Antrenor salvat cu succes! "
                    , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Eroare la salvarea antrenorului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/antrenori/{id}")
    public ResponseEntity<String> deleteAntrenor(@PathVariable Long id){
        try{
            antrenoriFitnessService.deleteAntrenor(id);
            return new ResponseEntity<>("Antrenor sters cu succes!", HttpStatus.OK);
        }
        catch (Exception ex){
            return new ResponseEntity<>("Eroare la stergerea antrenorului.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
