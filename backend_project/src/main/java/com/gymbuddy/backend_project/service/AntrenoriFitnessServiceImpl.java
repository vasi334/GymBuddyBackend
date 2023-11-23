package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AntrenoriFitnessServiceImpl implements AntrenoriFitnessService {

    private final AntrenoriFitnessRepository antrenoriFitnessRepository;

    /**
     * Folosim Pattern-ul depdendecy injection pentru a atine low coupling, high cohesion
     * Tag-ul @Autowired face rapida gasirea @Bean-uui de tip Repository, AntrenoriFitnessRepoository
     * @param antrenoriFitnessRepository-AntrenoriFitnessRepository
     */
    @Autowired
    public AntrenoriFitnessServiceImpl(AntrenoriFitnessRepository antrenoriFitnessRepository){
        this.antrenoriFitnessRepository = antrenoriFitnessRepository;
    }

    /**
     * @return Lista de antrenori disponibili
     */
    @Override
    public List<AntrenorFitnessDTO> findAllAntrenoriFitness() {
        return antrenoriFitnessRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    /**
     * Salvam informatiile unui antrenor de fitness(daca nu se repetea unele informatii deja),
     * SI DOAR CELE NECESARE!(excludem programul antrenorilor)
     * @param antrenorFitness-AntrenoriFitnessDTO
     */
    @Override
    public void save(AntrenorFitnessDTO antrenorFitness) {
        Optional<AntrenorFitness> existentAntrenor =
                antrenoriFitnessRepository.findByEmail(antrenorFitness.getContactInformation());
        /*exista deja un antrenor cu astfel de informatii de contact*/
        if(existentAntrenor.isPresent())
            throw new IllegalStateException("Exissta deja un antrenor cu aceste informatii de contact!");
        /*
        Avem nevoie de un obiect de tipul AntrenorFitness, ce il instantiem doar cu detaliile, informatiile necesare,
        specificate in instanta AntrenorFitnessDTO
         */
        AntrenorFitness antrenorToBeSaved = AntrenorFitness.builder().
                id(antrenorFitness.getId()).
                lastname(antrenorFitness.getLastName()).
                firstName(antrenorFitness.getFirstName()).
                contactInformation(antrenorFitness.getContactInformation())
                .build();
        antrenoriFitnessRepository.save(antrenorToBeSaved);
    }

    /**
     * @param contact-String
     * @return Gasim antrenorul de fitness ce are informatiile de contact dorite
     */
    @Override
    public Optional<AntrenorFitness> findAntrenorByContactInformation(String contact) {
        return antrenoriFitnessRepository.findByEmail(contact);
    }

    /**
     * @param antrenorFitness-AntrenorFitness
     * @return Obiectul DTO asociat(doar informatiile necesare ale unui antrenor
     * de fitness de care avem nevoie), folosinf prototipul Builder!!!("inlantuire constructii")
     */
    private AntrenorFitnessDTO mapToDTO(AntrenorFitness antrenorFitness){
        AntrenorFitnessDTO antrenorFitnessDTO = AntrenorFitnessDTO.builder().
                id(antrenorFitness.getId()).
                lastName(antrenorFitness.getLastname()).
                firstName(antrenorFitness.getFirstName()).
                contactInformation(antrenorFitness.getContactInformation()).build();
        return antrenorFitnessDTO;
    }

}
