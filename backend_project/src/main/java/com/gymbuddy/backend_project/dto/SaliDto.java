package com.gymbuddy.backend_project.dto;

import com.gymbuddy.backend_project.entity.Nutritionist;
import com.gymbuddy.backend_project.entity.SalaFitness;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SaliDto
{
    private Long id;

    @NotEmpty
    private String nume;

    @NotEmpty
    private String adresa;

    @NotEmpty(message = "Web adress should not be empty!")
    private String web_adresa;

    public SalaFitness getSala()
    {
        return new SalaFitness(id,nume,adresa,web_adresa);
    }
}
