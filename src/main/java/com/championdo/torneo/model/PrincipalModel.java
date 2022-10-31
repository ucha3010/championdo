package com.championdo.torneo.model;

import java.util.Date;
import java.util.List;

public class PrincipalModel {

    private String username;
    private int id;
    private Date fecha;
    private List<PrincipalUserModel> autorizaciones;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public List<PrincipalUserModel> getAutorizaciones() {
        return autorizaciones;
    }

    public void setAutorizaciones(List<PrincipalUserModel> autorizaciones) {
        this.autorizaciones = autorizaciones;
    }

    @Override
    public String toString() {
        return "PrincipalModel{" +
                "username='" + username + '\'' +
                ", id=" + id +
                ", fecha=" + fecha +
                ", autorizaciones=" + autorizaciones +
                '}';
    }
}
