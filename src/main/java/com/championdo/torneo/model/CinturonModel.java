package com.championdo.torneo.model;

public class CinturonModel {

    private int id;
    private String color;
    private String categoria;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "Cinturon{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
