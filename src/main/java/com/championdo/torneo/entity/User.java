package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User {
	
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "name", length = 60)
	private String name;
	
	@Column(name = "lastname", length = 60)
	private String lastname;
	
	@Column(name = "secondLastname", length = 60)
	private String secondLastname;

	@Column(name = "sexo", length = 9)
	private String sexo;
	private Date fechaNacimiento;
	private int idGimnasio;
	private int idCalidad;
	private int idPais;
	private int idCinturon;
	private Date fechaAlta;
	private Date fechaModificacion;

	@Column(name = "usernameModificacione", length = 45)
	private String usernameModificacione;

	@Column(name = "correo", length = 100)
	private String correo;

	@Column(name = "menor", nullable = false)
	private boolean menor;

	@Column(name = "dniMenor", length = 15)
	private String dniMenor;

	@Column(name = "usernameACargo", length = 45)
	private String usernameACargo;

	@Column(name = "domicilioCalle", length = 100)
	private String domicilioCalle;

	@Column(name = "domicilioNumero", length = 30)
	private String domicilioNumero;

	@Column(name = "domicilioOtros", length = 50)
	private String domicilioOtros;

	@Column(name = "domicilioLocalidad", length = 50)
	private String domicilioLocalidad;

	@Column(name = "domicilioCp", length = 10)
	private String domicilioCp;

	@Column(name = "inclusivo", nullable = false)
	private boolean inclusivo;

	@Column(name = "telefono", length = 20)
	private String telefono;
	private int idTorneo;
	private int idTorneoGimnasio;
	private int codigoGimnasio;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<>();

	public User(String username, String password, boolean enabled, boolean menor, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.menor = menor;
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", enabled=" + enabled +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", secondLastname='" + secondLastname + '\'' +
				", sexo='" + sexo + '\'' +
				", fechaNacimiento=" + fechaNacimiento +
				", idGimnasio=" + idGimnasio +
				", idCalidad=" + idCalidad +
				", idPais=" + idPais +
				", idCinturon=" + idCinturon +
				", fechaAlta=" + fechaAlta +
				", fechaModificacion=" + fechaModificacion +
				", usernameModificacione='" + usernameModificacione + '\'' +
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
				", telefono='" + telefono + '\'' +
				", codigoGimnasio=" + codigoGimnasio +
				", userRole=" + userRole +
				'}';
	}
}
