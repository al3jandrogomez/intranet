/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

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
@Table(name = "tbltiposrespuestas")
public class TipoRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idTipoRespuesta;
    private String descTipoRespuesta;
    private String descHtml;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdTipoRespuesta() {
        return idTipoRespuesta;
    }

    public void setIdTipoRespuesta(Integer idTipoRespuesta) {
        this.idTipoRespuesta = idTipoRespuesta;
    }

    public String getDescTipoRespuesta() {
        return descTipoRespuesta;
    }

    public void setDescTipoRespuesta(String descTipoRespuesta) {
        this.descTipoRespuesta = descTipoRespuesta;
    }

    public String getDescHtml() {
        return descHtml;
    }

    public void setDescHtml(String descHtml) {
        this.descHtml = descHtml;
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
