package com.championdo.torneo.model;

public class PoomsaeModel {

    private int id;
    private String nombre;
    private int position;
    private int codigoGimnasio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getCodigoGimnasio() {
        return codigoGimnasio;
    }

    public void setCodigoGimnasio(int codigoGimnasio) {
        this.codigoGimnasio = codigoGimnasio;
    }

    @Override
    public String toString() {
        return "PoomsaeModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", position=" + position +
                ", codigoGimnasio=" + codigoGimnasio +
                '}';
    }
}
