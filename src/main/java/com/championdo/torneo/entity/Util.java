package com.championdo.torneo.entity;

import com.championdo.torneo.util.Utils;
import lombok.*;

import javax.persistence.*;

@Entity
@IdClass(UtilId.class)
@Table(name = "util")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Util {

    @Id
    @Column(name = "clave", nullable = false, length = 100)
    private String clave;
    @Column(name = "valor", nullable = false, length = 200)
    private String valor;
    @Id
    private int codigoGimnasio;

    @Override
    public String toString() {
        return "Util{" +
                "clave='" + clave + '\'' +
                ", valor='" + Utils.ofuscar(valor) + '\'' +
                ", codigoGimnasio=" + codigoGimnasio +
                '}';
    }
}
