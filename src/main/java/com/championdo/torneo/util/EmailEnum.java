package com.championdo.torneo.util;

public enum EmailEnum {//TODO DAMIAN Falta hacer configuración para que ADMIN pueda seleccionar el proveeror y con eso relleno los datos de acá
    VACIO("Seleccionar proveedor","",""),
    GMAIL("Gmail","smtp.gmail.com","587"),
    OFFICE365("Office 365","smtp.office365.com","587"),
    YAHOO("Office 365","smtp.mail.yahoo.com","587");

    private final String proveedor;
    private final String host;
    private final String port;

    EmailEnum(String proveedor, String host, String port) {
        this.proveedor = proveedor;
        this.host = host;
        this.port = port;
    }

    public String getProveedor() {
        return proveedor;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }
}
