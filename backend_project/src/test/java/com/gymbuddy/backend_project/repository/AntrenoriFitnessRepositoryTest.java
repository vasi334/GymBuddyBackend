package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.AntrenorFitness;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Tag ul @DataJpaTest este necesar pentru a putea folosi @Bean ul AntrenoiFitnessRepository!
 * Altfel, tag ul @AutoWired nu merge!!! Nu gaseste acest bean
 * Pentru toate testele pentru Repository uri(layer ul ce comunica cu baza de date)
 * FOLOSIM acest tag!
 *
 * Metodele deja PREDEFINITE de Spring Data Jpa(deleteById(), save(), etc..)
 * NU VOR FI TESTATE! Ele au fost DEJA testate!
 */
@DataJpaTest
class AntrenoriFitnessRepositoryTest {
    @Autowired
    private AntrenoriFitnessRepository antrenoriFitnessTestRepository;


    @Test
    void checkExistsAntrenorWithDesiredEmail(){
        //given
        String contactInformation = "raul@gmail.com";
        AntrenorFitness antrenorFitness = AntrenorFitness.builder()
                .contactInformation(contactInformation)
                .lastName("Raul")
                .firstName("Dolha")
                .build();

        antrenoriFitnessTestRepository.save(antrenorFitness);

        //then
        Optional<AntrenorFitness> searchedAntrenor = antrenoriFitnessTestRepository.findByEmail(contactInformation);

        //expected(The coach exist!)
        assertThat(List.of(searchedAntrenor)).isNotEmpty();

    }

    /**
     * This tag marks that AFTER EACH UNIT TEST(that will be executed)
     * all the instances(rows) of testRepo will be deleted
     * so that tests will be much clearer; Used for setUp
     */
    @AfterEach
    void clear(){
        antrenoriFitnessTestRepository.deleteAll();
    }

    @Test
    void checkDoesNotExistAntrenorWithDesiredEmail(){
        //given
        String contactInformation = "raul@gmail.com";


        //then
        Optional<AntrenorFitness> searchedAntrenor = antrenoriFitnessTestRepository.findByEmail(contactInformation);

        //expected(The coach DOES exist!)
        assertThat(searchedAntrenor).isEmpty();

    }



}