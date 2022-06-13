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

import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.model.subtecnica.Materia;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "tblasesorias_informes")
public class AsesoriaInforme {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAsesoria;
    @OneToOne
    @JoinColumn(name = "idRepresentado")
    private Representados representando;
    @OneToOne
    @JoinColumn(name = "cveMateria")
    private Materia materia;
    private String activo;
    @OneToOne
    @JoinColumn(name = "idInforme")
    private Informe informe;
    @CreationTimestamp
    @Column(updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActulizacion;
    private Date fecha_asesoria;


    public Informe getInforme() {
        return this.informe;
    }

    public void setInforme(Informe informe) {
        this.informe = informe;
    }

    public Date getFecha_asesoria() {
        return this.fecha_asesoria;
    }

    public void setFecha_asesoria(Date fecha_asesoria) {
        this.fecha_asesoria = fecha_asesoria;
    }


    public Integer getIdAsesoria() {
        return this.idAsesoria;
    }

    public void setIdAsesoria(Integer idAsesoria) {
        this.idAsesoria = idAsesoria;
    }

    public Representados getRepresentando() {
        return this.representando;
    }

    public void setRepresentando(Representados representando) {
        this.representando = representando;
    }

    public Materia getMateria() {
        return this.materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
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

    public Date getFechaActulizacion() {
        return this.fechaActulizacion;
    }

    public void setFechaActulizacion(Date fechaActulizacion) {
        this.fechaActulizacion = fechaActulizacion;
    }

}
