package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.SalaDto;
import com.gymbuddy.backend_project.entity.Sala;

import java.util.List;

public interface SalaService {
    void saveSala(SalaDto sala );

    Sala findSalaByAdresa(String adresa);

    List<Sala> findAllSali();
}
