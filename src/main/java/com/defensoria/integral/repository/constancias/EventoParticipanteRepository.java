/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.constancias;

import com.defensoria.integral.model.constancias.EventoParticipante;
import com.defensoria.integral.model.constancias.Participantes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface EventoParticipanteRepository  extends JpaRepository<EventoParticipante, Long>{
    
    @Query("SELECT p FROM EventoParticipante ev inner join ev.participante p inner join ev.evento e WHERE e.idEvento=:idEvento and  CONCAT(p.nombre,' ',p.paterno,' ',p.materno) like:nombre and p.activo='S' ")
    List<Participantes> findByParticipante(@Param("nombre") String nombre,@Param("idEvento")Integer idEvento);
    
}
