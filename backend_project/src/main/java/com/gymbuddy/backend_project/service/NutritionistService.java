package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionist;

import java.util.List;

// interfata de NutritionistService
public interface NutritionistService {
    void saveNutritionist(NutritionistDto nutritionist );

    Nutritionist findNutritionistByEmail(String email);

    List<Nutritionist> findAllNutritionisti();
    void deleteNutritionist(Long id);
}
