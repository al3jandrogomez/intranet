/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
@Table(name = "tblPreguntas")
public class Pregunta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPregunta;
    private String descPregunta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEncuesta")
    private Encuesta encuesta;

    @OneToMany(mappedBy = "pregunta")
    private List<Respuesta> respuesta;
    private String activo;

    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public List<Respuesta> getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(List<Respuesta> respuesta) {
        this.respuesta = respuesta;
    }

    public Encuesta getEncuesta() {
        return encuesta;
    }

    public void setEncuesta(Encuesta encuesta) {
        this.encuesta = encuesta;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }

    public String getDescPregunta() {
        return descPregunta;
    }

    public void setDescPregunta(String descPregunta) {
        this.descPregunta = descPregunta;
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
