package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Nutritionist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// interfata  NutritionistRepository care e responsabila
// pentru gestionarea datelor entitatii Nutritionist in baza de date
@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionist, Long> {
    Nutritionist findByEmail(String email);
}
