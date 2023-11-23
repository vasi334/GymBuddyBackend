package com.gymbuddy.backend_project.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserDb {

    private Long id;
    @NotEmpty

    private String firstName;
    @NotEmpty

    private String lastName;

    private int day;
    @NotEmpty

    private int month;
    @NotEmpty

    private int year;
    @NotEmpty

    private int sex;
    @NotEmpty

    private int high;
    @NotEmpty

    private int weight;
    @NotEmpty

    @NotEmpty(message = "Email should not be empty")
    @Email
    private String email;

    @NotEmpty(message = "Password should not be empty")
    private String password;
}
