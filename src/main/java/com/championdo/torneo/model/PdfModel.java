package com.championdo.torneo.model;

public class PdfModel {

    private String nombre; //Damián Usheff Vellianitis
    private String dni; //05959715R
    private String fechaNacimiento; //30/10/1976
    private String domicilio; //calle Azorín 18
    private String localidad; //Cobeña
    private String gimnasio; //Championdo
    private String fechaCampeonato; //20/12/2022
    private String direccionCampeonato;
    private String calidadDe; //padre
    private String nombreMenor; //Ariadna Usheff Huertas
    private String dniMenor; //01234567A
    private boolean cinturonBlanco; //true
    private boolean mayorEdad; //false
    private int moverPorMenorEdad; //0
    private String cinturonActual;
    private int idInscripcion;
    private String categoria;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(String gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String getFechaCampeonato() {
        return fechaCampeonato;
    }

    public void setFechaCampeonato(String fechaCampeonato) {
        this.fechaCampeonato = fechaCampeonato;
    }

    public String getDireccionCampeonato() {
        return direccionCampeonato;
    }

    public void setDireccionCampeonato(String direccionCampeonato) {
        this.direccionCampeonato = direccionCampeonato;
    }

    public String getCalidadDe() {
        return calidadDe;
    }

    public void setCalidadDe(String calidadDe) {
        this.calidadDe = calidadDe;
    }

    public String getNombreMenor() {
        return nombreMenor;
    }

    public void setNombreMenor(String nombreMenor) {
        this.nombreMenor = nombreMenor;
    }

    public String getDniMenor() {
        return dniMenor;
    }

    public void setDniMenor(String dniMenor) {
        this.dniMenor = dniMenor;
    }

    public boolean isCinturonBlanco() {
        return cinturonBlanco;
    }

    public void setCinturonBlanco(boolean cinturonBlanco) {
        this.cinturonBlanco = cinturonBlanco;
    }

    public boolean isMayorEdad() {
        return mayorEdad;
    }

    public void setMayorEdad(boolean mayorEdad) {
        this.mayorEdad = mayorEdad;
    }

    public int getMoverPorMenorEdad() {
        return moverPorMenorEdad;
    }

    public void setMoverPorMenorEdad(int moverPorMenorEdad) {
        this.moverPorMenorEdad = moverPorMenorEdad;
    }

    public String getCinturonActual() {
        return cinturonActual;
    }

    public void setCinturonActual(String cinturonActual) {
        this.cinturonActual = cinturonActual;
    }

    public int getIdInscripcion() {
        return idInscripcion;
    }

    public void setIdInscripcion(int idInscripcion) {
        this.idInscripcion = idInscripcion;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    @Override
    public String toString() {
        return "PdfModel{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", localidad='" + localidad + '\'' +
                ", gimnasio='" + gimnasio + '\'' +
                ", fechaCampeonato='" + fechaCampeonato + '\'' +
                ", direccionCampeonato='" + direccionCampeonato + '\'' +
                ", calidadDe='" + calidadDe + '\'' +
                ", nombreMenor='" + nombreMenor + '\'' +
                ", dniMenor='" + dniMenor + '\'' +
                ", cinturonBlanco=" + cinturonBlanco +
                ", mayorEdad=" + mayorEdad +
                ", moverPorMenorEdad=" + moverPorMenorEdad +
                ", cinturonActual='" + cinturonActual + '\'' +
                ", idInscripcion=" + idInscripcion +
                ", categoria='" + categoria + '\'' +
                '}';
    }
}
