package com.gymbuddy.backend_project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
import com.gymbuddy.backend_project.repository.NutritionistRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class NutritionistIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private NutritionistRepository nutritionistRepository;

    private final Faker faker = new Faker();

    @Test
    @Transactional
    @Disabled
    /*
    Metoda nefunctionala!!!
     */
    void canSaveNutritionist() throws Exception {
        //given
        Nutritionist nutritionist = new Nutritionist();
        nutritionist.setNume(faker.name().toString());
        nutritionist.setSpecializare("dietetician");
        nutritionist.setNr_telefon(faker.phoneNumber().toString());
        nutritionist.setEmail(faker.internet().emailAddress());
        nutritionist.setSite(faker.internet().url());

        String uniqueEmail = nutritionist.getEmail();

        //when
        ResultActions resultActions = mockMvc
                .perform(post("/adauga_nutritionist")
                .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nutritionist)));

        //then
        resultActions.andExpect(status().isOk());
        Nutritionist nutritionistAdded = nutritionistRepository.save(nutritionist);
        assertThat(nutritionistAdded).isNotNull();
        assertThat(nutritionistAdded.getEmail()).isEqualTo(uniqueEmail);
        assertThat(nutritionistAdded.getNume()).isEqualTo(nutritionist.getNume());
    }

    @Test
    void canGetAllNutritionisti() throws Exception {
        //given
        Nutritionist nutritionist = new Nutritionist();
        nutritionist.setNume(faker.name().toString());
        nutritionist.setSpecializare("dietetician");
        nutritionist.setNr_telefon(faker.phoneNumber().toString());
        nutritionist.setEmail(faker.internet().emailAddress());
        nutritionist.setSite(faker.internet().url());
        nutritionistRepository.save(nutritionist);

        //when
        //then
        MvcResult getNutritionistiResult = mockMvc
                .perform(get("/nutritionists")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<Nutritionist> nutritionistsFound = (List<Nutritionist>) getNutritionistiResult
                .getModelAndView().getModel().get("nutritionisti");

        assertThat(nutritionistsFound).hasSizeGreaterThanOrEqualTo(1);
        assertThat(nutritionistsFound).filteredOn(nutritionistGAsit -> nutritionistGAsit
                        .getId().equals(nutritionist.getId()))
                .hasSize(1);
    }
}
