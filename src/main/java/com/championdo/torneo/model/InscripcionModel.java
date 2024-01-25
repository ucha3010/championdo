package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class InscripcionModel {//TODO DAMIAN modificar nombre a TournamentRegistration

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
    private int codigoGimnasio;
    private int idTorneo;

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
}
