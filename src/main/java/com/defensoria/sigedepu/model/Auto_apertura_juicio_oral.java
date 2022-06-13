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
@Table(name = "auto_apertura_juicio_oral")
public class Auto_apertura_juicio_oral {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    private Integer organo_jurisdiccional_juicio_id;
    private Date fecha_audiencia_juicio;
    private String nombre_acusado;
    private String medios_prueba_defensa;
    private String observaciones;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id")
    private Defensa defensa;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "distrito_id")
    private Distrito distrito;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    private Integer antecedente;
    private Integer cedula_antecedente_id;
    private Date fecha_fallo;
    private Date fecha_sentencia;
    private Integer hay_prueba_superveniente;
    private Integer created_by_id;
    private Integer fallo_condenatoria;
    private Integer fallo_absolutoria;
    private Date fecha_audiencia_individual;
    private Date fecha_creacion_object;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrgano_jurisdiccional_juicio_id() {
        return organo_jurisdiccional_juicio_id;
    }

    public void setOrgano_jurisdiccional_juicio_id(Integer organo_jurisdiccional_juicio_id) {
        this.organo_jurisdiccional_juicio_id = organo_jurisdiccional_juicio_id;
    }

    public Date getFecha_audiencia_juicio() {
        return fecha_audiencia_juicio;
    }

    public void setFecha_audiencia_juicio(Date fecha_audiencia_juicio) {
        this.fecha_audiencia_juicio = fecha_audiencia_juicio;
    }

    

    public String getNombre_acusado() {
        return nombre_acusado;
    }

    public void setNombre_acusado(String nombre_acusado) {
        this.nombre_acusado = nombre_acusado;
    }

    public String getMedios_prueba_defensa() {
        return medios_prueba_defensa;
    }

    public void setMedios_prueba_defensa(String medios_prueba_defensa) {
        this.medios_prueba_defensa = medios_prueba_defensa;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
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

    public Integer getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(Integer antecedente) {
        this.antecedente = antecedente;
    }

    public Integer getCedula_antecedente_id() {
        return cedula_antecedente_id;
    }

    public void setCedula_antecedente_id(Integer cedula_antecedente_id) {
        this.cedula_antecedente_id = cedula_antecedente_id;
    }

    public Date getFecha_fallo() {
        return fecha_fallo;
    }

    public void setFecha_fallo(Date fecha_fallo) {
        this.fecha_fallo = fecha_fallo;
    }

    public Date getFecha_sentencia() {
        return fecha_sentencia;
    }

    public void setFecha_sentencia(Date fecha_sentencia) {
        this.fecha_sentencia = fecha_sentencia;
    }

    public Integer getHay_prueba_superveniente() {
        return hay_prueba_superveniente;
    }

    public void setHay_prueba_superveniente(Integer hay_prueba_superveniente) {
        this.hay_prueba_superveniente = hay_prueba_superveniente;
    }

    public Integer getCreated_by_id() {
        return created_by_id;
    }

    public void setCreated_by_id(Integer created_by_id) {
        this.created_by_id = created_by_id;
    }

    public Integer getFallo_condenatoria() {
        return fallo_condenatoria;
    }

    public void setFallo_condenatoria(Integer fallo_condenatoria) {
        this.fallo_condenatoria = fallo_condenatoria;
    }

    public Integer getFallo_absolutoria() {
        return fallo_absolutoria;
    }

    public void setFallo_absolutoria(Integer fallo_absolutoria) {
        this.fallo_absolutoria = fallo_absolutoria;
    }

    public Date getFecha_audiencia_individual() {
        return fecha_audiencia_individual;
    }

    public void setFecha_audiencia_individual(Date fecha_audiencia_individual) {
        this.fecha_audiencia_individual = fecha_audiencia_individual;
    }

   
    public Date getFecha_creacion_object() {
        return fecha_creacion_object;
    }

    public void setFecha_creacion_object(Date fecha_creacion_object) {
        this.fecha_creacion_object = fecha_creacion_object;
    }

}
