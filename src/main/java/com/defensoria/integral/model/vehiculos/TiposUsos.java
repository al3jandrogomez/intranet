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
@Table(name="tbltipousos")
public class TiposUsos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveTipoUso;
	private String descTipoUso;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCveTipoUso() {
		return cveTipoUso;
	}
	public void setCveTipoUso(Integer cveTipoUso) {
		this.cveTipoUso = cveTipoUso;
	}
	public String getDescTipoUso() {
		return descTipoUso;
	}
	public void setDescTipoUso(String descTipoUso) {
		this.descTipoUso = descTipoUso;
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
