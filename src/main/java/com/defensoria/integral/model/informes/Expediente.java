package com.defensoria.integral.model.informes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblexpedientes")
public class Expediente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idExpediente;
    private String numeroExpediente;
    private String anioExpediente;
    private Integer tipoExpediente;
    private String juzgado;
    private String nombre;
    private String paterno;
    private String materno;
    private Integer cveDistrito;
    private Integer cveRegion;
    private String edad;
    private String grupo_vulnerable;
    private String etnia;
    private String discapacidad;
    private String sexo;
    private String numCarpetaInvestigacion;
    private Integer cveMes;
    private Integer anio;
    private String tipoCarpeta;
    private String accion;
    private String linea;
    private String delito;
    private String delito2;
    private String delito3;
    private String municipio;
    private String tipo_audiencia;
    private String conclusion;
    private String determinacion;
    private String interno_libertad;
    private Integer cveAdscripcion;
    private Integer cveUsuario;


    public Integer getCveUsuario() {
        return this.cveUsuario;
    }

    public void setCveUsuario(Integer cveUsuario) {
        this.cveUsuario = cveUsuario;
    }



    public Integer getCveAdscripcion() {
        return this.cveAdscripcion;
    }

    public void setCveAdscripcion(Integer cveAdscripcion) {
        this.cveAdscripcion = cveAdscripcion;
    }



    public String getInterno_libertad() {
        return this.interno_libertad;
    }

    public void setInterno_libertad(String interno_libertad) {
        this.interno_libertad = interno_libertad;
    }



    public String getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getTipo_audiencia() {
        return this.tipo_audiencia;
    }

    public void setTipo_audiencia(String tipo_audiencia) {
        this.tipo_audiencia = tipo_audiencia;
    }

    public String getConclusion() {
        return this.conclusion;
    }

    public void setConclusion(String conclusion) {
        this.conclusion = conclusion;
    }

    public String getDeterminacion() {
        return this.determinacion;
    }

    public void setDeterminacion(String determinacion) {
        this.determinacion = determinacion;
    }



    public String getDelito() {
        return this.delito;
    }

    public void setDelito(String delito) {
        this.delito = delito;
    }

    public String getDelito2() {
        return this.delito2;
    }

    public void setDelito2(String delito2) {
        this.delito2 = delito2;
    }

    public String getDelito3() {
        return this.delito3;
    }

    public void setDelito3(String delito3) {
        this.delito3 = delito3;
    }


    public String getLinea() {
        return this.linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }


    public Integer getIdExpediente() {
        return this.idExpediente;
    }

    public void setIdExpediente(Integer idExpediente) {
        this.idExpediente = idExpediente;
    }

    public String getNumeroExpediente() {
        return this.numeroExpediente;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getAnioExpediente() {
        return this.anioExpediente;
    }

    public void setAnioExpediente(String anioExpediente) {
        this.anioExpediente = anioExpediente;
    }

    public Integer getTipoExpediente() {
        return this.tipoExpediente;
    }

    public void setTipoExpediente(Integer tipoExpediente) {
        this.tipoExpediente = tipoExpediente;
    }

    public String getJuzgado() {
        return this.juzgado;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
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
    

    public String getSexo() {
        return this.sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getNumCarpetaInvestigacion() {
        return this.numCarpetaInvestigacion;
    }

    public void setNumCarpetaInvestigacion(String numCarpetaInvestigacion) {
        this.numCarpetaInvestigacion = numCarpetaInvestigacion;
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


    public String getTipoCarpeta() {
        return this.tipoCarpeta;
    }

    public void setTipoCarpeta(String tipoCarpeta) {
        this.tipoCarpeta = tipoCarpeta;
    }

    public String getAccion() {
        return this.accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }
    
    
}
