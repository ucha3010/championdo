package com.championdo.torneo.entity;

import javax.persistence.*;

@Entity
@Table(name = "util")
public class Util {

    @Id
    @Column(name = "clave", unique = true, nullable = false, length = 30)
    private String clave;

    @Column(name = "valor", nullable = false, length = 200)
    private String valor;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Util{" +
                "clave='" + clave + '\'' +
                ", valor='" + valor + '\'' +
                '}';
    }
}
