/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import java.util.Date;


/**
 *
 * @author alec1_000
 */
public class CountHoras {
    private Date horas;
    private Long conteo;
    
    public CountHoras( Date horas,Long conteo){
    this.horas=horas;
    this.conteo=conteo;
    
    }

    public Date getHoras() {
        return horas;
    }

    public void setHoras(Date horas) {
        this.horas = horas;
    }

    public Long getConteo() {
        return conteo;
    }

    public void setConteo(Long conteo) {
        this.conteo = conteo;
    }

   
    
    
    
    
}
