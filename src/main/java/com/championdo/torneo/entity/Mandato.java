package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "mandato")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Mandato {

    @Id
    @GeneratedValue
    private int id;
    private Date fechaAlta;
    @Column(name = "temporada", length = 11)
    private String temporada;
    private boolean adulto;
    @Column(name = "nombreMandante", length = 60)
    private String nombreMandante;
    @Column(name = "apellido1Mandante", length = 60)
    private String apellido1Mandante;
    @Column(name = "apellido2Mandante", length = 60)
    private String apellido2Mandante;
    @Column(name = "dniMandante", length = 45)
    private String dniMandante;
    @Column(name = "correoMandante", length = 100)
    private String correoMandante;
    @Column(name = "calidad", length = 20)
    private String calidad;
    @Column(name = "calidadOtro", length = 20)
    private String calidadOtro;
    @Column(name = "nombreAutorizado", length = 60)
    private String nombreAutorizado;
    @Column(name = "apellido1Autorizado", length = 60)
    private String apellido1Autorizado;
    @Column(name = "apellido2Autorizado", length = 60)
    private String apellido2Autorizado;
    @Column(name = "dniAutorizado", length = 45)
    private String dniAutorizado;
    @Column(name = "domicilioCalle", length = 100)
    private String domicilioCalle;
    @Column(name = "domicilioNumero", length = 30)
    private String domicilioNumero;
    @Column(name = "domicilioOtros", length = 50)
    private String domicilioOtros;
    @Column(name = "domicilioLocalidad", length = 50)
    private String domicilioLocalidad;
    @Column(name = "domicilioCp", length = 10)
    private String domicilioCp;
    @Column(name = "pais", length = 20)
    private String pais;
    private int codigoGimnasio;
    private boolean mandatoFirmado;

}
