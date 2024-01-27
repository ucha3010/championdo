package com.championdo.torneo.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "user_gym")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserGym {

    @Id
    @SequenceGenerator(name = "userGimGenerator", sequenceName = "CHANGE_LOW_SEQUENCE", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userGymGenerator")
    private int id;
    @Column(name = "username", nullable = false, length = 45)
    private String username;
    private int idGym;
    @Column(name = "usernameAdd", length = 45)
    private String usernameAdd;
    private Date dateAdd;

}
