package com.claex.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_aula")
public class AulaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_aula;

    @ManyToOne
    @JoinColumn(name = "id_grade")
    private GradeEntity grade;

    @Column(nullable = false)
    private int dia;

    @Column(nullable = false)
    private int periodo;

    @ManyToOne
    @JoinColumn(name = "id_materia")
    private MateriaEntity id_materia;

    @ManyToOne
    @JoinColumn(name = "id_professor")
    private ProfessorEntity id_professor;
}
