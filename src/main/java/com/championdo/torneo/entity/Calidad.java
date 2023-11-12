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
    @SequenceGenerator(name = "calidadGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calidadGenerator")
    private int id;
    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;
    @Column(name = "otro", length = 200)
    private String otro;
    private int position;

}
