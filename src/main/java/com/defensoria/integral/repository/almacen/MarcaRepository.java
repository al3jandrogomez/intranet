package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Marcas;


public interface MarcaRepository  extends JpaRepository <Marcas, Long> {
	@Query("SELECT m FROM Marcas m WHERE descMarca like:descMarca")
    public List<Marcas> find(@Param("descMarca") String descMarca);
	
}
