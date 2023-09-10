package com.championdo.torneo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class GimnasioRootModel {

    private int id;
    private boolean enabled;
    private String nombreGimnasio;
    private String nombreResponsable;
    private String apellido1Responsable;
    private String apellido2Responsable;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaNacimiento;
    private String domicilioCalle;
    private String domicilioNumero;
    private String domicilioOtros;
    private String domicilioLocalidad;
    private String domicilioCp;
    private String cifNif;
    private int visibilidadContratada;
    private int cantidadRegistrosContratados;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaAlta;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date fechaModificacion;
    private String usuarioModificacion;
    private String telefono;
    private String correo;

}
