package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "tournament_registration")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class TournamentRegistration {

    @Id
    @GeneratedValue
    private int id;
    private boolean registrationAdult;
    private boolean registrationYoung;
    private boolean registrationInclusive;
    private Date registrationDate;
    @Column(name = "tournamentDate", length = 10)
    private String tournamentDate;
    @Column(name = "tournamentName", length = 200)
    private String tournamentName;
    @Column(name = "tournamentAddress", length = 200)
    private String tournamentAddress;
    @Column(name = "category", length = 45)
    private String category;
    private int idGym;
    private int idTournament;

    //Registered user
    @Column(name = "registeredName", length = 60)
    private String registeredName;
    @Column(name = "registered1Lastname", length = 60)
    private String registered1Lastname;
    @Column(name = "registered2Lastname", length = 60)
    private String registered2Lastname;
    @Column(name = "registeredIdCard", length = 45)
    private String registeredIdCard;
    private Date registeredBirthdate;
    @Column(name = "registeredSex", nullable = false, length = 9)
    private String registeredSex;
    @Column(name = "registeredAddressStreet", length = 100)
    private String registeredAddressStreet;
    @Column(name = "registeredAddressNumber", length = 30)
    private String registeredAddressNumber;
    @Column(name = "registeredAddressOther", length = 50)
    private String registeredAddressOther;
    @Column(name = "registeredAddressCity", length = 50)
    private String registeredAddressCity;
    @Column(name = "registeredAddressPostcode", length = 10)
    private String registeredAddressPostcode;
    @Column(name = "gym", length = 100)
    private String gym;
    @Column(name = "country", length = 20)
    private String country;
    @Column(name = "belt", length = 40)
    private String belt;
    @Column(name = "poomsae", length = 50)
    private String poomsae;

    //Authorizer user
    @Column(name = "authorizerName", length = 60)
    private String authorizerName;
    @Column(name = "authorizer1Lastname", length = 60)
    private String authorizer1Lastname;
    @Column(name = "authorizer2Lastname", length = 60)
    private String authorizer2Lastname;
    @Column(name = "authorizerIdCard", length = 45)
    private String authorizerIdCard;
    @Column(name = "relationship", length = 20)
    private String relationship;
    @Column(name = "authorizerAddressStreet", length = 100)
    private String authorizerAddressStreet;
    @Column(name = "authorizerAddressNumber", length = 30)
    private String authorizerAddressNumber;
    @Column(name = "authorizerAddressOther", length = 50)
    private String authorizerAddressOther;
    @Column(name = "authorizerAddressCity", length = 50)
    private String authorizerAddressCity;
    @Column(name = "authorizerAddressPostcode", length = 10)
    private String authorizerAddressPostcode;

    //Payment data
    private boolean paid;
    private Date paymentDate;
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

}
