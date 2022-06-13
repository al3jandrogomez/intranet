package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.TiposContadores;

public interface TiposContadoresRepository extends JpaRepository<TiposContadores, Long> {
	@Query("SELECT tp FROM TiposContadores tp WHERE descTipoContador like:descTipoContador AND activo=:activo")
    public List<TiposContadores> find(@Param("descTipoContador") String descTipoContador,@Param("activo") String activo);

}
