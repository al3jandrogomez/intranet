/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "tblpreguntasrespuestas")
public class PreguntaRespuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPreguntaRespuesta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPregunta")
    private Pregunta pregunta;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRespuesta")
    private Respuesta respuesta;
//    private Integer idPregunta;
//    private Integer idRespuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idAplicacionEncuesta")
    private AplicacionEncuesta aplicacion;
    //private Integer idAplicacionEncuesta;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdPreguntaRespuesta() {
        return idPreguntaRespuesta;
    }

    public void setIdPreguntaRespuesta(Integer idPreguntaRespuesta) {
        this.idPreguntaRespuesta = idPreguntaRespuesta;
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public Respuesta getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(Respuesta respuesta) {
        this.respuesta = respuesta;
    }

//    public Integer getIdPregunta() {
//        return idPregunta;
//    }
//
//    public void setIdPregunta(Integer idPregunta) {
//        this.idPregunta = idPregunta;
//    }
//
//    public Integer getIdRespuesta() {
//        return idRespuesta;
//    }
//
//    public void setIdRespuesta(Integer idRespuesta) {
//        this.idRespuesta = idRespuesta;
//    }
//    public Integer getIdAplicacionEncuesta() {
//        return idAplicacionEncuesta;
//    }
//
//    public void setIdAplicacionEncuesta(Integer idAplicacionEncuesta) {
//        this.idAplicacionEncuesta = idAplicacionEncuesta;
//    }
    @JsonIgnore
    public AplicacionEncuesta getAplicacion() {
        return aplicacion;
    }

    @JsonProperty
    public void setAplicacion(AplicacionEncuesta aplicacion) {
        this.aplicacion = aplicacion;
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
