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
@Table(name = "tblperfiles")
public class Perfiles {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cvePerfil;
	private String descPerfil;
        
        
	
	@JsonIgnore
	@OneToMany (mappedBy="perfil")
	private List<PersonalPerfiles> personalPerfiles;
	
//	@JsonIgnore
	@OneToMany (mappedBy="perfila")
	private List<PermisosPerfiles> permisosPerfiles;
        
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	public Integer getCvePerfil() {
		return cvePerfil;
	}
	public void setCvePerfil(Integer cvePerfil) {
		this.cvePerfil = cvePerfil;
	}
	public String getDescPerfil() {
		return descPerfil;
	}
	public void setDescPerfil(String descPerfil) {
		this.descPerfil = descPerfil;
	}
	public List<PersonalPerfiles> getPersonalPerfiles() {
		return personalPerfiles;
	}
	public void setPersonalPerfiles(List<PersonalPerfiles> personalPerfiles) {
		this.personalPerfiles = personalPerfiles;
	}
	public List<PermisosPerfiles> getPermisosPerfiles() {
		return permisosPerfiles;
	}
	public void setPermisosPerfiles(List<PermisosPerfiles> permisosPerfiles) {
		this.permisosPerfiles = permisosPerfiles;
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
