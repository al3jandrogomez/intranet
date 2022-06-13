package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.Formularios;

public interface FormulariosRepository extends JpaRepository <Formularios,Long> {
	
	@Query("SELECT f FROM Formularios f inner join f.permisosPerfiles pp inner join pp.perfila p where p.cvePerfil in:cvePerfiles")
	List<Formularios>ListarFormulariosPorPerfil(@Param ("cvePerfiles") List<Integer> cvePerfiles);

}
