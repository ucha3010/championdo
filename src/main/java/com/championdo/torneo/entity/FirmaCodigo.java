package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "firma_codigo")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class FirmaCodigo {

    @Id
    @GeneratedValue
    private int id;
    private int idOperacion;
    private Date fechaCreacion;
    private Date fechaCaducidad;
    @Column(name = "codigo", nullable = false, length = 6)
    private String codigo;
    @Column(name = "dni", nullable = false, length = 45)
    private String dni;
    @Column(name = "paginaFirmaOk", nullable = false, length = 200)
    private String paginaFirmaOk;
    @Column(name = "operativaOriginal", nullable = false, length = 50)
    private String operativaOriginal;
    private int codigoGimnasio;

}
