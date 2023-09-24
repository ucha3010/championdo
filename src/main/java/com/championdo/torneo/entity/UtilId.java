package com.championdo.torneo.entity;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UtilId implements Serializable {

    private String clave;
    private int codigoGimnasio;
}
