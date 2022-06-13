package com.defensoria.integral.controller.sigedepu;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.sigedepu.model.Representacion;
import com.defensoria.sigedepu.repository.RepresentacionRepository;


@RestController
public class ReportesSigedepuRestController {

	@Autowired
	RepresentacionRepository representacionRepository;

	@PostMapping("/OficiosPendientes")
	public ResponseEntity<?> OficiosPendientes() throws ParseException {

		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String Dateimp = "2018-07-11";
		java.util.Date date = df.parse(Dateimp);
		// if you really need java.sql.Date
		
		List<Representacion> listaRepresentaciones = representacionRepository.findByFechaCreacion(date);

		if (listaRepresentaciones.size() > 0) {
			System.out.println("Se recuperaron " + listaRepresentaciones.size() + " registros de representacion");
			for (Representacion rp : listaRepresentaciones) {
				if (rp.getAbogadoAnt() != null)
					System.out.println(rp.getAbogadoAnt().getNombre_completo());
			}
		}

		return new ResponseEntity<List<Representacion>>(listaRepresentaciones, HttpStatus.OK);
	}

}
