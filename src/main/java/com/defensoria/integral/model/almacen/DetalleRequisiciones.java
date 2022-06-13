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
@Table(name="tbldetallerequisiciones")
public class DetalleRequisiciones {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idDetalleRequisicion;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="idRequisicion")
	private Requisiciones requisicion;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveArticulo")
	private Articulos articulo;
	
	private Integer cantidadRequerida;
	private Integer cantidadAutorizada;
	private String observaciones;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getIdDetalleRequisicion() {
		return idDetalleRequisicion;
	}
	public void setIdDetalleRequisicion(Integer idDetalleRequisicion) {
		this.idDetalleRequisicion = idDetalleRequisicion;
	}
	
	public Requisiciones getRequisicion() {
		return requisicion;
	}
	public void setRequisicion(Requisiciones requisicion) {
		this.requisicion = requisicion;
	}
	
	public Articulos getArticulo() {
		return articulo;
	}
	public void setArticulo(Articulos articulo) {
		this.articulo = articulo;
	}
	public Integer getCantidadRequerida() {
		return cantidadRequerida;
	}
	public void setCantidadRequerida(Integer cantidadRequerida) {
		this.cantidadRequerida = cantidadRequerida;
	}
	public Integer getCantidadAutorizada() {
		return cantidadAutorizada;
	}
	public void setCantidadAutorizada(Integer cantidadAutorizada) {
		this.cantidadAutorizada = cantidadAutorizada;
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
