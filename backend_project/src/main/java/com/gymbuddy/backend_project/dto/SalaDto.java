package com.gymbuddy.backend_project.dto;

import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.entity.Sala;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SalaDto {
    private Long id;

    private String nume;

    private String adresa;

    private String web_adresa;

    public Sala getSala()
    {
        return new Sala(id, nume,adresa,web_adresa);
    }
}