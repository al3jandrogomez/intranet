package com.defensoria.integral.repository.adminpersonal;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.adminpersonal.NPersonalC;

public interface NPersonalCRepository extends JpaRepository<NPersonalC, Long> {
	@Query("SELECT p FROM NPersonalC p where p.nombreCompleto like:nombre and activo='S'")
	List<NPersonalC>ListarPersonal(@Param("nombre")String nombre);

}
