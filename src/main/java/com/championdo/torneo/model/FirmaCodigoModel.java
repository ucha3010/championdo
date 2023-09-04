package com.championdo.torneo.model;

import java.util.Date;

public class FirmaCodigoModel {

    public FirmaCodigoModel () {

    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdOperacion() {
        return idOperacion;
    }

    public void setIdOperacion(int idOperacion) {
        this.idOperacion = idOperacion;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaCaducidad() {
        return fechaCaducidad;
    }

    public void setFechaCaducidad(Date fechaCaducidad) {
        this.fechaCaducidad = fechaCaducidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getPaginaFirmaOk() {
        return paginaFirmaOk;
    }

    public void setPaginaFirmaOk(String paginaFirmaOk) {
        this.paginaFirmaOk = paginaFirmaOk;
    }

    public String getOperativaOriginal() {
        return operativaOriginal;
    }

    public void setOperativaOriginal(String operativaOriginal) {
        this.operativaOriginal = operativaOriginal;
    }

    public int getCodigoGimnasio() {
        return codigoGimnasio;
    }

    public void setCodigoGimnasio(int codigoGimnasio) {
        this.codigoGimnasio = codigoGimnasio;
    }

    @Override
    public String toString() {
        return "FirmaCodigoModel{" +
                "id=" + id +
                ", idOperacion=" + idOperacion +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaCaducidad=" + fechaCaducidad +
                ", codigo='" + codigo + '\'' +
                ", dni='" + dni + '\'' +
                ", paginaFirmaOk='" + paginaFirmaOk + '\'' +
                ", operativaOriginal='" + operativaOriginal + '\'' +
                ", codigoGimnasio=" + codigoGimnasio +
                '}';
    }
}
