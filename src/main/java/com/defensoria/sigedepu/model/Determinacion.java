/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.sigedepu.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alejandro.gomez
 */
@Entity
@Table(name = "determinacion")
public class Determinacion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String determinacion_mp;
    private String archivo_determinacion_mp;
    private String otras_determinacion_mp;
    private Date fecha;
    private String comentarios;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    private Integer created_by_id;
    private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeterminacion_mp() {
        return determinacion_mp;
    }

    public void setDeterminacion_mp(String determinacion_mp) {
        this.determinacion_mp = determinacion_mp;
    }

    public String getArchivo_determinacion_mp() {
        return archivo_determinacion_mp;
    }

    public void setArchivo_determinacion_mp(String archivo_determinacion_mp) {
        this.archivo_determinacion_mp = archivo_determinacion_mp;
    }

    public String getOtras_determinacion_mp() {
        return otras_determinacion_mp;
    }

    public void setOtras_determinacion_mp(String otras_determinacion_mp) {
        this.otras_determinacion_mp = otras_determinacion_mp;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }
@JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public Integer getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(Integer created_by_id) {
        this.created_by_id = created_by_id;
    }

    

    public Date getFecha_creacion_object() {
        return fecha_creacion_object;
    }

    public void setFecha_creacion_object(Date fecha_creacion_object) {
        this.fecha_creacion_object = fecha_creacion_object;
    }
    
    

}
