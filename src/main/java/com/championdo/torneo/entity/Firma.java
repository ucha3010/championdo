package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "firma")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Firma {

    @Id
    @GeneratedValue
    private int id;
    private int idOperacion;
    private int numeroIntentos;
    private boolean firmado;
    @Column(name = "operativaOriginal", length = 50)
    private String operativaOriginal;
    private int codigoGimnasio;

}
