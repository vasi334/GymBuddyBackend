package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.SaliDto;
import com.gymbuddy.backend_project.entity.SalaFitness;

import java.util.List;

public interface SaliService {
    void saveSali(SaliDto salaFitness);

    SalaFitness findSalaFitnessByWebAdress(String webAdress);

    List<SalaFitness> findAllSali();
}
