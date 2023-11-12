package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "torneo_gimnasio")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TorneoGimnasio {

    @Id
    @GeneratedValue
    private int id;
    private int idTorneo;
    @Column(name = "nombreGimnasio", length = 100)
    private String nombreGimnasio;
    private int position;
    private int codigoGimnasio;

}
