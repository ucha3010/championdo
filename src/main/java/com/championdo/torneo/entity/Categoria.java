package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "edadInicio")
    private int edadInicio;

    @Column(name = "edadFin")
    private int edadFin;

    @Column(name = "nombre", nullable = false, length = 45)
    private String nombre;

    @Column(name = "idCinturonInicio")
    private int idCinturonInicio;

    @Column(name = "idCinturonFin")
    private int idCinturonFin;

    @Column(name = "idPoomsae")
    private int idPoomsae;

    @Column(name = "inclusivo", nullable = false)
    private boolean inclusivo;

    @Column(name = "infantil", nullable = false)
    private boolean infantil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEdadInicio() {
        return edadInicio;
    }

    public void setEdadInicio(int edadInicio) {
        this.edadInicio = edadInicio;
    }

    public int getEdadFin() {
        return edadFin;
    }

    public void setEdadFin(int edadFin) {
        this.edadFin = edadFin;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdCinturonInicio() {
        return idCinturonInicio;
    }

    public void setIdCinturonInicio(int idCinturonInicio) {
        this.idCinturonInicio = idCinturonInicio;
    }

    public int getIdCinturonFin() {
        return idCinturonFin;
    }

    public void setIdCinturonFin(int idCinturonFin) {
        this.idCinturonFin = idCinturonFin;
    }

    public int getIdPoomsae() {
        return idPoomsae;
    }

    public void setIdPoomsae(int idPoomsae) {
        this.idPoomsae = idPoomsae;
    }

    public boolean isInclusivo() {
        return inclusivo;
    }

    public void setInclusivo(boolean inclusivo) {
        this.inclusivo = inclusivo;
    }

    public boolean isInfantil() {
        return infantil;
    }

    public void setInfantil(boolean infantil) {
        this.infantil = infantil;
    }

    @Override
    public String toString() {
        return "Categoria{" +
                "id=" + id +
                ", anioInicio=" + edadInicio +
                ", anioFin=" + edadFin +
                ", nombre='" + nombre + '\'' +
                ", idCinturonInicio=" + idCinturonInicio +
                ", idCinturonFin=" + idCinturonFin +
                ", idPoomsae=" + idPoomsae +
                ", inclusivo=" + inclusivo +
                ", infantil=" + infantil +
                '}';
    }
}
