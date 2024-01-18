package com.gymbuddy.backend_project.entity;

import com.gymbuddy.backend_project.dto.NutritionistDto;
import jakarta.persistence.*;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="nutritionisti")
public class Nutritionist {
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
    public NutritionistDto modificareNutritionist()
    {
        return new NutritionistDto(id, nume,specializare,nr_telefon,email,site);
    }


}
