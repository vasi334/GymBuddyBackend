package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionisti;
import com.gymbuddy.backend_project.repository.NutritionistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NutritionistServiceImpl implements NutritionistiService {
    private NutritionistRepository nutritionistRepository;

    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository) {
        this.nutritionistRepository = nutritionistRepository;
    }

    @Override
    public void saveNutritionist(NutritionistDto nutritionist) {
        nutritionistRepository.save(nutritionist.getNutritionist());
    }

    @Override
    public Nutritionisti findNutritionistByEmail(String email) {
        return nutritionistRepository.findByEmail(email);
    }

    @Override
    public List<Nutritionisti> findAllNutritionisti() {
        List<Nutritionisti> nutritionisti=nutritionistRepository.findAll();
        List<Nutritionisti> nutritionistiDto=new ArrayList<>();
        for (Nutritionisti i :nutritionisti)
             nutritionistiDto.add(i);
        return nutritionistiDto;

    }
}
