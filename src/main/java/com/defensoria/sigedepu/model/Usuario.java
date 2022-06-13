package com.defensoria.sigedepu.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuario")

public class Usuario {
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Integer id;
	private String user_name;
	private String nombre_completo;
	private String nombre;
	private String paterno;
	private String materno;
	private String password;
	private String codigo_seguridad;
	private String perfil;
	private String email;
	private Integer distrito_id;
	private Integer modulo_id;
	private String pregunta_secreta;
	private String respuesta_secreta;
	private String numero_contacto;
	private String genero;
	private Integer director_general;
	private Integer director_regional;
	private Integer activo;
	private Integer evomatik;
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "usuario_id")
//	private UsuarioSecurityRole usuarioSecurityRole;
	
	
//	public UsuarioSecurityRole getUsuarioSecurityRole() {
//		return usuarioSecurityRole;
//	}
//	public void setUsuarioSecurityRole(UsuarioSecurityRole usuarioSecurityRole) {
//		this.usuarioSecurityRole = usuarioSecurityRole;
//	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getNombre_completo() {
		return nombre_completo;
	}
	public void setNombre_completo(String nombre_completo) {
		this.nombre_completo = nombre_completo;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCodigo_seguridad() {
		return codigo_seguridad;
	}
	public void setCodigo_seguridad(String codigo_seguridad) {
		this.codigo_seguridad = codigo_seguridad;
	}
	public String getPerfil() {
		return perfil;
	}
	public void setPerfil(String perfil) {
		this.perfil = perfil;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getDistrito_id() {
		return distrito_id;
	}
	public void setDistrito_id(Integer distrito_id) {
		this.distrito_id = distrito_id;
	}
	public Integer getModulo_id() {
		return modulo_id;
	}
	public void setModulo_id(Integer modulo_id) {
		this.modulo_id = modulo_id;
	}
	public String getPregunta_secreta() {
		return pregunta_secreta;
	}
	public void setPregunta_secreta(String pregunta_secreta) {
		this.pregunta_secreta = pregunta_secreta;
	}
	public String getRespuesta_secreta() {
		return respuesta_secreta;
	}
	public void setRespuesta_secreta(String respuesta_secreta) {
		this.respuesta_secreta = respuesta_secreta;
	}
	public String getNumero_contacto() {
		return numero_contacto;
	}
	public void setNumero_contacto(String numero_contacto) {
		this.numero_contacto = numero_contacto;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Integer getDirector_general() {
		return director_general;
	}
	public void setDirector_general(Integer director_general) {
		this.director_general = director_general;
	}
	public Integer getDirector_regional() {
		return director_regional;
	}
	public void setDirector_regional(Integer director_regional) {
		this.director_regional = director_regional;
	}
	public Integer getActivo() {
		return activo;
	}
	public void setActivo(Integer activo) {
		this.activo = activo;
	}
	public Integer getEvomatik() {
		return evomatik;
	}
	public void setEvomatik(Integer evomatik) {
		this.evomatik = evomatik;
	}
	
	
	
	

}
