package com.championdo.torneo.model;

import com.championdo.torneo.entity.Categoria;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import java.util.Date;

public class InscripcionModel {

    private int id;
    private boolean inscripcionPropia;
    private boolean inscripcionMenor;
    private boolean inscripcionInclusiva;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInscripcion;
    private String fechaCampeonato;
    private CategoriaModel categoria;
    private UserModel usuarioInscripto;
    private UserModel usuarioAutorizador;
    private boolean envioJustificanteEmail;
    private boolean pagoRealizado;
    private Date fechaPago;
    private String notas;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isInscripcionPropia() {
        return inscripcionPropia;
    }

    public void setInscripcionPropia(boolean inscripcionPropia) {
        this.inscripcionPropia = inscripcionPropia;
    }

    public boolean isInscripcionMenor() {
        return inscripcionMenor;
    }

    public void setInscripcionMenor(boolean inscripcionMenor) {
        this.inscripcionMenor = inscripcionMenor;
    }

    public boolean isInscripcionInclusiva() {
        return inscripcionInclusiva;
    }

    public void setInscripcionInclusiva(boolean inscripcionInclusiva) {
        this.inscripcionInclusiva = inscripcionInclusiva;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    public String getFechaCampeonato() {
        return fechaCampeonato;
    }

    public void setFechaCampeonato(String fechaCampeonato) {
        this.fechaCampeonato = fechaCampeonato;
    }

    public CategoriaModel getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaModel categoria) {
        this.categoria = categoria;
    }

    public UserModel getUsuarioInscripto() {
        return usuarioInscripto;
    }

    public void setUsuarioInscripto(UserModel usuarioInscripto) {
        this.usuarioInscripto = usuarioInscripto;
    }

    public UserModel getUsuarioAutorizador() {
        return usuarioAutorizador;
    }

    public void setUsuarioAutorizador(UserModel usuarioAutorizador) {
        this.usuarioAutorizador = usuarioAutorizador;
    }

    public boolean isEnvioJustificanteEmail() {
        return envioJustificanteEmail;
    }

    public void setEnvioJustificanteEmail(boolean envioJustificanteEmail) {
        this.envioJustificanteEmail = envioJustificanteEmail;
    }

    public boolean isPagoRealizado() {
        return pagoRealizado;
    }

    public void setPagoRealizado(boolean pagoRealizado) {
        this.pagoRealizado = pagoRealizado;
    }

    public Date getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(Date fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

    @Override
    public String toString() {
        return "InscripcionModel{" +
                "id=" + id +
                ", inscripcionPropia=" + inscripcionPropia +
                ", inscripcionMenor=" + inscripcionMenor +
                ", inscripcionInclusiva=" + inscripcionInclusiva +
                ", fechaInscripcion=" + fechaInscripcion +
                ", fechaCampeonato='" + fechaCampeonato + '\'' +
                ", categoria=" + categoria +
                ", usuarioInscripto=" + usuarioInscripto +
                ", usuarioAutorizador=" + usuarioAutorizador +
                ", envioJustificanteEmail=" + envioJustificanteEmail +
                ", pagoRealizado=" + pagoRealizado +
                ", fechaPago=" + fechaPago +
                ", notas='" + notas + '\'' +
                '}';
    }
}
