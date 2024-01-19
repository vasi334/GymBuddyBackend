package com.gymbuddy.backend_project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "antrenori",
        uniqueConstraints = {
                @UniqueConstraint(name = "antrenor_unique_constraint", columnNames = "contact_information")
        })
public class AntrenorFitness {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//tipul default e GenerationType.AUTO
    @Column(nullable = false,
            name = "id",
            updatable = false)
    private Long id;

    @Column(name = "firstname",
            nullable = false,
            columnDefinition = "TEXT")
    private String firstName;

    @Column(name = "lastname",
            nullable = false,
            columnDefinition = "TEXT")
    private String lastName;

    @Column(name = "contact_information",
            nullable = false,
            columnDefinition = "TEXT")
    private String contactInformation;

    /**
     * It s possible for trainers TO not be affiliated with
     * any gyms whatsoever; gymInformation respresent the NAME of teh affiliated gym
     */
    @Column(name = "gym_information",
    columnDefinition = "TEXT")
    private String gymInformation;

    /**
     * It s possible for trainers TO not have any reviews YET,
     * they might have recently began their activity
     */
    @Column(name = "best_review",
            columnDefinition = "TEXT")
    private String bestReview;


    /**
     * In momentul in care lucram cu tipuri de date ce nu sunt simple/primitive(
     * (gen colectii), foloseste @ElementCollection! De asemenea, aceste colectii de elemente
     * vor fi salvat intr-o tabela separate(cu un nume dorit), si LEGAM aceste inregistrati
     * de instanta curenta a clasei antrenor prin @ColectionTable(prin proprietatile
     * name = "specificam numele tabelei", joinColumns = "coloana de legatura care leaga aceste inregistrari
     * care se leaga aceste de instanta curenta a clasei Antrenor, NU E PRIMARY key-ul antrenorului!!!
     * (e reference key = cheie secundara in tabela ce stocheaza inregistrarile, program ul antrenorului)
     * Pentru inceput, consideram ca introducem doar timpul cand antrenorii incep sa fie
     * disponibili, NU si durata de disponibilitate!!!
     * Folosim @JsonFormat pentru a formata obiecte Java 8 de tipul DateTime!!!
     *
     */
    @ElementCollection
    @CollectionTable(
            name = "programe_antrenori",
            joinColumns = @JoinColumn(name = "antrenor_id")
    )
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    /*
    Acesta specifica tipul de date ce va fi asociat in baza de date pentru fiecare obiect al listei,
    care este TimeStamp(inglobeaza si data si ora)
     */
    @Temporal(TemporalType.TIMESTAMP)
    /*
    Aici, prin aceasta adnotare specificam NUMELE COLOANEI IN TABELA ASOCIATA unde vom
    stoca aceste valori ale field-ului program! Fiecare element al listei va fi salvat intr-o
    inregistrare a tabelei separate, legate de instanta antrenor dorita printr-un foreign key
     */
    @Column(name = "program")
    private List<Date> program;
}
