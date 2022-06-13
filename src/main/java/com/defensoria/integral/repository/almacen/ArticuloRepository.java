package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Articulos;

public interface ArticuloRepository extends JpaRepository<Articulos,Long>{
	@Query("SELECT a FROM Articulos a WHERE a.clave like:clave AND a.version like:version AND a.descArticulo like:descArticulo AND cvePartida=:cvePartida")
	public List<Articulos>find(@Param("clave") String clave,@Param("version") String version,@Param("descArticulo") String descArticulo,@Param("cvePartida") Integer cvePartida);
	@Query("SELECT a FROM Articulos a inner join a.marcas m left join a.unidades u WHERE  a.descArticulo like:descArticulo")
	 List<Articulos>findByDescArticulo(@Param("descArticulo")String descArticulo);

}
