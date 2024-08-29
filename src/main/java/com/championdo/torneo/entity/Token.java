package com.championdo.torneo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "token")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Token {

    @Id
    private String id;
    private String username;
    private Date expiration;
    private int attempts;
    private int codigoGimnasio;

}
