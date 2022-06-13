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
@Table(name="otra_audiencia")
public class OtraAudiencia {
     @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
     private String tipo_audiencia;
     private Integer organo_jurisdiccional_id;
     private Date fecha_audiencia;
     private String fundamento_legal;
     private String comentarios;
     private String causa_motivo;
     private String sintesis_audiencia;
     @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
     private Defensa defensa;
     private String clasificacion;
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

    public String getTipo_audiencia() {
        return tipo_audiencia;
    }

    public void setTipo_audiencia(String tipo_audiencia) {
        this.tipo_audiencia = tipo_audiencia;
    }

    public Integer getOrgano_jurisdiccional_id() {
        return organo_jurisdiccional_id;
    }

    public void setOrgano_jurisdiccional_id(Integer organo_jurisdiccional_id) {
        this.organo_jurisdiccional_id = organo_jurisdiccional_id;
    }

    public Date getFecha_audiencia() {
        return fecha_audiencia;
    }

    public void setFecha_audiencia(Date fecha_audiencia) {
        this.fecha_audiencia = fecha_audiencia;
    }

    public String getFundamento_legal() {
        return fundamento_legal;
    }

    public void setFundamento_legal(String fundamento_legal) {
        this.fundamento_legal = fundamento_legal;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getCausa_motivo() {
        return causa_motivo;
    }

    public void setCausa_motivo(String causa_motivo) {
        this.causa_motivo = causa_motivo;
    }

    public String getSintesis_audiencia() {
        return sintesis_audiencia;
    }

    public void setSintesis_audiencia(String sintesis_audiencia) {
        this.sintesis_audiencia = sintesis_audiencia;
    }
@JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
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
