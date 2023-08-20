package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

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

}
