package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TorneoModel {

    private int id;
    private String nombre;
    private String direccion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaTorneo;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaComienzoInscripcion;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaFinInscripcion;
    private List<TorneoGimnasioModel> gimnasios;
    private int codigoGimnasio;
}
