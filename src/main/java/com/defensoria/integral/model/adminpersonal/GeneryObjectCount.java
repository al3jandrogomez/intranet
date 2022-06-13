/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alejandro
 */
package com.defensoria.integral.model.adminpersonal;
import java.text.DateFormatSymbols;


public class GeneryObjectCount {
    private Long total;
  
    private String mes;
    private String nombre;
    private Integer numeroMes;
    private Municipios municipio;
    private String delito;


    public Municipios getMunicipio() {
        return this.municipio;
    }

    public void setMunicipio(Municipios municipio) {
        this.municipio = municipio;
    }



    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDelito() {
        return this.delito;
    }

    public void setDelito(String delito) {
        this.delito =altasBajas(delito);
    }
 

   

    public GeneryObjectCount(Long total, String name,String mes,Integer numero){
        this.total=total;
        this.nombre=name;
        if(mes.equals(""))
        this.mes=altasBajas( new DateFormatSymbols().getMonths()[numero-1]);
        else
        this.mes= altasBajas( mes);
        this.numeroMes=numero;

    }
    public GeneryObjectCount(Long total, String name,String mes,Integer numero,String delito){
        this.total=total;
        this.nombre=name;
        if(mes.equals(""))
        this.mes=altasBajas( new DateFormatSymbols().getMonths()[numero-1]);
        else
        this.mes=altasBajas( mes);
        this.numeroMes=numero;
        this.delito=altasBajas( delito);

    }

    public String altasBajas(String texto) {
        texto = texto.toLowerCase();

        String primero = texto.charAt(0) + "";

        texto = primero.toUpperCase() + texto.substring(1, texto.length());

        return texto;

    }


    public Long getTotal() {
        return this.total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


  

    public String getMes() {
        return this.mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }


    public Integer getNumeroMes() {
        return this.numeroMes;
    }

    public void setNumeroMes(Integer numeroMes) {
        this.numeroMes = numeroMes;
    }
    

    
}
