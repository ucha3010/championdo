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
    @GeneratedValue
    @Column(name = "id")
    private int id;
    private int edadInicio;
    private int edadFin;
    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;
    private int positionCinturonInicio;
    private int positionCinturonFin;
    private int idPoomsae;
    @Column(name = "inclusivo", nullable = false)
    private boolean inclusivo;
    @Column(name = "infantil", nullable = false)
    private boolean infantil;
    private int position;
    private int codigoGimnasio;
}
