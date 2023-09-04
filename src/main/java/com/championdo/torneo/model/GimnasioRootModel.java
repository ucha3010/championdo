package com.championdo.torneo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

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
    private String domicilioCalle;
    private String domicilioNumero;
    private String domicilioOtros;
    private String domicilioLocalidad;
    private String domicilioCp;
    private String cifNif;
    private int visibilidadContratada;
    private int cantidadRegistrosContratados;
    private Date fechaAlta;
    private Date fechaModificacion;
    private String usuarioModificacion;
    private String telefono;
    private String correo;

}
