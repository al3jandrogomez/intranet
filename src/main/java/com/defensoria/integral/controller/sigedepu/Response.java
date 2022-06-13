/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.sigedepu;

import java.util.Map;
import java.util.TreeMap;


public class Response {
  public static final String RESPONSE_OK = "OK";
    public static final String RESPONSE_NOT_OK = "NOT_OK";
    public static final String RESPONSE_EXISTE = "EXISTE";

    private String respuesta;
    private String estatus;
    private Map<String, Object> campos = new TreeMap<>();

    public Response() {
    }

    public Response(String respuesta, String estatus) {
        this.respuesta = respuesta;
        this.estatus = estatus;
    }

    public static Response getInstance() {
        return new Response();
    }

    public String getRespuesta() {
        return respuesta;
    }

    public void setRespuesta(String respuesta) {
        this.respuesta = respuesta;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public Map<String, Object> getCampos() {
        return campos;
    }

    public void setCampos(Map<String, Object> campos) {
        this.campos = campos;
    }

    public void addCampo(String key, Object value) {
        this.campos.put(key, value);
    }

    public Object getCampo(String Key) {
        return this.campos.get(Key);
    }

}
