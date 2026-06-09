package com.claex.crud.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "tbl_professor")
public class ProfessorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_professor;
    
    @Column(nullable = false)
    private String nome_professor;

    @Column(nullable = false)
    private String email_professor;

    @Column(nullable = false)
    private String senha_professor;
}
