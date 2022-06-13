package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.PersonalPerfiles;

public interface PersonalPerfilesRepository extends JpaRepository<PersonalPerfiles, Long> {
	@Query("select f from  PersonalPerfiles pper "
			+ "inner join pper.perfil p inner join p.permisosPerfiles perper inner join pper.personal pe inner join perper.formulario f inner join f.modulos m where pe.cveUsuario=:cveUsuario and m.cveModulo=:cveModulo and pper.activo='S'and perper.activo='S'")
	List<PersonalPerfiles> FormulariosPorPerfil(@Param("cveUsuario") Integer cveUsuario,@Param("cveModulo") Integer cveModulo);

}
