package com.defensoria.sigedepu.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.defensoria.sigedepu.model.Representacion;

public interface RepresentacionRepository extends JpaRepository<Representacion, Long>{
	
	@Query("SELECT r FROM Representacion r  where r.fecha_creacion>=:creationDate")
	List<Representacion>findByFechaCreacion(@Param("creationDate")Date creationDate);

}
