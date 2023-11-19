package com.gymbuddy.backend_project.dto;

import com.gymbuddy.backend_project.entity.Nutritionisti;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NutritionistDto {
    private Long id;

    private String nume;

    private String specializare;

    private String nr_telefon;

    private String email;

    private String site;
    public Nutritionisti getNutritionist()
    {
        return new Nutritionisti(id, nume,specializare,nr_telefon,email,site);
    }
}
