package com.defensoria.integral.model.sigedepu2;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="tblleyendas")

public class Leyenda {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveLeyenda;
	private String descLeyenda;
	private Integer anioLeyenda;
	private String activo ;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCveLeyenda() {
		return cveLeyenda;
	}
	public void setCveLeyenda(Integer cveLeyenda) {
		this.cveLeyenda = cveLeyenda;
	}
	public String getDescLeyenda() {
		return descLeyenda;
	}
	public void setDescLeyenda(String descLeyenda) {
		this.descLeyenda = descLeyenda;
	}
	public Integer getAnioLeyenda() {
		return anioLeyenda;
	}
	public void setAnioLeyenda(Integer anioLeyenda) {
		this.anioLeyenda = anioLeyenda;
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
	
	

}
