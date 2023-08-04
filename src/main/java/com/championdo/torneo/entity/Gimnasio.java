package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "gimnasio")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Gimnasio {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "direccion", length = 200)
    private String direccion;

    @Column(name = "position")
    private int position;

}
