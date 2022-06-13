package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.Personal;

public interface PersonalRepository  extends JpaRepository<Personal,Long>{
	@Query("SELECT p FROM Personal p inner join p.movAdscripciones ma inner join ma.adscripcion ads  where ads.cveAdscripcion=:cveAdscripcion and ma.titular='S' and ma.activo='S'" )
	List<Personal>TitularAdscripciones(@Param("cveAdscripcion") Integer cveAdscripcion);
	@Query("SELECT p FROM Personal p inner join p.movAdscripciones ma inner join ma.adscripcion ads  where ma.cveUsuario=:cveUsuario and ma.activo like 'S'")
	List<Personal>Adscripciones(@Param("cveUsuario") Integer cveUsuario);
	@Query("SELECT p FROM Personal p inner join p.personalPerfiles pp  inner join pp.perfil pe where pe.cvePerfil =:cvePerfil")
	List<Personal>ResponsablesAreas(@Param("cvePerfil") Integer cvePerfil); 
}
