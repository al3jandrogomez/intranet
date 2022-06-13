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
@Table(name = "acusacion")
public class Acusacion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Date fecha_audiencia_intermedia;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Integer created_by_id;
    private Date fecha_presentacion;
    private String clasificacion_legal;
    private Integer coadyuvancia_acusacion;
    private Date fecha_presentacion_coadyuvancia;
    private String clasificacion_legal_coadyuvancia;
    private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha_audiencia_intermedia() {
        return fecha_audiencia_intermedia;
    }

    public void setFecha_audiencia_intermedia(Date fecha_audiencia_intermedia) {
        this.fecha_audiencia_intermedia = fecha_audiencia_intermedia;
    }

    @JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public Distrito getDistrito() {
        return distrito;
    }

    public void setDistrito(Distrito distrito) {
        this.distrito = distrito;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Integer getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(Integer created_by_id) {
        this.created_by_id = created_by_id;
    }

    public Date getFecha_presentacion() {
        return fecha_presentacion;
    }

    public void setFecha_presentacion(Date fecha_presentacion) {
        this.fecha_presentacion = fecha_presentacion;
    }

    public String getClasificacion_legal() {
        return clasificacion_legal;
    }

    public void setClasificacion_legal(String clasificacion_legal) {
        this.clasificacion_legal = clasificacion_legal;
    }

    public Integer getCoadyuvancia_acusacion() {
        return coadyuvancia_acusacion;
    }

    public void setCoadyuvancia_acusacion(Integer coadyuvancia_acusacion) {
        this.coadyuvancia_acusacion = coadyuvancia_acusacion;
    }

    public Date getFecha_presentacion_coadyuvancia() {
        return fecha_presentacion_coadyuvancia;
    }

    public void setFecha_presentacion_coadyuvancia(Date fecha_presentacion_coadyuvancia) {
        this.fecha_presentacion_coadyuvancia = fecha_presentacion_coadyuvancia;
    }

    public String getClasificacion_legal_coadyuvancia() {
        return clasificacion_legal_coadyuvancia;
    }

    public void setClasificacion_legal_coadyuvancia(String clasificacion_legal_coadyuvancia) {
        this.clasificacion_legal_coadyuvancia = clasificacion_legal_coadyuvancia;
    }

    public Date getFecha_creacion_object() {
        return fecha_creacion_object;
    }

    public void setFecha_creacion_object(Date fecha_creacion_object) {
        this.fecha_creacion_object = fecha_creacion_object;
    }

}
