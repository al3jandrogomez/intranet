package com.defensoria.sigedepu.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "representacion")

public class Representacion {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String tipo_representacion;
	private Date fecha_recepcion_oficio;
	private Date fecha_presentacion_demanda;
	private Date fecha_termino_contestacion;
	private String fase_invedep;
	private String descripcion_otra_fase;
	private String estatus;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "distrito_id")
	private Distrito distrito;
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "abogado_anterior")
	private Usuario abogadoAnt;
	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "asesoria_id", referencedColumnName="id")
   	private Asesoria asesoria;
	private String numero_expediente;
	private Integer distrito_judicial_id;
	private String juzgado;
	private Date fecha_turno_patrocinio;
	private Integer municipio_representacion_id;
	private String numero_oficio_turnar;
	private Integer estado_representacion_id;
	private Date fecha_conclusion;
	private Date fecha_creacion;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTipo_representacion() {
		return tipo_representacion;
	}

	public void setTipo_representacion(String tipo_representacion) {
		this.tipo_representacion = tipo_representacion;
	}

	public Date getFecha_recepcion_oficio() {
		return fecha_recepcion_oficio;
	}

	public void setFecha_recepcion_oficio(Date fecha_recepcion_oficio) {
		this.fecha_recepcion_oficio = fecha_recepcion_oficio;
	}

	public Date getFecha_presentacion_demanda() {
		return fecha_presentacion_demanda;
	}

	public void setFecha_presentacion_demanda(Date fecha_presentacion_demanda) {
		this.fecha_presentacion_demanda = fecha_presentacion_demanda;
	}

	public Date getFecha_termino_contestacion() {
		return fecha_termino_contestacion;
	}

	public void setFecha_termino_contestacion(Date fecha_termino_contestacion) {
		this.fecha_termino_contestacion = fecha_termino_contestacion;
	}

	public String getFase_invedep() {
		return fase_invedep;
	}

	public void setFase_invedep(String fase_invedep) {
		this.fase_invedep = fase_invedep;
	}

	public String getDescripcion_otra_fase() {
		return descripcion_otra_fase;
	}

	public void setDescripcion_otra_fase(String descripcion_otra_fase) {
		this.descripcion_otra_fase = descripcion_otra_fase;
	}

	public String getEstatus() {
		return estatus;
	}

	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}

//	public Integer getUsuario_id() {
//		return usuario_id;
//	}
//
//	public void setUsuario_id(Integer usuario_id) {
//		this.usuario_id = usuario_id;
//	}
	

	public Distrito getDistrito() {
		return distrito;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public void setDistrito(Distrito distrito) {
		this.distrito = distrito;
	}

	// public Integer getDistrito_id() {
	// return distrito_id;
	// }
	// public void setDistrito_id(Integer distrito_id) {
	// this.distrito_id = distrito_id;
	// }
//	public Integer getAbogado_anterior() {
//		return abogado_anterior;
//	}
//
//	public void setAbogado_anterior(Integer abogado_anterior) {
//		this.abogado_anterior = abogado_anterior;
//	}

	
	
	public Usuario getAbogadoAnt() {
		return abogadoAnt;
	}

	
	public void setAbogadoAnt(Usuario abogadoAnt) {
		this.abogadoAnt = abogadoAnt;
	}

	
	public String getNumero_expediente() {
		return numero_expediente;
	}

	public void setNumero_expediente(String numero_expediente) {
		this.numero_expediente = numero_expediente;
	}

	public Integer getDistrito_judicial_id() {
		return distrito_judicial_id;
	}

	public void setDistrito_judicial_id(Integer distrito_judicial_id) {
		this.distrito_judicial_id = distrito_judicial_id;
	}

	public String getJuzgado() {
		return juzgado;
	}

	public void setJuzgado(String juzgado) {
		this.juzgado = juzgado;
	}

	public Date getFecha_turno_patrocinio() {
		return fecha_turno_patrocinio;
	}

	public void setFecha_turno_patrocinio(Date fecha_turno_patrocinio) {
		this.fecha_turno_patrocinio = fecha_turno_patrocinio;
	}

	public Integer getMunicipio_representacion_id() {
		return municipio_representacion_id;
	}

	public void setMunicipio_representacion_id(Integer municipio_representacion_id) {
		this.municipio_representacion_id = municipio_representacion_id;
	}

	public String getNumero_oficio_turnar() {
		return numero_oficio_turnar;
	}

	public void setNumero_oficio_turnar(String numero_oficio_turnar) {
		this.numero_oficio_turnar = numero_oficio_turnar;
	}

	public Integer getEstado_representacion_id() {
		return estado_representacion_id;
	}

	public void setEstado_representacion_id(Integer estado_representacion_id) {
		this.estado_representacion_id = estado_representacion_id;
	}

	public Date getFecha_conclusion() {
		return fecha_conclusion;
	}

	public void setFecha_conclusion(Date fecha_conclusion) {
		this.fecha_conclusion = fecha_conclusion;
	}

	
	
	public Asesoria getAsesoria() {
		return asesoria;
	}

	public void setAsesoria(Asesoria asesoria) {
		this.asesoria = asesoria;
	}

	public Date getFecha_creacion() {
		return fecha_creacion;
	}

	public void setFecha_creacion(Date fecha_creacion) {
		this.fecha_creacion = fecha_creacion;
	}

}
