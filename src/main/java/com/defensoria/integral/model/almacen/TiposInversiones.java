package com.defensoria.integral.model.almacen;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "tbltiposinversiones")

public class TiposInversiones {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveTipoInversion;
	private String descTipoInversion;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tipoInversion", cascade = CascadeType.ALL, 
	        orphanRemoval = false)
	private List<Entrada> entrada;
	public Integer getCveTipoInversion() {
		return cveTipoInversion;
	}
	public void setCveTipoInversion(Integer cveTipoInversion) {
		this.cveTipoInversion = cveTipoInversion;
	}
	public String getDescTipoInversion() {
		return descTipoInversion;
	}
	public void setDescTipoInversion(String descTipoInversion) {
		this.descTipoInversion = descTipoInversion;
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
	public List<Entrada> getEntrada() {
		return entrada;
	}
	public void setEntrada(List<Entrada> entrada) {
		this.entrada = entrada;
	}
	
	
	
	

}
