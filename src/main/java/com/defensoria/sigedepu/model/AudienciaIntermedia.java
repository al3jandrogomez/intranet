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
@Table(name = "audiencia_intermedia")
public class AudienciaIntermedia {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Date fecha_auto_de_apertura;
    private Date fecha_audiencia_juicio;
    private String observaciones_fase_escriba;
    private Date fecha_celebracion_audiencia;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    private Integer antecedente;
    private Integer cedula_antecedente_id;
    private String observaciones;
    private Integer created_by_id;
    private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_auto_de_apertura() {
        return fecha_auto_de_apertura;
    }

    public void setFecha_auto_de_apertura(Date fecha_auto_de_apertura) {
        this.fecha_auto_de_apertura = fecha_auto_de_apertura;
    }

    public Date getFecha_audiencia_juicio() {
        return fecha_audiencia_juicio;
    }

    public void setFecha_audiencia_juicio(Date fecha_audiencia_juicio) {
        this.fecha_audiencia_juicio = fecha_audiencia_juicio;
    }

    public String getObservaciones_fase_escriba() {
        return observaciones_fase_escriba;
    }

    public void setObservaciones_fase_escriba(String observaciones_fase_escriba) {
        this.observaciones_fase_escriba = observaciones_fase_escriba;
    }

    public Date getFecha_celebracion_audiencia() {
        return fecha_celebracion_audiencia;
    }

    public void setFecha_celebracion_audiencia(Date fecha_celebracion_audiencia) {
        this.fecha_celebracion_audiencia = fecha_celebracion_audiencia;
    }

    @JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public Integer getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Integer antecedente) {
        this.antecedente = antecedente;
    }

    public Integer getCedula_antecedente_id() {
        return cedula_antecedente_id;
    }

    public void setCedula_antecedente_id(Integer cedula_antecedente_id) {
        this.cedula_antecedente_id = cedula_antecedente_id;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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
