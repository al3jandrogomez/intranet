/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.sigedepu2;

import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alejandro.gomez
 */
public interface CarpetasRepresentadosRepository extends JpaRepository<CarpetaRepresentado, Long> {
    
}
