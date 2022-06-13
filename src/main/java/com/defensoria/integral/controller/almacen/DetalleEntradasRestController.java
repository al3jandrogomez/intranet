package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.DetalleEntradas;

import com.defensoria.integral.repository.almacen.DetalleEntradaRepository;

@RestController
public class DetalleEntradasRestController {
	@Autowired
	DetalleEntradaRepository detalleEntradaRepository;

	@PostMapping(value = "/guardardetalle")
	public ResponseEntity<?> GuardarDetalle(@RequestBody DetalleEntradas detalleEntrada) {

		detalleEntrada.setActivo("S");
		detalleEntradaRepository.save(detalleEntrada);

		return new ResponseEntity<DetalleEntradas>(detalleEntrada, HttpStatus.OK);

	}

	@PostMapping(value = "buscardetalle")
	public ResponseEntity<?> BuscarDetalle(@RequestBody DetalleEntradas detalleEntrada) {
		List<DetalleEntradas> resultado = detalleEntradaRepository
				.porIdEntrada(detalleEntrada.getEntrada().getIdEntrada());
		return new ResponseEntity<List<DetalleEntradas>>(resultado, HttpStatus.OK);

	}
	@PostMapping(value = "buscardetalle2")
	public ResponseEntity<?> BuscarDetalle2(@RequestBody DetalleEntradas detalleEntrada) {
		List<DetalleEntradas> resultado = detalleEntradaRepository
				.findByIdDetalleEntrada(detalleEntrada.getIdDetalleEntrada());
		return new ResponseEntity<List<DetalleEntradas>>(resultado, HttpStatus.OK);
		
	}
	
	@PostMapping(value = "eliminardetalle")
	public ResponseEntity<?> EliminarDetalle(@RequestBody DetalleEntradas detalleEntrada) {
		
		
		detalleEntradaRepository.Eliminar("N", detalleEntrada.getIdDetalleEntrada());
		return new ResponseEntity<DetalleEntradas>(detalleEntrada, HttpStatus.OK);
		
	}

}
