package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.entity.Video;
import com.gymbuddy.backend_project.repository.NutritionistRepository;
import com.gymbuddy.backend_project.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

// clasa de NutritionistServiceImpl care implemeteaza metodele din NutritionistService
@Service
public class NutritionistServiceImpl implements NutritionistService {
    private NutritionistRepository nutritionistRepository;

    // constructor pentru clasa de NutritionistServiceImpl
    @Autowired
    public NutritionistServiceImpl(NutritionistRepository nutritionistRepository) {
        this.nutritionistRepository = nutritionistRepository;
    }

    // metoda de salvare a unui nutritionist in baza de date
    @Override
    public void saveNutritionist(NutritionistDto nutritionist) {
        nutritionistRepository.save(nutritionist.getNutritionist());
    }

    // metoda de gasire a unui nutritionist dupa email
    @Override
    public Nutritionist findNutritionistByEmail(String email) {
        return nutritionistRepository.findByEmail(email);
    }

    // returneaza o lista cu toti nutritionistii din baza de date
    @Override
    public List<Nutritionist> findAllNutritionisti() {
        List<Nutritionist> nutritionisti=nutritionistRepository.findAll();
        List<Nutritionist> nutritionistiDto=new ArrayList<>();
        for (Nutritionist i :nutritionisti)
             nutritionistiDto.add(i);
        return nutritionistiDto;

    }

    // sterge un nutritionist dupa id
    @Override
    public void deleteNutritionist(Long id) {
        nutritionistRepository.deleteById(id);
    }
}
