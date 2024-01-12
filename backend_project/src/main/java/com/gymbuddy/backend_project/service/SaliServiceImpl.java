package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.SaliDto;
import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.repository.SaliRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaliServiceImpl implements SaliService{
    private SaliRepository saliRepository;

    public SaliServiceImpl(SaliRepository saliRepository){
        this.saliRepository = saliRepository;
    }


    @Override
    public void saveSali(SaliDto salaFitness) {
        saliRepository.save(salaFitness.getSala());
    }

    @Override
    public SalaFitness findSalaFitnessByAdresa(String adresa) {
        return saliRepository.findByAdresa(adresa);
    }

    @Override
    public List<SalaFitness> findAllSali() {
        List<SalaFitness> salaFitnesses=saliRepository.findAll();
        List<SalaFitness> saliDto=new ArrayList<>();
        for (SalaFitness i :salaFitnesses)
            saliDto.add(i);
        return saliDto;
    }
}
