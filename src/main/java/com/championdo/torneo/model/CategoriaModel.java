package com.championdo.torneo.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CategoriaModel {

    private int id;
    private int edadInicio;
    private int edadFin;
    private String nombre;
    private CinturonModel cinturonInicio;
    private CinturonModel cinturonFin;
    private PoomsaeModel poomsae;
    private boolean inclusivo;
    private boolean infantil;
    private int position;
    private int codigoGimnasio;
}
