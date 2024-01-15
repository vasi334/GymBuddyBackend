package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.service.NutritionistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class NutritionisController {
    private NutritionistService nutritionistService;

    // constructor pentru clasa de NutritionisController,
    // autowired - se creeaza o instanta de tipul acelei clase
    @Autowired
    public NutritionisController(NutritionistService nutritionistService) {
        this.nutritionistService = nutritionistService;
    }

    // metoda de afisare pentru toti nutritionistii
    @GetMapping("/nutritionisti")
    String getAllNutritionisti(Model model)
    {
        List<Nutritionist> listaNutritionisti=nutritionistService.findAllNutritionisti();
        model.addAttribute("nutritionisti", listaNutritionisti);
        return "nutritionisti";
    }

    // metoda care afiseaza formularul pentru adaugarea unui nutritionist nou
    @GetMapping("/nutritionisti/adaugare_nutritionist")
    public String showRegistrationForm(Model model){
        // create model object to store form data
        NutritionistDto nutritionist = new NutritionistDto();
        model.addAttribute("nutritionist", nutritionist);
        return "adaugare_nutritionist";
    }

    // metoda de stergere a unui nutritionist dupa id
    @DeleteMapping("/nutritionisti/{id}")
    public String deleteNutritionist(@PathVariable Long id) {
        //System.out.println("salut");
        nutritionistService.deleteNutritionist(id);
        return "/nutritionisti";

    }
}
