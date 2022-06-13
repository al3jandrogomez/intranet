package com.defensoria.integral.model.vehiculos;

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
@Table(name="tbltiposasignaciones")
public class TiposAsignaciones {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveTipoAsignacion;
	private String descTipoAsignacion;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCveTipoAsignacion() {
		return cveTipoAsignacion;
	}
	public void setCveTipoAsignacion(Integer cveTipoAsignacion) {
		this.cveTipoAsignacion = cveTipoAsignacion;
	}
	public String getDescTipoAsignacion() {
		return descTipoAsignacion;
	}
	public void setDescTipoAsignacion(String descTipoAsignacion) {
		this.descTipoAsignacion = descTipoAsignacion;
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
