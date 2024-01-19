package com.gymbuddy.backend_project.service;

import com.gymbuddy.backend_project.dto.AntrenorFitnessDTO;
import com.gymbuddy.backend_project.entity.AntrenorFitness;
import com.gymbuddy.backend_project.repository.AntrenoriFitnessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class AntrenoriFitnessServiceImpl implements AntrenoriFitnessService {

    private final AntrenoriFitnessRepository antrenoriFitnessRepository;

    /**
     * Folosim Pattern-ul depdendecy injection pentru a atine low coupling, high cohesion
     * Tag-ul @Autowired face rapida gasirea @Bean-uui de tip Repository, AntrenoriFitnessRepository
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
    @Transactional
    public AntrenorFitness save(AntrenorFitness antrenorFitness) {
        Optional<AntrenorFitness> existentAntrenor =
                antrenoriFitnessRepository.findByEmail(antrenorFitness.getContactInformation());
        /*exista deja un antrenor cu astfel de informatii de contact*/
        if(existentAntrenor.isPresent())
            throw new IllegalStateException("Exista deja un antrenor cu aceste informatii de contact!");
        /*
        Avem nevoie de un obiect de tipul AntrenorFitness, ce il instantiem doar cu detaliile, informatiile necesare,
        specificate in instanta AntrenorFitnessDTO
         */
        return antrenoriFitnessRepository.save(antrenorFitness);
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
     * Verificam daca exista antrenor ul de ID dorit ce terbuie sa
     * il stergem SI il stergem; In caz contrar, aruncam o exceptie
     * @param id - Id ul antrenorului de sters
     */
    @Override
    public void deleteAntrenor(Long id){
          boolean exists = antrenoriFitnessRepository.existsById(id);
          if(!exists){
              throw new IllegalStateException("Antrenorul cu id " + id +  " nu exista!");
          }
          antrenoriFitnessRepository.deleteById(id);
    }

    /**
     * @param antrenorFitness-AntrenorFitness
     * @return Obiectul DTO asociat(doar informatiile necesare ale unui antrenor
     * de fitness de care avem nevoie), folosind prototipul Builder!!!("inlantuire constructii")
     */
    private AntrenorFitnessDTO mapToDTO(AntrenorFitness antrenorFitness){
        AntrenorFitnessDTO antrenorFitnessDTO = AntrenorFitnessDTO.builder().
                id(antrenorFitness.getId()).
                lastName(antrenorFitness.getLastName()).
                firstName(antrenorFitness.getFirstName()).
                gymInformation(antrenorFitness.getGymInformation()).
                bestReview(antrenorFitness.getBestReview()).
                contactInformation(antrenorFitness.getContactInformation()).build();
        return antrenorFitnessDTO;
    }

}
