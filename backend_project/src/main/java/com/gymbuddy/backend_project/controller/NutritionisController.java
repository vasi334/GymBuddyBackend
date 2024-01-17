package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.dto.UserDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.service.NutritionistService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class NutritionisController {
    private NutritionistService nutritionistService;

    // Constructor to inject NutritionistService
    public NutritionisController(NutritionistService nutritionistService) {
        this.nutritionistService = nutritionistService;
    }

    // Handler method to get all nutritionists and display them in a view
    @GetMapping("/nutritionists")
    String getAllNutritionisti(Model model)
    {
        List<Nutritionist> listaNutritionisti = nutritionistService.findAllNutritionisti();
        model.addAttribute("nutritionisti", listaNutritionisti);
        return "nutritionists";
    }

    // Handler method to show the registration form for adding a nutritionist
    @GetMapping("/adauga_nutritionist")
    public String showRegistrationForm(Model model){
        // Create model object to store form data
        NutritionistDto nutritionist = new NutritionistDto();
        model.addAttribute("nutritionist", nutritionist);
        return "adaugare_nutritionist";
    }

}
