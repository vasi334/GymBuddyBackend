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
/**
 * Aceasta clasa are rolul de a extrage DOAR informatiile necesare ce
 * dorim sa le transmitem/procesam mai departe in request-uri, etc
 */
public class SaliDto
{
    private Long id;

    @NotEmpty
    private String nume;

    @NotEmpty
    private String adresa;

    @NotEmpty(message = "Web adress should not be empty!")
    private String web_adresa;


}