/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
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
@Table(name = "tblrespuestas")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRespuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idPregunta")
    private Pregunta pregunta;
    private String descRespuesta;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinColumn(name = "idTipoRespuesta")
    private TipoRespuesta tipoRespuesta;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "idRespuestaHija")
    private Respuesta padre;
    @OneToMany(mappedBy="padre")
    private List<Respuesta> listahijos;
   
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

   

    
    
    @JsonIgnore
    public Respuesta getPadre() {
        return padre;
    }

    @JsonProperty
    public void setPadre(Respuesta padre) {
        this.padre = padre;
    }

    public List<Respuesta> getListahijos() {
        return listahijos;
    }

    public void setListahijos(List<Respuesta> listahijos) {
        this.listahijos = listahijos;
    }

    public TipoRespuesta getTipoRespuesta() {
        return tipoRespuesta;
    }

    public void setTipoRespuesta(TipoRespuesta tipoRespuesta) {
        this.tipoRespuesta = tipoRespuesta;
    }

    public Integer getIdRespuesta() {
        return idRespuesta;
    }

    public void setIdRespuesta(Integer idRespuesta) {
        this.idRespuesta = idRespuesta;
    }

    @JsonIgnore
    public Pregunta getPregunta() {
        return pregunta;
    }

    @JsonProperty
    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
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
