package com.championdo.torneo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class InscripcionModel {

    private int id;
    private boolean inscripcionPropia;
    private boolean inscripcionMenor;
    private boolean inscripcionInclusiva;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaInscripcion;
    private String fechaCampeonato;
    private String nombreCampeonato;
    private String direccionCampeonato;
    private String categoria;

    //Usuario inscripto
    private String nombreInscripto;
    private String apellido1Inscripto;
    private String apellido2Inscripto;
    private String dniInscripto;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimientoInscripto;
    private String sexoInscripto;
    private String domicilioCalleInscripto;
    private String domicilioNumeroInscripto;
    private String domicilioOtrosInscripto;
    private String domicilioLocalidadInscripto;
    private String domicilioCpInscripto;
    private String gimnasio;
    private String pais;
    private String cinturon;
    private String poomsae;

    //Usuario autorizador
    private String nombreAutorizador;
    private String apellido1Autorizador;
    private String apellido2Autorizador;
    private String dniAutorizador;
    private String calidad;
    private String domicilioCalleAutorizador;
    private String domicilioNumeroAutorizador;
    private String domicilioOtrosAutorizador;
    private String domicilioLocalidadAutorizador;
    private String domicilioCpAutorizador;

    //Datos pago
    private boolean pagoRealizado;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
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

    public String getNombreCampeonato() {
        return nombreCampeonato;
    }

    public void setNombreCampeonato(String nombreCampeonato) {
        this.nombreCampeonato = nombreCampeonato;
    }

    public String getDireccionCampeonato() {
        return direccionCampeonato;
    }

    public void setDireccionCampeonato(String direccionCampeonato) {
        this.direccionCampeonato = direccionCampeonato;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNombreInscripto() {
        return nombreInscripto;
    }

    public void setNombreInscripto(String nombreInscripto) {
        this.nombreInscripto = nombreInscripto;
    }

    public String getApellido1Inscripto() {
        return apellido1Inscripto;
    }

    public void setApellido1Inscripto(String apellido1Inscripto) {
        this.apellido1Inscripto = apellido1Inscripto;
    }

    public String getApellido2Inscripto() {
        return apellido2Inscripto;
    }

    public void setApellido2Inscripto(String apellido2Inscripto) {
        this.apellido2Inscripto = apellido2Inscripto;
    }

    public String getDniInscripto() {
        return dniInscripto;
    }

    public void setDniInscripto(String dniInscripto) {
        this.dniInscripto = dniInscripto;
    }

    public Date getFechaNacimientoInscripto() {
        return fechaNacimientoInscripto;
    }

    public void setFechaNacimientoInscripto(Date fechaNacimientoInscripto) {
        this.fechaNacimientoInscripto = fechaNacimientoInscripto;
    }

    public String getSexoInscripto() {
        return sexoInscripto;
    }

    public void setSexoInscripto(String sexoInscripto) {
        this.sexoInscripto = sexoInscripto;
    }

    public String getDomicilioCalleInscripto() {
        return domicilioCalleInscripto;
    }

    public void setDomicilioCalleInscripto(String domicilioCalleInscripto) {
        this.domicilioCalleInscripto = domicilioCalleInscripto;
    }

    public String getDomicilioNumeroInscripto() {
        return domicilioNumeroInscripto;
    }

    public void setDomicilioNumeroInscripto(String domicilioNumeroInscripto) {
        this.domicilioNumeroInscripto = domicilioNumeroInscripto;
    }

    public String getDomicilioOtrosInscripto() {
        return domicilioOtrosInscripto;
    }

    public void setDomicilioOtrosInscripto(String domicilioOtrosInscripto) {
        this.domicilioOtrosInscripto = domicilioOtrosInscripto;
    }

    public String getDomicilioLocalidadInscripto() {
        return domicilioLocalidadInscripto;
    }

    public void setDomicilioLocalidadInscripto(String domicilioLocalidadInscripto) {
        this.domicilioLocalidadInscripto = domicilioLocalidadInscripto;
    }

    public String getDomicilioCpInscripto() {
        return domicilioCpInscripto;
    }

    public void setDomicilioCpInscripto(String domicilioCpInscripto) {
        this.domicilioCpInscripto = domicilioCpInscripto;
    }

    public String getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(String gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getCinturon() {
        return cinturon;
    }

    public void setCinturon(String cinturon) {
        this.cinturon = cinturon;
    }

    public String getPoomsae() {
        return poomsae;
    }

    public void setPoomsae(String poomsae) {
        this.poomsae = poomsae;
    }

    public String getNombreAutorizador() {
        return nombreAutorizador;
    }

    public void setNombreAutorizador(String nombreAutorizador) {
        this.nombreAutorizador = nombreAutorizador;
    }

    public String getApellido1Autorizador() {
        return apellido1Autorizador;
    }

    public void setApellido1Autorizador(String apellido1Autorizador) {
        this.apellido1Autorizador = apellido1Autorizador;
    }

    public String getApellido2Autorizador() {
        return apellido2Autorizador;
    }

    public void setApellido2Autorizador(String apellido2Autorizador) {
        this.apellido2Autorizador = apellido2Autorizador;
    }

    public String getDniAutorizador() {
        return dniAutorizador;
    }

    public void setDniAutorizador(String dniAutorizador) {
        this.dniAutorizador = dniAutorizador;
    }

    public String getCalidad() {
        return calidad;
    }

    public void setCalidad(String calidad) {
        this.calidad = calidad;
    }

    public String getDomicilioCalleAutorizador() {
        return domicilioCalleAutorizador;
    }

    public void setDomicilioCalleAutorizador(String domicilioCalleAutorizador) {
        this.domicilioCalleAutorizador = domicilioCalleAutorizador;
    }

    public String getDomicilioNumeroAutorizador() {
        return domicilioNumeroAutorizador;
    }

    public void setDomicilioNumeroAutorizador(String domicilioNumeroAutorizador) {
        this.domicilioNumeroAutorizador = domicilioNumeroAutorizador;
    }

    public String getDomicilioOtrosAutorizador() {
        return domicilioOtrosAutorizador;
    }

    public void setDomicilioOtrosAutorizador(String domicilioOtrosAutorizador) {
        this.domicilioOtrosAutorizador = domicilioOtrosAutorizador;
    }

    public String getDomicilioLocalidadAutorizador() {
        return domicilioLocalidadAutorizador;
    }

    public void setDomicilioLocalidadAutorizador(String domicilioLocalidadAutorizador) {
        this.domicilioLocalidadAutorizador = domicilioLocalidadAutorizador;
    }

    public String getDomicilioCpAutorizador() {
        return domicilioCpAutorizador;
    }

    public void setDomicilioCpAutorizador(String domicilioCpAutorizador) {
        this.domicilioCpAutorizador = domicilioCpAutorizador;
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
                ", nombreCampeonato='" + nombreCampeonato + '\'' +
                ", direccionCampeonato='" + direccionCampeonato + '\'' +
                ", categoria='" + categoria + '\'' +
                ", nombreInscripto='" + nombreInscripto + '\'' +
                ", apellido1Inscripto='" + apellido1Inscripto + '\'' +
                ", apellido2Inscripto='" + apellido2Inscripto + '\'' +
                ", dniInscripto='" + dniInscripto + '\'' +
                ", fechaNacimientoInscripto=" + fechaNacimientoInscripto +
                ", sexoInscripto='" + sexoInscripto + '\'' +
                ", domicilioCalleInscripto='" + domicilioCalleInscripto + '\'' +
                ", domicilioNumeroInscripto='" + domicilioNumeroInscripto + '\'' +
                ", domicilioOtrosInscripto='" + domicilioOtrosInscripto + '\'' +
                ", domicilioLocalidadInscripto='" + domicilioLocalidadInscripto + '\'' +
                ", domicilioCpInscripto='" + domicilioCpInscripto + '\'' +
                ", gimnasio='" + gimnasio + '\'' +
                ", pais='" + pais + '\'' +
                ", cinturon='" + cinturon + '\'' +
                ", poomsae='" + poomsae + '\'' +
                ", nombreAutorizador='" + nombreAutorizador + '\'' +
                ", apellido1Autorizador='" + apellido1Autorizador + '\'' +
                ", apellido2Autorizador='" + apellido2Autorizador + '\'' +
                ", dniAutorizador='" + dniAutorizador + '\'' +
                ", calidad='" + calidad + '\'' +
                ", domicilioCalleAutorizador='" + domicilioCalleAutorizador + '\'' +
                ", domicilioNumeroAutorizador='" + domicilioNumeroAutorizador + '\'' +
                ", domicilioOtrosAutorizador='" + domicilioOtrosAutorizador + '\'' +
                ", domicilioLocalidadAutorizador='" + domicilioLocalidadAutorizador + '\'' +
                ", domicilioCpAutorizador='" + domicilioCpAutorizador + '\'' +
                ", pagoRealizado=" + pagoRealizado +
                ", fechaPago=" + fechaPago +
                ", notas='" + notas + '\'' +
                '}';
    }
}
