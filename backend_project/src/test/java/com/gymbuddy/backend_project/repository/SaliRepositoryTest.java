package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.SalaFitness;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class SaliRepositoryTest {
    @Autowired
    private SaliRepository saliTestRepository;

    @AfterEach
    void clear(){
        saliTestRepository.deleteAll();
    }

    @Test
    void checkExistSaliWithDesiredNume(){
        //input
        String nume = "DesiredNume";
        String random = "random";
        SalaFitness salaFitness = SalaFitness.builder()
                .adresa(random)
                .webAdresa(random)
                .nume(nume)
                .build();

        saliTestRepository.save(salaFitness);

        //then
        List<SalaFitness> desiredSala = saliTestRepository.findByNume(nume);

        assertThat(desiredSala).hasSize(1);
        assertThat(saliTestRepository.findAll()).filteredOn(sala -> sala.getNume().equals(nume)).hasSize(1);

    }

    @Test
    void checkDoesNotExistSaliWithDesiredNume(){
        //input
        String nume = "DesiredNume";

        //then
        List<SalaFitness> desiredSali = saliTestRepository.findByNume(nume);

        //expected
        assertThat(desiredSali).isEmpty();
        assertThat(saliTestRepository.findAll()).filteredOn(sala -> sala.getNume().equals(nume)).isEmpty();
    }

    @Test
    void checkExistMultipleSaliWithDesiredNume(){
        //input
        String nume = "DesiredNume";
        String notRightNume = "SmthElse";
        String random1 = "RandomForFirstGym";
        String random2 = "RandomForSecondGym";
        String random = "Random";
        SalaFitness salaFitness1 = SalaFitness.builder()
                .adresa(random1)
                .webAdresa(random1)
                .nume(nume)
                .build();

        SalaFitness salaFitness2 = SalaFitness.builder()
                .adresa(random2)
                .webAdresa(random2)
                .nume(nume)
                .build();

        SalaFitness salaFitness3 = SalaFitness.builder()
                .adresa(random)
                .webAdresa(random)
                .nume(notRightNume)
                .build();

        saliTestRepository.saveAll(List.of(salaFitness1, salaFitness2));

        //then
        List<SalaFitness> desiredSali = saliTestRepository.findByNume(nume);

        //expected
        assertThat(desiredSali).hasSizeGreaterThan(1);
        assertThat(saliTestRepository.findAll())
                .filteredOn(sala -> sala.getNume().equals(nume)).hasSizeGreaterThan(1);
    }
}