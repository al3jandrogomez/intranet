/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "tblconclusiones")
public class Conclusion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idConclusion;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRepresentado")
    private Representados representado;
    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAudiencia")
    private Audiencias audiencia;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveTipoConclusion")
    private TiposConclusiones tipoConclusion;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdConclusion() {
        return idConclusion;
    }

    public void setIdConclusion(Integer idConclusion) {
        this.idConclusion = idConclusion;
    }

    public Representados getRepresentado() {
        return representado;
    }

    public void setRepresentado(Representados representado) {
        this.representado = representado;
    }

    public Audiencias getAudiencia() {
        return audiencia;
    }

    public void setAudiencia(Audiencias audiencia) {
        this.audiencia = audiencia;
    }

    public TiposConclusiones getTipoConclusion() {
        return tipoConclusion;
    }

    public void setTipoConclusion(TiposConclusiones tipoConclusion) {
        this.tipoConclusion = tipoConclusion;
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
