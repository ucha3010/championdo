package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "pais")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Pais {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "position")
    private int position;

}
