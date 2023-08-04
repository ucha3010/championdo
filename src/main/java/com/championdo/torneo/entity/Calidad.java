package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Calidad {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "otro", length = 200)
    private String otro;

    @Column(name = "position")
    private int position;

}
