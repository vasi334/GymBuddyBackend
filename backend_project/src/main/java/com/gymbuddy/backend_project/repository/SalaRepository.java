package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalaRepository  extends JpaRepository<Sala, Long> {
    Sala findByAdresa(String adresa);
}