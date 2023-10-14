package com.championdo.torneo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TorneoGimnasioModel {

    private int id;
    private int idTorneo;
    private String nombreGimnasio;
    private int position;
}
