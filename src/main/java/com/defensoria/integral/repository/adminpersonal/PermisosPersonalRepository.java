package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.PermisosPersonal;

public interface PermisosPersonalRepository extends JpaRepository<PermisosPersonal, Long> {
	
	@Query("SELECT p FROM  PermisosPersonal p join p.formulario where p.cveUsuario=:cveUsuario")
	List<PermisosPersonal>ListarPermisosPersonal(@Param("cveUsuario") Integer cveUsuario);

}
