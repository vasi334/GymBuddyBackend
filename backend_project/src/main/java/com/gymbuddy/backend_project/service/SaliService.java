package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.SalaFitness;

import java.util.List;

public interface SaliService {

    /**
     * @return Toate salile de fitness
     */
    List<SalaFitness> getAllSali();

    /**
     * @return Sala de fitness cu id-ul respectiv
     */
    SalaFitness getSalaById(Long id);

    SalaFitness createSala(SalaFitness sala);

    SalaFitness updateSala(Long id, SalaFitness updatedSala);

    /**
     * Metoda sterge o sala fitness de un anumit ID
     * @param id - Id ul salii fitness de sters
     */
    void deleteSala(Long id);

    /**
     * @return Salile de fitness cu numele respectiv
     */
    List<SalaFitness> getSaliByNume(String nume);
}