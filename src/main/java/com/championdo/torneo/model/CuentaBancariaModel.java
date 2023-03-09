package com.championdo.torneo.model;

public class CuentaBancariaModel {

    private int id;
    private String titular;
    private String iban;
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
        return "CuentaBancariaModel{" +
                "id=" + id +
                ", titular='" + titular + '\'' +
                ", iban='" + iban + '\'' +
                ", swift='" + swift + '\'' +
                '}';
    }
}
