package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.PermisosPerfiles;

public interface PermisosPerfilesRepository extends JpaRepository <PermisosPerfiles, Long> {
	@Query("SELECT pp FROM PermisosPerfiles pp  inner join pp.perfila p  where p.cvePerfil in:cvePerfiles")
	List<PermisosPerfiles>findByCvePerfil(@Param("cvePerfiles") List<Integer> cvePerfiles);

}
