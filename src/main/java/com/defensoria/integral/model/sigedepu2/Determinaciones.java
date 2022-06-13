/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
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
@Table(name="tbldeterminaciones")
public class Determinaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cveDeterminacion;
    private String descDeterminacion;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoCarpeta")
    private TiposCarpetas tipoCarpeta;
    private String activo;
    
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getCveDeterminacion() {
        return cveDeterminacion;
    }

    public void setCveDeterminacion(Integer cveDeterminacion) {
        this.cveDeterminacion = cveDeterminacion;
    }

    public String getDescDeterminacion() {
        return descDeterminacion;
    }

    public void setDescDeterminacion(String descDeterminacion) {
        this.descDeterminacion = descDeterminacion;
    }

    public TiposCarpetas getTipoCarpeta() {
        return tipoCarpeta;
    }

    public void setTipoCarpeta(TiposCarpetas tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
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
