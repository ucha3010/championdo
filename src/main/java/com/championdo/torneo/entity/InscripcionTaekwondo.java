package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "inscripcion_taekwondo")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class InscripcionTaekwondo {

    @Id
    @GeneratedValue
    private int id;
    private Date fechaInscripcion;

    //Mayor
    @Column(name = "mayorDni", nullable = false, length = 45)
    private String mayorDni;
    @Column(name = "mayorNombre", nullable = false, length = 60)
    private String mayorNombre;
    @Column(name = "mayorApellido1", nullable = false, length = 60)
    private String mayorApellido1;
    @Column(name = "mayorApellido2", length = 60)
    private String mayorApellido2;
    @Column(name = "mayorSexo", length = 9)
    private String mayorSexo;
    private Date mayorFechaNacimiento;
    @Column(name = "mayorCalidad", length = 20)
    private String mayorCalidad;
    @Column(name = "mayorPais", length = 20)
    private String mayorPais;
    @Column(name = "mayorCinturon", length = 40)
    private String mayorCinturon;
    @Column(name = "mayorCorreo", length = 100)
    private String mayorCorreo;
    @Column(name = "mayorDomicilioCalle", length = 100)
    private String mayorDomicilioCalle;
    @Column(name = "mayorDomicilioNumero", length = 30)
    private String mayorDomicilioNumero;
    @Column(name = "mayorDomicilioOtros", length = 50)
    private String mayorDomicilioOtros;
    @Column(name = "mayorDomicilioLocalidad", length = 50)
    private String mayorDomicilioLocalidad;
    @Column(name = "mayorDomicilioCp", length = 10)
    private String mayorDomicilioCp;
    private boolean mayorLicencia;
    @Column(name = "mayorTelefono", length = 20)
    private String mayorTelefono;
    private boolean mayorAutorizaWhatsApp;

    //Autorizado
    @Column(name = "autorizadoNombre", length = 60)
    private String autorizadoNombre;
    @Column(name = "autorizadoApellido1", length = 60)
    private String autorizadoApellido1;
    @Column(name = "autorizadoApellido2", length = 60)
    private String autorizadoApellido2;
    @Column(name = "autorizadoSexo", length = 9)
    private String autorizadoSexo;
    private Date autorizadoFechaNacimiento;
    @Column(name = "autorizadoPais", length = 20)
    private String autorizadoPais;
    @Column(name = "autorizadoCinturon", length = 40)
    private String autorizadoCinturon;
    @Column(name = "autorizadoDni", length = 45)
    private String autorizadoDni;
    private boolean autorizadoLicencia;
    private boolean domiciliacionSEPA;
    @Column(name = "titularCuenta", length = 100)
    private String titularCuenta;
    @Column(name = "iban", length = 34)
    private String iban;
    @Column(name = "swift", length = 11)
    private String swift;
    @Column(name = "notas", columnDefinition = "TEXT")
    private String notas;
    private boolean inscripcionFirmada;
    private boolean mandatoSEPAFirmado;

}
