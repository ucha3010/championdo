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
    @SequenceGenerator(name = "paisGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "paisGenerator")
    private int id;
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    private int position;

}
