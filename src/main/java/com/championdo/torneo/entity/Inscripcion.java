package com.championdo.torneo.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripcion")
public class Inscripcion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "fechaInscripcion", nullable = false)
    private Date fechaInscripcion;

    @Column(name = "username", nullable = false, length = 45)
    private String username;

    @Column(name = "usernameInscripto", nullable = false, length = 45)
    private String usernameInscripto;

    @Column(name = "envioJustificanteEmail")
    private boolean envioJustificanteEmail;

    @Column(name = "idCategoria")
    private int idCategoria;

    @Column(name = "pagoRealizado", nullable = false)
    private boolean pagoRealizado;

    @Column(name = "notas", columnDefinition = "TEXT", nullable = true)
    private String notas;

    @Column(name = "fechaPago", nullable = true)
    private Date fechaPago;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsernameInscripto() {
        return usernameInscripto;
    }

    public void setUsernameInscripto(String usernameInscripto) {
        this.usernameInscripto = usernameInscripto;
    }

    public boolean isEnvioJustificanteEmail() {
        return envioJustificanteEmail;
    }

    public void setEnvioJustificanteEmail(boolean envioJustificanteEmail) {
        this.envioJustificanteEmail = envioJustificanteEmail;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "inscripcion{" +
                "id=" + id +
                ", fechaInscripcion=" + fechaInscripcion +
                ", username='" + username + '\'' +
                ", usernameInscripto='" + usernameInscripto + '\'' +
                ", envioJustificanteEmail=" + envioJustificanteEmail +
                ", idCategoria=" + idCategoria +
                ", pagoRealizado=" + pagoRealizado +
                ", notas='" + notas + '\'' +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
