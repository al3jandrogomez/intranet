package com.defensoria.sigedepu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="residencia")
public class Residencia {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String nombre_residencia;
	private String domicilio;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre_residencia() {
		return nombre_residencia;
	}
	public void setNombre_residencia(String nombre_residencia) {
		this.nombre_residencia = nombre_residencia;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	
}
