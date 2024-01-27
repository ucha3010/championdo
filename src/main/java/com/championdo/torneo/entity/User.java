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
	private int idPais;
	private Date fechaAlta;
	private Date fechaModificacion;

	@Column(name = "usernameModificacion", length = 45)
	private String usernameModificacion;

	@Column(name = "correo", length = 100)
	private String correo;

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

	@Column(name = "telefono", length = 20)
	private String telefono;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<>();

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
				", idPais=" + idPais +
				", fechaAlta=" + fechaAlta +
				", fechaModificacion=" + fechaModificacion +
				", usernameModificacion='" + usernameModificacion + '\'' +
				", correo='" + correo + '\'' +
				", domicilioCalle='" + domicilioCalle + '\'' +
				", domicilioNumero='" + domicilioNumero + '\'' +
				", domicilioOtros='" + domicilioOtros + '\'' +
				", domicilioLocalidad='" + domicilioLocalidad + '\'' +
				", domicilioCp='" + domicilioCp + '\'' +
				", telefono='" + telefono + '\'' +
				", userRole=" + userRole +
				'}';
	}
}
