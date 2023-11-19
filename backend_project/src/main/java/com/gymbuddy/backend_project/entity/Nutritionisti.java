package com.gymbuddy.backend_project.entity;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="nutritionisti")
public class Nutritionisti {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable=false)
    private String nume;
    @Column(nullable=false)
    private String specializare;
    @Column(nullable=false)
    private String nr_telefon;
    @Column(nullable=false, unique=true)
    private String email;
    @Column(nullable=false)
    private String site;
    NutritionistDto modificareNutritionist()
    {
        return new NutritionistDto(id, nume,specializare,nr_telefon,email,site);
    }


}
