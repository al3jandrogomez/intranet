/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import com.defensoria.integral.model.adminpersonal.Personal;
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
@Table(name = "tblpermisoschat")
public class PermisoChat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idPermisoChat;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idChat")
    private Chat chat;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveUsuario")
    private Personal usuario;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdPermisoChat() {
        return idPermisoChat;
    }

    public void setIdPermisoChat(Integer idPermisoChat) {
        this.idPermisoChat = idPermisoChat;
    }

    public Chat getChat() {
        return chat;
    }

    public void setChat(Chat chat) {
        this.chat = chat;
    }

    public Personal getUsuario() {
        return usuario;
    }

    public void setUsuario(Personal usuario) {
        this.usuario = usuario;
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
