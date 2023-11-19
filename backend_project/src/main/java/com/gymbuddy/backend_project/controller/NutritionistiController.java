package com.gymbuddy.backend_project.controller;

import com.gymbuddy.backend_project.entity.Nutritionisti;
import com.gymbuddy.backend_project.service.NutritionistiService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class NutritionistiController {
    private NutritionistiService nutritionistService;

    public NutritionistiController(NutritionistiService nutritionistService) {
        this.nutritionistService = nutritionistService;
    }

    @GetMapping("/nutritionisti")
    List<Nutritionisti> getAllNutritionisti()
    {
        return nutritionistService.findAllNutritionisti();
    }


}
