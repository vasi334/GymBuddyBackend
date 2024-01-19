package com.gymbuddy.backend_project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class AntrenorFitnessIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private AntrenoriFitnessRepository antrenoriFitnessRepository;

    private final Faker faker = new Faker();

    /**
     * Folosim tag-ul @Transactional pentru a ne asigura ca dupa fiecare metoda
     * FACEM rollback <=> Eliminam modificarile facute asupra bazei de date
     * IN TIMPUL testului(adaugari de inregistrari, etc)
     * @throws Exception
     */
    @Test
    //@RepeatedTest(3)
    @Transactional
    void canSaveAntrenor() throws Exception {
        // given
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .bestReview("Nice")
                .gymInformation("18Gym")
                .contactInformation(faker.internet().emailAddress())
                .build();
        String uniqueEmail = antrenorFitness.getContactInformation();

        // when
        ResultActions resultActions = mockMvc
                .perform(post("/trainers/add_trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(antrenorFitness)));
        // then
        resultActions.andExpect(status().isOk());
        Optional<AntrenorFitness> antrenorFitnessAdded = antrenoriFitnessRepository.findByEmail(uniqueEmail);
        assertThat(antrenorFitnessAdded).isPresent();
        assertThat(antrenorFitnessAdded.get().getFirstName()).isEqualTo(antrenorFitness.getFirstName());
        assertThat(antrenorFitnessAdded.get().getLastName()).isEqualTo(antrenorFitness.getLastName());
    }

    @Test
    @Transactional
    void canDeleteAntrenor() throws Exception {
        // given
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .bestReview("Nice")
                .gymInformation("18Gym")
                .contactInformation(faker.internet().emailAddress())
                .build();


        mockMvc.perform(post("/trainers/add_trainer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(antrenorFitness)))
                .andExpect(status().isOk());

        MvcResult getAntrenoriFitnessResult = mockMvc.perform(get("/trainers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        var modelMap = getAntrenoriFitnessResult.getModelAndView().getModelMap();
        var entryIterator = modelMap.entrySet().iterator();
        List<AntrenorFitnessDTO> antrenoriDeFitness = new ArrayList<>();
        /*
        Exista o singura intrare(key-value pair), CARE CONTINE valorile salvate din request(in Value de entry)
         */
        while(entryIterator.hasNext())
        {
            var entry = entryIterator.next();
            antrenoriDeFitness = (List<AntrenorFitnessDTO>) entry.getValue();
        }
        /**
        List<AntrenorFitnessDTO> antrenorFitnessDTOS = (List<AntrenorFitnessDTO>) modelMap.get("trainers");
        Aceasta este ALTA MODALITATE, mai usoara, de a obtine datele dintr-un ModelMap; DAR trebuie sa ne uitam
        in controller pentru a vedea numele atributului, ACEL String din cadrul metodei de la metoda save din Controller,
        ce il returnam reprezinta numele atributului din ModelMap caruia ii asociem Colectia de obiecte(de tip antrenor)
         */

        long idForAntrenorTOBeDeleted = antrenoriDeFitness.stream()
                .filter(antrenorSearched -> antrenorSearched.getContactInformation()
                .equals(antrenorFitness.getContactInformation()))
                .map(AntrenorFitnessDTO::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Antrenor cu email: " + antrenorFitness.getContactInformation() + " nu a fost gasit!"));

        // when
        ResultActions resultActions = mockMvc
                .perform(delete("/trainers/" + idForAntrenorTOBeDeleted));

        // then
        resultActions.andExpect(status().isOk());
        boolean existsAntrenorThatHasBeenDeleted = antrenoriFitnessRepository.existsById(idForAntrenorTOBeDeleted);
        assertThat(existsAntrenorThatHasBeenDeleted).isFalse();
    }

    @Test
    void canGetAllStudents() throws Exception {
        //given
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .bestReview("Nice")
                .gymInformation("18Gym")
                .contactInformation(faker.internet().emailAddress())
                .build();

        antrenoriFitnessRepository.save(antrenorFitness);


        //when
        MvcResult getAntrenoriFitnessResult = mockMvc.perform(get("/trainers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();
        var modelMap = getAntrenoriFitnessResult.getModelAndView().getModelMap();
        var entryIterator = modelMap.entrySet().iterator();
        List<AntrenorFitnessDTO> antrenoriDeFitness = new ArrayList<>();

        while(entryIterator.hasNext())
        {
            var entry = entryIterator.next();
            antrenoriDeFitness = (List<AntrenorFitnessDTO>) entry.getValue();
        }

        //then
        assertThat(antrenoriDeFitness).hasSizeGreaterThanOrEqualTo(1);
        assertThat(antrenoriDeFitness)
                .filteredOn(antrenorFitnessDTO -> antrenorFitnessDTO.getId().equals(antrenorFitness.getId()))
                .hasSize(1);
    }
}
