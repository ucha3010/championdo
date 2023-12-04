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
    @Column(name = "mandante", length = 200)
    private String mandante;
    @Column(name = "dniMandante", length = 45)
    private String dniMandante;
    @Column(name = "autorizado", length = 200)
    private String autorizado;
    @Column(name = "dniAutorizado", length = 45)
    private String dniAutorizado;
    private int codigoGimnasio;

}
