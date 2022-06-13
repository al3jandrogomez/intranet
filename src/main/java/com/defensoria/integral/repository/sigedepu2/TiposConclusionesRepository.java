/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.TiposConclusiones;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface TiposConclusionesRepository extends JpaRepository<TiposConclusiones, Long> {

    @Query("SELECT tc FROM  TiposConclusiones tc  WHERE tc.descTipoConclusiones like:descTipoConclusiones and tc.activo='S' and tc.idTipoCarpeta=:idTipoCarpeta")
    List<TiposConclusiones> findTipoConclusiones(@Param("descTipoConclusiones") String descTipoConclusiones, @Param("idTipoCarpeta") Integer idTipoCarpeta);

    List<TiposConclusiones> findByDescTipoConclusionesLikeAndActivoEquals(String descTipoConclusiones, String activo);
}
