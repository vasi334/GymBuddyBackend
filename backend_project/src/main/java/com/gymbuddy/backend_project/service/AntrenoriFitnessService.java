package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.entity.AntrenorFitness;

import java.util.List;
import java.util.Optional;

public interface AntrenoriFitnessService {

    /**
     * Antrenorul ce il dorim sa il salvam
     * @param antrenoriFitness-AntrenorFitness
     */
    AntrenorFitness save(AntrenorFitness antrenoriFitness);

    /**
     *
     * @param contact-Informatiile de contact ale Antrenorului ce dorim sa il gasim
     * @return Antrenorul de fitness ce are informatiile de contact dorite
     */
    Optional<AntrenorFitness> findAntrenorByContactInformation(String contact);

    /**
     * @return Gaseste toti antrenorii de fitness
     */
    List<AntrenorFitnessDTO> findAllAntrenoriFitness();

    /**
     * Metoda sterge un atrenor de un anumit ID
     * @param id - Id ul antrenorului de sters
     */
    void deleteAntrenor(Long id);
}
