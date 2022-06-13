/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author alejandro.gomez
 */
@Entity
@Table(name="tblrepresentacionfirma")
public class RepresentacionFirma {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRepresentacionFirma;
    private Integer idRepresentacion;
    private Integer idFirma;
    private String hash;
    private Integer idSolicitudAtencion;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdRepresentacionFirma() {
        return idRepresentacionFirma;
    }

    public void setIdRepresentacionFirma(Integer idRepresentacionFirma) {
        this.idRepresentacionFirma = idRepresentacionFirma;
    }

    public Integer getIdRepresentacion() {
        return idRepresentacion;
    }

    public void setIdRepresentacion(Integer idRepresentacion) {
        this.idRepresentacion = idRepresentacion;
    }

    public Integer getIdFirma() {
        return idFirma;
    }

    public void setIdFirma(Integer idFirma) {
        this.idFirma = idFirma;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getIdSolicitudAtencion() {
        return idSolicitudAtencion;
    }

    public void setIdSolicitudAtencion(Integer idSolicitudAtencion) {
        this.idSolicitudAtencion = idSolicitudAtencion;
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
