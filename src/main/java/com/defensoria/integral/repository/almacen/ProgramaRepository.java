package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Programa;


public interface ProgramaRepository extends JpaRepository <Programa, Long>{
	@Query("Select p FROM Programa p WHERE activo like:activo")
	public List<Programa> ListaCatalogo(@Param("activo")String activo);
	@Query("Select p FROM Programa p WHERE descPrograma like:descPrograma")
	List<Programa> find(@Param("descPrograma")String descPrograma);
}
