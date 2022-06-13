package com.defensoria.integral.model.documentos;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
 
    private String description;
    private Integer cveAdscripcion;
    private Integer numMes;
    private Integer anio;


    public Integer getAnio() {
        return this.anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

 
    private MultipartFile[] files;


    public Integer getCveAdscripcion() {
        return this.cveAdscripcion;
    }

    public void setCveAdscripcion(Integer cveAdscripcion) {
        this.cveAdscripcion = cveAdscripcion;
    }

    public Integer getNumMes() {
        return this.numMes;
    }

    public void setNumMes(Integer numMes) {
        this.numMes = numMes;
    }

 
    public String getDescription() {
        return description;
    }
 
    public void setDescription(String description) {
        this.description = description;
    }
 
    public MultipartFile[] getFiles() {
        return files;
    }
 
    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }
 
}