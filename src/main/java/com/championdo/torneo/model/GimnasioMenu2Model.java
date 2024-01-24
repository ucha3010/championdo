package com.championdo.torneo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;
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
