package com.defensoria.integral.model.informes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblasesoriasinf")
public class AsesoriaInf {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAsesoriaInf;
    private String materia;
    private String nombre_juzgado;
    private String nombre_representado;
    private String paterno_representado;
    private String materno_representado;
    private String sexo;
    private String edad;
    private String grupo_vulnerable;
    private String etnia;
    private String discapacidad;
    private Integer cveDistrito;
    private Integer cveRegion;
    private Integer tipoExpediente;
    private Integer cveMes;
    private Integer anio;
    private Integer cveUsuario;


    public Integer getCveUsuario() {
        return this.cveUsuario;
    }

    public void setCveUsuario(Integer cveUsuario) {
        this.cveUsuario = cveUsuario;
    }




    public Integer getIdAsesoriaInf() {
        return this.idAsesoriaInf;
    }

    public void setIdAsesoriaInf(Integer idAsesoriaInf) {
        this.idAsesoriaInf = idAsesoriaInf;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getNombre_juzgado() {
        return this.nombre_juzgado;
    }

    public void setNombre_juzgado(String nombre_juzgado) {
        this.nombre_juzgado = nombre_juzgado;
    }

    public String getNombre_representado() {
        return this.nombre_representado;
    }

    public void setNombre_representado(String nombre_representado) {
        this.nombre_representado = nombre_representado;
    }

    public String getPaterno_representado() {
        return this.paterno_representado;
    }

    public void setPaterno_representado(String paterno_representado) {
        this.paterno_representado = paterno_representado;
    }

    public String getMaterno_representado() {
        return this.materno_representado;
    }

    public void setMaterno_representado(String materno_representado) {
        this.materno_representado = materno_representado;
    }

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return this.edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getGrupo_vulnerable() {
        return this.grupo_vulnerable;
    }

    public void setGrupo_vulnerable(String grupo_vulnerable) {
        this.grupo_vulnerable = grupo_vulnerable;
    }

    public String getEtnia() {
        return this.etnia;
    }

    public void setEtnia(String etnia) {
        this.etnia = etnia;
    }

    public String getDiscapacidad() {
        return this.discapacidad;
    }

    public void setDiscapacidad(String discapacidad) {
        this.discapacidad = discapacidad;
    }

    public Integer getCveDistrito() {
        return this.cveDistrito;
    }

    public void setCveDistrito(Integer cveDistrito) {
        this.cveDistrito = cveDistrito;
    }

    public Integer getCveRegion() {
        return this.cveRegion;
    }

    public void setCveRegion(Integer cveRegion) {
        this.cveRegion = cveRegion;
    }

    public Integer getTipoExpediente() {
        return this.tipoExpediente;
    }

    public void setTipoExpediente(Integer tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public Integer getCveMes() {
        return this.cveMes;
    }

    public void setCveMes(Integer cveMes) {
        this.cveMes = cveMes;
    }

    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }


    
    
}
