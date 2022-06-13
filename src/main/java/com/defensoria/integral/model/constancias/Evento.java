/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.constancias;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.sql.Time;
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
@Table(name = "tbleventos")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idEvento;
    private String descEvento;
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaEvento;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE )
    @JoinColumn(name = "idDocumentoEvento")
    private DocumentoEvento documentoEvento;
//    private Integer idDocumentoEvento;
    @JsonIgnore
    @OneToMany(mappedBy = "evento")
    private List<EventoParticipante> listaParticipantes;
    @JsonIgnore
    @OneToMany(mappedBy = "eventoI")
    private List<EventoInstitucion> listaInstituciones;
    private String motivoEvento;
    private String publicar;
    private String introduccion;
    private Time horario;
    private String lugarEvento;
    private String imagen;

    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getDescEvento() {
        return descEvento;
    }

    public void setDescEvento(String descEvento) {
        this.descEvento = descEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getMotivoEvento() {
        return motivoEvento;
    }

    public void setMotivoEvento(String motivoEvento) {
        this.motivoEvento = motivoEvento;
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

    public List<EventoParticipante> getListaParticipantes() {
        return listaParticipantes;
    }

    public void setListaParticipantes(List<EventoParticipante> listaParticipantes) {
        this.listaParticipantes = listaParticipantes;
    }

    public List<EventoInstitucion> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<EventoInstitucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public String getPublicar() {
        return publicar;
    }

    public void setPublicar(String publicar) {
        this.publicar = publicar;
    }

    public String getIntroduccion() {
        return introduccion;
    }

    public void setIntroduccion(String introduccion) {
        this.introduccion = introduccion;
    }

    public Time getHorario() {
        return horario;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public String getLugarEvento() {
        return lugarEvento;
    }

    public void setLugarEvento(String lugarEvento) {
        this.lugarEvento = lugarEvento;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public DocumentoEvento getDocumentoEvento() {
        return documentoEvento;
    }

    public void setDocumentoEvento(DocumentoEvento documentoEvento) {
        this.documentoEvento = documentoEvento;
    }
    
    
    
    
    
    

}
