package com.championdo.torneo.model;


public class FirmaModel {

    private int id;
    private int idOperacion;
    private int numeroIntentos;
    private boolean firmado;

    public FirmaModel(){

    }

    public FirmaModel(int idOperacion){
        this.idOperacion = idOperacion;
    }

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

    public int getNumeroIntentos() {
        return numeroIntentos;
    }

    public void setNumeroIntentos(int numeroIntentos) {
        this.numeroIntentos = numeroIntentos;
    }

    public boolean isFirmado() {
        return firmado;
    }

    public void setFirmado(boolean firmado) {
        this.firmado = firmado;
    }

    @Override
    public String toString() {
        return "FirmaModel{" +
                "id=" + id +
                ", idOperacion=" + idOperacion +
                ", numeroIntentos=" + numeroIntentos +
                ", firmado=" + firmado +
                '}';
    }
}
