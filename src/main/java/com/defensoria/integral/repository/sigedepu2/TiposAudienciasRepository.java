/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.TiposAudiencias;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface TiposAudienciasRepository extends JpaRepository<TiposAudiencias, Long> {
    
     @Query("SELECT ta FROM  TiposAudiencias ta WHERE ta.descTipoAudiencia like:descTipoAudiencia and ta.idTipoCarpeta=:idTipoCarpeta and ta.activo='S' ")
    List<TiposAudiencias> findTipoAudiencia(@Param("descTipoAudiencia") String desTipoAudiencia,@Param("idTipoCarpeta") Integer idTipoCarpeta);
    
    List<TiposAudiencias> findByDescTipoAudienciaLikeAndActivoEquals(String descTipoAudiencia,String activo);
    
}
