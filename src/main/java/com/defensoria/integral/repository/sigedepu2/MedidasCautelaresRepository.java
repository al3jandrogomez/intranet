/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.MedidaCautelares;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface MedidasCautelaresRepository extends JpaRepository<MedidaCautelares, Long>{
    
    @Query("SELECT m FROM MedidaCautelares m where  m.descMedidaCautelar like:ads  and m.activo='S'")
	List<MedidaCautelares>findDescMedidasCautelares(@Param("ads")String ads);
    
}
