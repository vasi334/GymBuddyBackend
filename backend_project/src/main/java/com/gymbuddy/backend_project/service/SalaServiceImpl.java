package com.gymbuddy.backend_project.service;
import com.gymbuddy.backend_project.dto.SalaDto;
import com.gymbuddy.backend_project.entity.Sala;
import com.gymbuddy.backend_project.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SalaServiceImpl implements SalaService {
    private SalaRepository salaRepository;

    public SalaServiceImpl(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }
    @Override
    public void saveSala(SalaDto sala) {
        salaRepository.save(sala.getSala());

    }

    @Override
    public Sala findSalaByAdresa(String adresa) {
        return salaRepository.findByAdresa(adresa);
    }

    @Override
    public List<Sala> findAllSali() {
        List<Sala> sali=salaRepository.findAll();
        List<Sala> saliDto=new ArrayList<>();
        for (Sala i :sali)
            saliDto.add(i);
        return saliDto;
    }
}