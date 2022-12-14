package com.championdo.torneo.model;

public class CalidadModel {

    private int id;
    private String nombre;
    private String otro;
    private int position;

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

    public String getOtro() {
        return otro;
    }

    public void setOtro(String otro) {
        this.otro = otro;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "CalidadModel{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", otro='" + otro + '\'' +
                ", position=" + position +
                '}';
    }
}
