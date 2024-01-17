package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.entity.SalaFitness;
import com.gymbuddy.backend_project.repository.SaliRepository;
import com.gymbuddy.backend_project.service.SaliService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class SaliServiceImpl implements SaliService {

    private final SaliRepository saliRepository;

    @Autowired
    public SaliServiceImpl(SaliRepository saliRepository) {
        this.saliRepository = saliRepository;
    }

    @Override
    public List<SalaFitness> getAllSali() {
        return saliRepository.findAll();
    }

    @Override
    public SalaFitness getSalaById(Long id) {
        return saliRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public SalaFitness createSala(SalaFitness sala) {
        return saliRepository.save(sala);
    }

    @Override
    public SalaFitness updateSala(Long id, SalaFitness updatedSala) {
        SalaFitness existingSala = saliRepository.findById(id).orElse(null);

        if (existingSala != null) {
            existingSala.setNume(updatedSala.getNume());
            existingSala.setAdresa(updatedSala.getAdresa());
            existingSala.setWebAdresa(updatedSala.getWebAdresa());

            return saliRepository.save(existingSala);
        }

        return null;
    }

    @Override
    public void deleteSala(Long id) {
        saliRepository.deleteById(id);
    }

    @Override
    public List<SalaFitness> getSaliByNume(String nume) {
        return saliRepository.findByNume(nume);
    }
}
