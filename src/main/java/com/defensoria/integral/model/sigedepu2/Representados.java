/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import com.defensoria.integral.model.adminpersonal.Municipios;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "tblrepresentados")
public class Representados {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idRepresentado;
    private String nombre;
    private String paterno;
    private String materno;
    private Date fechaNacimiento;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveMunicipio")
    private Municipios municipio;
    private Integer cveSexo;
    private Integer edad;
    private String activo;
    private String descDiscapacidad;
    private String correo;
    private String curp;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveEtnia")
    private Etnia etnia;
    @OneToMany(mappedBy = "representado" )
    private List<DelitosRepresentados> delitosRepresentados;
    @OneToMany(mappedBy = "representado" )
    private List<RepresentadoGruposVulnerables> gruposRepresentados;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdRepresentado() {
        return idRepresentado;
    }

    public void setIdRepresentado(Integer idRepresentado) {
        this.idRepresentado = idRepresentado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPaterno() {
        return paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }

    public String getMaterno() {
        return materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Municipios getMunicipio() {
        return municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }

    public Integer getCveSexo() {
        return cveSexo;
    }

    public void setCveSexo(Integer cveSexo) {
        this.cveSexo = cveSexo;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public String getDescDiscapacidad() {
        return descDiscapacidad;
    }

    public void setDescDiscapacidad(String descDiscapacidad) {
        this.descDiscapacidad = descDiscapacidad;
    }

    public Etnia getEtnia() {
        return etnia;
    }

    public void setEtnia(Etnia etnia) {
        this.etnia = etnia;
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

    public List<DelitosRepresentados> getDelitosRepresentados() {
        return delitosRepresentados;
    }

    public void setDelitosRepresentados(List<DelitosRepresentados> delitosRepresentados) {
        this.delitosRepresentados = delitosRepresentados;
    }

    public List<RepresentadoGruposVulnerables> getGruposRepresentados() {
        return gruposRepresentados;
    }

    public void setGruposRepresentados(List<RepresentadoGruposVulnerables> gruposRepresentados) {
        this.gruposRepresentados = gruposRepresentados;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }
    
    

    
    
    

}
