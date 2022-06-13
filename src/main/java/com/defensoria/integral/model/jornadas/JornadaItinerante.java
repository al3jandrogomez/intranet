package com.defensoria.integral.model.jornadas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name="tbljornadasitinerantes")
public class JornadaItinerante {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idJornadaItinerante;
    private Integer numMes;
    private Integer numSemana;
    private Integer anio;
    private String url;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;



    public Integer getIdJornadaItinerante() {
        return this.idJornadaItinerante;
    }

    public void setIdJornadaItinerante(Integer idJornadaItinerante) {
        this.idJornadaItinerante = idJornadaItinerante;
    }

    public Integer getNumMes() {
        return this.numMes;
    }

    public void setNumMes(Integer numMes) {
        this.numMes = numMes;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Date getFechaRegistro() {
        return this.fechaRegistro;
    }

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    public Integer getNumSemana() {
        return this.numSemana;
    }

    public void setNumSemana(Integer numSemana) {
        this.numSemana = numSemana;
    }



    
}
