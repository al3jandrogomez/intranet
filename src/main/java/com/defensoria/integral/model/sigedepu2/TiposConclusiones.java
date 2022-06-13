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
@Table(name="tbltiposconclusiones")



public class TiposConclusiones {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Integer cveTipoConclusion;
    private String descTipoConclusiones;
    private String activo;
    private Integer idTipoCarpeta;
    
     @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getCveTipoConclusion() {
        return cveTipoConclusion;
    }

    public void setCveTipoConclusion(Integer cveTipoConclusion) {
        this.cveTipoConclusion = cveTipoConclusion;
    }

    public String getDescTipoConclusiones() {
        return descTipoConclusiones;
    }

    public void setDescTipoConclusiones(String descTipoConclusiones) {
        this.descTipoConclusiones = descTipoConclusiones;
    }

    public Integer getIdTipoCarpeta() {
        return idTipoCarpeta;
    }

    public void setIdTipoCarpeta(Integer idTipoCarpeta) {
        this.idTipoCarpeta = idTipoCarpeta;
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
