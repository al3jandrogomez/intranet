package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.defensoria.integral.model.almacen.Unidades;

public interface UnidadRepository extends JpaRepository <Unidades, Long>{
	@Query("SELECT u FROM Unidades u WHERE descUnidad like:descUnidad")
    public List<Unidades> find(@Param("descUnidad") String descUnidad);
	
}
