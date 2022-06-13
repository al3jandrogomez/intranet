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
@Table(name="tblpartidas")
public class Partida {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cvePartida;
	private String clavePartida;
	private String descPartida;
	private String activo;
	@JsonIgnore
	@OneToMany(mappedBy = "partidas") 
	private List <Articulos> articulo ;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCvePartida() {
		return cvePartida;
	}
	public void setCvePartida(Integer cvePartida) {
		this.cvePartida = cvePartida;
	}
	public String getClavePartida() {
		return clavePartida;
	}
	public void setClavePartida(String clavePartida) {
		this.clavePartida = clavePartida;
	}
	public String getDescPartida() {
		return descPartida;
	}
	public void setDescPartida(String descPartida) {
		this.descPartida = descPartida;
	}
	public String getActivo() {
		return activo;
	}
	public void setActivo(String activo) {
		this.activo = activo;
	}
	
	public List<Articulos> getArticulo() {
		return articulo;
	}
	public void setArticulo(List<Articulos> articulo) {
		this.articulo = articulo;
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
