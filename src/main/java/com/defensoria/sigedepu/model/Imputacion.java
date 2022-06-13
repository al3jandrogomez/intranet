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
@Table(name="imputacion")
public class Imputacion {
   @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
   
   private Integer procedimiento_ordinario;
   private String clasificacion_legal;
   private String observaciones;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
   private Defensa defensa;
     @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "distrito_id")
   private Distrito distrito;
      @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
   private Usuario usuario;
   private Integer antecedente;
   private Integer cedula_antecedente_id;
   private Integer created_by_id;
   private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProcedimiento_ordinario() {
        return procedimiento_ordinario;
    }

    public void setProcedimiento_ordinario(Integer procedimiento_ordinario) {
        this.procedimiento_ordinario = procedimiento_ordinario;
    }

    public String getClasificacion_legal() {
        return clasificacion_legal;
    }

    public void setClasificacion_legal(String clasificacion_legal) {
        this.clasificacion_legal = clasificacion_legal;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
@JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
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
