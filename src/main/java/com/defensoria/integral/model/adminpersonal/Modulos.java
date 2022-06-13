package com.defensoria.integral.model.adminpersonal;

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
@Table(name="tblmodulos")
public class Modulos {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer cveModulo;
	private String descModulo;
	private String rutaImagen;
	@JsonIgnore
	@OneToMany(mappedBy="modulos")
	private List<Formularios> formularios;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCveModulo() {
		return cveModulo;
	}
	public void setCveModulo(Integer cveModulo) {
		this.cveModulo = cveModulo;
	}
	public String getDescModulo() {
		return descModulo;
	}
	public void setDescModulo(String descModulo) {
		this.descModulo = descModulo;
	}
	public String getRutaImagen() {
		return rutaImagen;
	}
	public void setRutaImagen(String rutaImagen) {
		this.rutaImagen = rutaImagen;
	}
	public List<Formularios> getFormularios() {
		return formularios;
	}
	public void setFormularios(List<Formularios> formularios) {
		this.formularios = formularios;
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
