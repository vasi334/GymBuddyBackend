package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Nutritionist;
import org.aspectj.lang.annotation.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class NutritionistRepositoryTest {

    @Autowired
    private NutritionistRepository nutritionistTestRepository;

    @AfterEach
    void clear(){
        nutritionistTestRepository.deleteAll();
    }

    @Test
    void checkExistsNutritionistWithDesiredEmail(){
        //given
        String email = "mirel@gmail.com";
        String randomTtext = "random";
        Nutritionist nutritionist = new Nutritionist();
        nutritionist.setEmail(email);
        nutritionist.setSite(randomTtext);
        nutritionist.setNr_telefon(randomTtext);
        nutritionist.setNume(randomTtext);
        nutritionist.setSpecializare(randomTtext);

        nutritionistTestRepository.save(nutritionist);

        //then
        Nutritionist searchedNutritionist = nutritionistTestRepository.findByEmail(email);

        //expected(The nutritionist exist!)
        assertThat(searchedNutritionist).isNotNull();
    }

    @Test
    void checkDoesNotExistNutritionistWithDesiredEmail(){
        //given
        String email = "mirel@gmail.com";

        //then
        Nutritionist searchedNutritionist = nutritionistTestRepository.findByEmail(email);

        //expected
        assertThat(searchedNutritionist).isNull();
    }
}