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

@Entity
@Table(name = "menu1")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Menu1 {

    @Id
    @SequenceGenerator(name = "menu1Generator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu1Generator")
    private int id;
    private boolean enabled;
    @Column(name = "nombre", length = 30)
    private String nombre;
    private int position;

}