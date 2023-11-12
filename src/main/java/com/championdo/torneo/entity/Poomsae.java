package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "poomsae")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Poomsae {

    @Id
    @SequenceGenerator(name = "poomsaeGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "poomsaeGenerator")
    private int id;
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;
    private int position;
    private int codigoGimnasio;

    public Poomsae (String nombre, int position, int codigoGimnasio) {
        this.nombre = nombre;
        this.position = position;
        this.codigoGimnasio = codigoGimnasio;
    }

}
