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
@Table(name="tblvehiculos")


public class Vehiculos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idVehiculo;
	private Integer cveTipoUso;
	private String numeroMotor;
	private String serie;
	private Integer cveMarcaVehiculo;
	private Integer cveTipoVehiculo;
	private Integer cveModelo;
	private String color;
	private Integer cveTipoAsignacion;
	private String cilindros;
	private String numeroInventario;
	private String placas;
	private String observaciones;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getIdVehiculo() {
		return idVehiculo;
	}
	public void setIdVehiculo(Integer idVehiculo) {
		this.idVehiculo = idVehiculo;
	}
	public Integer getCveTipoUso() {
		return cveTipoUso;
	}
	public void setCveTipoUso(Integer cveTipoUso) {
		this.cveTipoUso = cveTipoUso;
	}
	public String getNumeroMotor() {
		return numeroMotor;
	}
	public void setNumeroMotor(String numeroMotor) {
		this.numeroMotor = numeroMotor;
	}
	public String getSerie() {
		return serie;
	}
	public void setSerie(String serie) {
		this.serie = serie;
	}
	public Integer getCveMarcaVehiculo() {
		return cveMarcaVehiculo;
	}
	public void setCveMarcaVehiculo(Integer cveMarcaVehiculo) {
		this.cveMarcaVehiculo = cveMarcaVehiculo;
	}
	public Integer getCveTipoVehiculo() {
		return cveTipoVehiculo;
	}
	public void setCveTipoVehiculo(Integer cveTipoVehiculo) {
		this.cveTipoVehiculo = cveTipoVehiculo;
	}
	public Integer getCveModelo() {
		return cveModelo;
	}
	public void setCveModelo(Integer cveModelo) {
		this.cveModelo = cveModelo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getCveTipoAsignacion() {
		return cveTipoAsignacion;
	}
	public void setCveTipoAsignacion(Integer cveTipoAsignacion) {
		this.cveTipoAsignacion = cveTipoAsignacion;
	}
	public String getCilindros() {
		return cilindros;
	}
	public void setCilindros(String cilindros) {
		this.cilindros = cilindros;
	}
	public String getNumeroInventario() {
		return numeroInventario;
	}
	public void setNumeroInventario(String numeroInventario) {
		this.numeroInventario = numeroInventario;
	}
	public String getPlacas() {
		return placas;
	}
	public void setPlacas(String placas) {
		this.placas = placas;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
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
