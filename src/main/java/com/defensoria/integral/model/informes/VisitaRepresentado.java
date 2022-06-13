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
import com.defensoria.integral.model.sigedepu2.Representados;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tblvisitasrepresentados")
public class VisitaRepresentado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idVisitaRepresentado;
    @OneToOne
    @JoinColumn(name = "idCarpeta")
    private Carpeta carpeta;
    @OneToOne
    @JoinColumn(name = "idInforme")
    private Informe informe;
    @OneToOne
    @JoinColumn(name = "idRepresentado")
    private Representados representado;
    private Date fechaVisita;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;


    public Carpeta getCarpeta() {
        return this.carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }


    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getIdVisitaRepresentado() {
        return this.idVisitaRepresentado;
    }

    public void setIdVisitaRepresentado(Integer idVisitaRepresentado) {
        this.idVisitaRepresentado = idVisitaRepresentado;
    }


    public Informe getInforme() {
        return this.informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }
    
    public Representados getRepresentado() {
        return this.representado;
    }

    public void setRepresentado(Representados representado) {
        this.representado = representado;
    }

    public Date getFechaVisita() {
        return this.fechaVisita;
    }

    public void setFechaVisita(Date fechaVisita) {
        this.fechaVisita = fechaVisita;
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
