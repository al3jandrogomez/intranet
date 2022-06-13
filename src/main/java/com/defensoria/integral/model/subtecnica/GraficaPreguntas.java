/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.model.subtecnica;

import javax.persistence.Column;

/**
 *
 * @author alejandro.gomez
 */
public class GraficaPreguntas {
    
    public GraficaPreguntas(Integer idPregunta,String descPregunta,String descRespuesta,Long total){
        this.descPregunta= descPregunta; 
        this.descRespuesta=descRespuesta;
        this.total = total;
        this.idPregunta = idPregunta;
        
    };
    @Column(name="idPregunta") 
    private Integer idPregunta;
   @Column(name="descPregunta") 
    private String descPregunta;
   @Column(name="descRespuesta")
    private String descRespuesta;
   @Column(name="total")
    private Long total;

    public String getDescPregunta() {
        return descPregunta;
    }

    public void setDescPregunta(String descPregunta) {
        this.descPregunta = descPregunta;
    }

    public String getDescRespuesta() {
        return descRespuesta;
    }

    public void setDescRespuesta(String descRespuesta) {
        this.descRespuesta = descRespuesta;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getIdPregunta() {
        return idPregunta;
    }

    public void setIdPregunta(Integer idPregunta) {
        this.idPregunta = idPregunta;
    }
    
    

   
   
   
    
    
    
}
