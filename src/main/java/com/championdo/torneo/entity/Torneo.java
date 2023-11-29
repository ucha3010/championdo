package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "torneo")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Torneo {

    @Id
    @GeneratedValue
    private int id;
    @Column(name = "nombre", length = 200)
    private String nombre;
    @Column(name = "direccion", length = 200)
    private String direccion;
    private Date fechaTorneo;
    private Date fechaComienzoInscripcion;
    private Date fechaFinInscripcion;
    private boolean adulto;
    private boolean menor;
    private boolean inclusivo;
    private int codigoGimnasio;

}
