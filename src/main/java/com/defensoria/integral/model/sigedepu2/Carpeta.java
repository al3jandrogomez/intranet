/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.subtecnica.Materia;

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
 * @author alejandro.gomez
 */
@Entity
@Table(name = "tblcarpetas")
public class Carpeta {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idCarpeta;
    private Integer numero;
    private Integer anio;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveAdscripcion")
    private Adscripciones adscripcion;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoCarpeta")
    private TiposCarpetas tipoCarpeta;
    private String activo;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveUsuario")
    private Personal personal;
    private String nuc;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    @OneToOne
    @JoinColumn(name = "cveMateria")
    private Materia materia;
    private String caravana;
    private String linea;
    private Date fechaRadicacion;
    private String pruebas;
    private String numAdmin;


    public String getNumAdmin() {
        return this.numAdmin;
    }

    public void setNumAdmin(String numAdmin) {
        this.numAdmin = numAdmin;
    }

    

    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }



    public String getPruebas() {
        return this.pruebas;
    }

    public void setPruebas(String pruebas) {
        this.pruebas = pruebas;
    }




    public String getCaravana() {
        return this.caravana;
    }

    public void setCaravana(String caravana) {
        this.caravana = caravana;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }

    

    public Date getFechaRadicacion() {
        return this.fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }


    public Integer getIdCarpeta() {
        return idCarpeta;
    }

    public void setIdCarpeta(Integer idCarpeta) {
        this.idCarpeta = idCarpeta;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Adscripciones getAdscripcion() {
        return adscripcion;
    }

    public void setAdscripcion(Adscripciones adscripcion) {
        this.adscripcion = adscripcion;
    }

    public TiposCarpetas getTipoCarpeta() {
        return tipoCarpeta;
    }

    public void setTipoCarpeta(TiposCarpetas tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
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

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    

    public String getNuc() {
        return nuc;
    }

    public void setNuc(String nuc) {
        this.nuc = nuc;
    }

}
