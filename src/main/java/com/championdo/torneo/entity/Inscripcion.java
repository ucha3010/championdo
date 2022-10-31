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

    @Column(name = "nameInscripto", nullable = false, length = 60)
    private String nameInscripto;

    @Column(name = "lastnameInscripto", nullable = false, length = 60)
    private String lastnameInscripto;

    @Column(name = "secondLastnameInscripto", nullable = true, length = 60)
    private String secondLastnameInscripto;

    @Column(name = "sexo", nullable = false, length = 9)
    private String sexo;

    @Column(name = "fechaNacimiento", nullable = false)
    private Date fechaNacimiento;

    @Column(name = "domicilioCalle", nullable = true, length = 100)
    private String domicilioCalle;

    @Column(name = "domicilioNumero", nullable = true, length = 30)
    private String domicilioNumero;

    @Column(name = "domicilioOtros", nullable = true, length = 50)
    private String domicilioOtros;

    @Column(name = "domicilioLocalidad", nullable = true, length = 50)
    private String domicilioLocalidad;

    @Column(name = "domicilioCp", nullable = true, length = 10)
    private String domicilioCp;

    @Column(name = "idGimnasio")
    private int idGimnasio;

    @Column(name = "idPais")
    private int idPais;

    @Column(name = "idCinturon")
    private int idCinturon;

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

    public String getNameInscripto() {
        return nameInscripto;
    }

    public void setNameInscripto(String nameInscripto) {
        this.nameInscripto = nameInscripto;
    }

    public String getLastnameInscripto() {
        return lastnameInscripto;
    }

    public void setLastnameInscripto(String lastnameInscripto) {
        this.lastnameInscripto = lastnameInscripto;
    }

    public String getSecondLastnameInscripto() {
        return secondLastnameInscripto;
    }

    public void setSecondLastnameInscripto(String secondLastnameInscripto) {
        this.secondLastnameInscripto = secondLastnameInscripto;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilioCalle() {
        return domicilioCalle;
    }

    public void setDomicilioCalle(String domicilioCalle) {
        this.domicilioCalle = domicilioCalle;
    }

    public String getDomicilioNumero() {
        return domicilioNumero;
    }

    public void setDomicilioNumero(String domicilioNumero) {
        this.domicilioNumero = domicilioNumero;
    }

    public String getDomicilioOtros() {
        return domicilioOtros;
    }

    public void setDomicilioOtros(String domicilioOtros) {
        this.domicilioOtros = domicilioOtros;
    }

    public String getDomicilioLocalidad() {
        return domicilioLocalidad;
    }

    public void setDomicilioLocalidad(String domicilioLocalidad) {
        this.domicilioLocalidad = domicilioLocalidad;
    }

    public String getDomicilioCp() {
        return domicilioCp;
    }

    public void setDomicilioCp(String domicilioCp) {
        this.domicilioCp = domicilioCp;
    }

    public int getIdGimnasio() {
        return idGimnasio;
    }

    public void setIdGimnasio(int idGimnasio) {
        this.idGimnasio = idGimnasio;
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public int getIdCinturon() {
        return idCinturon;
    }

    public void setIdCinturon(int idCinturon) {
        this.idCinturon = idCinturon;
    }

    @Override
    public String toString() {
        return "Inscripcion{" +
                "id=" + id +
                ", fechaInscripcion=" + fechaInscripcion +
                ", username='" + username + '\'' +
                ", usernameInscripto='" + usernameInscripto + '\'' +
                ", envioJustificanteEmail=" + envioJustificanteEmail +
                ", idCategoria=" + idCategoria +
                ", pagoRealizado=" + pagoRealizado +
                ", notas='" + notas + '\'' +
                ", fechaPago=" + fechaPago +
                ", nameInscripto='" + nameInscripto + '\'' +
                ", lastnameInscripto='" + lastnameInscripto + '\'' +
                ", secondLastnameInscripto='" + secondLastnameInscripto + '\'' +
                ", sexo='" + sexo + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", domicilioCalle='" + domicilioCalle + '\'' +
                ", domicilioNumero='" + domicilioNumero + '\'' +
                ", domicilioOtros='" + domicilioOtros + '\'' +
                ", domicilioLocalidad='" + domicilioLocalidad + '\'' +
                ", domicilioCp='" + domicilioCp + '\'' +
                ", idGimnasio=" + idGimnasio +
                ", idPais=" + idPais +
                ", idCinturon=" + idCinturon +
                '}';
    }
}
