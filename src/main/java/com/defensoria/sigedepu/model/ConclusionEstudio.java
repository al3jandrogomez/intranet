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
@Table(name="conclusion_estudio")
public class ConclusionEstudio {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String estatus_patrocinio;
	private String accion_juzgado;
	private String observaciones_conclusion;
	private Integer es_viable;
	private Date fecha;
//	private Integer estudio_socio_economico_id;
	private Integer concluded_by_id;
	private Integer sello_id;
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "asesoria_id", referencedColumnName="id")
	private Asesoria asesoria3;
	//@JsonIgnore
	@OneToOne
	@JoinColumn(name = "estudio_socio_economico_id", referencedColumnName = "id")
	private EstudioSocioeconomico estudio;
	private String estatus;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getEstatus_patrocinio() {
		return estatus_patrocinio;
	}
	public void setEstatus_patrocinio(String estatus_patrocinio) {
		this.estatus_patrocinio = estatus_patrocinio;
	}
	public String getAccion_juzgado() {
		return accion_juzgado;
	}
	public void setAccion_juzgado(String accion_juzgado) {
		this.accion_juzgado = accion_juzgado;
	}
	public String getObservaciones_conclusion() {
		return observaciones_conclusion;
	}
	public void setObservaciones_conclusion(String observaciones_conclusion) {
		this.observaciones_conclusion = observaciones_conclusion;
	}
	public Integer getEs_viable() {
		return es_viable;
	}
	public void setEs_viable(Integer es_viable) {
		this.es_viable = es_viable;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
//	public Integer getEstudio_socio_economico_id() {
//		return estudio_socio_economico_id;
//	}
//	public void setEstudio_socio_economico_id(Integer estudio_socio_economico_id) {
//		this.estudio_socio_economico_id = estudio_socio_economico_id;
//	}
	public Integer getConcluded_by_id() {
		return concluded_by_id;
	}
	public void setConcluded_by_id(Integer concluded_by_id) {
		this.concluded_by_id = concluded_by_id;
	}
	public Integer getSello_id() {
		return sello_id;
	}
	public void setSello_id(Integer sello_id) {
		this.sello_id = sello_id;
	}
	
	public Asesoria getAsesoria3() {
		return asesoria3;
	}
	public void setAsesoria3(Asesoria asesoria3) {
		this.asesoria3 = asesoria3;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public EstudioSocioeconomico getEstudio() {
		return estudio;
	}
	public void setEstudio(EstudioSocioeconomico estudio) {
		this.estudio = estudio;
	}
	
	

}
