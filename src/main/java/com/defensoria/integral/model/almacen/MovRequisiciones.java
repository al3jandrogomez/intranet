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


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tblmovrequisiciones")

public class MovRequisiciones {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer idMovRequisicion;
	@JsonIgnore
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idRequisicion")
	private Requisiciones requisicion;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveEstatusRequisicion")
	
	private EstatusRequisiciones estatusRequisicion;
	
	private String activo;
	private Integer cveUsuario;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	private String observaciones;
	public Integer getIdMovRequisicion() {
		return idMovRequisicion;
	}
	public void setIdMovRequisicion(Integer idMovRequisicion) {
		this.idMovRequisicion = idMovRequisicion;
	}
	
	
	public Requisiciones getRequisicion() {
		return requisicion;
	}
	public void setRequisicion(Requisiciones requisicion) {
		this.requisicion = requisicion;
	}
	
	public EstatusRequisiciones getEstatusRequisicion() {
		return estatusRequisicion;
	}
	public void setEstatusRequisicion(EstatusRequisiciones estatusRequisicion) {
		this.estatusRequisicion = estatusRequisicion;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public Integer getCveUsuario() {
		return cveUsuario;
	}
	public void setCveUsuario(Integer cveUsuario) {
		this.cveUsuario = cveUsuario;
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
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	
	

}
