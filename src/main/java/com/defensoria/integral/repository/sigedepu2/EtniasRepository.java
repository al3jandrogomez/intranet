/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.Etnia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface EtniasRepository extends JpaRepository<Etnia, Long>{
     @Query("SELECT e FROM Etnia e where e.descEtnia like:descEtnia")
	List<Etnia>findEtnias(@Param("descEtnia")String descEtnia);
    
}
