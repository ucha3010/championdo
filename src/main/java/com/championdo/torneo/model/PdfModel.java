package com.championdo.torneo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class PdfModel {

    private String nombre; //Nombre Autorizador Apellido1dor Apellido2dor
    private String dni; //22222222A
    private String fechaNacimiento; //30/10/1960
    private String domicilio; //calle Mayor 150
    private String localidad; //Madrid (28003) - España
    private String telefono; //654654654
    private String correo; //pepe@pepe.com
    private String gimnasio; //Championdo
    private String direccionGimnasio; //Av Viñuelas 30 Tres Cantos (28760)
    private String nombreCampeonato; //CAMPEONATO DE TRES CANTOS
    private String fechaCampeonato; //20/12/2022
    private String direccionCampeonato; //Polideportivo La Luz
    private String calidadDe; //padre
    private String nombreMenor; //Nombre Autorizado Apellido1do Apellido2do
    private String dniMenor; //01234567A
    private String fechaNacimientoMenor; //30/10/1960
    private boolean cinturonBlanco; //true
    private boolean mayorEdad; //false
    private boolean inclusivo;
    private String cinturonActual; //Amarillo Naranja
    private int idInscripcion;
    private String categoria;
    private String poomsae;
    private String seccion;
    private String extension;
    private CuentaBancariaModel cuentaBancaria;
}
