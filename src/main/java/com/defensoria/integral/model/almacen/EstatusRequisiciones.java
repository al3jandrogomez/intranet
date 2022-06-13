package com.defensoria.integral.model.almacen;

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
@Table(name="tblestatusrequisiciones")

public class EstatusRequisiciones {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveEstatusRequisicion;
	private String descEstatusRequisicion;
	private String activo;
	
	
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	
	public Integer getCveEstatusRequisicion() {
		return cveEstatusRequisicion;
	}
	public void setCveEstatusRequisicion(Integer cveEstatusRequisicion) {
		this.cveEstatusRequisicion = cveEstatusRequisicion;
	}
	public String getDescEstatusRequisicion() {
		return descEstatusRequisicion;
	}
	public void setDescEstatusRequisicion(String descEstatusRequisicion) {
		this.descEstatusRequisicion = descEstatusRequisicion;
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
