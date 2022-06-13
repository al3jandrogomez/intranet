/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.adminpersonal;

import com.defensoria.integral.model.adminpersonal.MovAdscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.repository.adminpersonal.MovAdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author alejandro.gomez
 */
@Controller
public class PersonalController {

    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    MovAdscripcionesRepository movimientosRepository;

    @GetMapping(value = "/verificar")
    public ModelAndView VerificarPersonal(@RequestParam Integer id) {

        Personal p = new Personal();
        p.setCveUsuario(id);
        p.setActivo("S");
        MovAdscripciones mv = new MovAdscripciones();
        mv.setPersonal(p);
        mv.setActivo("S");
        List<MovAdscripciones> lista = movimientosRepository.findAll(Example.of(mv));

        Map<String, Object> params = new HashMap<>();
        String nombre = lista.get(0).getPersonal().getNombre() + " " + lista.get(0).getPersonal().getPaterno() + " " + lista.get(0).getPersonal().getMaterno();
        Personal pr = lista.get(0).getPersonal();
        params.put("nombreCompleto", nombre);
        params.put("cveServidor", lista.get(0).getPersonal().getCveServidorPub());
        params.put("correo", lista.get(0).getPersonal().getEmail());
        if (lista.get(0).getPersonal().getPersonalCorreos().size() > 0) {
            params.put("correos", lista.get(0).getPersonal().getPersonalCorreos().get(0).getCorreoElectronico());
        }
        String descPuesto = "";
        Integer tipoMov = 0;
        MovAdscripciones mv2= new MovAdscripciones();
        for (MovAdscripciones puesto : lista.get(0).getPersonal().getMovAdscripciones()) {

            if (puesto.getActivo().equals("S")) {
                mv2=puesto;
                tipoMov = puesto.getCveTipoMovimientoPersonal();
                descPuesto = puesto.getPuesto().getDescPuesto();
                if (puesto.getPuestoFuncional() != null) {
                    descPuesto = puesto.getPuesto().getDescPuesto();
                }
            }
        }

        String estatus = "Servidor Público Activo";
        String imagen = "../resources/images/listo.png";
        String color = "#58ba47";
        String cargo = "";
        String puestoFuncional = "";
        if (tipoMov == 4) {
            estatus = "Servidor Público Dado de Baja";
            imagen = "../resources/images/tache.png";
            color = "#e81615";
        }

        cargo = mv2.getPuesto().getDescPuesto();

        if (mv2.getPuestoFuncional() != null) {
            puestoFuncional = mv.getPuestoFuncional().getDescPuestoFuncional();

        } else {
            System.out.println("Cargo" + cargo + " contains " + cargo.contains("JF"));
            if (mv2.getTipoTitular() != null) {
                if (mv2.getTipoTitular().equals("E")) {
                    if (pr.getCveGenero() == 1) {
                        cargo = "ENCARGADO DEL ";
                    } else {
                        cargo = "ENCARGADA DEL ";
                    }
                    cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();
                }
            }
            if (cargo.contains("JF")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "JEFE DEL ";
                } else {
                    cargo = "JEFA DEL ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else if (cargo.contains("GENERAL")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "DIRECTOR DEL ";
                } else {
                    cargo = "DIRECTORA DEL ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else if (cargo.contains("DIRECTOR")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "DIRECTOR DE LA ";
                } else {
                    cargo = "DIRECTORA DE LA ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else {
                cargo = mv2.getPuesto().getDescPuesto();
            }

        }

        params.put("nombreCompleto", nombre);
        params.put("cveServidor", lista.get(0).getPersonal().getCveServidorPub());
        params.put("correo", lista.get(0).getPersonal().getEmail());
        params.put("puesto", cargo);
        params.put("estatus", estatus);
        params.put("imagen", imagen);
        params.put("color", color);

        return new ModelAndView("adminpersonal/verificar", params);
    }

}
