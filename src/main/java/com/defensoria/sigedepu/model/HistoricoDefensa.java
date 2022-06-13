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
@Table(name = "historico_defensa")

public class HistoricoDefensa {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Integer id_seccion;
    private Integer seccion_id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    private Date fecha;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_seccion() {
        return id_seccion;
    }

    public void setId_seccion(Integer id_seccion) {
        this.id_seccion = id_seccion;
    }

    public Integer getSeccion_id() {
        return seccion_id;
    }

    public void setSeccion_id(Integer seccion_id) {
        this.seccion_id = seccion_id;
    }
@JsonIgnore
    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

}
