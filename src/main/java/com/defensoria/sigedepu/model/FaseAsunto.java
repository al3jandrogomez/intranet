package com.defensoria.sigedepu.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fase_asunto")
public class FaseAsunto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String tipo_fase_asunto;
    private String nombre_fase;


    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_fase_asunto() {
        return this.tipo_fase_asunto;
    }

    public void setTipo_fase_asunto(String tipo_fase_asunto) {
        this.tipo_fase_asunto = tipo_fase_asunto;
    }

    public String getNombre_fase() {
        return this.nombre_fase;
    }

    public void setNombre_fase(String nombre_fase) {
        this.nombre_fase = nombre_fase;
    }

    
}
