package com.defensoria.integral.model.almacen;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@Table(name = "tbltiposdocumentos")

public class TiposDocumentos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveTipoDocumento;
	private String descTipoDocumento;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	@JsonIgnore
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "tipoDocumento", cascade = CascadeType.ALL, 
	        orphanRemoval = true)
	 
	private List<Entrada> entrada;
	public Integer getCveTipoDocumento() {
		return cveTipoDocumento;
	}
	public void setCveTipoDocumento(Integer cveTipoDocumento) {
		this.cveTipoDocumento = cveTipoDocumento;
	}
	public String getDescTipoDocumento() {
		return descTipoDocumento;
	}
	public void setDescTipoDocumento(String descTipoDocumento) {
		this.descTipoDocumento = descTipoDocumento;
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
	public List<Entrada> getEntrada() {
		return entrada;
	}
	public void setEntrada(List<Entrada> entrada) {
		this.entrada = entrada;
	}
	
	
	
	

}
