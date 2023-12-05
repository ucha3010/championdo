package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MandatoModel {

    private int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaAlta;
    private String temporada;
    private boolean adulto;
    private String nombreMandante;
    private String apellido1Mandante;
    private String apellido2Mandante;
    private String dniMandante;
    private String correoMandante;
    private String calidad;
    private String calidadOtro;
    private String nombreAutorizado;
    private String apellido1Autorizado;
    private String apellido2Autorizado;
    private String dniAutorizado;
    private String domicilioCalle;
    private String domicilioNumero;
    private String domicilioOtros;
    private String domicilioLocalidad;
    private String domicilioCp;
    private String pais;
    private int codigoGimnasio;
    private boolean mandatoFirmado;
}
