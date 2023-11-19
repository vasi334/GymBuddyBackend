package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionisti;

import java.util.List;

public interface NutritionistiService {
    void saveNutritionist(NutritionistDto nutritionist );

    Nutritionisti findNutritionistByEmail(String email);

    List<Nutritionisti> findAllNutritionisti();
}
