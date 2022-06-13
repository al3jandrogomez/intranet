/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.constancias;

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
@Table(name = "tbleventoparticipante")
public class EventoParticipante {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEventoParticipante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idEvento")
    private Evento evento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idParticipante")
    private Participantes participante;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idInstitucion")
    private Instituciones institucion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCargo")
    private Cargo cargo;
    private String asistencia;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    
    
    private Date fechaActualizacion;
    
    
    
     
     

    public Integer getIdEventoParticipante() {
        return idEventoParticipante;
    }

    public void setIdEventoParticipante(Integer idEventoParticipante) {
        this.idEventoParticipante = idEventoParticipante;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
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

    public Participantes getParticipante() {
        return participante;
    }

    public void setParticipante(Participantes participante) {
        this.participante = participante;
    }

    public Cargo getCargo() {
        return cargo;
    }

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public Instituciones getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Instituciones institucion) {
        this.institucion = institucion;
    }

    public String getAsistencia() {
        return asistencia;
    }

    public void setAsistencia(String asistencia) {
        this.asistencia = asistencia;
    }
    
    

}
