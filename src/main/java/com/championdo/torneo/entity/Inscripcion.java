package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripcion")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Inscripcion {

    @Id
    @GeneratedValue
    private int id;
    private boolean inscripcionPropia;
    private boolean inscripcionMenor;
    private boolean inscripcionInclusiva;
    private Date fechaInscripcion;
    @Column(name = "fechaCampeonato", length = 10)
    private String fechaCampeonato;
    @Column(name = "nombreCampeonato", length = 200)
    private String nombreCampeonato;
    @Column(name = "direccionCampeonato", length = 200)
    private String direccionCampeonato;
    @Column(name = "categoria", length = 45)
    private String categoria;

    //Usuario inscripto
    @Column(name = "nombreInscripto", length = 60)
    private String nombreInscripto;
    @Column(name = "apellido1Inscripto", length = 60)
    private String apellido1Inscripto;
    @Column(name = "apellido2Inscripto", length = 60)
    private String apellido2Inscripto;
    @Column(name = "dniInscripto", length = 45)
    private String dniInscripto;
    private Date fechaNacimientoInscripto;
    @Column(name = "sexoInscripto", nullable = false, length = 9)
    private String sexoInscripto;
    @Column(name = "domicilioCalleInscripto", length = 100)
    private String domicilioCalleInscripto;
    @Column(name = "domicilioNumeroInscripto", length = 30)
    private String domicilioNumeroInscripto;
    @Column(name = "domicilioOtrosInscripto", length = 50)
    private String domicilioOtrosInscripto;
    @Column(name = "domicilioLocalidadInscripto", length = 50)
    private String domicilioLocalidadInscripto;
    @Column(name = "domicilioCpInscripto", length = 10)
    private String domicilioCpInscripto;
    @Column(name = "gimnasio", length = 100)
    private String gimnasio;
    @Column(name = "pais", length = 20)
    private String pais;
    @Column(name = "cinturon", length = 40)
    private String cinturon;
    @Column(name = "poomsae", length = 50)
    private String poomsae;

    //Usuario autorizador
    @Column(name = "nombreAutorizador", length = 60)
    private String nombreAutorizador;
    @Column(name = "apellido1Autorizador", length = 60)
    private String apellido1Autorizador;
    @Column(name = "apellido2Autorizador", length = 60)
    private String apellido2Autorizador;
    @Column(name = "dniAutorizador", length = 45)
    private String dniAutorizador;
    @Column(name = "calidad", length = 20)
    private String calidad;
    @Column(name = "domicilioCalleAutorizador", length = 100)
    private String domicilioCalleAutorizador;
    @Column(name = "domicilioNumeroAutorizador", length = 30)
    private String domicilioNumeroAutorizador;
    @Column(name = "domicilioOtrosAutorizador", length = 50)
    private String domicilioOtrosAutorizador;
    @Column(name = "domicilioLocalidadAutorizador", length = 50)
    private String domicilioLocalidadAutorizador;
    @Column(name = "domicilioCpAutorizador", length = 10)
    private String domicilioCpAutorizador;

    //Datos pago
    private boolean pagoRealizado;
    private Date fechaPago;
    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;

}
