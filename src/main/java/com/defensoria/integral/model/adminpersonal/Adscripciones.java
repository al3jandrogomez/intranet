package com.defensoria.integral.model.adminpersonal;

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

import com.defensoria.integral.model.almacen.Distribucion;

@Entity
@Table(name = "tbladscripciones")

public class Adscripciones {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cveAdscripcion;
	private String descAdscripcion;
	private Integer cveadsPadre;
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="cveMunicipio")
	private Municipios municipio;
	//private Integer cveMunicipio;
	private Integer cveDistrito;
	private Integer cveRegion;
	private String activo;
        private String domicilio;
	@OneToMany(mappedBy = "adscripcion")
	private List<MovAdscripciones> movAdscripciones;
	@OneToMany(mappedBy = "adscripcion")
	private List<Distribucion> distribucion;
	private String codigoUnidad;

	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;

	public Integer getCveAdscripcion() {
		return cveAdscripcion;
	}

	public void setCveAdscripcion(Integer cveAdscripcion) {
		this.cveAdscripcion = cveAdscripcion;
	}

	public String getDescAdscripcion() {
		return descAdscripcion;
	}

	public void setDescAdscripcion(String descAdscripcion) {
		this.descAdscripcion = descAdscripcion;
	}

	public Integer getCveadsPadre() {
		return cveadsPadre;
	}

	public void setCveadsPadre(Integer cveadsPadre) {
		this.cveadsPadre = cveadsPadre;
	}

//	public Integer getCveMunicipio() {
//		return cveMunicipio;
//	}
//
//	public void setCveMunicipio(Integer cveMunicipio) {
//		this.cveMunicipio = cveMunicipio;
//	}

	public Integer getCveDistrito() {
		return cveDistrito;
	}

	public void setCveDistrito(Integer cveDistrito) {
		this.cveDistrito = cveDistrito;
	}

	public Integer getCveRegion() {
		return cveRegion;
	}

	public void setCveRegion(Integer cveRegion) {
		this.cveRegion = cveRegion;
	}

	public String getActivo() {
		return activo;
	}

	public void setActivo(String activo) {
		this.activo = activo;
	}

	// public List<Adscripciones> getAdscripcion() {
	// return adscripcion;
	// }
	// public void setAdscripcion(List<Adscripciones> adscripcion) {
	// this.adscripcion = adscripcion;
	// }
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

	public Municipios getMunicipio() {
		return municipio;
	}

	public void setMunicipio(Municipios municipio) {
		this.municipio = municipio;
	}

	public String getCodigoUnidad() {
		return codigoUnidad;
	}

	public void setCodigoUnidad(String codigoUnidad) {
		this.codigoUnidad = codigoUnidad;
	}

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }
	
	
	
	

}
