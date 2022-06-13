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


@Entity
@Table(name = "tblpermisospersonal")
public class PermisosPersonal {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idPermisoPersonal;
	private Integer cveUsuario;

	
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cveFormulario")
	private Formularios formulario;
	private String activo;
	private String crear;
	private String borrar;
	private String buscar;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	public Integer getIdPermisoPersonal() {
		return idPermisoPersonal;
	}

	public void setIdPermisoPersonal(Integer idPermisoPersonal) {
		this.idPermisoPersonal = idPermisoPersonal;
	}

	public Integer getCveUsuario() {
		return cveUsuario;
	}

	public void setCveUsuario(Integer cveUsuario) {
		this.cveUsuario = cveUsuario;
	}

	public Formularios getFormulario() {
		return formulario;
	}

	public void setFormulario(Formularios formulario) {
		this.formulario = formulario;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
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
