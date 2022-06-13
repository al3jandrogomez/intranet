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
import org.springframework.format.annotation.DateTimeFormat;


import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
@Table(name="tblentradas")
//@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@idEntrada")
public class Entrada {

@Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer idEntrada;
private String noDocumento;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cveTipoDocumento")
private TiposDocumentos tipoDocumento;

@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
private Date fechaEntrada;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cveProveedor" )
private Proveedor proveedor;

@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cveTipoInversion")
private TiposInversiones tipoInversion;


@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "cvePrograma")
private Programa programa;

private String observaciones;
private String numeroPedido;
@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
private Date fechaPedido;
private String activo;
@CreationTimestamp
@Temporal(TemporalType.TIMESTAMP)
@Column(updatable = false)
private Date  fechaRegistro ;
@UpdateTimestamp
@Temporal(TemporalType.TIMESTAMP)
private Date fechaActualizacion;
public Integer getIdEntrada() {
	return idEntrada;
}
public void setIdEntrada(Integer idEntrada) {
	this.idEntrada = idEntrada;
}


public TiposDocumentos getTipoDocumento() {
	return tipoDocumento;
}
public void setTipoDocumento(TiposDocumentos tipoDocumento) {
	this.tipoDocumento = tipoDocumento;
}
public String getNoDocumento() {
	return noDocumento;
}
public void setNoDocumento(String noDocumento) {
	this.noDocumento = noDocumento;
}


public Date getFechaEntrada() {
	return fechaEntrada;
}
public void setFechaEntrada(Date fechaEntrada) {
	this.fechaEntrada = fechaEntrada;
}



public Proveedor getProveedor() {
	return proveedor;
}

public TiposInversiones getTipoInversion() {
	return tipoInversion;
}
public void setTipoInversion(TiposInversiones tipoInversion) {
	this.tipoInversion = tipoInversion;
}
public Programa getPrograma() {
	return programa;
}
public void setPrograma(Programa programa) {
	this.programa = programa;
}
public void setProveedor(Proveedor proveedor) {
	this.proveedor = proveedor;
}

public String getObservaciones() {
	return observaciones;
}
public void setObservaciones(String observaciones) {
	this.observaciones = observaciones;
}
public String getNumeroPedido() {
	return numeroPedido;
}
public void setNumeroPedido(String numeroPedido) {
	this.numeroPedido = numeroPedido;
}
public Date getFechaPedido() {
	return fechaPedido;
}
public void setFechaPedido(Date fechaPedido) {
	this.fechaPedido = fechaPedido;
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
