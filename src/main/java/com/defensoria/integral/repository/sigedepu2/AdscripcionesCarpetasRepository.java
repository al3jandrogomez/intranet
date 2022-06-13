/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.AdscripcionesCarpetas;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface AdscripcionesCarpetasRepository extends JpaRepository<AdscripcionesCarpetas, Long>{
    
    @Query("SELECT ac FROM AdscripcionesCarpetas ac inner join ac.adscripcion a inner join ac.tipoCarpeta tc where  a.descAdscripcion like:ads and a.activo='S' and tc.idTipoCarpeta =:idTipoCarpeta")
	List<AdscripcionesCarpetas>findDescAdscripcion(@Param("ads")String ads,@Param("idTipoCarpeta")Integer idTipoCarpeta);
    
}
