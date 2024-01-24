package com.championdo.torneo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClaveUsuarioModel {

    private int id;
    private String username;
    private String antiguaClave;
    private String nuevaClave;
    private String nuevaClaveRepeticion;
}
