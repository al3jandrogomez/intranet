package com.defensoria.integral.controller.almacen;

import java.util.ArrayList;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.helpers.CambiarEstatusRequisiciones;
import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.almacen.DetalleRequisiciones;
import com.defensoria.integral.model.almacen.EstatusRequisiciones;
import com.defensoria.integral.model.almacen.MovRequisiciones;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.repository.almacen.DetalleEntradaRepository;
import com.defensoria.integral.repository.almacen.DetalleRequisicionesRepository;
import com.defensoria.integral.repository.almacen.MovRequisicionesRepository;
import com.defensoria.integral.repository.almacen.RequisicionesRepository;

@RestController
@Transactional
public class DetalleRequisicionesRestController {
	@Autowired
	DetalleRequisicionesRepository dRequisicionRepository;
	@Autowired
	MovRequisicionesRepository movRequisicionRepository;
	@Autowired
	DetalleEntradaRepository detalleEntradaRepository;
	@Autowired
	DetalleRequisicionesRepository detalleRequisicionesRepository;
	@Autowired
	RequisicionesRepository requisionesRepository;

	public CambiarEstatusRequisiciones cambiarEstatus = new CambiarEstatusRequisiciones();

	@PostMapping("/guardardrequisicion")
	public ResponseEntity<?> guardarDetalleRequisicion(@RequestBody DetalleRequisiciones dRequisicion) {

		// GUARDAR ARTICULO DE LA REQUISICION
		dRequisicion.setCantidadAutorizada(0);
		dRequisicion.setObservaciones("");
		dRequisicionRepository.save(dRequisicion);
		// IDENTIFICAR ULTIMO REGISTRO DEL MOVIMIENTO
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(dRequisicion.getRequisicion());
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);
		if ((resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion()) < 2) {
			System.err.println(" SE ACTUALIZA EL ESTATUS " + resultado.get(0).getIdMovRequisicion());
			// ACTUALIZAR ESTATUS DE LA REQUISICION
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(2);
			movRequisiciones.setRequisicion(dRequisicion.getRequisicion());
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(1);
			movRequisicionRepository.save(movRequisiciones);
		} else {
			System.err.println("NO SE ACTUALIZA EL ESTATUS");
		}

		return new ResponseEntity<DetalleRequisiciones>(dRequisicion, HttpStatus.OK);

	}

	@PostMapping("/guardardrequisicionaut")
	public ResponseEntity<?> guardarDetalleRequisicionAut(@RequestBody List<DetalleRequisiciones> dRequisiciones) {
		DetalleRequisiciones detalle = new DetalleRequisiciones();
		for (DetalleRequisiciones dRequisicion : dRequisiciones) {

			detalle.setIdDetalleRequisicion(dRequisicion.getIdDetalleRequisicion());
			List<DetalleRequisiciones> result = dRequisicionRepository.findAll(Example.of(detalle));
			result.get(0).setCantidadAutorizada(dRequisicion.getCantidadAutorizada());
			result.get(0).setObservaciones(dRequisicion.getObservaciones());
			dRequisicionRepository.save(result.get(0));
		}
		// IDENTIFICAR ULTIMO REGISTRO DEL MOVIMIENTO
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(dRequisiciones.get(0).getRequisicion());
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);
		if ((resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion()) == 3) {
			System.err.println(" SE ACTUALIZA EL ESTATUS " + resultado.get(0).getIdMovRequisicion());
			// ACTUALIZAR ESTATUS DE LA REQUISICION
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(5);
			movRequisiciones.setRequisicion(dRequisiciones.get(0).getRequisicion());
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(1);
			movRequisicionRepository.save(movRequisiciones);
		} else {
			System.err.println("NO SE ACTUALIZA EL ESTATUS");
		}

		return new ResponseEntity<List<DetalleRequisiciones>>(dRequisiciones, HttpStatus.OK);

	}

	// @PostMapping("/buscardetallerequisicion")
	// public ResponseEntity<?> BuscarDetalleRequisicion(@RequestBody
	// DetalleRequisiciones dRequisicion) {
	// // Example<DetalleRequisiciones> example = Example.of(dRequisicion);
	// List<DetalleRequisiciones> resultado = dRequisicionRepository
	// .findByIdRequisicion(dRequisicion.getRequisicion().getIdRequisicion());
	// List<Integer> listaClvArticulos = new ArrayList<>();
	// Map<Integer, Object> articulos = new HashMap<>();
	// if (resultado.size() > 0) {
	// System.out.println("Requisiciones encontradas =" + resultado.size());
	// //LISTA DE ARTICULOS EN LA REQUISICION
	// for (DetalleRequisiciones detalle : resultado) {
	// listaClvArticulos.add(detalle.getArticulo().getCveArticulo());
	// }
	// // DE LOS ARTICULOS SE BUSCA LA CANTIDAD QUE SE ENCUENTRAN DISPONIBLES EN
	// ALMACEN
	// List<DetalleEntradas> listaArticulos =
	// detalleEntradaRepository.ListaArticulosEntradas(listaClvArticulos);
	// if (listaArticulos.size() > 0) {
	// for (DetalleEntradas det : listaArticulos) {
	// System.out.println(det.getArticulo().getDescArticulo() + " cantidad
	// Ent=>" + det.getCantidad());
	// articulos.put(det.getArticulo().getCveArticulo(), det.getCantidad());
	// }
	// System.out.println("articulos =>" + articulos);
	//// System.out.println("articulosget =>" + articulos.get(4));
	// Integer nuevaCantidad = 0;
	// Integer key = 0;
	// List<DetalleRequisiciones> listaArticulosAut =
	// detalleRequisicionesRepository
	// .ListaArticulosAutorizados(listaClvArticulos);
	// if (listaArticulosAut.size() > 0)
	// for (DetalleRequisiciones det2 : listaArticulosAut) {
	// nuevaCantidad = 0;
	// System.out.println(det2.getArticulo().getDescArticulo() + " cantidad
	// Aut=>"
	// + det2.getCantidadAutorizada());
	// System.out.println(
	// "articulo en MAP=>" +
	// articulos.get(det2.getArticulo().getCveArticulo()).toString());
	// key = det2.getArticulo().getCveArticulo();
	// nuevaCantidad =
	// Integer.parseInt(articulos.get(det2.getArticulo().getCveArticulo()).toString())
	// - det2.getCantidadAutorizada();
	// articulos.put(key, nuevaCantidad);
	// }
	// else {
	// System.out.println("No hay articulos en detalleRequisiciones");
	// }
	// } else {
	// System.out.println("No hay articulos en entradas");
	// }
	// }
	//
	// System.out.println("cantidad de articulos =>" + articulos);
	// return new ResponseEntity<List<DetalleRequisiciones>>(resultado,
	// HttpStatus.OK);
	//
	// }
	// }
	@PostMapping("/buscardetallerequisicion")
	public ResponseEntity<?> BuscarDetalleRequisicion(@RequestBody DetalleRequisiciones dRequisicion) {
		// Example<DetalleRequisiciones> example = Example.of(dRequisicion);
		List<Integer> listaRequisiciones = new ArrayList<>();
		listaRequisiciones.add(dRequisicion.getRequisicion().getIdRequisicion());
		List<DetalleRequisiciones> resultado = dRequisicionRepository.findByClaveRequisiciones(listaRequisiciones);
		// List<Integer> listaClvArticulos = new ArrayList<>();
		// Map<Integer, Object> articulos = new HashMap<>();
		if (resultado.size() > 0) {
			System.out.println("Requisiciones encontradas =" + resultado.size());
			// LISTA DE ARTICULOS EN LA REQUISICION
			// for (DetalleRequisiciones detalle : resultado) {
			// listaClvArticulos.add(detalle.getArticulo().getCveArticulo());
			// }
			// DE LOS ARTICULOS SE BUSCA LA CANTIDAD QUE SE ENCUENTRAN
			// DISPONIBLES EN ALMACEN
			// List<DetalleEntradas> listaArticulos =
			// detalleEntradaRepository.ListaArticulosEntradas(listaClvArticulos);
			// if (listaArticulos.size() > 0) {
			// for (DetalleEntradas det : listaArticulos) {
			// System.out.println(det.getArticulo().getDescArticulo() + "
			// cantidad Ent=>" + det.getCantidad());
			// articulos.put(det.getArticulo().getCveArticulo(),
			// det.getCantidad());
			// }
			// System.out.println("articulos =>" + articulos);
			//// System.out.println("articulosget =>" + articulos.get(4));
			// Integer nuevaCantidad = 0;
			// Integer key = 0;
			// List<DetalleRequisiciones> listaArticulosAut =
			// detalleRequisicionesRepository
			// .ListaArticulosAutorizados(listaClvArticulos);
			// if (listaArticulosAut.size() > 0)
			// for (DetalleRequisiciones det2 : listaArticulosAut) {
			// nuevaCantidad = 0;
			// System.out.println(det2.getArticulo().getDescArticulo() + "
			// cantidad Aut=>"
			// + det2.getCantidadAutorizada());
			// System.out.println(
			// "articulo en MAP=>" +
			// articulos.get(det2.getArticulo().getCveArticulo()).toString());
			// key = det2.getArticulo().getCveArticulo();
			// nuevaCantidad =
			// Integer.parseInt(articulos.get(det2.getArticulo().getCveArticulo()).toString())
			// - det2.getCantidadAutorizada();
			// articulos.put(key, nuevaCantidad);
			// }
			// else {
			// System.out.println("No hay articulos en detalleRequisiciones");
			// }
			// } else {
			// System.out.println("No hay articulos en entradas");
			// }
		}

		// System.out.println("cantidad de articulos =>" + articulos);
		return new ResponseEntity<List<DetalleRequisiciones>>(resultado, HttpStatus.OK);

	}

	@PostMapping("/buscardetallerequisicion2")
	public ResponseEntity<?> BuscarDetalleRequisicion2(@RequestBody DetalleRequisiciones dRequisicion) {
		// BUSCAMOS LA REQUISICION PADRE
		List<DetalleRequisiciones> listaArt=null;
		Requisiciones rq = new Requisiciones();
		rq.setIdRequisicion(dRequisicion.getRequisicion().getIdRequisicion());
		// rq.setTipoRequisicion("V");
		List<Requisiciones> resultado = requisionesRepository.findAll(Example.of(rq));
		List<Integer> listaRequisiciones = new ArrayList<>();
		listaRequisiciones.add(rq.getIdRequisicion());
		if (resultado.size() > 0) {
			
			Adscripciones ads = new Adscripciones();
			ads.setCveAdscripcion(resultado.get(0).getAdscripcion().getCveAdscripcion());
			// BUSCAMOS LOS VALES CON LA CLAVE DEL PADRE
			Requisiciones hijosrq = new Requisiciones();
			hijosrq.setTipoRequisicion("V");
			hijosrq.setIdRequisicionPadre(rq.getIdRequisicion());
			resultado = requisionesRepository.findAll(Example.of(hijosrq));
			if (resultado.size() == 0) {
				System.out.println(
						"No se encontraron vales con la clave del padre, buscamos vales por la clave del area");
				// BUSCAMOS LOS VALES SIN LA CLAVE DEL PADRE

				hijosrq = new Requisiciones();
				hijosrq.setTipoRequisicion("V");
				hijosrq.setAdscripcion(ads);
				resultado = requisionesRepository.findAll(Example.of(hijosrq));
				if (resultado.size() > 0) {
					System.out.println("vales sin padre localizados ");
					for (Requisiciones rqtemp : resultado) {
						if(rqtemp.getIdRequisicionPadre()==null){
						rqtemp.setIdRequisicionPadre(rq.getIdRequisicion());
						requisionesRepository.save(rqtemp);
						listaRequisiciones.add(rqtemp.getIdRequisicion());
						}
					}
				}
			}
			else{
				for(Requisiciones rqtemp:resultado){
					listaRequisiciones.add(rqtemp.getIdRequisicion());
				}
			}
			 listaArt=detalleRequisicionesRepository.findByClaveRequisiciones(listaRequisiciones);
		}

		return new ResponseEntity<List<DetalleRequisiciones>>(listaArt, HttpStatus.OK);

	}

	@PostMapping("/buscardetallearticulo")
	public ResponseEntity<?> BuscarDetalleArticulo(@RequestBody DetalleRequisiciones dRequisicion) {

		List<DetalleRequisiciones> resultado = dRequisicionRepository
				.detalleArticulo(dRequisicion.getIdDetalleRequisicion());

		return new ResponseEntity<List<DetalleRequisiciones>>(resultado, HttpStatus.OK);
	}

	@PostMapping("/eliminardetallearticulo")
	public ResponseEntity<?> EliminarrDetalleArticulo(@RequestBody DetalleRequisiciones dRequisicion) {
		List<DetalleRequisiciones> newRequisicion = dRequisicionRepository.findAll(Example.of(dRequisicion));
		
		if (newRequisicion.size()>0) {
			newRequisicion.get(0).setActivo("N");

			dRequisicionRepository.save(newRequisicion.get(0));

		//	List<DetalleRequisiciones> resultado = dRequisicionRepository
		//			.detalleArticulo(dRequisicion.getIdDetalleRequisicion());

		}

		return new ResponseEntity<DetalleRequisiciones>(dRequisicion, HttpStatus.OK);
	}

}
