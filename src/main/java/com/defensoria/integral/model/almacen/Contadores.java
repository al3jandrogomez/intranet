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

import com.defensoria.integral.model.adminpersonal.Adscripciones;
@Entity
@Table(name="tblcontadores")
public class Contadores {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idContador;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveTipoContador")
	private TiposContadores tipoContador;
	private Integer numero;
	private Integer anio;
	private String activo;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveAdscripcion")
	private Adscripciones adscripcion;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getIdContador() {
		return idContador;
	}
	public void setIdContador(Integer idContador) {
		this.idContador = idContador;
	}
	
	public TiposContadores getTipoContador() {
		return tipoContador;
	}
	public void setTipoContador(TiposContadores tipoContador) {
		this.tipoContador = tipoContador;
	}
	public Integer getNumero() {
		return numero;
	}
	public void setNumero(Integer numero) {
		this.numero = numero;
	}
	public Integer getAnio() {
		return anio;
	}
	public void setAnio(Integer anio) {
		this.anio = anio;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	
	public Adscripciones getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(Adscripciones adscripcion) {
		this.adscripcion = adscripcion;
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
	
	
	

}
