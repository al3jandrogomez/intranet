package com.defensoria.integral.repository.documentos;


import com.defensoria.integral.model.documentos.DocumentoFormulario;

import org.springframework.data.jpa.repository.JpaRepository;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */

public interface DocumentosFormulariosRepository extends JpaRepository<DocumentoFormulario, Long> {
    
}
