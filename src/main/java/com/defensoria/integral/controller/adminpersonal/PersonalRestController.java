/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.adminpersonal;

import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.adminpersonal.Puesto;
import com.defensoria.integral.model.adminpersonal.PuestoFuncional;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.adminpersonal.PuestoFuncionalRepository;
import com.defensoria.integral.repository.adminpersonal.PuestosRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alejandro.gomez
 */
@RestController
public class PersonalRestController {

    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PuestosRepository puestosRepository;
    @Autowired
    PuestoFuncionalRepository puestoFuncionalRepository;

    @PostMapping(value = "/consultaPersonal")
    public ResponseEntity<?> ConsultaPersonal(@RequestBody Personal p) {

        p.setActivo("S");

        List<Personal> lista = personalRepository.findAll(Example.of(p));
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @PostMapping(value = "/consultapuestos")
    public ResponseEntity<?> ConsultaPuestos(@RequestBody Puesto p) {

        p.setActivo("S");

        List<Puesto> lista = puestosRepository.findPuestos("%"+p.getDescPuesto()+"%");
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @PostMapping(value = "/consultapuestosnominal")
    public ResponseEntity<?> ConsultaPuestosFuncional(@RequestBody PuestoFuncional p) {
        System.out.println("consultapuestosnominal " +p.getDescPuestoFuncional());

        p.setActivo("S");

        List<PuestoFuncional> lista = puestoFuncionalRepository.findByDescPuestoFuncionalLike(p.getDescPuestoFuncional());
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    
    

}
