package com.defensoria.integral.controller.almacen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.almacen.Contadores;
import com.defensoria.integral.model.almacen.DetalleRequisiciones;
import com.defensoria.integral.model.almacen.EstatusRequisiciones;
import com.defensoria.integral.model.almacen.MovRequisiciones;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.model.almacen.TiposContadores;
import com.defensoria.integral.repository.almacen.DetalleRequisicionesRepository;
import com.defensoria.integral.repository.almacen.EstatusRequisicionesRepository;
import com.defensoria.integral.repository.almacen.MovRequisicionesRepository;
import com.defensoria.integral.repository.almacen.RequisicionesRepository;

@RestController
@Transactional
public class ValesRestController {
	@Autowired
	RequisicionesRepository requisicionesRepository;
	@Autowired
	DetalleRequisicionesRepository detRequisicionesRepository;
	@Autowired
	MovRequisicionesRepository movRequisicionRepository;
	@Autowired
	ContadoresRestController contadorController;
	@Autowired
	EstatusRequisicionesRepository estatusRequisiconesRepository;
	@Autowired
	private HttpSession httpSession;
	@Autowired
	ApplicationContext applicationContext;
	@Autowired
	DetalleRequisicionesRepository detalleRequisicionesRepository;

	@PostMapping("/guardarvales")
	public ResponseEntity<?> GuardarRequisicion(@RequestBody Requisiciones requisicion) {

		if (requisicion.getIdRequisicion() == null) {
			requisicion.setActivo("S");
			Adscripciones adscripcion = new Adscripciones();
			Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
			requisicion.setCveUsuario(cveUsuario);

			Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
			adscripcion.setCveAdscripcion(cveAdscripcion);
			requisicion.setAdscripcion(adscripcion);
			requisicion.setTipoRequisicion("V");
			requisicionesRepository.save(requisicion);
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(1);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(cveUsuario);
			movRequisicionRepository.save(movRequisiciones);
			return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);
		} else {
			requisicion.setActivo("S");
			requisicionesRepository.save(requisicion);

			return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);
		}

	}

	@PostMapping("/enviarvales")
	public ResponseEntity<?> EnviarRequisicion(@RequestBody Requisiciones requisicion) {

		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(requisicion);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);
		if ((resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion()) < 3) {
			System.err.println(" SE ACTUALIZA EL ESTATUS " + resultado.get(0).getIdMovRequisicion());
			// ACTUALIZAR ESTATUS DE LA REQUISICION
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(3);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString()));
			movRequisicionRepository.save(movRequisiciones);
			// ACTUALIZAR EL NUMERO DE LA REQUISICION

			// Contadores contador = new Contadores();
			Adscripciones adscripcion = new Adscripciones();
			// TiposContadores tipoContador = new TiposContadores();

			// adscripcion.setCveAdscripcion(80);
			// tipoContador.setCveTipoContador(1);
			// contador.setAdscripcion(adscripcion);
			// contador.setTipoContador(tipoContador);
			// contador.setAnio(2018);
			// contador = contadorController.GenerarContador(contador);
			requisicion.setActivo("S");
			requisicion.setTipoRequisicion("V");
			requisicion.setCveUsuario(Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString()));
			Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
			adscripcion.setCveAdscripcion(cveAdscripcion);
			requisicion.setAdscripcion(adscripcion);
			// requisicion.setAnioRequisicion(contador.getAnio());
			// requisicion.setNumeroRequisicion(contador.getNumero());
			requisicionesRepository.save(requisicion);
		} else {
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);
			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(3);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString()));
			movRequisicionRepository.save(movRequisiciones);
		}
		return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);

	}

	@PostMapping("/regresarvales")
	public ResponseEntity<?> RegresarRequisicion(@RequestBody MovRequisiciones movimiento) {

		System.out.println(movimiento.getIdMovRequisicion());
		Requisiciones rq = new Requisiciones();
		rq.setIdRequisicion(movimiento.getIdMovRequisicion());
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(rq);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);
		System.err.println(" SE ACTUALIZA EL ESTATUS " + resultado.get(0).getIdMovRequisicion());
		// ACTUALIZAR ESTATUS DE LA REQUISICION
		MovRequisiciones movRequisiciones = new MovRequisiciones();
		movRequisiciones = resultado.get(0);
		movRequisiciones.setActivo("N");
		movRequisicionRepository.save(movRequisiciones);

		// REGISTRAR EL ESTATUS DE LA REQUISICION
		movRequisiciones = new MovRequisiciones();
		EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
		EstRequisiciones.setCveEstatusRequisicion(movimiento.getEstatusRequisicion().getCveEstatusRequisicion());
		movRequisiciones.setRequisicion(rq);
		movRequisiciones.setActivo("S");
		movRequisiciones.setObservaciones(movimiento.getObservaciones());
		movRequisiciones.setEstatusRequisicion(EstRequisiciones);
		movRequisiciones.setCveUsuario(Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString()));
		movRequisicionRepository.save(movRequisiciones);
		return new ResponseEntity<MovRequisiciones>(movimiento, HttpStatus.OK);

	};

	@PostMapping("/autorizarvales")
	public ResponseEntity<?> AutorizarReq(@RequestBody Requisiciones requisicion) {
		Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
		// Requisiciones newRequisicion = new Requisiciones();
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(requisicion);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);

		System.out.println("Movimiento actual " + resultado.get(0).getEstatusRequisicion().getDescEstatusRequisicion());

		if (resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion() == 5) {
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(6);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(cveUsuario);
			movRequisicionRepository.save(movRequisiciones);

		}

		return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);
	}

	@PostMapping("/noautorizarvales")
	public ResponseEntity<?> NoAutorizarReq(@RequestBody Requisiciones requisicion) {
		Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
		// Requisiciones newRequisicion = new Requisiciones();
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(requisicion);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);

		System.out.println("Movimiento actual " + resultado.get(0).getEstatusRequisicion().getDescEstatusRequisicion());
		// Estatus de requisicion 5 ==
		if (resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion() == 5
				|| resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion() == 3) {
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(4);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(cveUsuario);
			movRequisicionRepository.save(movRequisiciones);

		}

		return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);
	}

	@PostMapping("/eliminarvales")
	public ResponseEntity<?> EliminarRequisicion(@RequestBody Requisiciones requisicion) {
		Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
		Requisiciones newRequisicion = new Requisiciones();
		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(requisicion);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);
		if ((resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion()) < 10) {
			// CAMBIAR ESTATUS

			List<Requisiciones> tempNewRequisicion = requisicionesRepository.findAll(Example.of(requisicion));
			if (tempNewRequisicion.size() > 0) {
				newRequisicion=tempNewRequisicion.get(0);
				if (newRequisicion != null) {
					DetalleRequisiciones detalleArticulos = new DetalleRequisiciones();
					detalleArticulos.setRequisicion(requisicion);
					List<DetalleRequisiciones> articulos = detRequisicionesRepository
							.findAll(Example.of(detalleArticulos));
					if (articulos != null) {
						for (DetalleRequisiciones articulo : articulos) {
							articulo.setActivo("N");
							detRequisicionesRepository.save(articulo);
						}

					}
					newRequisicion.setActivo("N");
					requisicionesRepository.save(newRequisicion);
					System.err.println(" SE ACTUALIZA EL ESTATUS " + resultado.get(0).getIdMovRequisicion());
					// ACTUALIZAR ESTATUS DE LA REQUISICION
					MovRequisiciones movRequisiciones = new MovRequisiciones();
					movRequisiciones = resultado.get(0);
					movRequisiciones.setActivo("N");
					movRequisicionRepository.save(movRequisiciones);

					// REGISTRAR EL ESTATUS DE LA REQUISICION
					movRequisiciones = new MovRequisiciones();
					EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
					EstRequisiciones.setCveEstatusRequisicion(6);
					movRequisiciones.setRequisicion(requisicion);
					movRequisiciones.setActivo("S");
					movRequisiciones.setEstatusRequisicion(EstRequisiciones);
					movRequisiciones.setCveUsuario(cveUsuario);
					movRequisicionRepository.save(movRequisiciones);
				} else {
					System.err.println("No existe el requerimiento");
				}
			}
		} else {
			System.err.println("NO SE ACTUALIZA EL ESTATUS");
		}
		return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);

	}

	@PostMapping("/buscarvales")
	public ResponseEntity<?> BuscarRequisiciones(@RequestBody Requisiciones requisicion) {
		if (getHttpSession().getAttribute("cveAdscripcion") != null) {
			Adscripciones adscripcion = new Adscripciones();
			Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
			adscripcion.setCveAdscripcion(cveAdscripcion);
			requisicion.setAdscripcion(adscripcion);
			requisicion.setActivo("S");
			requisicion.setTipoRequisicion("V");
			// List<Requisiciones> resultado =
			// requisicionesRepository.findAll(Example.of(requisicion));
			List<Requisiciones> resultado = requisicionesRepository.findByAdscripcion(cveAdscripcion, "S",
					 Sort.by(Sort.Direction.DESC, "idRequisicion"), "V");

			return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);
		} else {
			Map<String, Object> respuesta = new HashMap<String, Object>();
			respuesta.put("mensaje", "Se termino la sesion");
			respuesta.put("errorType", 1);
			return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
		}

	}

	@PostMapping("/buscarvalesaut")
	public ResponseEntity<?> BuscarRequisicionesAut(@RequestBody Requisiciones requisicion) {

		List<Requisiciones> resultado = requisicionesRepository.findByAutorizar(requisicion.getIdRequisicion());

		return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);

	}

	@PostMapping("/buscarvalesEntrega")
	public ResponseEntity<?> BuscarRequisicionesEntrega(@RequestBody Requisiciones requisicion) {

		List<Requisiciones> resultado = requisicionesRepository.findAll(Example.of(requisicion));

		return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);

	}

	// @PostMapping("/listaestatusreq")
	// public ResponseEntity<?> ListaEstatusReq(@RequestBody EstatusRequisiciones
	// estatus) {
	//
	// List<EstatusRequisiciones> ListaEstatus =
	// estatusRequisiconesRepository.findAll(Example.of(estatus));
	//
	// return new ResponseEntity<List<EstatusRequisiciones>>(ListaEstatus,
	// HttpStatus.OK);
	//
	// }

	@PostMapping("/entregarvales")
	public ResponseEntity<?> entregarRequisicion(@RequestBody Requisiciones requisicion) {

		Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());

		MovRequisiciones movRequisiciones0 = new MovRequisiciones();
		movRequisiciones0.setRequisicion(requisicion);
		movRequisiciones0.setActivo("S");
		Example<MovRequisiciones> example = Example.of(movRequisiciones0);
		List<MovRequisiciones> resultado = movRequisicionRepository.findAll(example);

		System.out.println("Movimiento actual " + resultado.get(0).getEstatusRequisicion().getDescEstatusRequisicion());

		if (resultado.get(0).getEstatusRequisicion().getCveEstatusRequisicion() == 6) {
			MovRequisiciones movRequisiciones = new MovRequisiciones();
			movRequisiciones = resultado.get(0);
			movRequisiciones.setActivo("N");
			movRequisicionRepository.save(movRequisiciones);

			// REGISTRAR EL ESTATUS DE LA REQUISICION
			movRequisiciones = new MovRequisiciones();
			EstatusRequisiciones EstRequisiciones = new EstatusRequisiciones();
			EstRequisiciones.setCveEstatusRequisicion(7);
			movRequisiciones.setRequisicion(requisicion);
			movRequisiciones.setActivo("S");
			movRequisiciones.setEstatusRequisicion(EstRequisiciones);
			movRequisiciones.setCveUsuario(cveUsuario);
			movRequisicionRepository.save(movRequisiciones);

			return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);
		}
		return new ResponseEntity<Requisiciones>(requisicion, HttpStatus.OK);

	}

	// @PostMapping("/reporterequisicion")
	// public ModelAndView ReporteRequisicion(@RequestBody Requisiciones
	// requisicion) {
	// JasperReportsPdfView view = new JasperReportsPdfView();
	// view.setUrl("classpath:/resources/reports/requisiciones.jrxml");
	// view.setApplicationContext(applicationContext);
	// Map<String, Object> params = new HashMap<>();
	// List<Map<String, Object>> listaArticulos = new ArrayList<>();
	// DetalleRequisiciones detalle = new DetalleRequisiciones();
	// detalle.setRequisicion(requisicion);
	// List<DetalleRequisiciones> listaRequisiciones =
	// detalleRequisicionesRepository.findAll(Example.of(detalle));
	//
	// for (DetalleRequisiciones detalle2 : listaRequisiciones) {
	// Map<String, Object> articulo = new HashMap<>();
	// articulo.put("cantidadRequerida", detalle2.getCantidadRequerida());
	// articulo.put("cantidadAutorizada", detalle2.getCantidadAutorizada());
	// articulo.put("descArticulo", detalle2.getArticulo().getDescArticulo());
	// articulo.put("unidad",
	// detalle2.getArticulo().getUnidades().getDescUnidad());
	// listaArticulos.add(articulo);
	//
	// }
	// params.put("datasource", listaArticulos);
	// return new ModelAndView(view, params);
	//
	// }

	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}

}
