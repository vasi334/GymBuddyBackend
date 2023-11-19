package com.gymbuddy.backend_project.repository;

import com.gymbuddy.backend_project.entity.Nutritionisti;
import com.gymbuddy.backend_project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NutritionistRepository extends JpaRepository<Nutritionisti, Long> {
    Nutritionisti findByEmail(String email);
}
