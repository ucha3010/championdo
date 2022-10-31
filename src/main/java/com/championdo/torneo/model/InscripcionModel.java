package com.championdo.torneo.model;

import com.championdo.torneo.entity.Categoria;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InscripcionModel {

    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInscripcion;
    private UserModel usuario;
    private UserModel usuarioInscripto;
    private boolean envioJustificanteEmail;
    private CategoriaModel categoria;
    private boolean pagoRealizado;
    private String notas;
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

    public UserModel getUsuario() {
        return usuario;
    }

    public void setUsuario(UserModel usuario) {
        this.usuario = usuario;
    }

    public UserModel getUsuarioInscripto() {
        return usuarioInscripto;
    }

    public void setUsuarioInscripto(UserModel usuarioInscripto) {
        this.usuarioInscripto = usuarioInscripto;
    }

    public boolean isEnvioJustificanteEmail() {
        return envioJustificanteEmail;
    }

    public void setEnvioJustificanteEmail(boolean envioJustificanteEmail) {
        this.envioJustificanteEmail = envioJustificanteEmail;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
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
        return "InscripcionModel{" +
                "id=" + id +
                ", fechaInscripcion=" + fechaInscripcion +
                ", usuario=" + usuario +
                ", usuarioInscripto=" + usuarioInscripto +
                ", envioJustificanteEmail=" + envioJustificanteEmail +
                ", categoria=" + categoria +
                ", pagoRealizado=" + pagoRealizado +
                ", notas='" + notas + '\'' +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
