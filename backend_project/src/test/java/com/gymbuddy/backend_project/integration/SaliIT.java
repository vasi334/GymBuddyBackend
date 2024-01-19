package com.gymbuddy.backend_project.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.repository.SaliRepository;
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

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-it.properties"
)
@AutoConfigureMockMvc
public class SaliIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SaliRepository salaFitnessRepository;

    private final Faker faker = new Faker();


    @Test
    //@RepeatedTest(3)
    @Transactional
    void canSaveSala() throws Exception {
        // given
        SalaFitness salaFitness = new SalaFitness();
        salaFitness.setId(1L);
        salaFitness.setNume(faker.name().fullName());
        salaFitness.setAdresa(faker.address().fullAddress());
        salaFitness.setWebAdresa(faker.internet().emailAddress());

        // when
        ResultActions resultActions = mockMvc
                .perform(post("/gyms/add_sali_fitness")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salaFitness)));
        // then
        resultActions.andExpect(status().isOk());
        List<SalaFitness> salaFitnessAdded = salaFitnessRepository.findByNume(salaFitness.getNume());
        assertThat(salaFitnessAdded).isNotEmpty();
        assertThat(salaFitnessAdded.get(0).getNume()).isEqualTo(salaFitness.getNume());
        assertThat(salaFitnessAdded.get(0).getWebAdresa()).isEqualTo(salaFitness.getWebAdresa());
    }


    @Test
    @Transactional
    void canDeleteSala() throws Exception {
        // given
        SalaFitness salaFitness = new SalaFitness();
        salaFitness.setId(1L);
        salaFitness.setNume(faker.name().fullName());
        salaFitness.setAdresa(faker.address().fullAddress());
        salaFitness.setWebAdresa(faker.internet().emailAddress());


        mockMvc.perform(post("/gyms/add_sali_fitness")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salaFitness)))
                .andExpect(status().isOk());

        MvcResult getSaliFitnessResult = mockMvc.perform(get("/gyms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<SalaFitness> saliFinessSearched =(List<SalaFitness>) getSaliFitnessResult
                            .getModelAndView().getModelMap().get("sali");

        long idForSalaToBeDeleted = saliFinessSearched.stream()
                .filter(antrenorSearched -> antrenorSearched.getWebAdresa()
                        .equals(salaFitness.getWebAdresa()))
                .map(SalaFitness::getId)
                .findFirst()
                .orElseThrow(() ->
                        new IllegalStateException(
                                "Sala cu adresa web: " + salaFitness.getWebAdresa() + " nu a fost gasita!"));

        // when
        ResultActions resultActions = mockMvc
                .perform(delete("/gyms/" + idForSalaToBeDeleted));

        // then
        resultActions.andExpect(status().isOk());
        boolean existsSalaThatHasBeenDeleted = salaFitnessRepository.existsById(idForSalaToBeDeleted);
        assertThat(existsSalaThatHasBeenDeleted).isFalse();
    }

    @Test
    @Transactional
    void canGetAllSali() throws Exception {
        // given
        SalaFitness salaFitness1 = new SalaFitness();
        salaFitness1.setId(1L);
        salaFitness1.setNume(faker.name().fullName());
        salaFitness1.setAdresa(faker.address().fullAddress());
        salaFitness1.setWebAdresa(faker.internet().emailAddress());

        SalaFitness salaFitness2 = new SalaFitness();
        salaFitness2.setId(2L);
        salaFitness2.setNume(faker.name().fullName());
        salaFitness2.setAdresa(faker.address().fullAddress());
        salaFitness2.setWebAdresa(faker.internet().emailAddress());


        mockMvc.perform(post("/gyms/add_sali_fitness")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salaFitness1)))
                .andExpect(status().isOk());
        mockMvc.perform(post("/gyms/add_sali_fitness")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(salaFitness2)))
                .andExpect(status().isOk());

        MvcResult getSaliFitnessResult = mockMvc.perform(get("/gyms")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andReturn();

        List<SalaFitness> saliFinessSearched =(List<SalaFitness>) getSaliFitnessResult
                .getModelAndView().getModelMap().get("sali");

        assertThat(saliFinessSearched).hasSizeGreaterThan(1);
        assertThat(saliFinessSearched)
                .filteredOn(salaFitness -> salaFitness.getId().equals(salaFitness1.getId()))
                .hasSize(1);
    }

    @Test
    @Transactional
    void canGetSalaById() throws Exception {
            // given
            SalaFitness salaFitnessExample = salaFitnessRepository
                    .findAll().get(0);

            MvcResult getSaliFitnessResult = mockMvc.perform(get("/gyms/" + salaFitnessExample.getId())
                            .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andReturn();


            SalaFitness saliFinessSearched =(SalaFitness) getSaliFitnessResult
                    .getModelAndView().getModelMap().get("sala");

            assertThat(saliFinessSearched).isEqualTo(salaFitnessExample);
    }
}
