package com.claex.crud.Entity;

import java.time.LocalTime;

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
@Table(name = "tbl_salas")
public class SalaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_sala;

    @Column(nullable = false)
    private String nome_sala;

    @Column(nullable = false)
    private int duracao_aula;

    @Column(nullable = false)
    private int duracao_intervalo;

    @Column(nullable = false)
    private int quant_aula;

    @Column(nullable = false)
    private LocalTime inicio_aula;

    @ManyToOne
    @JoinColumn(name = "id_escola")
    private CadastroEntity escola;
}
