package com.championdo.torneo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserAutorizacionModel {

    private UserModel mayorAutorizador;
    private UserModel autorizado;
    private CuentaBancariaModel cuentaBancaria;

    public UserAutorizacionModel (UserModel mayorAutorizador){
        this.mayorAutorizador = mayorAutorizador;
    }
}
