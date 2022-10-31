package com.championdo.torneo.model;

public class UserAutorizacionModel {

    private UserModel mayorAutorizador;
    private UserModel autorizado;

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

    @Override
    public String toString() {
        return "UserAutorizacionModel{" +
                "mayorAutorizador=" + mayorAutorizador +
                ", autorizado=" + autorizado +
                '}';
    }
}
