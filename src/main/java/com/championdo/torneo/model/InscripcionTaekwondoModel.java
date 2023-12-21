package com.championdo.torneo.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InscripcionTaekwondoModel {

    private int id;
    private Date fechaInscripcion;
    private int codigoGimnasio;

    //Mayor
    private String mayorDni;
    private String mayorNombre;
    private String mayorApellido1;
    private String mayorApellido2;
    private String mayorSexo;
    private Date mayorFechaNacimiento;
    private String mayorCalidad;
    private String mayorPais;
    private String mayorCinturon;
    private String mayorCorreo;
    private String mayorDomicilioCalle;
    private String mayorDomicilioNumero;
    private String mayorDomicilioOtros;
    private String mayorDomicilioLocalidad;
    private String mayorDomicilioCp;
    private boolean mayorLicencia;
    private String mayorTelefono;
    private boolean mayorAutorizaWhatsApp;

    //Autorizado
    private boolean autorizadoMenor;
    private String autorizadoNombre;
    private String autorizadoApellido1;
    private String autorizadoApellido2;
    private String autorizadoSexo;
    private Date autorizadoFechaNacimiento;
    private String autorizadoPais;
    private String autorizadoCinturon;
    private String autorizadoDni;
    private boolean autorizadoLicencia;
    private boolean domiciliacionSEPA;
    private String titularCuenta;
    private String iban;
    private String swift;
    private String notas;
    private boolean inscripcionFirmada;
    private boolean domiciliacionSEPAFirmada;
    private String extensionSEPAFirmado;
}
