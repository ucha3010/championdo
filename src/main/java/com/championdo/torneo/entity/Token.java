package com.championdo.torneo.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
