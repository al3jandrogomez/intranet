/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import com.defensoria.integral.model.subtecnica.Asesoria;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author yo
 */
public interface AsesoriasRepository extends JpaRepository<Asesoria, Serializable> {
    
}
