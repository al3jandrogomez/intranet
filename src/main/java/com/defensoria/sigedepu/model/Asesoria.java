package com.defensoria.sigedepu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "asesoria")
public class Asesoria {

	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String estatus;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	private Integer solicitud_atencion_id;
	private Date fecha_modifica;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_modifico")
	private Usuario usuario_modifico;
	private Date fecha;
	private String estatus_peticionario;
	private Integer pendiente_regresar;
	private Integer procede_patrocinio;
	private Integer aplicar_estudio;
	private String descripcion_hecho;
	private String accion_juzgado;
	private String opinion_juridica;
	private Integer municipio_id;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distrito_id")
	private Distrito distrito;
	// @JsonIgnore
	@OneToOne(mappedBy = "asesoria")
	private Representacion representacion;
	// @JsonIgnore||
	// @OneToOne( mappedBy = "asesoria2")
	// private Representacion representacion;
	
	@OneToOne(mappedBy = "asesoria3")
	private ConclusionEstudio conclusionEstudio;

	private Integer estado_competencia_id;
	private String ciudad_competencia;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "residencia_id" , referencedColumnName = "id")
	private Residencia residencia;

	// private Integer residencia_id;
	private Integer retorno_trabajo_social;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

	public Integer getSolicitud_atencion_id() {
		return solicitud_atencion_id;
	}

	public void setSolicitud_atencion_id(Integer solicitud_atencion_id) {
		this.solicitud_atencion_id = solicitud_atencion_id;
	}

	public Date getFecha_modifica() {
		return fecha_modifica;
	}

	public void setFecha_modifica(Date fecha_modifica) {
		this.fecha_modifica = fecha_modifica;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstatus_peticionario() {
		return estatus_peticionario;
	}

	public void setEstatus_peticionario(String estatus_peticionario) {
		this.estatus_peticionario = estatus_peticionario;
	}

	public Integer getPendiente_regresar() {
		return pendiente_regresar;
	}

	public void setPendiente_regresar(Integer pendiente_regresar) {
		this.pendiente_regresar = pendiente_regresar;
	}

	public Integer getProcede_patrocinio() {
		return procede_patrocinio;
	}

	public void setProcede_patrocinio(Integer procede_patrocinio) {
		this.procede_patrocinio = procede_patrocinio;
	}

	public Integer getAplicar_estudio() {
		return aplicar_estudio;
	}

	public void setAplicar_estudio(Integer aplicar_estudio) {
		this.aplicar_estudio = aplicar_estudio;
	}

	public String getDescripcion_hecho() {
		return descripcion_hecho;
	}

	public void setDescripcion_hecho(String descripcion_hecho) {
		this.descripcion_hecho = descripcion_hecho;
	}

	public String getAccion_juzgado() {
		return accion_juzgado;
	}

	public void setAccion_juzgado(String accion_juzgado) {
		this.accion_juzgado = accion_juzgado;
	}

	public String getOpinion_juridica() {
		return opinion_juridica;
	}

	public void setOpinion_juridica(String opinion_juridica) {
		this.opinion_juridica = opinion_juridica;
	}

	public Integer getMunicipio_id() {
		return municipio_id;
	}

	public void setMunicipio_id(Integer municipio_id) {
		this.municipio_id = municipio_id;
	}

	public Integer getEstado_competencia_id() {
		return estado_competencia_id;
	}

	public void setEstado_competencia_id(Integer estado_competencia_id) {
		this.estado_competencia_id = estado_competencia_id;
	}

	public String getCiudad_competencia() {
		return ciudad_competencia;
	}

	public void setCiudad_competencia(String ciudad_competencia) {
		this.ciudad_competencia = ciudad_competencia;
	}

	// public Integer getResidencia_id() {
	// return residencia_id;
	// }
	// public void setResidencia_id(Integer residencia_id) {
	// this.residencia_id = residencia_id;
	// }
	public Integer getRetorno_trabajo_social() {
		return retorno_trabajo_social;
	}

	public void setRetorno_trabajo_social(Integer retorno_trabajo_social) {
		this.retorno_trabajo_social = retorno_trabajo_social;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario_modifico() {
		return usuario_modifico;
	}

	public void setUsuario_modifico(Usuario usuario_modifico) {
		this.usuario_modifico = usuario_modifico;
	}

	public Distrito getDistrito() {
		return distrito;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	public Representacion getRepresentacion() {
		return representacion;
	}

	public void setRepresentacion(Representacion representacion) {
		this.representacion = representacion;
	}

	public Residencia getResidencia() {
		return residencia;
	}

	public void setResidencia(Residencia residencia) {
		this.residencia = residencia;
	}

	public ConclusionEstudio getConclusionEstudio() {
		return conclusionEstudio;
	}

	public void setConclusionEstudio(ConclusionEstudio conclusionEstudio) {
		this.conclusionEstudio = conclusionEstudio;
	}

}
