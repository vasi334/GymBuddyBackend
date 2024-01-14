package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.SalaFitness;

import java.util.List;

public interface SaliService {

    List<SalaFitness> getAllSali();

    SalaFitness getSalaById(Long id);

    SalaFitness createSala(SalaFitness sala);

    SalaFitness updateSala(Long id, SalaFitness updatedSala);

    void deleteSala(Long id);

    List<SalaFitness> getSaliByNume(String nume);
}