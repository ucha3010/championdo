package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "pais")
public class Pais {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

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

    @Override
    public String toString() {
        return "Pais{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
