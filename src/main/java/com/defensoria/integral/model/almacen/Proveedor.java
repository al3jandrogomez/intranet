package com.defensoria.integral.model.almacen;

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
@Table(name = "tblproveedores")
//@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="cveProveedor")
//@JsonIdentityReference(alwaysAsId = true) 
public class Proveedor {
	
	
	public Proveedor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer cveProveedor;
	private String descProveedor;
	private String activo;
	@CreationTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date fechaRegistro;
	@UpdateTimestamp
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaActualizacion;
//	@ManyToOne
	@JsonIgnore
	@OneToMany(mappedBy = "proveedor")
	private List <Entrada> entrada;

	public Integer getCveProveedor() {
		return cveProveedor;
	}

	public void setCveProveedor(Integer cveProveedor) {
		this.cveProveedor = cveProveedor;
	}

	public String getDescProveedor() {
		return descProveedor;
	}

	public void setDescProveedor(String descProveedor) {
		this.descProveedor = descProveedor;
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

//	public Set<Entrada> getEntrada() {
//		return entrada;
//	}
//
//	public void setEntrada(Set<Entrada> entrada) {
//		this.entrada = entrada;
//	}
	
	

	
	

	

	

	
	

	
	

}
