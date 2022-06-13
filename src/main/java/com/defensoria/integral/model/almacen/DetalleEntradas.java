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
@Table(name="tbldetalleentradas")
public class DetalleEntradas {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idDetalleEntrada;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idEntrada")
	private Entrada entrada;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveArticulo")
	private Articulos articulo;
	private Integer cantidad;
	private double precio;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getIdDetalleEntrada() { 
		return idDetalleEntrada;
	}
	public void setIdDetalleEntrada(Integer idDetalleEntrada) {
		this.idDetalleEntrada = idDetalleEntrada;
	}
	
	public Entrada getEntrada() {
		return entrada;
	}
	public void setEntrada(Entrada entrada) {
		this.entrada = entrada;
	}
	
	public Articulos getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulos articulo) {
		this.articulo = articulo;
	}
	public Integer getCantidad() {
		return cantidad;
	}
	public void setCantidad(Integer cantidad) {
		this.cantidad = cantidad;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
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
