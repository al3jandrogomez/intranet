/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.sigedepu.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author alejandro.gomez
 */
@Entity
@Table(name="flagrancia")
public class Flagrancia {
    
    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Integer flagrancia ;
    private String observaciones;
    private Date created_by_id;
    private Integer defensa_id;
    private Date fecha_creacion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFlagrancia() {
        return flagrancia;
    }

    public void setFlagrancia(Integer flagrancia) {
        this.flagrancia = flagrancia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(Date created_by_id) {
        this.created_by_id = created_by_id;
    }

    public Integer getDefensa_id() {
        return defensa_id;
    }

    public void setDefensa_id(Integer defensa_id) {
        this.defensa_id = defensa_id;
    }

    public Date getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(Date fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }
    
    
    
    
}
