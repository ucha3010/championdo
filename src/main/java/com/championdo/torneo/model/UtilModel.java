package com.championdo.torneo.model;

public class UtilModel {

    private String clave;
    private String valor;
    private int codigoGimnasio;

    public UtilModel() {
    }

    public UtilModel(String clave, String valor) {
        this.clave = clave;
        this.valor = valor;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public int getCodigoGimnasio() {
        return codigoGimnasio;
    }

    public void setCodigoGimnasio(int codigoGimnasio) {
        this.codigoGimnasio = codigoGimnasio;
    }

    @Override
    public String toString() {
        return "UtilModel{" +
                "clave='" + clave + '\'' +
                ", valor='" + valor + '\'' +
                ", codigoGimnasio=" + codigoGimnasio +
                '}';
    }
}
