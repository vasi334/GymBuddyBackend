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
     * @return Multimea de informatii dorite(DTO) ale antrenorilor existenti, prezentati la pagina dorita
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

    /**
     *
     * @param antrenorRequest - Antrenorul ce dorim să îl adaugam
     * @return Raspunsul vizavi de adaugarea cu succes a antrenorului
     * sau esuarea adaugarii acestuia(fie informatiile de contact ce le-a
     * prezentat sunt deja preluate de catre un alt antrenor, fie etc)
     */
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

    /**
     *
     * @param id - Id-ul antrenorului ce dorim sa il stergem
     * @return Raspsunul vizavi de stergerea cu succes a antrenorului(A existat
     * un antrenor de Id dorit) sau absenta antrenorului de Id dorit ce dorim
     * sa il stergem
     */
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
