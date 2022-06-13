/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.repository.subtecnica;

import org.springframework.data.jpa.repository.JpaRepository;
import com.defensoria.integral.model.subtecnica.Chat;
/**
 *
 * @author yo
 */
public interface ChatRepository  extends JpaRepository<Chat, Long>{
    
}
