package com.defensoria.integral.model.almacen;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.defensoria.integral.model.adminpersonal.Adscripciones;




@Entity
@Table(name="tblrequisiciones")
public class Requisiciones {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idRequisicion;
	private Integer cveUsuario;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveAdscripcion")
	private Adscripciones adscripcion;
	private Integer numeroRequisicion;
	private Integer anioRequisicion;
	private String activo;
	
	@OneToMany(mappedBy="requisicion")
	@Where(clause="activo='S'")
	private List<MovRequisiciones> movRequisicion;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
	private String tipoRequisicion;
	private Integer idRequisicionPadre;

	
	
	public Integer getIdRequisicion() {
		return idRequisicion;
	}
	public void setIdRequisicion(Integer idRequisicion) {
		this.idRequisicion = idRequisicion;
	}
	public Integer getCveUsuario() {
		return cveUsuario;
	}
	public void setCveUsuario(Integer cveUsuario) {
		this.cveUsuario = cveUsuario;
	}
		
	public Adscripciones getAdscripcion() {
		return adscripcion;
	}
	public void setAdscripcion(Adscripciones adscripcion) {
		this.adscripcion = adscripcion;
	}
	public Integer getNumeroRequisicion() {
		return numeroRequisicion;
	}
	public void setNumeroRequisicion(Integer numeroRequisicion) {
		this.numeroRequisicion = numeroRequisicion;
	}
	public Integer getAnioRequisicion() {
		return anioRequisicion;
	}
	public void setAnioRequisicion(Integer anioRequisicion) {
		this.anioRequisicion = anioRequisicion;
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
	public List<MovRequisiciones> getMovRequisicion() {
		return movRequisicion;
	}
	public void setMovRequisicion(List<MovRequisiciones> movRequisicion) {
		this.movRequisicion = movRequisicion;
	}
	public String getTipoRequisicion() {
		return tipoRequisicion;
	}
	public void setTipoRequisicion(String tipoRequisicion) {
		this.tipoRequisicion = tipoRequisicion;
	}
	public Integer getIdRequisicionPadre() {
		return idRequisicionPadre;
	}
	public void setIdRequisicionPadre(Integer idRequisicionPadre) {
		this.idRequisicionPadre = idRequisicionPadre;
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
