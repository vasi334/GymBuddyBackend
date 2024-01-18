package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.repository.NutritionistRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatList;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class NutritionistServiceTest {
    @Mock
    NutritionistRepository nutritionistRepository;

    private NutritionistServiceImpl testNutritionistService;

    @BeforeEach
    void setUp(){
        testNutritionistService = new NutritionistServiceImpl(nutritionistRepository);
    }

    @Test
    void canSaveNutritionist() {
        //given
        NutritionistDto nutritionistDto = new NutritionistDto();
        nutritionistDto.setId(1L);
        nutritionistDto.setNume("Mirel Radoi");
        nutritionistDto.setSpecializare("dietetician");
        nutritionistDto.setEmail("mirelradoi@gmail.com");
        nutritionistDto.setSite("site");
        nutritionistDto.setNr_telefon("0741314234");

        //when
        testNutritionistService.saveNutritionist(nutritionistDto);

        //then
        ArgumentCaptor<Nutritionist> nutritionistArgumentCaptor = ArgumentCaptor
                .forClass(Nutritionist.class);
        verify(nutritionistRepository).save(nutritionistArgumentCaptor.capture());
        NutritionistDto capturedFromMethod = nutritionistArgumentCaptor.getValue()
                .modificareNutritionist();
        boolean oneAndTheSame = nutritionistDto.getId().equals(capturedFromMethod.getId());
        assertThat(oneAndTheSame).isTrue();
    }

    @Test
    void canGettAllNutritionisti() {
        //when
        testNutritionistService.findAllNutritionisti();

        //then
        verify(nutritionistRepository).findAll();

        assertThatList(nutritionistRepository.findAll())
                .hasSizeLessThanOrEqualTo(testNutritionistService.findAllNutritionisti().size());
    }

    @Test
    void canFindNutritionistByEmail(){
        //given
        Nutritionist nutritionist = new Nutritionist();
        nutritionist.setId(1L);
        nutritionist.setNume("Mirel Radoi");
        nutritionist.setSpecializare("dietetician");
        nutritionist.setEmail("mirelradoi@gmail.com");
        nutritionist.setSite("site");
        nutritionist.setNr_telefon("0741314234");

        //when
        testNutritionistService.findNutritionistByEmail(nutritionist.getEmail());

        //then
        ArgumentCaptor<String> emailCaptor = ArgumentCaptor.forClass(String.class);
        verify(nutritionistRepository).findByEmail(emailCaptor.capture());
        String emailCaptured = emailCaptor.getValue();
        assertThat(emailCaptured).isEqualTo(nutritionist.getEmail());
    }

}