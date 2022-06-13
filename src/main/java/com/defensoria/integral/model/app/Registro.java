package com.defensoria.integral.model.app;

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

import com.defensoria.integral.model.adminpersonal.Municipios;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;



@Entity
@Table(name="tblregistro")
public class Registro {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer idRegistro;
    private String nombre;
    private String paterno;
    private String materno;
    @OneToOne
    @JoinColumn( name="cveMunicipio")
    private Municipios municipios;
    private String municipio;
    private String curp;
    private String email;
    private String contrasena;
    private String activo;
    @CreationTimestamp
   @Temporal(TemporalType.TIMESTAMP)
   @Column(updatable = false)
   private Date fechaRegistro;
   @UpdateTimestamp
   @Temporal(TemporalType.TIMESTAMP)
   private Date fechaActualizacion;


    public Integer getIdRegistro() {
        return this.idRegistro;
    }

    public void setIdRegistro(Integer idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return this.paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return this.materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

  

    public String getCurp() {
        return this.curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return this.contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
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


    public Municipios getMunicipios() {
        return this.municipios;
    }

    public void setMunicipios(Municipios municipios) {
        this.municipios = municipios;
    }


    
}
