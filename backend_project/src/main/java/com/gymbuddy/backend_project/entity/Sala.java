package com.gymbuddy.backend_project.entity;

import com.gymbuddy.backend_project.dto.SalaDto;
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
@Table(name="sali")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(nullable=false)
    private String nume;
    @Column(nullable=false)
    private String adresa;
    @Column(nullable=false)
    private String web_adresa;
    SalaDto modificareSala()
    {
        return new SalaDto(id, nume,adresa,web_adresa);
    }


}
