package com.defensoria.integral.model.adminpersonal;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblpermisosperfiles")
public class PermisosPerfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPermisoPerfil;
	@JsonIgnore
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cvePerfil")
	private Perfiles perfila;
        
       
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveFormulario")
	private Formularios formulario;

	private String crear;
	private String borrar;
	private String buscar;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	public Integer getIdPermisoPerfil() {
		return idPermisoPerfil;
	}

	public void setIdPermisoPerfil(Integer idPermisoPerfil) {
		this.idPermisoPerfil = idPermisoPerfil;
	}

    public Perfiles getPerfila() {
        return perfila;
    }

    public void setPerfila(Perfiles perfila) {
        this.perfila = perfila;
    }

	

	public Formularios getFormulario() {
		return formulario;
	}

	public void setFormulario(Formularios formulario) {
		this.formulario = formulario;
	}

	public String getCrear() {
		return crear;
	}

	public void setCrear(String crear) {
		this.crear = crear;
	}

	public String getBorrar() {
		return borrar;
	}

	public void setBorrar(String borrar) {
		this.borrar = borrar;
	}

	public String getBuscar() {
		return buscar;
	}

	public void setBuscar(String buscar) {
		this.buscar = buscar;
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
