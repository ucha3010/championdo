package com.championdo.torneo.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "gimnasio_menu2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GimnasioMenu2 {

    @Id
    @SequenceGenerator(name = "gimnasioMenu2Generator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gimnasioMenu2Generator")
    private int id;
    private int idGimnasio;
    private int idMenu2;
    @Column(name = "usernameAlta", length = 45)
    private String usernameAlta;
    private Date fechaAlta;

}
