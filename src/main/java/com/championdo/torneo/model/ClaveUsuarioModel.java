package com.championdo.torneo.model;

public class ClaveUsuarioModel {

    private String username;
    private String antiguaClave;
    private String nuevaClave;
    private String nuevaClaveRepeticion;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAntiguaClave() {
        return antiguaClave;
    }

    public void setAntiguaClave(String antiguaClave) {
        this.antiguaClave = antiguaClave;
    }

    public String getNuevaClave() {
        return nuevaClave;
    }

    public void setNuevaClave(String nuevaClave) {
        this.nuevaClave = nuevaClave;
    }

    public String getNuevaClaveRepeticion() {
        return nuevaClaveRepeticion;
    }

    public void setNuevaClaveRepeticion(String nuevaClaveRepeticion) {
        this.nuevaClaveRepeticion = nuevaClaveRepeticion;
    }

    @Override
    public String toString() {
        return "ClaveUsuarioModel{" +
                "username='" + username + '\'' +
                ", antiguaClave='" + antiguaClave + '\'' +
                ", nuevaClave='" + nuevaClave + '\'' +
                ", nuevaClaveRepeticion='" + nuevaClaveRepeticion + '\'' +
                '}';
    }
}
