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
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @Column(name = "position")
    private int position;

}
