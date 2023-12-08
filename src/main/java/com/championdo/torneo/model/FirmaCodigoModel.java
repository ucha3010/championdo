package com.championdo.torneo.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class FirmaCodigoModel {

    public FirmaCodigoModel (int idOperacion, String codigo, String dni, String paginaFirmaOk, String operativaOriginal) {
        this.idOperacion = idOperacion;
        this.codigo = codigo;
        this.dni = dni;
        this.paginaFirmaOk = paginaFirmaOk;
        this.operativaOriginal = operativaOriginal;
    }

    private int id;
    private int idOperacion;
    private Date fechaCreacion;
    private Date fechaCaducidad;
    private String codigo;
    private String dni;
    private String paginaFirmaOk;
    private String operativaOriginal;
    private int codigoGimnasio;
}
