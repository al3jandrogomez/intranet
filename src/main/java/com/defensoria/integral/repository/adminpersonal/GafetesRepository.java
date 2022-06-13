/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.adminpersonal;

import com.defensoria.integral.model.adminpersonal.Gafetes;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author alejandro.gomez
 */
public interface GafetesRepository extends JpaRepository<Gafetes, Serializable>{
    
}
