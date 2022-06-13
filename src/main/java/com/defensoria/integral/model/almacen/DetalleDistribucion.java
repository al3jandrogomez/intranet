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

import com.defensoria.integral.model.adminpersonal.Personal;

@Entity
@Table(name = "tbldetalledistribucion")
public class DetalleDistribucion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idDetalleDistribucion;
	private Integer cantidadDistribuida;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveArticulo")
	private Articulos articulo;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDistribucion")
	private Distribucion distribucion;
//	private Integer cveUsuarioRecibe;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveUsuario")
	private Personal personal;
	private String activo;
//	private Date fechaEntrega;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	

	public Integer getIdDetalleDistribucion() {
		return idDetalleDistribucion;
	}

	public void setIdDetalleDistribucion(Integer idDetalleDistribucion) {
		this.idDetalleDistribucion = idDetalleDistribucion;
	}

	public Integer getCantidadDistribuida() {
		return cantidadDistribuida;
	}

	public void setCantidadDistribuida(Integer cantidadDistribuida) {
		this.cantidadDistribuida = cantidadDistribuida;
	}

	public Articulos getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulos articulo) {
		this.articulo = articulo;
	}

//	public Integer getCveUsuarioRecibe() {
//		return cveUsuarioRecibe;
//	}
//
//	public void setCveUsuarioRecibe(Integer cveUsuarioRecibe) {
//		this.cveUsuarioRecibe = cveUsuarioRecibe;
//	}
	
	

	public String getActivo() {
		return activo;
	}

	public Personal getPersonal() {
		return personal;
	}

	public void setPersonal(Personal personal) {
		this.personal = personal;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

//	public Date getFechaEntrega() {
//		return fechaEntrega;
//	}
//
//	public void setFechaEntrega(Date fechaEntrega) {
//		this.fechaEntrega = fechaEntrega;
//	}

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

	public Distribucion getDistribucion() {
		return distribucion;
	}

	public void setDistribucion(Distribucion distribucion) {
		this.distribucion = distribucion;
	}
	
	

	

	
	
	

}
