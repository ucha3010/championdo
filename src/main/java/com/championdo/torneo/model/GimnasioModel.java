package com.championdo.torneo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GimnasioModel {

    private int id;
    private String nombre;
    private String direccion;
    private int position;
    private int codigoGimnasio;
}
