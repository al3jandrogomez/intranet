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

@Entity
@Table(name="tblarticulos")
public class Articulos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveArticulo;
	private String descArticulo;
	private String especificacion;
	private String capacidad;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveUnidad")
	private Unidades unidades;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveMarcaAlm")
	private Marcas marcas;
	private String clave;
	private String version;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cvePartida")
	private Partida partidas;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCveArticulo() {
		return cveArticulo;
	}
	public void setCveArticulo(Integer cveArticulo) {
		this.cveArticulo = cveArticulo;
	}
	public String getDescArticulo() {
		return descArticulo;
	}
	public void setDescArticulo(String descArticulo) {
		this.descArticulo = descArticulo;
	}
	public String getEspecificacion() {
		return especificacion;
	}
	public void setEspecificacion(String especificacion) {
		this.especificacion = especificacion;
	}
	public String getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(String capacidad) {
		this.capacidad = capacidad;
	}
	public Unidades getUnidades() {
		return unidades;
	}
	public void setUnidades(Unidades unidades) {
		this.unidades = unidades;
	}
	public Marcas getMarcas() {
		return marcas;
	}
	public void setMarcas(Marcas marcas) {
		this.marcas = marcas;
	}
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public Partida getPartidas() {
		return partidas;
	}
	public void setPartidas(Partida partidas) {
		this.partidas = partidas;
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
