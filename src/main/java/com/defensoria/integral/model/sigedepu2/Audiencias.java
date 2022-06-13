/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.sigedepu2;

import com.defensoria.integral.model.adminpersonal.Personal;
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
@Table(name = "tblaudiencias")
public class Audiencias {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idAudiencia;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "idCarpeta")
    private Carpeta carpeta;
//    private Integer idCarpeta;
    private Date fechaAudiencia;
    private Date fechaVinculacion;
    private Date fechaControlDetencion;
    private Date fechaFormulacionImputacion;
    private Date fechaPeriodoInvInicia;
    private Date fechaPeriodoInvFin;
    private Date fechaInicioJuicioOral;
    private Date fechaProximaAudiencia;
    private Date fechaPeticionPartes;
    private Date fechaAcuerdoRetencion;
    private Date fechaAcusacion;
    private Date fechaDescubrimiento;
    private Date fechaIntermedia;
    private Date fechaApertura;
    private Date fechaAlegato;
    private Date fechaFallo;
    private Date fechaIndividualizacion;
    private Date fechaLecturaSentencia;
    private Date fechaSancion;
    private Date fechaCita;
    private Date fechaProrroga;
    private Date fechaMedidaCautelar;
    private Date fechaRadicacion;
    private Date fechaDebate;
    private Date fechaAlegatoCierre;
    private Date fechaContinuacion;
    private Date fechaPruebaNueva;
    private Date fechaAceptacion;
    private Date fechaAmonestacion;
    private Date fechaActividades;
    
    
    
    
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveTipoAudiencia")
    private TiposAudiencias tipoAudiencia;
//    private Integer cveTipoAudiencia;
    private Integer cveDefensorSig;
//    private Integer cveDefensorPersonal;
     @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveDefensorPersonal")
    private Personal personal;
    private String observaciones;
    private String activo;
    private String interno;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cveRecurso")
    private Recurso recurso;
    @OneToMany(mappedBy = "audiencia")
    private List<Conclusion> conclusiones;
    @OneToMany(mappedBy = "audiencia")
    private List<AudienciaMedidaCautelar> medidasCautelares;
    private Integer defensa_id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;

    public Integer getIdAudiencia() {
        return idAudiencia;
    }

    public void setIdAudiencia(Integer idAudiencia) {
        this.idAudiencia = idAudiencia;
    }

    public Carpeta getCarpeta() {
        return carpeta;
    }

    public void setCarpeta(Carpeta carpeta) {
        this.carpeta = carpeta;
    }

    public Date getFechaAudiencia() {
        return fechaAudiencia;
    }

    public void setFechaAudiencia(Date fechaAudiencia) {
        this.fechaAudiencia = fechaAudiencia;
    }

    public Date getFechaVinculacion() {
        return fechaVinculacion;
    }

    public void setFechaVinculacion(Date fechaVinculacion) {
        this.fechaVinculacion = fechaVinculacion;
    }

    public Date getFechaControlDetencion() {
        return fechaControlDetencion;
    }

    public void setFechaControlDetencion(Date fechaControlDetencion) {
        this.fechaControlDetencion = fechaControlDetencion;
    }

    public Date getFechaFormulacionImputacion() {
        return fechaFormulacionImputacion;
    }

    public void setFechaFormulacionImputacion(Date fechaFormulacionImputacion) {
        this.fechaFormulacionImputacion = fechaFormulacionImputacion;
    }

    public Date getFechaPeriodoInvInicia() {
        return fechaPeriodoInvInicia;
    }

    public void setFechaPeriodoInvInicia(Date fechaPeriodoInvInicia) {
        this.fechaPeriodoInvInicia = fechaPeriodoInvInicia;
    }

    public Date getFechaPeriodoInvFin() {
        return fechaPeriodoInvFin;
    }

    public void setFechaPeriodoInvFin(Date fechaPeriodoInvFin) {
        this.fechaPeriodoInvFin = fechaPeriodoInvFin;
    }

    public Date getFechaInicioJuicioOral() {
        return fechaInicioJuicioOral;
    }

    public void setFechaInicioJuicioOral(Date fechaInicioJuicioOral) {
        this.fechaInicioJuicioOral = fechaInicioJuicioOral;
    }

    public TiposAudiencias getTipoAudiencia() {
        return tipoAudiencia;
    }

    public void setTipoAudiencia(TiposAudiencias tipoAudiencia) {
        this.tipoAudiencia = tipoAudiencia;
    }

    public Integer getCveDefensorSig() {
        return cveDefensorSig;
    }

    public void setCveDefensorSig(Integer cveDefensorSig) {
        this.cveDefensorSig = cveDefensorSig;
    }

//    public Integer getCveDefensorPersonal() {
//        return cveDefensorPersonal;
//    }
//
//    public void setCveDefensorPersonal(Integer cveDefensorPersonal) {
//        this.cveDefensorPersonal = cveDefensorPersonal;
//    }
    
    

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getActivo() {
        return activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getDefensa_id() {
        return defensa_id;
    }

    public void setDefensa_id(Integer defensa_id) {
        this.defensa_id = defensa_id;
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

    public List<Conclusion> getConclusiones() {
        return conclusiones;
    }

    public void setConclusiones(List<Conclusion> conclusiones) {
        this.conclusiones = conclusiones;
    }

    public String getInterno() {
        return interno;
    }

    public void setInterno(String interno) {
        this.interno = interno;
    }

    public Date getFechaProximaAudiencia() {
        return fechaProximaAudiencia;
    }

    public void setFechaProximaAudiencia(Date fechaProximaAudiencia) {
        this.fechaProximaAudiencia = fechaProximaAudiencia;
    }

    public Recurso getRecurso() {
        return recurso;
    }

    public void setRecurso(Recurso recurso) {
        this.recurso = recurso;
    }

    public Personal getPersonal() {
        return personal;
    }

    public void setPersonal(Personal personal) {
        this.personal = personal;
    }

    public List<AudienciaMedidaCautelar> getMedidasCautelares() {
        return medidasCautelares;
    }

    public void setMedidasCautelares(List<AudienciaMedidaCautelar> medidasCautelares) {
        this.medidasCautelares = medidasCautelares;
    }

    public Date getFechaPeticionPartes() {
        return fechaPeticionPartes;
    }

    public void setFechaPeticionPartes(Date fechaPeticionPartes) {
        this.fechaPeticionPartes = fechaPeticionPartes;
    }

    public Date getFechaAcuerdoRetencion() {
        return fechaAcuerdoRetencion;
    }

    public void setFechaAcuerdoRetencion(Date fechaAcuerdoRetencion) {
        this.fechaAcuerdoRetencion = fechaAcuerdoRetencion;
    }

    public Date getFechaAcusacion() {
        return fechaAcusacion;
    }

    public void setFechaAcusacion(Date fechaAcusacion) {
        this.fechaAcusacion = fechaAcusacion;
    }

    public Date getFechaDescubrimiento() {
        return fechaDescubrimiento;
    }

    public void setFechaDescubrimiento(Date fechaDescubrimiento) {
        this.fechaDescubrimiento = fechaDescubrimiento;
    }

    public Date getFechaIntermedia() {
        return fechaIntermedia;
    }

    public void setFechaIntermedia(Date fechaIntermedia) {
        this.fechaIntermedia = fechaIntermedia;
    }

    public Date getFechaApertura() {
        return fechaApertura;
    }

    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }

    public Date getFechaAlegato() {
        return fechaAlegato;
    }

    public void setFechaAlegato(Date fechaAlegato) {
        this.fechaAlegato = fechaAlegato;
    }

    public Date getFechaFallo() {
        return fechaFallo;
    }

    public void setFechaFallo(Date fechaFallo) {
        this.fechaFallo = fechaFallo;
    }

    public Date getFechaIndividualizacion() {
        return fechaIndividualizacion;
    }

    public void setFechaIndividualizacion(Date fechaIndividualizacion) {
        this.fechaIndividualizacion = fechaIndividualizacion;
    }

    public Date getFechaLecturaSentencia() {
        return fechaLecturaSentencia;
    }

    public void setFechaLecturaSentencia(Date fechaLecturaSentencia) {
        this.fechaLecturaSentencia = fechaLecturaSentencia;
    }

    public Date getFechaSancion() {
        return fechaSancion;
    }

    public void setFechaSancion(Date fechaSancion) {
        this.fechaSancion = fechaSancion;
    }

    public Date getFechaCita() {
        return fechaCita;
    }

    public void setFechaCita(Date fechaCita) {
        this.fechaCita = fechaCita;
    }

    public Date getFechaProrroga() {
        return fechaProrroga;
    }

    public void setFechaProrroga(Date fechaProrroga) {
        this.fechaProrroga = fechaProrroga;
    }

    public Date getFechaMedidaCautelar() {
        return fechaMedidaCautelar;
    }

    public void setFechaMedidaCautelar(Date fechaMedidaCautelar) {
        this.fechaMedidaCautelar = fechaMedidaCautelar;
    }

    public Date getFechaRadicacion() {
        return fechaRadicacion;
    }

    public void setFechaRadicacion(Date fechaRadicacion) {
        this.fechaRadicacion = fechaRadicacion;
    }

    public Date getFechaDebate() {
        return fechaDebate;
    }

    public void setFechaDebate(Date fechaDebate) {
        this.fechaDebate = fechaDebate;
    }

    public Date getFechaAlegatoCierre() {
        return fechaAlegatoCierre;
    }

    public void setFechaAlegatoCierre(Date fechaAlegatoCierre) {
        this.fechaAlegatoCierre = fechaAlegatoCierre;
    }

    public Date getFechaContinuacion() {
        return fechaContinuacion;
    }

    public void setFechaContinuacion(Date fechaContinuacion) {
        this.fechaContinuacion = fechaContinuacion;
    }

    public Date getFechaPruebaNueva() {
        return fechaPruebaNueva;
    }

    public void setFechaPruebaNueva(Date fechaPruebaNueva) {
        this.fechaPruebaNueva = fechaPruebaNueva;
    }

    public Date getFechaAceptacion() {
        return fechaAceptacion;
    }

    public void setFechaAceptacion(Date fechaAceptacion) {
        this.fechaAceptacion = fechaAceptacion;
    }

    public Date getFechaAmonestacion() {
        return fechaAmonestacion;
    }

    public void setFechaAmonestacion(Date fechaAmonestacion) {
        this.fechaAmonestacion = fechaAmonestacion;
    }

    public Date getFechaActividades() {
        return fechaActividades;
    }

    public void setFechaActividades(Date fechaActividades) {
        this.fechaActividades = fechaActividades;
    }

}
