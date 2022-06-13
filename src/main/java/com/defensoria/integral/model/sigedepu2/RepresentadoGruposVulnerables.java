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
@Table(name="tblrepresentadogrupovulnerable")
public class RepresentadoGruposVulnerables {
    
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    private Integer idRepresentadoGrupoVulnerable;
   
    @JsonBackReference
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRepresentado")
    private Representados representado;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveGrupoVulnerable")
    private GruposVulnerables grupoVulnerable;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdRepresentadoGrupoVulnerable() {
        return idRepresentadoGrupoVulnerable;
    }

    public void setIdRepresentadoGrupoVulnerable(Integer idRepresentadoGrupoVulnerable) {
        this.idRepresentadoGrupoVulnerable = idRepresentadoGrupoVulnerable;
    }

    public Representados getRepresentado() {
        return representado;
    }

    public void setRepresentado(Representados representado) {
        this.representado = representado;
    }

    public GruposVulnerables getGrupoVulnerable() {
        return grupoVulnerable;
    }

    public void setGrupoVulnerable(GruposVulnerables grupoVulnerable) {
        this.grupoVulnerable = grupoVulnerable;
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
