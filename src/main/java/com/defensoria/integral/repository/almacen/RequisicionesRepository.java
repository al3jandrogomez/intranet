package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Requisiciones;

public interface RequisicionesRepository extends JpaRepository <Requisiciones, Long>{
//@Query("SELECT r FROM Requisiciones r  inner join r.movRequisicion mr on   mr.activo like:activo  inner join mr.estatusRequisicion er on   mr.activo like:activo WHERE r.cveAdscripcion=:cveAdscripcion")
	@Query("SELECT r FROM Requisiciones r  inner join r.adscripcion ad   WHERE ad.cveAdscripcion=:cveAdscripcion and r.activo like:activo and r.tipoRequisicion=:tipoRequisicion")
List<Requisiciones>findByAdscripcion(@Param("cveAdscripcion")Integer cveAdscripcion,@Param("activo") String activo,Sort sort,@Param("tipoRequisicion")String tipoRequisicion);
	@Query("SELECT r FROM Requisiciones r  inner join r.movRequisicion mr inner join mr.estatusRequisicion er WHERE er.cveEstatusRequisicion=:cveEstatusRequisicion and r.activo='S' order by mr.fechaRegistro DESC")   
	List<Requisiciones>findByAutorizar(@Param("cveEstatusRequisicion") Integer cveEstatusRequisicion);
	@Query("SELECT r FROM Requisiciones r  inner join r.movRequisicion mr inner join mr.estatusRequisicion er WHERE er.cveEstatusRequisicion>=:cveEstatusRequisicion")   
	List<Requisiciones>findByAutorizarOrderBy(@Param("cveEstatusRequisicion") Integer cveEstatusRequisicion);
	
}
	
