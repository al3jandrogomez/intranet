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
@Table(name = "tblmunicipios")
public class Municipios {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Integer idMunicipio;
    private Integer cveMunicipio;
    private String descMunicipio;
    private String cabecera;
    private Integer cveDistrito;
    private Integer cveDistrito2;
    private Integer cveRegion;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveEstado")
    private Estados estado;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getCveMunicipio() {
        return cveMunicipio;
    }

    public void setCveMunicipio(Integer cveMunicipio) {
        this.cveMunicipio = cveMunicipio;
    }

    public String getDescMunicipio() {
        return descMunicipio;
    }

    public void setDescMunicipio(String descMunicipio) {
        this.descMunicipio = descMunicipio;
    }

    public String getCabecera() {
        return cabecera;
    }

    public void setCabecera(String cabecera) {
        this.cabecera = cabecera;
    }

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

    public Estados getEstado() {
        return estado;
    }

    public void setEstado(Estados estado) {
        this.estado = estado;
    }

    public Integer getCveDistrito2() {
        return this.cveDistrito2;
    }

    public void setCveDistrito2(Integer cveDistrito2) {
        this.cveDistrito2 = cveDistrito2;
    }
    
    

}
