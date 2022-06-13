/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.estadistica;

import java.util.ArrayList;
import java.util.List;

import com.defensoria.integral.model.adminpersonal.Adscripciones;

import com.defensoria.integral.model.informes.Informe;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.informes.InformesRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alejandro
 */
@RestController
public class InformesRestController {

    @Autowired
    AdscripcionesRepository adscripcionesRepository;
    @Autowired
    InformesRepository informesRepository;

    @PostMapping("listaentregainformesfamiliares")
    public ResponseEntity<?> ListaEntregaInformesFamiliares(@RequestBody Informe inf) {
        List<Integer> claves = this.listaclavesAdscripcion("%familiares%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves2 = this.listaclavesAdscripcion("%responsabilidades%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves3 = this.listaclavesAdscripcion("%civil%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves4 = this.listaclavesAdscripcion("%linea%", inf.getAdscripcion().getCveRegion());
        claves.addAll(claves2);
        claves.addAll(claves3);
        claves.addAll(claves4);


        List<Informe> lista = informesRepository.findInformesPorAdscripcionesPorEstatus(claves, inf.getEstatusInforme(), inf.getCveMes(),inf.getAnio());

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("listainformesservidorpublico")
    public ResponseEntity<?> Listainfomresservidorpublico(@RequestBody Informe inf) {
      


        List<Informe> lista = informesRepository.findAll(Example.of(inf));

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @PostMapping("informeexcel")
    public ResponseEntity<?> InformeExcel(@RequestBody Informe inf) {
      


        List<Informe> lista = informesRepository.findAll(Example.of(inf));

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    
    @PostMapping("rechazarinforme")
    public ResponseEntity<?> RechazarInforme(@RequestBody Informe inf) {
      


        List<Informe> lista = informesRepository.findAll(Example.of(inf));
        inf=lista.get(0);
        inf.setEstatusInforme("R");
        informesRepository.save(inf);

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    @PostMapping("autorizarinforme")
    public ResponseEntity<?>AutorizarInforme(@RequestBody Informe inf) {
      


        List<Informe> lista = informesRepository.findAll(Example.of(inf));
        inf=lista.get(0);
        inf.setEstatusInforme("A");
        informesRepository.save(inf);


        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("listaentregainformespenales")
    public ResponseEntity<?> ListaEntregaInformesPenales(@RequestBody Informe inf) {
        List<Integer> claves = this.listaclavesAdscripcion("%control%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves17 = this.listaclavesAdscripcion("%tribunal%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves18 = this.listaclavesAdscripcion("%procuraduria%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves19 = this.listaclavesAdscripcion("%penal%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves20 = this.listaclavesAdscripcion("%CIUDADANA%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves2 = this.listaclavesAdscripcion("%primera%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves3 = this.listaclavesAdscripcion("%sala%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves4 = this.listaclavesAdscripcion("%adolescentes%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves5 = this.listaclavesAdscripcion("%sentencias%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves6 = this.listaclavesAdscripcion("%agencia%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves7 = this.listaclavesAdscripcion("%ministerio%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves8 = this.listaclavesAdscripcion("%justicia%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves9 = this.listaclavesAdscripcion("%tribunal%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves10 = this.listaclavesAdscripcion("%AMPEVFSG%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves11 = this.listaclavesAdscripcion("%CENTRO%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves12 = this.listaclavesAdscripcion("%fiscalia%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves13 = this.listaclavesAdscripcion("%especializada%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves14 = this.listaclavesAdscripcion("%penales%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves15 = this.listaclavesAdscripcion("%OCRA%", inf.getAdscripcion().getCveRegion());
        List<Integer> claves21 = this.listaclavesAdscripcion("%mesa%", inf.getAdscripcion().getCveRegion());
        



        claves.addAll(claves2);
        claves.addAll(claves3);
        claves.addAll(claves4);
        claves.addAll(claves5);
        claves.addAll(claves6);
        claves.addAll(claves7);
        claves.addAll(claves8);
        claves.addAll(claves9);
        claves.addAll(claves10);
        claves.addAll(claves11);
        claves.addAll(claves12);
        claves.addAll(claves13);
        claves.addAll(claves14);
        claves.addAll(claves15);
        claves.addAll(claves17);
        claves.addAll(claves18);
        claves.addAll(claves19);
        claves.addAll(claves20);
        claves.addAll(claves21);
        

        List<Informe> lista = informesRepository.findInformesPorAdscripcionesPorEstatus(claves, inf.getEstatusInforme(), inf.getCveMes(),inf.getAnio());

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    public List<Integer> listaclavesAdscripcion(String descAdscripcion, Integer cveRegion) {

        List<Integer> listaClaves = new ArrayList<>();

        List<Adscripciones> lista = adscripcionesRepository.findDescAdscripcionCveRegion(descAdscripcion, cveRegion);
        for (Adscripciones ads : lista) {
            listaClaves.add(ads.getCveAdscripcion());
        }
 
        return listaClaves;

    }

}
