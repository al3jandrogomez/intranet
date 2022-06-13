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
import org.hibernate.annotations.Where;


@Entity
@Table(name = "tblpersonalperfiles")
public class PersonalPerfiles {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPersonalPerfil;

  

//	@OneToMany (mappedBy="personal")
//	private List<Perfiles> perfil;
//	@JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveUsuario")
    private Personal personal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cvePerfil")
    private Perfiles perfil;
    @Where(clause = "activo='S'")
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

  
    public Integer getIdPersonalPerfil() {
        return idPersonalPerfil;
    }

    public void setIdPersonalPerfil(Integer idPersonalPerfil) {
        this.idPersonalPerfil = idPersonalPerfil;
    }

//	public List<Perfiles> getPerfil() {
//		return perfil;
//	}
//
//	public void setPerfil(List<Perfiles> perfil) {
//		this.perfil = perfil;
//	}
    public Perfiles getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfiles perfil) {
        this.perfil = perfil;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
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
