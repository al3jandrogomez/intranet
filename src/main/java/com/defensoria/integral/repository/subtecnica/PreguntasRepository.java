/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import com.defensoria.integral.model.subtecnica.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alejandro.gomez
 */
public interface PreguntasRepository extends JpaRepository<Pregunta, Long>{
    
}
