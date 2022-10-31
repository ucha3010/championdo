package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "calidad")
public class Calidad {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "nombre", nullable = false, length = 20)
    private String nombre;

    @Column(name = "otro", nullable = true, length = 200)
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
