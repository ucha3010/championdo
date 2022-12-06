package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "cinturon")
public class Cinturon {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "color", nullable = false, length = 40)
    private String color;

    @Column(name = "categoria", nullable = false, length = 50)
    private String categoria;

    @Column(name = "position")
    private int position;

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

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Cinturon{" +
                "id=" + id +
                ", color='" + color + '\'' +
                ", categoria='" + categoria + '\'' +
                ", position=" + position +
                '}';
    }
}
