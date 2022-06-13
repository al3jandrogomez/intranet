package com.defensoria.integral.controller.almacen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.almacen.ArticulosAutorizados;
import com.defensoria.integral.model.almacen.Contadores;
import com.defensoria.integral.model.almacen.DetalleRequisiciones;
import com.defensoria.integral.model.almacen.EstatusRequisiciones;
import com.defensoria.integral.model.almacen.MovRequisiciones;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.model.almacen.TiposContadores;
import com.defensoria.integral.repository.almacen.DetalleEntradaRepository;
import com.defensoria.integral.repository.almacen.DetalleRequisicionesRepository;
import com.defensoria.integral.repository.almacen.EstatusRequisicionesRepository;
import com.defensoria.integral.repository.almacen.MovRequisicionesRepository;
import com.defensoria.integral.repository.almacen.RequisicionesRepository;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@Transactional
public class RequisicionesRestController {

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
    @Autowired
    DetalleEntradaRepository detalleEntradaRepository;

    @PostMapping("/guardarrequisicion")
    public ResponseEntity<?> GuardarRequisicion(@RequestBody Requisiciones requisicion) {

        if (requisicion.getIdRequisicion() == null) {
            requisicion.setActivo("S");
            Adscripciones adscripcion = new Adscripciones();
            Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
            requisicion.setCveUsuario(cveUsuario);
            Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
            adscripcion.setCveAdscripcion(cveAdscripcion);
            requisicion.setAdscripcion(adscripcion);
            requisicion.setTipoRequisicion("R");
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

    @PostMapping("/enviarrequisicion")
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

            Contadores contador = new Contadores();
            Adscripciones adscripcion = new Adscripciones();
            TiposContadores tipoContador = new TiposContadores();

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = new Date();
            System.out.println(dateFormat.format(date));
            String[] fecha = dateFormat.format(date).toString().split("-");

            adscripcion.setCveAdscripcion(80);
            tipoContador.setCveTipoContador(1);
            contador.setAdscripcion(adscripcion);
            contador.setTipoContador(tipoContador);
            contador.setAnio(Integer.parseInt(fecha[0]));
            contador = contadorController.GenerarContador(contador);
            requisicion.setTipoRequisicion("R");
            requisicion.setActivo("S");
            requisicion.setCveUsuario(Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString()));
            Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
            adscripcion.setCveAdscripcion(cveAdscripcion);
            requisicion.setAdscripcion(adscripcion);
            requisicion.setAnioRequisicion(contador.getAnio());
            requisicion.setNumeroRequisicion(contador.getNumero());
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

    @PostMapping("/regresarrequisicion")
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

    }

    ;

    @PostMapping("/autorizarreq")
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

    @PostMapping("/noautorizarreq")
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

    @PostMapping("/eliminarrequisicion")
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

            List<Requisiciones> TempRequisicion = requisicionesRepository.findAll(Example.of(requisicion));
            if (TempRequisicion.size() > 0) {
                newRequisicion=TempRequisicion.get(0);
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
                    // PASAR A NULL LOS VALES
                    Requisiciones rqVales = new Requisiciones();

                    rqVales.setIdRequisicionPadre(requisicion.getIdRequisicion());
                    List<Requisiciones> lista = new ArrayList<>();
                    lista = requisicionesRepository.findAll(Example.of(rqVales));

                    if (lista.size() > 0) {
                        for (Requisiciones vale : lista) {
                            vale.setIdRequisicionPadre(null);
                            requisicionesRepository.save(vale);
                        }
                    }

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

    @PostMapping("/buscarrequisiones")
    public ResponseEntity<?> BuscarRequisiciones(@RequestBody Requisiciones requisicion) {
        if (getHttpSession().getAttribute("cveAdscripcion") != null) {
            Adscripciones adscripcion = new Adscripciones();
            Integer cveAdscripcion = Integer.parseInt(getHttpSession().getAttribute("cveAdscripcion").toString());
            adscripcion.setCveAdscripcion(cveAdscripcion);
            requisicion.setAdscripcion(adscripcion);
            requisicion.setActivo("S");
            // List<Requisiciones> resultado =
            // requisicionesRepository.findAll(Example.of(requisicion));
            List<Requisiciones> resultado = requisicionesRepository.findByAdscripcion(cveAdscripcion, "S",
                     Sort.by(Sort.Direction.DESC, "idRequisicion"), "R");

            return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);
        } else {
            Map<String, Object> respuesta = new HashMap<String, Object>();
            respuesta.put("mensaje", "Se termino la sesion");
            respuesta.put("errorType", 1);
            return new ResponseEntity<Map<String, Object>>(respuesta, HttpStatus.OK);
        }

    }

    @PostMapping("/buscarrequisionesaut")
    public ResponseEntity<?> BuscarRequisicionesAut(@RequestBody Requisiciones requisicion) {

        List<Requisiciones> resultado = requisicionesRepository.findByAutorizar(requisicion.getIdRequisicion());

        return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);

    }

    @PostMapping("/buscarrequisionesEntrega")
    public ResponseEntity<?> BuscarRequisicionesEntrega(@RequestBody Requisiciones requisicion) {

        List<Requisiciones> resultado = requisicionesRepository.findAll(Example.of(requisicion));

        return new ResponseEntity<List<Requisiciones>>(resultado, HttpStatus.OK);

    }

    @PostMapping("/listaestatusreq")
    public ResponseEntity<?> ListaEstatusReq(@RequestBody EstatusRequisiciones estatus) {

        List<EstatusRequisiciones> ListaEstatus = estatusRequisiconesRepository.findAll(Example.of(estatus));

        return new ResponseEntity<List<EstatusRequisiciones>>(ListaEstatus, HttpStatus.OK);

    }

    @PostMapping("/entregarrequisicion")
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
    @RequestMapping("/reporteexistenciasexcel2")
    // @PostMapping (value="/reportecontraloria")
    public ResponseEntity<?> ReporteContraloria() throws JRException {
        Personal per1 = new Personal();
        Personal per2 = new Personal();
        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
        // JasperReportsPdfView view = new JasperReportsPdfView();
        // JasperReportsHtmlView view = new JasperReportsHtmlView();
        // JasperReportsMultiFormatView view = new JasperReportsMultiFormatView();

        // JasperReportsXlsView view = new JasperReportsXlsView();
        String classpath = "classpath:/reports/reportecontraloria.jrxml";
        //
       
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();

        List<Map<String, Object>> listaParams = new ArrayList<>();

        List<ArticulosAutorizados> listaRequisiciones = detalleRequisicionesRepository.ListaArticulosAutorizados();
        List<ArticulosAutorizados> listaRequisiciones2 = detalleEntradaRepository.ListaArticulosAutorizadosEx();
        List<ArticulosAutorizados> resultado = new ArrayList<>();

        for (ArticulosAutorizados registro : listaRequisiciones2) {
            ArticulosAutorizados aa = new ArticulosAutorizados();
            Long total = (long) 0;
            boolean encontrado = false;
            for (ArticulosAutorizados registro2 : listaRequisiciones) {
                // if(registro.getCveArticulo()==187){
                // System.err.println (registro2.getDescArticulo());
                //
                // }
                if (registro.getCveArticulo().equals(registro2.getCveArticulo())) {
                    if (registro.getCveArticulo().equals(187)) {
                        System.out.println("encontrado" + registro2.getDescArticulo());

                    }

                    total = registro.getTotal() - registro2.getTotal();
                    encontrado = true;
                    break;
                }
            }
            if (!encontrado) {
                total = registro.getTotal();
            }
            aa.setTotal(total);
            aa.setClavePartida(registro.getClavePartida());
            aa.setCveArticulo(registro.getCveArticulo());
            aa.setCvePartida(registro.getCvePartida());
            aa.setDescArticulo(registro.getDescArticulo());
            aa.setDescPartida(registro.getDescPartida());
            aa.setDescUnidad(registro.getDescUnidad());
            resultado.add(aa);
        }

        // per1 = BuscarTitulares(83);
        // per2 = BuscarTitulares(80);
        // per3 = BuscarEncargados(5);
        // ads = BuscarAdscripcion(80);
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(resultado);
        params2.put("lista", itemsJRBean);

        params2.put("titularFinanzas", per1.getNombre() + " " + per1.getPaterno() + " " + per1.getMaterno());
        params2.put("titularMateriales", per2.getNombre() + " " + per2.getPaterno() + " " + per2.getMaterno());
        params2.put("encargadoAlm", per3.getNombre() + " " + per3.getPaterno() + " " + per3.getMaterno());
        params2.put("areaRecursos", ads.getDescAdscripcion());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String[] fecha = dateFormat.format(date).toString().split("-");
        String[] dia = fecha[2].toString().split(" ");
        params2.put("dia", dia[0]);
        params2.put("mes", fecha[1]);
        params2.put("anio", fecha[0]);

        listaParams.add(params2);

        params.put("datasource", listaParams);
        params.put("logodes", "classpath:/static/images/logodecisiones.png");
        // params.put("logoidp", "classpath:/static/images/logoidp.png");
        params.put("logoidp", "classpath:/static/images/logoido2.png");
        params.put("logoedo", "classpath:/static/images/logoedomex.png");
        params.put("footidp", "classpath:/static/images/footidp.png");
        // Map<String, Object> params = new HashMap<>();
        // InputStream jasperStream =
        // this.getClass().getResourceAsStream("reportecontraloria.jasper");
        // JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //
        // JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        // ByteArrayOutputStream os = new ByteArrayOutputStream();
        // xlsxExporter.exportReport();
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
