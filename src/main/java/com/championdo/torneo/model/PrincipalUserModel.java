package com.championdo.torneo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PrincipalUserModel {

    private int id;
    private Date fechaInscripcion;
    private String nombreTorneo;
    private String fechaTorneo;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private boolean inscripcionPropia;
}
