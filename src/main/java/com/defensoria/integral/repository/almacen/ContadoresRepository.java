package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.defensoria.integral.model.almacen.Contadores;

public interface ContadoresRepository extends JpaRepository<Contadores, Long>{
	@Query("SELECT c FROM Contadores c where cveTipoContador=:cveTipoContador and cveAdscripcion=:cveAdscripcion AND anio=:anio")
	List<Contadores>findExistencia(@Param("cveTipoContador")Integer cveTipoContador,@Param("cveAdscripcion") Integer cveAdscripcion, @Param("anio")Integer anio);

}
