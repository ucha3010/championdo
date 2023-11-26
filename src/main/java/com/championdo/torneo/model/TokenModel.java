package com.championdo.torneo.model;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TokenModel {

    private String id;
    private String username;
    private Date expiration;
    private int attempts;
    private int codigoGimnasio;
    private String password;
    private String name;
    private String lastname;
    private String secondLastname;

}
