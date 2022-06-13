package com.defensoria.sigedepu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.sigedepu.model.SolicitudAtencion;

public interface SolicitudAtencionRepository extends JpaRepository<SolicitudAtencion, Long> {
	
	@Query("SELECT s FROM SolicitudAtencion s inner join s.region r   where s.fecha_registro>=:fechaRegistro and s.fecha_registro<=:fechaRegistro2 and s.estatus like:estatus and r.id=:region Order by s.fecha_atencion desc")
	List<SolicitudAtencion>findByPendientesFechaRegistro(@Param("fechaRegistro")Date fechaRegistro,@Param("fechaRegistro2")Date fechaRegistro2,@Param("estatus")String estatus,@Param("region")Integer region);
        
        
       /* @Query("SELECT s FROM SolicitudAtencion s inner join s.region r   where s.fecha_registro>='2019-07-26 00:00:00' and s.fecha_registro<='2019-07-26 23:00:00' and s.estatus like:estatus and r.id=:region Order by s.fecha_atencion desc")
	List<SolicitudAtencion>findByPendientesFechaRegistro(@Param("estatus")String estatus,@Param("region")Integer region);
        */
        
	@Query("SELECT s FROM SolicitudAtencion s inner join s.region r   where s.fecha_atencion>=:fechaRegistro and s.fecha_atencion<=:fechaRegistro2 and s.tipo_solicitud like'%Atención a Público%' and s.estatus_informativa is not null and r.id=:region order by s.fecha_atencion DESC ")
	List<SolicitudAtencion>findByAtencionesFechaRegistro(@Param("fechaRegistro")Date fechaRegistro,@Param("fechaRegistro2")Date fechaRegistro2,@Param("region")Integer region);

}
