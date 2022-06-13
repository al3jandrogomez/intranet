package com.defensoria.integral.model.informes;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.TiposCarpetas;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="tblinformes")
public class Informe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idInforme;
    @OneToOne
    @JoinColumn(name="cveUsuario")
    private Personal personal;
    @OneToOne
    @JoinColumn(name="cveAdscripcion")
    private Adscripciones adscripcion;
    private Integer cveMes;
    private Integer anio;
    private String rutaArchivo;
    private String activo;
    @CreationTimestamp
     @Temporal(TemporalType.TIMESTAMP)
     @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    private String estatusInforme;
    @OneToOne
    @JoinColumn(name = "cveTipoCarpeta")
    private TiposCarpetas tipoCarpeta;
    @OneToOne
    @JoinColumn(name="idCarpeta")
    private Carpeta carpeta;

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }


    public Carpeta getCarpeta() {
        return this.carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }
    


    public TiposCarpetas getTipoCarpeta() {
        return this.tipoCarpeta;
    }

    public void setTipoCarpeta(TiposCarpetas tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
    }



    public String getEstatusInforme() {
        return this.estatusInforme;
    }

    public void setEstatusInforme(String estatusInforme) {
        this.estatusInforme = estatusInforme;
    }


    public Integer getIdInforme() {
        return this.idInforme;
    }

    public void setIdInforme(Integer idInforme) {
        this.idInforme = idInforme;
    }

    public Personal getPersonal() {
        return this.personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public Adscripciones getAdscripcion() {
        return this.adscripcion;
    }

    public void setAdscripcion(Adscripciones adscripcion) {
        this.adscripcion = adscripcion;
    }

    public Integer getCveMes() {
        return this.cveMes;
    }

    public void setCveMes(Integer cveMes) {
        this.cveMes = cveMes;
    }

    public String getRutaArchivo() {
        return this.rutaArchivo;
    }

    public void setRutaArchivo(String rutaArchivo) {
        this.rutaArchivo = rutaArchivo;
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

    public void setFechaRegistro(Date fechaRegitro) {
        this.fechaRegistro = fechaRegitro;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }



}
