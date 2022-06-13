/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.documentos;

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

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name="tblgruposdocumentos")
public class GrupoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cveGrupoDocumento;
    private String descGrupoDocumento;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getCveGrupoDocumento() {
        return cveGrupoDocumento;
    }

    public void setCveGrupoDocumento(Integer cveGrupoDocumento) {
        this.cveGrupoDocumento = cveGrupoDocumento;
    }

    public String getDescGrupoDocumento() {
        return descGrupoDocumento;
    }

    public void setDescGrupoDocumento(String descGrupoDocumento) {
        this.descGrupoDocumento = descGrupoDocumento;
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
