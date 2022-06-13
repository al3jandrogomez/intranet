package com.defensoria.integral.model.subtecnica;

public class Hora {
    private String hora;
    private String activo;
    private Integer numCitas;
    private Integer numAsesores;


    public String getHora() {
        return this.hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getActivo() {
        return this.activo;
    }

    public void setActivo(String activo) {
        this.activo = activo;
    }

    public Integer getNumCitas() {
        return this.numCitas;
    }

    public void setNumCitas(Integer numCitas) {
        this.numCitas = numCitas;
    }

    public Integer getNumAsesores() {
        return this.numAsesores;
    }

    public void setNumAsesores(Integer numAsesores) {
        this.numAsesores = numAsesores;
    }

    
}
