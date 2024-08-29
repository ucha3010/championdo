package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GimnasioMenu2Model {

    private int id;
    private int idGimnasio;
    private int idMenu2;
    private String usernameAlta;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date fechaAlta;

}
