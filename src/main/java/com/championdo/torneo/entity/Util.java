package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "util")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Util {

    @Id
    @Column(name = "clave", unique = true, nullable = false, length = 30)
    private String clave;

    @Column(name = "valor", nullable = false, length = 200)
    private String valor;

}
