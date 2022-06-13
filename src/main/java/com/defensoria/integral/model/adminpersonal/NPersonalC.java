package com.defensoria.integral.model.adminpersonal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class NPersonalC {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveUsuario;
	private String cveServidorPub;
	private String nombreCompleto;
	private String activo;
	private Integer cveModulo;
	public Integer getCveUsuario() {
		return cveUsuario;
	}
	public void setCveUsuario(Integer cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
	public String getCveServidorPub() {
		return cveServidorPub;
	}
	public void setCveServidorPub(String cveServidorPub) {
		this.cveServidorPub = cveServidorPub;
	}
	public String getNombreCompleto() {
		return nombreCompleto;
	}
	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Integer getCveModulo() {
		return cveModulo;
	}
	public void setCveModulo(Integer cveModulo) {
		this.cveModulo = cveModulo;
	}
	
	
	
	

}
