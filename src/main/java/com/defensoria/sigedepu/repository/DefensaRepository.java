/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.sigedepu.repository;

import com.defensoria.sigedepu.model.Defensa;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface DefensaRepository extends JpaRepository<Defensa, Long> {
    
    @Query("SELECT d FROM Defensa d   where d.fecha_presentacion>=:fechaPresentacion and d.fecha_presentacion<=:fechaPresentacion2 and d.usuario_id=:usuario  Order by d.id desc")
	List<Defensa>findDefensasByFechaRegistro(@Param("fechaPresentacion")Date fechaPresentacion,@Param("fechaPresentacion2")Date fechaPresentacion2,@Param("usuario")Integer usuario);
    
        
         @Query("SELECT d FROM Defensa d   where  d.usuario_id=:usuario  and d.id not in:listaDefensas Order by d.id desc")
	List<Defensa> listaDefensasPendientes(@Param("usuario") Integer usuario,@Param("listaDefensas") List<Integer> listaDefensas);
    
}
