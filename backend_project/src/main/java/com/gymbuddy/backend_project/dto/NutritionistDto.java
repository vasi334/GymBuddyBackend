package com.gymbuddy.backend_project.dto;
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

}