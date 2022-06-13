/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.RepresentacionFirma;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface RepresentacionFirmaRepository extends JpaRepository<RepresentacionFirma, Long>{
    
    @Query("SELECT r FROM RepresentacionFirma r WHERE r.idRepresentacion in :ids")
	public List<RepresentacionFirma>findFirmas(@Param("ids") List<Integer> ids);
    
}
