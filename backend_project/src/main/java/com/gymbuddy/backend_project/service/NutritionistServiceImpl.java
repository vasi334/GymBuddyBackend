package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.repository.NutritionistRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class NutritionistServiceImpl implements NutritionistService {
    private NutritionistRepository nutritionistRepository;

    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository) {
        this.nutritionistRepository = nutritionistRepository;
    }

    @Override
    public void saveNutritionist(NutritionistDto nutritionist) {
        nutritionistRepository.save(nutritionist.getNutritionist());
    }

    @Override
    public Nutritionist findNutritionistByEmail(String email) {
        return nutritionistRepository.findByEmail(email);
    }

    @Override
    public List<Nutritionist> findAllNutritionisti() {
        List<Nutritionist> nutritionisti=nutritionistRepository.findAll();
        List<Nutritionist> nutritionistiDto=new ArrayList<>();
        for (Nutritionist i :nutritionisti)
             nutritionistiDto.add(i);
        return nutritionistiDto;

    }
}
