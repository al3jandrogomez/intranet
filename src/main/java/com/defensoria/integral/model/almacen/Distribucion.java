package com.defensoria.integral.model.almacen;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;

@Entity
@Table(name="tbldistribucion")
public class Distribucion {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idDistribucion;
	private Integer idRequisicion;
	private Integer numeroDistribucion;
	private Integer anioDistribucion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="cveAdscripcion")
	private Adscripciones adscripcion;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveUsuario")
	private Personal personal;
	private String activo;
	private String observaciones;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getIdDistribucion() {
		return idDistribucion;
	}
	public void setIdDistribucion(Integer idDistribucion) {
		this.idDistribucion = idDistribucion;
	}
	
	public Adscripciones getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(Adscripciones adscripcion) {
		this.adscripcion = adscripcion;
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
	public Integer getNumeroDistribucion() {
		return numeroDistribucion;
	}
	public void setNumeroDistribucion(Integer numeroDistribucion) {
		this.numeroDistribucion = numeroDistribucion;
	}
	public Integer getAnioDistribucion() {
		return anioDistribucion;
	}
	public void setAnioDistribucion(Integer anioDistribucion) {
		this.anioDistribucion = anioDistribucion;
	}
	public Personal getPersonal() {
		return personal;
	}
	public void setPersonal(Personal personal) {
		this.personal = personal;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Integer getIdRequisicion() {
		return idRequisicion;
	}
	public void setIdRequisicion(Integer idRequisicion) {
		this.idRequisicion = idRequisicion;
	}
	
	
	
	
	
	
	

}
