package com.defensoria.integral.model.adminpersonal;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
@Entity
@Table(name = "tblpuestos")
public class Puesto {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int cvePuesto;
	private String descPuesto;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	@OneToMany(mappedBy = "adscripcion")
        @JsonIgnore
	private List<MovAdscripciones> movAdscripciones;
	public int getCvePuesto() {
		return cvePuesto;
	}
	public void setCvePuesto(int cvePuesto) {
		this.cvePuesto = cvePuesto;
	}
	public String getDescPuesto() {
		return descPuesto;
	}
	public void setDescPuesto(String descPuesto) {
		this.descPuesto = descPuesto;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	public Date getFechaRegistro() {
		return fechaRegistro;
	}
	public void setFechaRegistro(Date fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}
	public Date getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(Date fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
	public List<MovAdscripciones> getMovAdscripciones() {
		return movAdscripciones;
	}
	public void setMovAdscripciones(List<MovAdscripciones> movAdscripciones) {
		this.movAdscripciones = movAdscripciones;
	}
	
	
	
	

}
