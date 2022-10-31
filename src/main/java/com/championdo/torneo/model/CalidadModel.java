package com.championdo.torneo.model;

public class CalidadModel {

    private int id;
    private String nombre;
    private String otro;

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

    @Override
    public String toString() {
        return "Calidad{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", otro='" + otro + '\'' +
                '}';
    }
}
