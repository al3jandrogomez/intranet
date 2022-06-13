package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.EstatusRequisiciones;

public interface EstatusRequisicionesRepository extends JpaRepository <EstatusRequisiciones,Long>{
	
	@Query("SELECT er FROM EstatusRequisiciones er where er.activo=:activo")
	List<EstatusRequisiciones>findByActivo(@Param("activo")String activo);
	@Query("SELECT er FROM EstatusRequisiciones er WHERE er.cveEstatusRequisicion =:cveEstatusRequisicion")
	List<EstatusRequisiciones>findByEstatusRequisicion(@Param("cveEstatusRequisicion")Integer cveEstatusRequisicion);

}
