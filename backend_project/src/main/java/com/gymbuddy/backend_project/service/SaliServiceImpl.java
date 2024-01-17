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

    /**
     * Folosim Pattern-ul depdendecy injection pentru a tine low coupling, high cohesion
     * Tag-ul @Autowired face rapida gasirea @Bean-uui de tip Repository, SaliRepository
     * @param saliRepository-SaliRepository
     */
    @Autowired
    public SaliServiceImpl(SaliRepository saliRepository) {
        this.saliRepository = saliRepository;
    }

    /**
     * @return Lista de sali de fitness existente
     */
    @Override
    public List<SalaFitness> getAllSali() {
        return saliRepository.findAll();
    }

    /**
     * @return Sala fitness care are id-ul respectiv
     */
    @Override
    public SalaFitness getSalaById(Long id) {
        return saliRepository.findById(id).orElse(null);
    }

    /**
     * Salvam informatiile unei sali de fitness(daca nu se repetea unele informatii deja),
     * @param sala-SaliDTO
     */
    @Override
    @Transactional
    public SalaFitness createSala(SalaFitness sala) {
        return saliRepository.save(sala);
    }

    /**
     Se vor modifica informatii despre sala de fitness care are id-ul dat ca parametru
     * @param id - Id ul salii de fitness
     */
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

    /**
     Se va sterge sala de fitness care are id-ul dat ca parametru
     * @param id - Id ul salii de fitness care se doreste a fi stearsa
     */
    @Override
    public void deleteSala(Long id) {
        saliRepository.deleteById(id);
    }

    /**
     * @return Lista de sali de fitness care au un anumit nume
     */
    @Override
    public List<SalaFitness> getSaliByNume(String nume) {
        return saliRepository.findByNume(nume);
    }
}