package com.championdo.torneo.model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FirmaModel {

    private int id;
    private int idOperacion;
    private int numeroIntentos;
    private boolean firmado;
    private String operativaOriginal;
    private int codigoGimnasio;

    public FirmaModel(int idOperacion){
        this.idOperacion = idOperacion;
    }

}
