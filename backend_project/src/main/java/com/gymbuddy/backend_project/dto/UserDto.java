package com.gymbuddy.backend_project.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;

    private String password;
    private String goal;
    private String height;
    private String weight;
    private String city;

    private String sex;
    private String dateOfBirth;

    public UserDto(String name, String email, String goal, String height, String weight, String city, String sex, String dateOfBirth) {
        this.name = name;
        this.email = email;
        this.goal = goal;
        this.height = height;
        this.weight = weight;
        this.city = city;
        this.sex = sex;
        this.dateOfBirth = dateOfBirth;
    }
}
