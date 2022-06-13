/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.constancias;

import com.defensoria.integral.model.constancias.Participantes;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author alejandro.gomez
 */
public interface ParticipanteRepository extends JpaRepository<Participantes, Long> {

    @Query("SELECT p FROM Participantes p  WHERE  CONCAT(p.nombre,' ',p.paterno,' ',p.materno) like:nombre and p.activo='S' ")
    List<Participantes> findByParticipante(@Param("nombre") String nombre);

    @Query("SELECT p FROM Participantes p  WHERE  p.correoElectronico like:correo and p.activo='S' ")
    List<Participantes> findByCorreo(@Param("correo") String correo);

}
