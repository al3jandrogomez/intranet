package com.defensoria.integral.model.almacen;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="tbltiposcontadores")

public class TiposContadores {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveTipoContador;
	private String descTipoContador;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	@JsonIgnore
	@OneToMany(mappedBy = "tipoContador")
	private List <Contadores> contador ;
	public Integer getCveTipoContador() {
		return cveTipoContador;
	}
	public void setCveTipoContador(Integer cveTipoContador) {
		this.cveTipoContador = cveTipoContador;
	}
	
	public String getDescTipoContador() {
		return descTipoContador;
	}
	public void setDescTipoContador(String descTipoContador) {
		this.descTipoContador = descTipoContador;
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
