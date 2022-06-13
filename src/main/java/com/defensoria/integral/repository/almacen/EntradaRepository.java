package com.defensoria.integral.repository.almacen;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.defensoria.integral.model.almacen.Entrada;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada,Long> {
	
	@Query("SELECT e FROM Entrada e WHERE cveProveedor =:cveProveedor AND cveTipoDocumento=:cveTipoDocumento AND noDocumento like:noDocumento")
    public List<Entrada> find(@Param("cveProveedor") Integer cveProveedor,@Param("cveTipoDocumento") Integer cveTipoDocumento,@Param("noDocumento")String nodocumento);
	
	


}
