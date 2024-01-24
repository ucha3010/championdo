package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "gimnasio")
@Getter
@Setter
@NoArgsConstructor
public class Gimnasio {

    @Id
    @SequenceGenerator(name = "gimnasioGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gimnasioGenerator")
    private int id;
    private boolean enabled;
    @Column(name = "nombreGimnasio", length = 100)
    private String nombreGimnasio;
    @Column(name = "nombreResponsable", length = 60)
    private String nombreResponsable;
    @Column(name = "apellido1Responsable", length = 60)
    private String apellido1Responsable;
    @Column(name = "apellido2Responsable", length = 60)
    private String apellido2Responsable;
    private Date fechaNacimiento;
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
    @Column(name = "cifNif", length = 15)
    private String cifNif;
    private int visibilidadContratada;
    private int cantidadRegistrosContratados;
    private Date fechaAlta;
    private Date fechaModificacion;
    @Column(name = "usuarioModificacion", length = 45)
    private String usuarioModificacion;
    @Column(name = "telefono", length = 20)
    private String telefono;
    @Column(name = "correo", length = 100)
    private String correo;
    @Column(name = "emailPassword", length = 60)
    private String emailPassword;
    @Column(name = "emailHost", length = 200)
    private String emailHost;
    @Column(name = "emailPort", length = 5)
    private String emailPort;

    @Override
    public String toString() {
        return "Gimnasio{" +
                "id=" + id +
                ", enabled=" + enabled +
                ", nombreGimnasio='" + nombreGimnasio + '\'' +
                ", nombreResponsable='" + nombreResponsable + '\'' +
                ", apellido1Responsable='" + apellido1Responsable + '\'' +
                ", apellido2Responsable='" + apellido2Responsable + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
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
                '}';
    }
}
