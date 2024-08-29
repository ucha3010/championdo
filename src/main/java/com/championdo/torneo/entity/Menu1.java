package com.championdo.torneo.entity;

import jakarta.persistence.*;
import lombok.*;

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
