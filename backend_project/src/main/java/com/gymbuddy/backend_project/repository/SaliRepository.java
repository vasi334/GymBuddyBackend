package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.SalaFitness;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SaliRepository extends JpaRepository<SalaFitness, Long> {
    SalaFitness findByWebAdress(String webAdress);
}
