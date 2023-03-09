package com.championdo.torneo.model;

public class UserAutorizacionModel {

    private UserModel mayorAutorizador;
    private UserModel autorizado;

    private CuentaBancariaModel cuentaBancaria;

    public UserAutorizacionModel (){
    }

    public UserAutorizacionModel (UserModel mayorAutorizador){
        this.mayorAutorizador = mayorAutorizador;
    }


    public UserModel getMayorAutorizador() {
        return mayorAutorizador;
    }

    public void setMayorAutorizador(UserModel mayorAutorizador) {
        this.mayorAutorizador = mayorAutorizador;
    }

    public UserModel getAutorizado() {
        return autorizado;
    }

    public void setAutorizado(UserModel autorizado) {
        this.autorizado = autorizado;
    }

    public CuentaBancariaModel getCuentaBancaria() {
        return cuentaBancaria;
    }

    public void setCuentaBancaria(CuentaBancariaModel cuentaBancaria) {
        this.cuentaBancaria = cuentaBancaria;
    }

    @Override
    public String toString() {
        return "UserAutorizacionModel{" +
                "mayorAutorizador=" + mayorAutorizador +
                ", autorizado=" + autorizado +
                ", cuentaBancaria=" + cuentaBancaria +
                '}';
    }
}
