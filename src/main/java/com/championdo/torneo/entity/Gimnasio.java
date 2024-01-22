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
@ToString
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

}
