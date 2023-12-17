package com.championdo.torneo.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import java.util.Date;

@Entity
@Table(name = "gimnasio_root_menu2")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GimnasioRootMenu2 {

    @Id
    @SequenceGenerator(name = "gimnasioRootMenu2Generator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gimnasioRootMenu2Generator")
    private int id;
    private int idGimnasioRoot;
    private int idMenu2;
    @Column(name = "usernameAlta", length = 45)
    private String usernameAlta;
    private Date fechaAlta;

}
