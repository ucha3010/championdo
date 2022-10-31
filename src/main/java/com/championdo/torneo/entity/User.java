package com.championdo.torneo.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
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
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "user")
	private Set<UserRole> userRole = new HashSet<>();
	
	public User() {
		super();
	}

	public User(String username, String password, boolean enabled, boolean menor, Set<UserRole> userRole) {
		super();
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.menor = menor;
		this.userRole = userRole;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getSecondLastname() {
		return secondLastname;
	}

	public void setSecondLastname(String secondLastname) {
		this.secondLastname = secondLastname;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getIdGimnasio() {
		return idGimnasio;
	}

	public void setIdGimnasio(int idGimnasio) {
		this.idGimnasio = idGimnasio;
	}

	public int getIdCalidad() {
		return idCalidad;
	}

	public void setIdCalidad(int idCalidad) {
		this.idCalidad = idCalidad;
	}

	public int getIdPais() {
		return idPais;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public int getIdCinturon() {
		return idCinturon;
	}

	public void setIdCinturon(int idCinturon) {
		this.idCinturon = idCinturon;
	}

	public Date getFechaAlta() {
		return fechaAlta;
	}

	public void setFechaAlta(Date fechaAlta) {
		this.fechaAlta = fechaAlta;
	}

	public Date getFechaModificacion() {
		return fechaModificacion;
	}

	public void setFechaModificacion(Date fechaModificacion) {
		this.fechaModificacion = fechaModificacion;
	}

	public String getUsernameModificacione() {
		return usernameModificacione;
	}

	public void setUsernameModificacione(String usernameModificacione) {
		this.usernameModificacione = usernameModificacione;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public boolean isMenor() {
		return menor;
	}

	public void setMenor(boolean menor) {
		this.menor = menor;
	}

	public String getDniMenor() {
		return dniMenor;
	}

	public void setDniMenor(String dniMenor) {
		this.dniMenor = dniMenor;
	}

	public String getUsernameACargo() {
		return usernameACargo;
	}

	public void setUsernameACargo(String usernameACargo) {
		this.usernameACargo = usernameACargo;
	}

	public String getDomicilioCalle() {
		return domicilioCalle;
	}

	public void setDomicilioCalle(String domicilioCalle) {
		this.domicilioCalle = domicilioCalle;
	}

	public String getDomicilioNumero() {
		return domicilioNumero;
	}

	public void setDomicilioNumero(String domicilioNumero) {
		this.domicilioNumero = domicilioNumero;
	}

	public String getDomicilioOtros() {
		return domicilioOtros;
	}

	public void setDomicilioOtros(String domicilioOtros) {
		this.domicilioOtros = domicilioOtros;
	}

	public String getDomicilioLocalidad() {
		return domicilioLocalidad;
	}

	public void setDomicilioLocalidad(String domicilioLocalidad) {
		this.domicilioLocalidad = domicilioLocalidad;
	}

	public String getDomicilioCp() {
		return domicilioCp;
	}

	public void setDomicilioCp(String domicilioCp) {
		this.domicilioCp = domicilioCp;
	}

	public boolean isInclusivo() {
		return inclusivo;
	}

	public void setInclusivo(boolean inclusivo) {
		this.inclusivo = inclusivo;
	}

	public Set<UserRole> getUserRole() {
		return userRole;
	}

	public void setUserRole(Set<UserRole> userRole) {
		this.userRole = userRole;
	}

	@Override
	public String toString() {
		return "User{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
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
				", userRole=" + userRole +
				'}';
	}
}
