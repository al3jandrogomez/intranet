package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.TiposDocumentos;

public interface TiposDocumentosRepository extends JpaRepository  <TiposDocumentos, Long>{
	
	@Query("Select td FROM TiposDocumentos td WHERE activo like:activo")
	public List<TiposDocumentos> ListaCatalogo(@Param("activo")String activo);
	@Query("Select td FROM TiposDocumentos td WHERE descTipoDocumento like:descTipoDocumento")
	List<TiposDocumentos> find(@Param("descTipoDocumento")String descTipoDocumento);
	

}
