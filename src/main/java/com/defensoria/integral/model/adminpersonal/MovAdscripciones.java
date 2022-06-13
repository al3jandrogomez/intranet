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
@Table(name = "tblmovadscripciones")
public class MovAdscripciones {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMovAdscripcion;

  
    @ManyToOne
    @JoinColumn(name = "cveAdscripcion")
    private Adscripciones adscripcion;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cveusuario")
    private Personal personal;
    
    
    private String noOficio;
    private Integer cveUsuario;
    private Integer cveTipoMovimientoPersonal;
    @Column(insertable = false, updatable = false)
    private Integer cvePuesto;
    @Column(insertable = false, updatable = false)
    private Integer cvePerfilPersonal;
    private String titular;
    private Date fechaMovimiento;
    // private Integer cvePuesto;
//	@JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cvePuesto")
    private Puesto puesto;
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cvePuestoFuncional")
    private PuestoFuncional puestoFuncional;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    private String tipoTitular;

    public String getTipoTitular() {
        return tipoTitular;
    }

    public void setTipoTitular(String tipoTitular) {
        this.tipoTitular = tipoTitular;
    }

    public Integer getIdMovAdscripcion() {
        return idMovAdscripcion;
    }

    public void setIdMovAdscripcion(Integer idMovAdscripcion) {
        this.idMovAdscripcion = idMovAdscripcion;
    }

    public String getNoOficio() {
        return noOficio;
    }

    public void setNoOficio(String noOficio) {
        this.noOficio = noOficio;
    }

    public Integer getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(Integer cveUsuario) {
        this.cveUsuario = cveUsuario;
    }

    public Integer getCveTipoMovimientoPersonal() {
        return cveTipoMovimientoPersonal;
    }

    public void setCveTipoMovimientoPersonal(Integer cveTipoMovimientoPersonal) {
        this.cveTipoMovimientoPersonal = cveTipoMovimientoPersonal;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Date getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(Date fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public Puesto getPuesto() {
        return puesto;
    }

    public void setPuesto(Puesto puesto) {
        this.puesto = puesto;
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

    public Adscripciones getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(Adscripciones adscripcion) {
        this.adscripcion = adscripcion;
    }

    public Integer getCvePuesto() {
        return cvePuesto;
    }

    public void setCvePuesto(Integer cvePuesto) {
        this.cvePuesto = cvePuesto;
    }

    public Integer getCvePerfilPersonal() {
        return cvePerfilPersonal;
    }

    public void setCvePerfilPersonal(Integer cvePerfilPersonal) {
        this.cvePerfilPersonal = cvePerfilPersonal;
    }

    public PuestoFuncional getPuestoFuncional() {
        return puestoFuncional;
    }

    public void setPuestoFuncional(PuestoFuncional puestoFuncional) {
        this.puestoFuncional = puestoFuncional;
    }
    
    

}
