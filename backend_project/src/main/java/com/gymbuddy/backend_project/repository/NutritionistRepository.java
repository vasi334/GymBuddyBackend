package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {
    Nutritionist findByEmail(String email);
}
