package com.defensoria.integral.model.adminpersonal;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tblpersonal")
public class Personal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer cveUsuario;
    private String nombre;
    private String paterno;
    private String materno;
    private String cveServidorPub;
    private String curp;
    private String rfc;
    private Date fechaNacimiento;
    private Integer cveGenero;
    @JsonIgnore
    @OneToMany(mappedBy = "personal")
    private List<MovAdscripciones> movAdscripciones;
    @JsonIgnore
    @OneToMany(mappedBy = "personal")
    private List<PersonalPerfiles> personalPerfiles;
    @OneToMany(mappedBy = "personal")
    private List<Domicilio> domicilios;
    @OneToMany(mappedBy = "personal")
    private List<MovAdscripciones> movimientos;

    private String activo;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date fechaRegistro;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaActualizacion;
    private String email;
    private Integer cveEstadoNacimiento;
    private String usuario;
    private String contrasena;
    private String cveIssemym;
    private String titulo;
    private Integer usuario_id;

    @OneToMany(mappedBy = "personal")
    private List<PersonalCorreos> personalCorreos;
    @OneToMany(mappedBy = "personal")
    private List<FotografiaPersonal> fotografiasPersonal;
    @OneToMany(mappedBy = "personal")
    private List<FirmaPersonal> firmasPersonal;

    public Integer getCveUsuario() {
        return cveUsuario;
    }

    public void setCveUsuario(Integer cveUsuario) {
        this.cveUsuario = cveUsuario;
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

    public String getCveServidorPub() {
        return cveServidorPub;
    }

    public void setCveServidorPub(String cveServidorPub) {
        this.cveServidorPub = cveServidorPub;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getCveGenero() {
        return cveGenero;
    }

    public void setCveGenero(Integer cveGenero) {
        this.cveGenero = cveGenero;
    }

    public List<PersonalPerfiles> getPersonalPerfiles() {
        return personalPerfiles;
    }

    public void setPersonalPerfiles(List<PersonalPerfiles> personalPerfiles) {
        this.personalPerfiles = personalPerfiles;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getCveEstadoNacimiento() {
        return cveEstadoNacimiento;
    }

    public void setCveEstadoNacimiento(Integer cveEstadoNacimiento) {
        this.cveEstadoNacimiento = cveEstadoNacimiento;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCveIssemym() {
        return cveIssemym;
    }

    public void setCveIssemym(String cveIssemym) {
        this.cveIssemym = cveIssemym;
    }

    public List<MovAdscripciones> getMovAdscripciones() {
        return movAdscripciones;
    }

    public void setMovAdscripciones(List<MovAdscripciones> movAdscripciones) {
        this.movAdscripciones = movAdscripciones;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(Integer usuario_id) {
        this.usuario_id = usuario_id;
    }

    public List<PersonalCorreos> getPersonalCorreos() {
        return personalCorreos;
    }

    public void setPersonalCorreos(List<PersonalCorreos> personalCorreos) {
        this.personalCorreos = personalCorreos;
    }

    public List<FotografiaPersonal> getFotografiasPersonal() {
        return fotografiasPersonal;
    }

    public void setFotografiasPersonal(List<FotografiaPersonal> fotografiasPersonal) {
        this.fotografiasPersonal = fotografiasPersonal;
    }

    public List<FirmaPersonal> getFirmasPersonal() {
        return firmasPersonal;
    }

    public void setFirmasPersonal(List<FirmaPersonal> firmasPersonal) {
        this.firmasPersonal = firmasPersonal;
    }



    public List<Domicilio> getDomicilios() {
        return this.domicilios;
    }

    public void setDomicilios(List<Domicilio> domicilios) {
        this.domicilios = domicilios;
    }
   

    public List<MovAdscripciones> getMovimientos() {
        return this.movimientos;
    }

    public void setMovimientos(List<MovAdscripciones> movimientos) {
        this.movimientos = movimientos;
    }


}
