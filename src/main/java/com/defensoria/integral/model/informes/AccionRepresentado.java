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

import com.defensoria.integral.model.sigedepu2.Accion;
import com.defensoria.integral.model.sigedepu2.Representados;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
 @Table(name="tblacciones_representados")
public class AccionRepresentado {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAccion_Representado;
    @OneToOne
    @JoinColumn(name="cveAccion")
    private Accion accion;
    @OneToOne
    @JoinColumn(name = "idRepresentado")
    private Representados representado;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;


    public Integer getIdAccion_Representado() {
        return this.idAccion_Representado;
    }

    public void setIdAccion_Representado(Integer idAccion_Representado) {
        this.idAccion_Representado = idAccion_Representado;
    }

    public Accion getAccion() {
        return this.accion;
    }

    public void setAccion(Accion accion) {
        this.accion = accion;
    }

    public Representados getRepresentado() {
        return this.representado;
    }

    public void setRepresentado(Representados representado) {
        this.representado = representado;
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
