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
@Table(name = "radicacion_ejecucion")
public class RadicacionEjecucion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Integer organo_jurisdiccional_radicacion_ejecucion_id;
    private Date fecha_audiencia_amonestacion;
    private String num_expediente_ejecucion;
    private Integer hay_sustitucion;
    private String observaciones_sustitucion;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Integer concede_beneficio;
    private Integer beneficio_id;
    private Integer created_by_id;
    private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgano_jurisdiccional_radicacion_ejecucion_id() {
        return organo_jurisdiccional_radicacion_ejecucion_id;
    }

    public void setOrgano_jurisdiccional_radicacion_ejecucion_id(Integer organo_jurisdiccional_radicacion_ejecucion_id) {
        this.organo_jurisdiccional_radicacion_ejecucion_id = organo_jurisdiccional_radicacion_ejecucion_id;
    }

    public Date getFecha_audiencia_amonestacion() {
        return fecha_audiencia_amonestacion;
    }

    public void setFecha_audiencia_amonestacion(Date fecha_audiencia_amonestacion) {
        this.fecha_audiencia_amonestacion = fecha_audiencia_amonestacion;
    }

    public String getNum_expediente_ejecucion() {
        return num_expediente_ejecucion;
    }

    public void setNum_expediente_ejecucion(String num_expediente_ejecucion) {
        this.num_expediente_ejecucion = num_expediente_ejecucion;
    }

    public Integer getHay_sustitucion() {
        return hay_sustitucion;
    }

    public void setHay_sustitucion(Integer hay_sustitucion) {
        this.hay_sustitucion = hay_sustitucion;
    }

    public String getObservaciones_sustitucion() {
        return observaciones_sustitucion;
    }

    public void setObservaciones_sustitucion(String observaciones_sustitucion) {
        this.observaciones_sustitucion = observaciones_sustitucion;
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

    public Integer getConcede_beneficio() {
        return concede_beneficio;
    }

    public void setConcede_beneficio(Integer concede_beneficio) {
        this.concede_beneficio = concede_beneficio;
    }

    public Integer getBeneficio_id() {
        return beneficio_id;
    }

    public void setBeneficio_id(Integer beneficio_id) {
        this.beneficio_id = beneficio_id;
    }

    public Integer getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(Integer created_by_id) {
        this.created_by_id = created_by_id;
    }

    public Date getFecha_creacion_object() {
        return fecha_creacion_object;
    }

    public void setFecha_creacion_object(Date fecha_creacion_object) {
        this.fecha_creacion_object = fecha_creacion_object;
    }

}
