/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.sigedepu2.Representados;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author yo
 */
@Entity
@Table(name = "tblchat")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idChat;
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveAdscripcion")
    private Adscripciones adscripcion;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveUsuario")
    private Personal personal;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idRepresentado")
    private Representados representado;
     @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveTipoChat")
    private TipoChat tipoChat;
    private String activo;
    private String observaciones;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    private Integer cveRegion;

    public Integer getIdChat() {
        return idChat;
    }

    public void setIdChat(Integer idChat) {
        this.idChat = idChat;
    }

    
    public Adscripciones getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(Adscripciones adscripcion) {
        this.adscripcion = adscripcion;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Representados getRepresentado() {
        return representado;
    }

    public void setRepresentado(Representados representado) {
        this.representado = representado;
    }
    

    public TipoChat getTipoChat() {
        return tipoChat;
    }

    public void setTipoChat(TipoChat tipoChat) {
        this.tipoChat = tipoChat;
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

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getCveRegion() {
        return cveRegion;
    }

    public void setCveRegion(Integer cveRegion) {
        this.cveRegion = cveRegion;
    }
    
    

    

}
