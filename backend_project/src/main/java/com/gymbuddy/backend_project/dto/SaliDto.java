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
    private String Name;

    @NotEmpty
    private String adress;

    @NotEmpty(message = "Web adress should not be empty!")
    private String webAdress;

    public SalaFitness getSala()
    {
        return new SalaFitness(id,Name,adress,webAdress);
    }
}
