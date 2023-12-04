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
    private String mandante;
    private String dniMandante;
    private String autorizado;
    private String dniAutorizado;
    private int codigoGimnasio;
}
