package com.gymbuddy.backend_project.dto;


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
public class AntrenorFitnessDTO {

    private Long id;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String firstName;

    @NotEmpty(message = "Contact Information should not be empty!")
    private String contactInformation;


    private String gymInformation;

    private String bestReview;
}
