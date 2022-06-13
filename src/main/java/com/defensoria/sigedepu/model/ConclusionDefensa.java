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
@Table(name = "conclusion_defensa")
public class ConclusionDefensa {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private String comentarios_conclusion;
    private Date fecha_conclusion;
    private Integer tipo_conclusion_id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    private String motivo_rechazo;
    private String sentido_resolucion;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getComentarios_conclusion() {
        return comentarios_conclusion;
    }

    public void setComentarios_conclusion(String comentarios_conclusion) {
        this.comentarios_conclusion = comentarios_conclusion;
    }

    public Date getFecha_conclusion() {
        return fecha_conclusion;
    }

    public void setFecha_conclusion(Date fecha_conclusion) {
        this.fecha_conclusion = fecha_conclusion;
    }

   

    public Integer getTipo_conclusion_id() {
        return tipo_conclusion_id;
    }

    public void setTipo_conclusion_id(Integer tipo_conclusion_id) {
        this.tipo_conclusion_id = tipo_conclusion_id;
    }

    @JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public String getMotivo_rechazo() {
        return motivo_rechazo;
    }

    public void setMotivo_rechazo(String motivo_rechazo) {
        this.motivo_rechazo = motivo_rechazo;
    }

    public String getSentido_resolucion() {
        return sentido_resolucion;
    }

    public void setSentido_resolucion(String sentido_resolucion) {
        this.sentido_resolucion = sentido_resolucion;
    }

}
