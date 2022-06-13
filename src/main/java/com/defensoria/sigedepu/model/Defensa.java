/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.sigedepu.model;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author alejandro.gomez
 */
@Entity
@Table(name = "defensa")
public class Defensa {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "solicitud_atencion_id")
    private SolicitudAtencion solicitud;
//    private Integer solicitud_atencion_id;
    private String estatus;
    private Integer usuario_id;
    private String path_ecm;
    private String nuc;
    private String proceso;
    private String toca_numero;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_inicio_termino;
    private String hora_inicio_termino;
    private String observaciones;
    private Integer abogado_anterior;
    private Integer otro_delito;
    private String cuerpo_normativo;
    private String articulo;
    private String descripcion_legal;
    private String nombre_solicitante;
    private String dependencia_solicitante;
    private String cargo_solicitante;
    private Integer edad_delito;
    private Integer delito_grave;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_reapertura;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_solicitud_reapertura;
    private Integer autorizador_reapertura_id;
    private String motivo_reapertura;
    private String motivo_rechazo_reapertura;
    private Integer reapertura;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_presentacion;
    private String hora_presentacion;
    private String lugar_presentacion;
//    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha_reasignacion_distrito;
    private Integer reasignar;
    private String carpeta_investigacion;
    private String tipo_audiencia;

    @OneToMany(mappedBy = "defensa")
    private List<Acusacion> acusacion;
    @OneToMany(mappedBy = "defensa")
    private List<AudienciaIntermedia> audienciaIntermedia;
    @OneToMany(mappedBy = "defensa")
    private List<Auto_apertura_juicio_oral> autoAperturajuiciooral;
    @OneToMany(mappedBy = "defensa")
    private List<ConclusionDefensa> conclusionDefensa;
    @OneToMany(mappedBy = "defensa")
    private List<DefensaDefensor> defensaDefensor;
    @OneToMany(mappedBy = "defensa")
    private List<DefensaDelito> defensaDelito;
    @OneToMany(mappedBy = "defensa")
    private List<Determinacion> determinacion;
    @OneToMany(mappedBy = "defensa")
    private List<HistoricoDefensa> historicoDefensa;
    @OneToMany(mappedBy = "defensa")
    private List<Imputacion> imputacion;
    @OneToMany(mappedBy = "defensa")
    private List<InvestigacionComplementaria> investigacionComplementaria;
    @OneToMany(mappedBy = "defensa")
    private List<MedidaCautelar> medidaCautelar;
    @OneToMany(mappedBy = "defensa")
    private List<OtraAudiencia> otraAudiencia;
    @OneToMany(mappedBy = "defensa")
    private List<RadicacionEjecucion> radicacionEjecucion;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

//    public Integer getSolicitud_atencion_id() {
//        return solicitud_atencion_id;
//    }
//
//    public void setSolicitud_atencion_id(Integer solicitud_atencion_id) {
//        this.solicitud_atencion_id = solicitud_atencion_id;
//    }
    public SolicitudAtencion getSolicitud() {
        return solicitud;
    }

    public void setSolicitud(SolicitudAtencion solicitud) {
        this.solicitud = solicitud;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public String getPath_ecm() {
        return path_ecm;
    }

    public void setPath_ecm(String path_ecm) {
        this.path_ecm = path_ecm;
    }

    public String getNuc() {
        return nuc;
    }

    public void setNuc(String nuc) {
        this.nuc = nuc;
    }

    public String getProceso() {
        return proceso;
    }

    public void setProceso(String proceso) {
        this.proceso = proceso;
    }

    public String getToca_numero() {
        return toca_numero;
    }

    public void setToca_numero(String toca_numero) {
        this.toca_numero = toca_numero;
    }

    public Date getFecha_inicio_termino() {
        return fecha_inicio_termino;
    }

    public void setFecha_inicio_termino(Date fecha_inicio_termino) {
        this.fecha_inicio_termino = fecha_inicio_termino;
    }

    public String getHora_inicio_termino() {
        return hora_inicio_termino;
    }

    public void setHora_inicio_termino(String hora_inicio_termino) {
        this.hora_inicio_termino = hora_inicio_termino;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Integer getAbogado_anterior() {
        return abogado_anterior;
    }

    public void setAbogado_anterior(Integer abogado_anterior) {
        this.abogado_anterior = abogado_anterior;
    }

    public Integer getOtro_delito() {
        return otro_delito;
    }

    public void setOtro_delito(Integer otro_delito) {
        this.otro_delito = otro_delito;
    }

    public String getCuerpo_normativo() {
        return cuerpo_normativo;
    }

    public void setCuerpo_normativo(String cuerpo_normativo) {
        this.cuerpo_normativo = cuerpo_normativo;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getDescripcion_legal() {
        return descripcion_legal;
    }

    public void setDescripcion_legal(String descripcion_legal) {
        this.descripcion_legal = descripcion_legal;
    }

    public String getNombre_solicitante() {
        return nombre_solicitante;
    }

    public void setNombre_solicitante(String nombre_solicitante) {
        this.nombre_solicitante = nombre_solicitante;
    }

    public String getDependencia_solicitante() {
        return dependencia_solicitante;
    }

    public void setDependencia_solicitante(String dependencia_solicitante) {
        this.dependencia_solicitante = dependencia_solicitante;
    }

    public String getCargo_solicitante() {
        return cargo_solicitante;
    }

    public void setCargo_solicitante(String cargo_solicitante) {
        this.cargo_solicitante = cargo_solicitante;
    }

    public Integer getEdad_delito() {
        return edad_delito;
    }

    public void setEdad_delito(Integer edad_delito) {
        this.edad_delito = edad_delito;
    }

    public Integer getDelito_grave() {
        return delito_grave;
    }

    public void setDelito_grave(Integer delito_grave) {
        this.delito_grave = delito_grave;
    }

    public Date getFecha_reapertura() {
        return fecha_reapertura;
    }

    public void setFecha_reapertura(Date fecha_reapertura) {
        this.fecha_reapertura = fecha_reapertura;
    }

    public Date getFecha_solicitud_reapertura() {
        return fecha_solicitud_reapertura;
    }

    public void setFecha_solicitud_reapertura(Date fecha_solicitud_reapertura) {
        this.fecha_solicitud_reapertura = fecha_solicitud_reapertura;
    }

    public Integer getAutorizador_reapertura_id() {
        return autorizador_reapertura_id;
    }

    public void setAutorizador_reapertura_id(Integer autorizador_reapertura_id) {
        this.autorizador_reapertura_id = autorizador_reapertura_id;
    }

    public String getMotivo_reapertura() {
        return motivo_reapertura;
    }

    public void setMotivo_reapertura(String motivo_reapertura) {
        this.motivo_reapertura = motivo_reapertura;
    }

    public String getMotivo_rechazo_reapertura() {
        return motivo_rechazo_reapertura;
    }

    public void setMotivo_rechazo_reapertura(String motivo_rechazo_reapertura) {
        this.motivo_rechazo_reapertura = motivo_rechazo_reapertura;
    }

    public Integer getReapertura() {
        return reapertura;
    }

    public void setReapertura(Integer reapertura) {
        this.reapertura = reapertura;
    }

    public Date getFecha_presentacion() {
        return fecha_presentacion;
    }

    public void setFecha_presentacion(Date fecha_presentacion) {
        this.fecha_presentacion = fecha_presentacion;
    }

    public String getHora_presentacion() {
        return hora_presentacion;
    }

    public void setHora_presentacion(String hora_presentacion) {
        this.hora_presentacion = hora_presentacion;
    }

    public String getLugar_presentacion() {
        return lugar_presentacion;
    }

    public void setLugar_presentacion(String lugar_presentacion) {
        this.lugar_presentacion = lugar_presentacion;
    }

    public Date getFecha_reasignacion_distrito() {
        return fecha_reasignacion_distrito;
    }

    public void setFecha_reasignacion_distrito(Date fecha_reasignacion_distrito) {
        this.fecha_reasignacion_distrito = fecha_reasignacion_distrito;
    }

    public Integer getReasignar() {
        return reasignar;
    }

    public void setReasignar(Integer reasignar) {
        this.reasignar = reasignar;
    }

    public String getCarpeta_investigacion() {
        return carpeta_investigacion;
    }

    public void setCarpeta_investigacion(String carpeta_investigacion) {
        this.carpeta_investigacion = carpeta_investigacion;
    }

    public String getTipo_audiencia() {
        return tipo_audiencia;
    }

    public void setTipo_audiencia(String tipo_audiencia) {
        this.tipo_audiencia = tipo_audiencia;
    }

    public List<Acusacion> getAcusacion() {
        return acusacion;
    }

    public void setAcusacion(List<Acusacion> acusacion) {
        this.acusacion = acusacion;
    }

    public List<AudienciaIntermedia> getAudienciaIntermedia() {
        return audienciaIntermedia;
    }

    public void setAudienciaIntermedia(List<AudienciaIntermedia> audienciaIntermedia) {
        this.audienciaIntermedia = audienciaIntermedia;
    }

    public List<ConclusionDefensa> getConclusionDefensa() {
        return conclusionDefensa;
    }

    public void setConclusionDefensa(List<ConclusionDefensa> conclusionDefensa) {
        this.conclusionDefensa = conclusionDefensa;
    }

    public List<DefensaDefensor> getDefensaDefensor() {
        return defensaDefensor;
    }

    public void setDefensaDefensor(List<DefensaDefensor> defensaDefensor) {
        this.defensaDefensor = defensaDefensor;
    }

    public List<DefensaDelito> getDefensaDelito() {
        return defensaDelito;
    }

    public void setDefensaDelito(List<DefensaDelito> defensaDelito) {
        this.defensaDelito = defensaDelito;
    }

    public List<Determinacion> getDeterminacion() {
        return determinacion;
    }

    public void setDeterminacion(List<Determinacion> determinacion) {
        this.determinacion = determinacion;
    }

    public List<HistoricoDefensa> getHistoricoDefensa() {
        return historicoDefensa;
    }

    public void setHistoricoDefensa(List<HistoricoDefensa> historicoDefensa) {
        this.historicoDefensa = historicoDefensa;
    }

    public List<Imputacion> getImputacion() {
        return imputacion;
    }

    public void setImputacion(List<Imputacion> imputacion) {
        this.imputacion = imputacion;
    }

    public List<InvestigacionComplementaria> getInvestigacionComplementaria() {
        return investigacionComplementaria;
    }

    public void setInvestigacionComplementaria(List<InvestigacionComplementaria> investigacionComplementaria) {
        this.investigacionComplementaria = investigacionComplementaria;
    }

    public List<MedidaCautelar> getMedidaCautelar() {
        return medidaCautelar;
    }

    public void setMedidaCautelar(List<MedidaCautelar> medidaCautelar) {
        this.medidaCautelar = medidaCautelar;
    }

    public List<OtraAudiencia> getOtraAudiencia() {
        return otraAudiencia;
    }

    public void setOtraAudiencia(List<OtraAudiencia> otraAudiencia) {
        this.otraAudiencia = otraAudiencia;
    }

    public List<RadicacionEjecucion> getRadicacionEjecucion() {
        return radicacionEjecucion;
    }

    public void setRadicacionEjecucion(List<RadicacionEjecucion> radicacionEjecucion) {
        this.radicacionEjecucion = radicacionEjecucion;
    }
    
    
    
    

}
