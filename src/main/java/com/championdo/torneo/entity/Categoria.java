package com.championdo.torneo.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Categoria {

    @Id
    @SequenceGenerator(name = "categoriaGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoriaGenerator")
    private int id;
    private int edadInicio;
    private int edadFin;
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    private int positionCinturonInicio;
    private int positionCinturonFin;
    private int idCinturonInicio;
    private int idCinturonFin;
    private int idPoomsae;
    private boolean inclusivo;
    private boolean preinfantil;
    private boolean infantil;
    private boolean adulto;
    private int position;
    private int codigoGimnasio;
}
