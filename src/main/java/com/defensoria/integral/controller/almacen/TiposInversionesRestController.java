package com.defensoria.integral.controller.almacen;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.almacen.TiposInversiones;
import com.defensoria.integral.repository.almacen.TiposInversionRepository;

@RestController
public class TiposInversionesRestController {
	@Autowired
	TiposInversionRepository tiposInversionRespository;

	@PostMapping(value = "/guardartiposinversiones")
	public ResponseEntity<?> GuardarTipoInversion(@RequestBody TiposInversiones tipoInversion ) {
		if (tipoInversion.getCveTipoInversion() == null) {

			if (tiposInversionRespository.ListaCatalogo(tipoInversion.getDescTipoInversion()).size() == 0) {

				System.err.println("guardando registro");
				tipoInversion.setActivo("S");
				tiposInversionRespository.save(tipoInversion);
				return new ResponseEntity<TiposInversiones>(tipoInversion, HttpStatus.OK);
			} else {
				return new ResponseEntity<TiposInversiones>(tipoInversion, HttpStatus.OK);
			}
		} else {
			System.err.println("actualizando registro");
			tipoInversion.setActivo("S");
			tiposInversionRespository.save(tipoInversion);
			return new ResponseEntity<TiposInversiones>(tipoInversion, HttpStatus.OK);
		}
	}

	@PostMapping("/buscartiposinversiones")
	public ResponseEntity<?> buscarTiposInversiones(@RequestBody TiposInversiones tipoInversion ) {
		Example<TiposInversiones> example = Example.of(tipoInversion);
		List<TiposInversiones> resultado = tiposInversionRespository.findAll(example);

		return new ResponseEntity<List<TiposInversiones>>(resultado, HttpStatus.OK);
	}
}
