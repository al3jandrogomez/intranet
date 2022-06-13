package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.TiposInversiones;



public interface TiposInversionRepository extends JpaRepository<TiposInversiones, Long>{
	@Query("Select ti FROM TiposInversiones ti WHERE activo like:activo")
	public List<TiposInversiones> ListaCatalogo(@Param("activo")String activo);
	@Query("Select ti FROM TiposInversiones ti WHERE descTipoInversion like:descTipoInversion")
	List<TiposInversiones> find(@Param("descTipoInversion")String descTipoInversion);
	

}
