package com.defensoria.sigedepu.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.OneToOne;

@Entity
@Table(name = "solicitud_atencion")
public class SolicitudAtencion {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Integer id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "peticionario_id")
    private Peticionario peticionario;
//	private Integer peticionario_id;
    private String tipo_solicitud;
    private String identificacion;
    private String identificacion_otro;
    private String procedencia;
    private String observaciones;
    private String estatus;
    private String descripcion_hecho;

    private Date fecha_solicitud;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date fecha_registro;
    private String dictamen_tecnico;
    private String nombre_remite;
    private Integer representacion_id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "asesoria_id", referencedColumnName = "id")
    private Asesoria asesoria;
    private Integer orientacion_id;
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "defensa_id", referencedColumnName = "id")
    private Defensa defensa;
//    private Integer defensa_id;
    private Integer usuario_id; 
    private Integer materia_id;
    private String motivo_rechazo;
    private Date fecha_rechazo;
    private Integer usuario_rechazo_id;
    private String motivo_rechazo_conclusion;
    private Date fecha_rechazo_conclusion;
    private Integer usuario_rechazo_conclusion_id;
    private String dictamen_tecnico_rechazo;
    private String hora_llegada;
    private String hora_atencion;
    private String usuario_autorizo;
    private String cedula_antecedente_id;
    private Integer motivo_antecedente;
    private String motivo_otro_antecedente;
    private String antecedente;
    private Integer distrito_id;
    private String nuc;
    private Integer id_referencia;
    private Date transaction_date;
    private String transaction_hour;
    private String transaction_estatus;
    private String cargo_solicita;
    private String agencia;
    private String juzgado;
    private Integer asesor_id;
    //private Integer region_id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "region_id")
    private Region region;

//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="asesoria_id",referencedColumnName="asesoria_id")
//	private Representacion representacion; 
//	
    private String ticket;
    private Integer procede_patrocinio;
    private Integer aplicar_estudio;
    private Integer estudio_viable;
    private Date fecha_atencion;
    private Integer updated_by_id;
    private Integer municipio_id;
    private Date fecha_activacion;
    private String folio;
    private String aviso;
    private String estatus_informativa;
    private Integer error_created_by_id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Peticionario getPeticionario() {
        return peticionario;
    }

    public void setPeticionario(Peticionario peticionario) {
        this.peticionario = peticionario;
    }
    //	public Integer getPeticionario_id() {
//		return peticionario_id;
//	}
//	public void setPeticionario_id(Integer peticionario_id) {
//		this.peticionario_id = peticionario_id;
//	}

    public String getTipo_solicitud() {
        return tipo_solicitud;
    }

    public void setTipo_solicitud(String tipo_solicitud) {
        this.tipo_solicitud = tipo_solicitud;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getIdentificacion_otro() {
        return identificacion_otro;
    }

    public void setIdentificacion_otro(String identificacion_otro) {
        this.identificacion_otro = identificacion_otro;
    }

    public String getProcedencia() {
        return procedencia;
    }

    public void setProcedencia(String procedencia) {
        this.procedencia = procedencia;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getDescripcion_hecho() {
        return descripcion_hecho;
    }

    public void setDescripcion_hecho(String descripcion_hecho) {
        this.descripcion_hecho = descripcion_hecho;
    }

    public Date getFecha_solicitud() {
        return fecha_solicitud;
    }

    public void setFecha_solicitud(Date fecha_solicitud) {
        this.fecha_solicitud = fecha_solicitud;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public String getDictamen_tecnico() {
        return dictamen_tecnico;
    }

    public void setDictamen_tecnico(String dictamen_tecnico) {
        this.dictamen_tecnico = dictamen_tecnico;
    }

    public Integer getRepresentacion_id() {
        return representacion_id;
    }

    public void setRepresentacion_id(Integer representacion_id) {
        this.representacion_id = representacion_id;
    }
//	public Integer getAsesoria_id() {
//		return asesoria_id;
//	}
//	public void setAsesoria_id(Integer asesoria_id) {
//		this.asesoria_id = asesoria_id;
//	}

    public Integer getOrientacion_id() {
        return orientacion_id;
    }

    public Asesoria getAsesoria() {
        return asesoria;
    }

    public void setAsesoria(Asesoria asesoria) {
        this.asesoria = asesoria;
    }

    public void setOrientacion_id(Integer orientacion_id) {
        this.orientacion_id = orientacion_id;
    }

//    public Integer getDefensa_id() {
//        return defensa_id;
//    }
//
//    public void setDefensa_id(Integer defensa_id) {
//        this.defensa_id = defensa_id;
//    }
    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public Integer getMateria_id() {
        return materia_id;
    }

    public void setMateria_id(Integer materia_id) {
        this.materia_id = materia_id;
    }

    public String getMotivo_rechazo() {
        return motivo_rechazo;
    }

    public void setMotivo_rechazo(String motivo_rechazo) {
        this.motivo_rechazo = motivo_rechazo;
    }

    public Date getFecha_rechazo() {
        return fecha_rechazo;
    }

    public void setFecha_rechazo(Date fecha_rechazo) {
        this.fecha_rechazo = fecha_rechazo;
    }

    public Integer getUsuario_rechazo_id() {
        return usuario_rechazo_id;
    }

    public void setUsuario_rechazo_id(Integer usuario_rechazo_id) {
        this.usuario_rechazo_id = usuario_rechazo_id;
    }

    public String getMotivo_rechazo_conclusion() {
        return motivo_rechazo_conclusion;
    }

    public void setMotivo_rechazo_conclusion(String motivo_rechazo_conclusion) {
        this.motivo_rechazo_conclusion = motivo_rechazo_conclusion;
    }

    public Date getFecha_rechazo_conclusion() {
        return fecha_rechazo_conclusion;
    }

    public void setFecha_rechazo_conclusion(Date fecha_rechazo_conclusion) {
        this.fecha_rechazo_conclusion = fecha_rechazo_conclusion;
    }

    public Integer getUsuario_rechazo_conclusion_id() {
        return usuario_rechazo_conclusion_id;
    }

    public void setUsuario_rechazo_conclusion_id(Integer usuario_rechazo_conclusion_id) {
        this.usuario_rechazo_conclusion_id = usuario_rechazo_conclusion_id;
    }

    public String getDictamen_tecnico_rechazo() {
        return dictamen_tecnico_rechazo;
    }

    public void setDictamen_tecnico_rechazo(String dictamen_tecnico_rechazo) {
        this.dictamen_tecnico_rechazo = dictamen_tecnico_rechazo;
    }

    public String getHora_llegada() {
        return hora_llegada;
    }

    public void setHora_llegada(String hora_llegada) {
        this.hora_llegada = hora_llegada;
    }

    public String getHora_atencion() {
        return hora_atencion;
    }

    public void setHora_atencion(String hora_atencion) {
        this.hora_atencion = hora_atencion;
    }

    public String getUsuario_autorizo() {
        return usuario_autorizo;
    }

    public void setUsuario_autorizo(String usuario_autorizo) {
        this.usuario_autorizo = usuario_autorizo;
    }

    public String getCedula_antecedente_id() {
        return cedula_antecedente_id;
    }

    public void setCedula_antecedente_id(String cedula_antecedente_id) {
        this.cedula_antecedente_id = cedula_antecedente_id;
    }

    public Integer getMotivo_antecedente() {
        return motivo_antecedente;
    }

    public void setMotivo_antecedente(Integer motivo_antecedente) {
        this.motivo_antecedente = motivo_antecedente;
    }

    public String getMotivo_otro_antecedente() {
        return motivo_otro_antecedente;
    }

    public void setMotivo_otro_antecedente(String motivo_otro_antecedente) {
        this.motivo_otro_antecedente = motivo_otro_antecedente;
    }

    public String getAntecedente() {
        return antecedente;
    }

    public void setAntecedente(String antecedente) {
        this.antecedente = antecedente;
    }

    public Integer getDistrito_id() {
        return distrito_id;
    }

    public void setDistrito_id(Integer distrito_id) {
        this.distrito_id = distrito_id;
    }

    public String getNuc() {
        return nuc;
    }

    public void setNuc(String nuc) {
        this.nuc = nuc;
    }

    public Integer getId_referencia() {
        return id_referencia;
    }

    public void setId_referencia(Integer id_referencia) {
        this.id_referencia = id_referencia;
    }

    public Date getTransaction_date() {
        return transaction_date;
    }

    public void setTransaction_date(Date transaction_date) {
        this.transaction_date = transaction_date;
    }

    public String getTransaction_hour() {
        return transaction_hour;
    }

    public void setTransaction_hour(String transaction_hour) {
        this.transaction_hour = transaction_hour;
    }

    public String getTransaction_estatus() {
        return transaction_estatus;
    }

    public void setTransaction_estatus(String transaction_estatus) {
        this.transaction_estatus = transaction_estatus;
    }

    public String getCargo_solicita() {
        return cargo_solicita;
    }

    public void setCargo_solicita(String cargo_solicita) {
        this.cargo_solicita = cargo_solicita;
    }

    public String getAgencia() {
        return agencia;
    }

    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

    public String getJuzgado() {
        return juzgado;
    }

    public void setJuzgado(String juzgado) {
        this.juzgado = juzgado;
    }

    public Integer getAsesor_id() {
        return asesor_id;
    }

    public void setAsesor_id(Integer asesor_id) {
        this.asesor_id = asesor_id;
    }
//	public Integer getRegion_id() {
//		return region_id;
//	}
//	public void setRegion_id(Integer region_id) {
//		this.region_id = region_id;
//	}

    public String getTicket() {
        return ticket;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public void setTicket(String ticket) {
        this.ticket = ticket;
    }

    public Integer getProcede_patrocinio() {
        return procede_patrocinio;
    }

    public void setProcede_patrocinio(Integer procede_patrocinio) {
        this.procede_patrocinio = procede_patrocinio;
    }

    public Integer getAplicar_estudio() {
        return aplicar_estudio;
    }

    public void setAplicar_estudio(Integer aplicar_estudio) {
        this.aplicar_estudio = aplicar_estudio;
    }

    public Integer getEstudio_viable() {
        return estudio_viable;
    }

    public void setEstudio_viable(Integer estudio_viable) {
        this.estudio_viable = estudio_viable;
    }

    public Date getFecha_atencion() {
        return fecha_atencion;
    }

    public void setFecha_atencion(Date fecha_atencion) {
        this.fecha_atencion = fecha_atencion;
    }

    public String getNombre_remite() {
        return nombre_remite;
    }

    public void setNombre_remite(String nombre_remite) {
        this.nombre_remite = nombre_remite;
    }

    public Integer getUpdated_by_id() {
        return updated_by_id;
    }

    public void setUpdated_by_id(Integer updated_by_id) {
        this.updated_by_id = updated_by_id;
    }

    public Integer getMunicipio_id() {
        return municipio_id;
    }

    public void setMunicipio_id(Integer municipio_id) {
        this.municipio_id = municipio_id;
    }

    public Date getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(Date fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getAviso() {
        return aviso;
    }

    public void setAviso(String aviso) {
        this.aviso = aviso;
    }

    public String getEstatus_informativa() {
        return estatus_informativa;
    }

    public void setEstatus_informativa(String estatus_informativa) {
        this.estatus_informativa = estatus_informativa;
    }

    public Integer getError_created_by_id() {
        return error_created_by_id;
    }

    public void setError_created_by_id(Integer error_created_by_id) {
        this.error_created_by_id = error_created_by_id;
    }

    public Defensa getDefensa() {
        return defensa;
    }

    public void setDefensa(Defensa defensa) {
        this.defensa = defensa;
    }

}
