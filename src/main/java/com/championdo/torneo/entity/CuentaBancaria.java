package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "cuenta_bancaria")
public class CuentaBancaria {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "titular", nullable = true, length = 100)
    private String titular;

    @Column(name = "iban", nullable = true, length = 34)
    private String iban;

    @Column(name = "swift", nullable = true, length = 11)
    private String swift;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getSwift() {
        return swift;
    }

    public void setSwift(String swift) {
        this.swift = swift;
    }

    @Override
    public String toString() {
        return "CuentaBancaria{" +
                "id=" + id +
                ", titular='" + titular + '\'' +
                ", iban='" + iban + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}
