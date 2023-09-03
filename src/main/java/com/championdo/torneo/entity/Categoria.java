package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Categoria {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "edadInicio")
    private int edadInicio;

    @Column(name = "edadFin")
    private int edadFin;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "idCinturonInicio")
    private int idCinturonInicio;

    @Column(name = "idCinturonFin")
    private int idCinturonFin;

    @Column(name = "idPoomsae")
    private int idPoomsae;

    @Column(name = "inclusivo", nullable = false)
    private boolean inclusivo;

    @Column(name = "infantil", nullable = false)
    private boolean infantil;

    @Column(name = "position")
    private int position;

    private int codigoGimnasio;
}
