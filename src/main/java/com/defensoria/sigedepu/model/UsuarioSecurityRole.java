package com.defensoria.sigedepu.model;

import java.util.List;


import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity(name = "usuario_security_role")
@Table(name = "usuario_security_role")
public class UsuarioSecurityRole {

	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name = "id")
	private List<Usuario> usuarios;
	@Id
	private Integer security_role_id;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Integer getSecurity_role_id() {
		return security_role_id;
	}

	public void setSecurity_role_id(Integer security_role_id) {
		this.security_role_id = security_role_id;
	}

}
