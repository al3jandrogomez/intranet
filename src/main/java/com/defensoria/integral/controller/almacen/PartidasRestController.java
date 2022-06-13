package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.defensoria.integral.model.almacen.Partida;
import com.defensoria.integral.repository.almacen.PartidaRepository;

@RestController
public class PartidasRestController {
	@Autowired
	PartidaRepository partidaRepository;

	@PostMapping("/guardarpartida")
	public ResponseEntity<?> guardarPartida(@RequestBody Partida partida) {

		if (partida.getCvePartida() == null) {

			if (partidaRepository.find(partida.getClavePartida(), partida.getDescPartida()).size() == 0) {
				partida.setActivo("S");
				partidaRepository.save(partida);
				return new ResponseEntity<Partida>(partida, HttpStatus.OK);
			} else {
				return new ResponseEntity<Partida>(partida, HttpStatus.OK);

			}
		} else {
			partida.setActivo("S");
			partidaRepository.save(partida);
			return new ResponseEntity<Partida>(partida, HttpStatus.OK);
		}

	}
	@PostMapping("/buscarpartida")
	public ResponseEntity<?>buscarPartida(@RequestBody Partida partida){
		Example<Partida> example = Example.of(partida);
		List<Partida> resultado =partidaRepository.findAll(example);
		return new ResponseEntity<List<Partida>>(resultado, HttpStatus.OK);
		
	}
	@PostMapping("/buscarpartidaactiva")
	public ResponseEntity<?>buscarPartidaActiva(@RequestBody Partida partida){
		
		List<Partida> resultado =partidaRepository.findByActivo("S");
		return new ResponseEntity<List<Partida>>(resultado, HttpStatus.OK);
		
	}

}
