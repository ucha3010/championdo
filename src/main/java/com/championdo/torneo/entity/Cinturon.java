package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "cinturon")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Cinturon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "color", nullable = false, length = 40)
    private String color;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "position")
    private int position;

    private int codigoGimnasio;

}
