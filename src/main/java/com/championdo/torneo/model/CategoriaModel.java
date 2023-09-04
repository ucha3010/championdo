package com.championdo.torneo.model;

public class CategoriaModel {

    private int id;
    private int edadInicio;
    private int edadFin;
    private String nombre;
    private CinturonModel cinturonInicio;
    private CinturonModel cinturonFin;
    private PoomsaeModel poomsae;
    private boolean inclusivo;
    private boolean infantil;
    private int position;
    private int codigoGimnasio;

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

    public CinturonModel getCinturonInicio() {
        return cinturonInicio;
    }

    public void setCinturonInicio(CinturonModel cinturonInicio) {
        this.cinturonInicio = cinturonInicio;
    }

    public CinturonModel getCinturonFin() {
        return cinturonFin;
    }

    public void setCinturonFin(CinturonModel cinturonFin) {
        this.cinturonFin = cinturonFin;
    }

    public PoomsaeModel getPoomsae() {
        return poomsae;
    }

    public void setPoomsae(PoomsaeModel poomsae) {
        this.poomsae = poomsae;
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
        return "CategoriaModel{" +
                "id=" + id +
                ", edadInicio=" + edadInicio +
                ", edadFin=" + edadFin +
                ", nombre='" + nombre + '\'' +
                ", cinturonInicio=" + cinturonInicio +
                ", cinturonFin=" + cinturonFin +
                ", poomsae=" + poomsae +
                ", inclusivo=" + inclusivo +
                ", infantil=" + infantil +
                ", position=" + position +
                ", codigoGimnasio=" + codigoGimnasio +
                '}';
    }
}
