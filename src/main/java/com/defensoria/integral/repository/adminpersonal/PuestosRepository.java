/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.adminpersonal;

import com.defensoria.integral.model.adminpersonal.Puesto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface PuestosRepository extends JpaRepository<Puesto, Long> {
    
    @Query("SELECT p FROM Puesto p  where p.descPuesto like:descPuesto")
	List<Puesto>findPuestos(@Param("descPuesto") String descPuesto); 
    
}
