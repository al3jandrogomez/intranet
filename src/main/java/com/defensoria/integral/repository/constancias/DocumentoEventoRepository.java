/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.constancias;

import com.defensoria.integral.model.constancias.DocumentoEvento;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alejandro.gomez
 */
public interface DocumentoEventoRepository  extends JpaRepository<DocumentoEvento, Long>{
    
}
