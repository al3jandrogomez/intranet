package com.defensoria.sigedepu.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="fase")
public class Fase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
   
    @OneToOne
    @JoinColumn(name = "fase_asunto_id", referencedColumnName = "id")
    private FaseAsunto faseAsunto;
    private String observaciones;
    private Date fecha_ingreso_fase;
    private Date fecha_real_fase;
    private Date fecha_maxima_atencion;
    @OneToOne
    @JoinColumn(name = "representacion_id", referencedColumnName = "id")
    private Representacion representacion;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public FaseAsunto getFaseAsunto() {
        return this.faseAsunto;
    }

    public void setFaseAsunto(FaseAsunto faseAsunto) {
        this.faseAsunto = faseAsunto;
    }
   

    public String getObservaciones() {
        return this.observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha_ingreso_fase() {
        return this.fecha_ingreso_fase;
    }

    public void setFecha_ingreso_fase(Date fecha_ingreso_fase) {
        this.fecha_ingreso_fase = fecha_ingreso_fase;
    }

    public Date getFecha_real_fase() {
        return this.fecha_real_fase;
    }

    public void setFecha_real_fase(Date fecha_real_fase) {
        this.fecha_real_fase = fecha_real_fase;
    }

    public Date getFecha_maxima_atencion() {
        return this.fecha_maxima_atencion;
    }

    public void setFecha_maxima_atencion(Date fecha_maxima_atencion) {
        this.fecha_maxima_atencion = fecha_maxima_atencion;
    }

    public Representacion getRepresentacion() {
        return this.representacion;
    }

    public void setRepresentacion(Representacion representacion) {
        this.representacion = representacion;
    }

    

    
}
