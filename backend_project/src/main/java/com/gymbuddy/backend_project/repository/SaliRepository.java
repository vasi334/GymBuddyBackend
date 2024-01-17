package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.SalaFitness;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SaliRepository extends JpaRepository<SalaFitness, Long> {
    //Gasirea salilor de fitness dupa numele acestora
    List<SalaFitness> findByNume(String nume);
}