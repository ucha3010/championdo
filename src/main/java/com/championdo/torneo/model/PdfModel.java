package com.championdo.torneo.model;

public class PdfModel {

    private String nombre; //Nombre Autorizador Apellido1dor Apellido2dor
    private String dni; //22222222A
    private String fechaNacimiento; //30/10/1960
    private String domicilio; //calle Mayor 150
    private String localidad; //Madrid (28003) - España
    private String telefono; //654654654
    private String correo; //pepe@pepe.com
    private String gimnasio; //Championdo
    private String nombreCampeonato; //CAMPEONATO DE TRES CANTOS
    private String fechaCampeonato; //20/12/2022
    private String direccionCampeonato; //Polideportivo La Luz
    private String calidadDe; //padre
    private String nombreMenor; //Nombre Autorizado Apellido1do Apellido2do
    private String dniMenor; //01234567A
    private String fechaNacimientoMenor; //30/10/1960
    private boolean cinturonBlanco; //true
    private boolean mayorEdad; //false
    private boolean inclusivo;
    private String cinturonActual; //Amarillo Naranja
    private int idInscripcion;
    private String categoria;
    private String poomsae;
    private String seccion;
    private String extension;
    private CuentaBancariaModel cuentaBancaria;

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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGimnasio() {
        return gimnasio;
    }

    public void setGimnasio(String gimnasio) {
        this.gimnasio = gimnasio;
    }

    public String getNombreCampeonato() {
        return nombreCampeonato;
    }

    public void setNombreCampeonato(String nombreCampeonato) {
        this.nombreCampeonato = nombreCampeonato;
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

    public String getFechaNacimientoMenor() {
        return fechaNacimientoMenor;
    }

    public void setFechaNacimientoMenor(String fechaNacimientoMenor) {
        this.fechaNacimientoMenor = fechaNacimientoMenor;
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

    public boolean isInclusivo() {
        return inclusivo;
    }

    public void setInclusivo(boolean inclusivo) {
        this.inclusivo = inclusivo;
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

    public String getPoomsae() {
        return poomsae;
    }

    public void setPoomsae(String poomsae) {
        this.poomsae = poomsae;
    }

    public String getSeccion() {
        return seccion;
    }

    public void setSeccion(String seccion) {
        this.seccion = seccion;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public CuentaBancariaModel getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaModel cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        return "PdfModel{" +
                "nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", domicilio='" + domicilio + '\'' +
                ", localidad='" + localidad + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", gimnasio='" + gimnasio + '\'' +
                ", nombreCampeonato='" + nombreCampeonato + '\'' +
                ", fechaCampeonato='" + fechaCampeonato + '\'' +
                ", direccionCampeonato='" + direccionCampeonato + '\'' +
                ", calidadDe='" + calidadDe + '\'' +
                ", nombreMenor='" + nombreMenor + '\'' +
                ", dniMenor='" + dniMenor + '\'' +
                ", fechaNacimientoMenor='" + fechaNacimientoMenor + '\'' +
                ", cinturonBlanco=" + cinturonBlanco +
                ", mayorEdad=" + mayorEdad +
                ", inclusivo=" + inclusivo +
                ", cinturonActual='" + cinturonActual + '\'' +
                ", idInscripcion=" + idInscripcion +
                ", categoria='" + categoria + '\'' +
                ", poomsae='" + poomsae + '\'' +
                ", seccion='" + seccion + '\'' +
                ", extension='" + extension + '\'' +
                ", cuentaBancaria=" + cuentaBancaria +
                '}';
    }
}
