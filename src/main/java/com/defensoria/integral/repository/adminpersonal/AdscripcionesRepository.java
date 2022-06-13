package com.defensoria.integral.repository.adminpersonal;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface AdscripcionesRepository extends JpaRepository<Adscripciones, Long>{
	//@OrderBy("descAdscripcion ASC")
    //List<Adscripciones>findAll(Example example);

	@Query("SELECT a FROM Adscripciones a where  a.descAdscripcion like:ads  and a.activo='S'")
	List<Adscripciones>findDescAdscripcion(@Param("ads")String ads);
	@Query("SELECT a FROM Adscripciones a where  a.descAdscripcion like:ads and a.cveRegion=:cveRegion  and a.activo='S'")
	List<Adscripciones>findDescAdscripcionCveRegion(@Param("ads")String ads,@Param("cveRegion") Integer cveRegion);

}
