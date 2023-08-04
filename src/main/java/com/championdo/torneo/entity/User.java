package com.championdo.torneo.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {
	
	@Id
	@Column(name = "username", unique = true, nullable = false, length = 45)
	private String username;
	
	@Column(name = "password", nullable = false, length = 60)
	private String password;
	
	@Column(name = "enabled", nullable = false)
	private boolean enabled;
	
	@Column(name = "name", nullable = true, length = 60)
	private String name;
	
	@Column(name = "lastname", nullable = true, length = 60)
	private String lastname;
	
	@Column(name = "secondLastname", nullable = true, length = 60)
	private String secondLastname;

	@Column(name = "sexo", nullable = true, length = 9)
	private String sexo;

	@Column(name = "fechaNacimiento", nullable = true)
	private Date fechaNacimiento;

	@Column(name = "idGimnasio")
	private int idGimnasio;

	@Column(name = "idCalidad")
	private int idCalidad;

	@Column(name = "idPais")
	private int idPais;

	@Column(name = "idCinturon")
	private int idCinturon;

	@Column(name = "fechaAlta", nullable = true)
	private Date fechaAlta;

	@Column(name = "fechaModificacion", nullable = true)
	private Date fechaModificacion;

	@Column(name = "usernameModificacione", nullable = true, length = 45)
	private String usernameModificacione;

	@Column(name = "correo", nullable = true, length = 100)
	private String correo;

	@Column(name = "menor", nullable = false)
	private boolean menor;

	@Column(name = "dniMenor", nullable = true, length = 15)
	private String dniMenor;

	@Column(name = "usernameACargo", nullable = true, length = 45)
	private String usernameACargo;

	@Column(name = "domicilioCalle", nullable = true, length = 100)
	private String domicilioCalle;

	@Column(name = "domicilioNumero", nullable = true, length = 30)
	private String domicilioNumero;

	@Column(name = "domicilioOtros", nullable = true, length = 50)
	private String domicilioOtros;

	@Column(name = "domicilioLocalidad", nullable = true, length = 50)
	private String domicilioLocalidad;

	@Column(name = "domicilioCp", nullable = true, length = 10)
	private String domicilioCp;

	@Column(name = "inclusivo", nullable = false)
	private boolean inclusivo;
	@Column(name = "telefono", nullable = true, length = 20)
	private String telefono;
	
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

}
