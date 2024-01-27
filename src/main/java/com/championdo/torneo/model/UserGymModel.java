package com.championdo.torneo.model;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserGymModel {

    private int id;
    private String username;
    private int idGym;
    private String usernameAdd;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateAdd;

}
