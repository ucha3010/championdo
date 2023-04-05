package com.championdo.torneo.model;

import com.championdo.torneo.entity.Calidad;
import com.championdo.torneo.entity.Cinturon;
import com.championdo.torneo.entity.Gimnasio;
import com.championdo.torneo.entity.Pais;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
	private GimnasioModel gimnasio;
	private CalidadModel calidad;
	private PaisModel pais;
	private CinturonModel cinturon;
	private Date fechaAlta;
	private Date fechaModificacion;
	private String usernameModificacione;
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
	private List<String> userRoles = new ArrayList<>();

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

	public GimnasioModel getGimnasio() {
		return gimnasio;
	}

	public void setGimnasio(GimnasioModel gimnasio) {
		this.gimnasio = gimnasio;
	}

	public CalidadModel getCalidad() {
		return calidad;
	}

	public void setCalidad(CalidadModel calidad) {
		this.calidad = calidad;
	}

	public PaisModel getPais() {
		return pais;
	}

	public void setPais(PaisModel pais) {
		this.pais = pais;
	}

	public CinturonModel getCinturon() {
		return cinturon;
	}

	public void setCinturon(CinturonModel cinturon) {
		this.cinturon = cinturon;
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

	public boolean isLicencia() {
		return licencia;
	}

	public void setLicencia(boolean licencia) {
		this.licencia = licencia;
	}

	public String getMenorEntreCategorias() {
		return menorEntreCategorias;
	}

	public void setMenorEntreCategorias(String menorEntreCategorias) {
		this.menorEntreCategorias = menorEntreCategorias;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public List<String> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<String> userRoles) {
		this.userRoles = userRoles;
	}

	@Override
	public String toString() {
		return "UserModel{" +
				"username='" + username + '\'' +
				", password='" + password + '\'' +
				", enabled=" + enabled +
				", name='" + name + '\'' +
				", lastname='" + lastname + '\'' +
				", secondLastname='" + secondLastname + '\'' +
				", sexo='" + sexo + '\'' +
				", fechaNacimiento=" + fechaNacimiento +
				", gimnasio=" + gimnasio +
				", calidad=" + calidad +
				", pais=" + pais +
				", cinturon=" + cinturon +
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
				", licencia=" + licencia +
				", menorEntreCategorias='" + menorEntreCategorias + '\'' +
				", telefono='" + telefono + '\'' +
				", userRoles=" + userRoles +
				'}';
	}
}
