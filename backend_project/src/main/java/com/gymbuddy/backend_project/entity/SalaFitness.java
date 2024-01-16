package com.gymbuddy.backend_project.entity;

import com.gymbuddy.backend_project.dto.SaliDto;
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
public class SalaFitness {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false,
            name = "id",
            updatable = false)
    private Long id;

    @Column(nullable=false,
            name = "nume",
            columnDefinition = "TEXT"
    )
    private String nume;

    @Column(nullable=false,
            name = "adresa",
            columnDefinition = "TEXT")
    private String adresa;

    @Column(nullable=false,
            name = "webAdresa",
            columnDefinition = "TEXT")
    private String webAdresa;

    public SaliDto modificareSali() {
        return new SaliDto(id, nume, adresa, webAdresa);
    }
}