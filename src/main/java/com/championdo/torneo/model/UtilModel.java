package com.championdo.torneo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UtilModel {

    private String clave;
    private String valor;
    private int codigoGimnasio;

    public UtilModel(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }
}
