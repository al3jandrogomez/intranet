package com.defensoria.integral.model.adminpersonal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblformularios")
public class Formularios implements Comparable< Formularios >{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cveFormulario;
    private String descFormulario;
    private String rutaFormulario;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveModulo")
    private Modulos modulos;
    private Integer cveFormularioPadre;

    @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cveFormulario")
    @Where(clause = "activo='S'")
    private List<PermisosPerfiles> permisosPerfiles;

   @JsonIgnore
    @OneToMany
    @JoinColumn(name = "cveFormulario")
    @Where(clause = "activo='S'")
    private List<PermisosPersonal> permisosPersonal;

    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    
    private Integer orden;
    

    public Integer getCveFormulario() {
        return cveFormulario;
    }

    public void setCveFormulario(Integer cveFormulario) {
        this.cveFormulario = cveFormulario;
    }

    public String getDescFormulario() {
        return descFormulario;
    }

    public void setDescFormulario(String descFormulario) {
        this.descFormulario = descFormulario;
    }

    public String getRutaFormulario() {
        return rutaFormulario;
    }

    public void setRutaFormulario(String rutaFormulario) {
        this.rutaFormulario = rutaFormulario;
    }

    public Modulos getModulos() {
        return modulos;
    }

    public void setModulos(Modulos modulos) {
        this.modulos = modulos;
    }

    public List<PermisosPerfiles> getPermisosPerfiles() {
        return permisosPerfiles;
    }

    public void setPermisosPerfiles(List<PermisosPerfiles> permisosPerfiles) {
        this.permisosPerfiles = permisosPerfiles;
    }

    public List<PermisosPersonal> getPermisosPersonal() {
        return permisosPersonal;
    }

    public void setPermisosPersonal(List<PermisosPersonal> permisosPersonal) {
        this.permisosPersonal = permisosPersonal;
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

    public Integer getCveFormularioPadre() {
        return cveFormularioPadre;
    }

    public void setCveFormularioPadre(Integer cveFormularioPadre) {
        this.cveFormularioPadre = cveFormularioPadre;
    }

    public Integer getOrden() {
        return orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }

    @Override
    public int compareTo(Formularios o) {
        return this.getOrden().compareTo(o.getOrden()); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
