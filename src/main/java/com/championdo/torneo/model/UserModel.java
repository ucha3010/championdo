package com.championdo.torneo.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class UserModel {

	private String username;
	private String password;
	private boolean enabled;
	private String name;
	private String lastname;
	private String secondLastname;
	private String sexo;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date fechaNacimiento;
	private CalidadModel calidad;
	private PaisModel pais;
	private CinturonModel cinturon;
	private Date fechaAlta;
	private Date fechaModificacion;
	private String usernameModificacion;
	private String correo;
	private boolean menor;
	private String dniMenor;
	private String usernameACargo;
	private String domicilioCalle;
	private String domicilioNumero;
	private String domicilioOtros;
	private String domicilioLocalidad;
	private String domicilioCp;
	private boolean inclusivo;
	private boolean licencia;
	private String menorEntreCategorias;
	private String telefono;
	private boolean autorizaWhatsApp;
	private boolean domiciliacion;
	private int idTorneo;
	private int idTorneoGimnasio;
	private int codigoGimnasio;
	private List<String> userRoles = new ArrayList<>();

	@Override
	public String toString() {
		return "UserModel{" +
				"username='" + username + '\'' +
				", enabled=" + enabled +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", secondLastname='" + secondLastname + '\'' +
				", sexo='" + sexo + '\'' +
				", fechaNacimiento=" + fechaNacimiento +
				", calidad=" + calidad +
				", pais=" + pais +
				", cinturon=" + cinturon +
				", fechaAlta=" + fechaAlta +
				", fechaModificacion=" + fechaModificacion +
				", usernameModificacione='" + usernameModificacion + '\'' +
				", correo='" + correo + '\'' +
				", menor=" + menor +
				", dniMenor='" + dniMenor + '\'' +
				", usernameACargo='" + usernameACargo + '\'' +
				", domicilioCalle='" + domicilioCalle + '\'' +
				", domicilioNumero='" + domicilioNumero + '\'' +
				", domicilioOtros='" + domicilioOtros + '\'' +
				", domicilioLocalidad='" + domicilioLocalidad + '\'' +
				", domicilioCp='" + domicilioCp + '\'' +
				", inclusivo=" + inclusivo +
				", licencia=" + licencia +
				", menorEntreCategorias='" + menorEntreCategorias + '\'' +
				", telefono='" + telefono + '\'' +
				", autorizaWhatsApp=" + autorizaWhatsApp +
				", domiciliacion=" + domiciliacion +
				", idTorneo=" + idTorneo +
				", idTorneoGimnasio=" + idTorneoGimnasio +
				", codigoGimnasio=" + codigoGimnasio +
				", userRoles=" + userRoles +
				'}';
	}
}
