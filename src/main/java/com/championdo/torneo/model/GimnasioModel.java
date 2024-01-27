package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GimnasioModel {

    private int id;
    private boolean enabled;
    private String nombreGimnasio;
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
    private String emailPassword;
    private String emailHost;
    private String emailPort;
    private List<Menu2Model> menu2ModelList;

    @Override
    public String toString() {
        return "GimnasioModel{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", nombreGimnasio='" + nombreGimnasio + '\'' +
                ", domicilioCalle='" + domicilioCalle + '\'' +
                ", domicilioNumero='" + domicilioNumero + '\'' +
                ", domicilioOtros='" + domicilioOtros + '\'' +
                ", domicilioLocalidad='" + domicilioLocalidad + '\'' +
                ", domicilioCp='" + domicilioCp + '\'' +
                ", cifNif='" + cifNif + '\'' +
                ", visibilidadContratada=" + visibilidadContratada +
                ", cantidadRegistrosContratados=" + cantidadRegistrosContratados +
                ", fechaAlta=" + fechaAlta +
                ", fechaModificacion=" + fechaModificacion +
                ", usuarioModificacion='" + usuarioModificacion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", correo='" + correo + '\'' +
                ", emailHost='" + emailHost + '\'' +
                ", emailPort='" + emailPort + '\'' +
                ", menu2ModelList=" + menu2ModelList +
                '}';
    }
}
