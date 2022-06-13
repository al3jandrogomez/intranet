/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.constancias;

import com.defensoria.integral.model.constancias.Instituciones;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface InstitucionesRepository  extends JpaRepository<Instituciones, Serializable>{
    
    @Query("SELECT  i FROM Instituciones i  WHERE  i.descInstitucion like:descInstitucion")
	 List<Instituciones>findByDescInstituciones(@Param("descInstitucion")String descInstitucion);
    
}
