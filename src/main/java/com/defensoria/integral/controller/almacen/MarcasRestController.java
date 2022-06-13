package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.Marcas;
import com.defensoria.integral.repository.almacen.MarcaRepository;

@RestController
public class MarcasRestController {
	@Autowired
	MarcaRepository marcaRepository;

	@PostMapping(value = "/guardarmarcasAlm")
	public ResponseEntity<?> GuardarMarcas(@RequestBody Marcas marcas) {

		if (marcas.getCveMarcaAlm() == null) {
			if (marcaRepository.find(marcas.getDescMarca()).size() == 0) {
				marcas.setActivo("S");
				marcaRepository.save(marcas);
				return new ResponseEntity<Marcas>(marcas, HttpStatus.OK);
			} else {
				return new ResponseEntity<Marcas>(marcas, HttpStatus.OK);
			}
		} else {
			marcas.setActivo("S");
			marcaRepository.save(marcas);
			return new ResponseEntity<Marcas>(marcas, HttpStatus.OK);
		}

	}

	@PostMapping(value = "/buscarmarcas")
	public ResponseEntity<?> buscarMarcas(@RequestBody Marcas marcas) {
		Example<Marcas> example = Example.of(marcas);
		List<Marcas> resultado = marcaRepository.findAll(example);
		return new ResponseEntity<List<Marcas>>(resultado, HttpStatus.OK);

	}
}
