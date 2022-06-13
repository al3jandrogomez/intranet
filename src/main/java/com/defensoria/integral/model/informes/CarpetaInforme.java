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

import com.defensoria.integral.model.sigedepu2.Carpeta;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name ="tblcarpetasinformes")
public class CarpetaInforme {
    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private Integer idCarpetaInforme;
    @OneToOne
    @JoinColumn(name = "idCarpeta")
    private Carpeta carpeta;
    @OneToOne
    @JoinColumn(name = "idInforme")
    private Informe informe;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;


    public Integer getIdCarpetaInforme() {
        return this.idCarpetaInforme;
    }

    public void setIdCarpetaInforme(Integer idCarpetaInforme) {
        this.idCarpetaInforme = idCarpetaInforme;
    }

    public Carpeta getCarpeta() {
        return this.carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    public Informe getInforme() {
        return this.informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
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

    public void setFechaRegistro(Date fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Date getFechaActualizacion() {
        return this.fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }


    
}
