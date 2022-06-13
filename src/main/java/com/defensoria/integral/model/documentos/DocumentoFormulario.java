/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.documentos;

import com.defensoria.integral.model.adminpersonal.Formularios;
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
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author Alejandro
 */
@Entity
@Table(name = "tbldocumentosformularios")
public class DocumentoFormulario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDocumentoFormulario;
    private String descDocumentoFormulario;
    private String rutaDocumentoFormulario;
    @OneToOne
    @JoinColumn(name = "cveFormulario")
    private Formularios formulario;
    @OneToOne
    @JoinColumn(name = "cveGrupoDocumento")
    private GrupoDocumento grupoDocumento;
    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    private Integer orden;


    public Integer getOrden() {
        return this.orden;
    }

    public void setOrden(Integer orden) {
        this.orden = orden;
    }
    

    public Integer getIdDocumentoFormulario() {
        return idDocumentoFormulario;
    }

    public void setIdDocumentoFormulario(Integer idDocumentoFormulario) {
        this.idDocumentoFormulario = idDocumentoFormulario;
    }

    public String getDescDocumentoFormulario() {
        return descDocumentoFormulario;
    }

    public void setDescDocumentoFormulario(String descDocumentoFormulario) {
        this.descDocumentoFormulario = descDocumentoFormulario;
    }

    public String getRutaDocumentoFormulario() {
        return rutaDocumentoFormulario;
    }

    public void setRutaDocumentoFormulario(String rutaDocumentoFormulario) {
        this.rutaDocumentoFormulario = rutaDocumentoFormulario;
    }

    public Formularios getFormulario() {
        return formulario;
    }

    public void setFormulario(Formularios formulario) {
        this.formulario = formulario;
    }

    public GrupoDocumento getGrupoDocumento() {
        return grupoDocumento;
    }

    public void setGrupoDocumento(GrupoDocumento grupoDocumento) {
        this.grupoDocumento = grupoDocumento;
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

}
