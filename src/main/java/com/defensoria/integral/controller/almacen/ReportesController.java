package com.defensoria.integral.controller.almacen;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;

import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.almacen.ArticulosAutorizados;
import com.defensoria.integral.model.almacen.DetalleDistribucion;
import com.defensoria.integral.model.almacen.DetalleRequisiciones;
import com.defensoria.integral.model.almacen.Distribucion;
import com.defensoria.integral.model.almacen.Requisiciones;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.almacen.DetalleDistribucionRepository;
import com.defensoria.integral.repository.almacen.DetalleEntradaRepository;
import com.defensoria.integral.repository.almacen.DetalleRequisicionesRepository;
import com.defensoria.integral.repository.almacen.RequisicionesRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsxReportConfiguration;

import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

@Controller
@RequestMapping("reportes")
public class ReportesController {

    @Autowired
    private HttpSession httpSession;
    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    DetalleRequisicionesRepository detalleRequisicionesRepository;
    @Autowired
    RequisicionesRepository requisicionesRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    AdscripcionesRepository adscripcionesRepository;
    @Autowired
    DetalleEntradaRepository detalleEntradaRepository;
    @Autowired
    DetalleDistribucionRepository detalleDistribucionRepository;
    @Autowired
    ResourceLoader resourceLoader;

    @RequestMapping(value = "/reporterequisicion", method = RequestMethod.POST)
    public ResponseEntity<byte[]> ReporteRequisicion(Requisiciones requisicion)
            throws FileNotFoundException, JRException {

        String classpath = "requisiciones.jrxml";

        // JasperReportsHtmlView view = new JasperReportsHtmlView();
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> listaArticulos = new ArrayList<>();
        List<Map<String, Object>> listaArticulos2 = new ArrayList<>();
        DetalleRequisiciones detalle = new DetalleRequisiciones();
        detalle.setRequisicion(requisicion);
        detalle.setActivo("S");
        String titulo = "";
        // BUSCANDO AL PADRE REQUISICION
        Requisiciones rq = new Requisiciones();
        rq.setIdRequisicion(requisicion.getIdRequisicion());

        List<Requisiciones> padre = requisicionesRepository.findAll(Example.of(rq));
        if (padre.size() > 0) {
            if (padre.get(0).getTipoRequisicion().equals("R")) {
                titulo = "REQUISICIÓN DE ALMACÉN";
            } else if (padre.get(0).getTipoRequisicion().equals("V")) {
                titulo = "VALE DE ALMACÉN";
            }

        }

        // BUSCANDO A LOS HIJOS DE LA REQUISICION
        rq = new Requisiciones();
        rq.setIdRequisicionPadre(requisicion.getIdRequisicion());
        List<Integer> listaRequisicion = new ArrayList<>();
        List<Requisiciones> hijos = requisicionesRepository.findAll(Example.of(rq));
        listaRequisicion.add(requisicion.getIdRequisicion());
        if (hijos.size() > 0) {
            for (Requisiciones rs : hijos) {
                listaRequisicion.add(rs.getIdRequisicion());

            }

        }
        System.out.println("lista productos: " + listaRequisicion);
        List<DetalleRequisiciones> listaRequisiciones = detalleRequisicionesRepository
                .findByClaveRequisiciones(listaRequisicion);
        if (listaRequisiciones == null)
            System.out.println("AQUI");
        if (listaRequisiciones.size() > 0) {
            System.out.println("preparando la lista");
            for (DetalleRequisiciones detalle2 : listaRequisiciones) {
                Map<String, Object> articulo = new HashMap<>();
                articulo.put("cantidadRequerida", detalle2.getCantidadRequerida());
                articulo.put("cantidadAutorizada", detalle2.getCantidadAutorizada());
                articulo.put("descArticulo", detalle2.getArticulo().getDescArticulo() + " ");
                articulo.put("unidad", detalle2.getArticulo().getUnidades().getDescUnidad());
                articulo.put("observaciones", detalle2.getObservaciones());
                listaArticulos.add(articulo);
            }

            Personal per = new Personal();
            Personal per2 = new Personal();
            Personal per3 = new Personal();
            Adscripciones ads = new Adscripciones();
            // per =BuscarTitulares(
            // listaRequisiciones.get(0).getRequisicion().getAdscripcion().getCveAdscripcion());
            per = null;
            if (per == null) {
                per = new Personal();
                per.setCveUsuario(listaRequisiciones.get(0).getRequisicion().getCveUsuario());
                List<Personal> listPersonal = personalRepository.findAll(Example.of(per));
                if (listPersonal.size() > 0) {
                    per = listPersonal.get(0);
                }
            }
            per2 = BuscarTitulares(80);
            per3 = BuscarEncargados(5);
            ads = BuscarAdscripcion(80);
            JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaArticulos);
            params2.put("lista", itemsJRBean);
            params2.put("titulo", titulo);
            params2.put("titularSolicita", per.getNombre() + " " + per.getPaterno() + " " + per.getMaterno());
            params2.put("titularMateriales", per2.getNombre() + " " + per2.getPaterno() + " " + per2.getMaterno());
            params2.put("encargadoAlm", per3.getNombre() + " " + per3.getPaterno() + " " + per3.getMaterno());
            params2.put("areaRecursos", ads.getDescAdscripcion());
            params2.put("areaSolicita",
                    listaRequisiciones.get(0).getRequisicion().getAdscripcion().getDescAdscripcion());
            params2.put("areaResponsable", per3.getPersonalPerfiles().get(0).getPerfil().getDescPerfil());
            String[] fecha = listaRequisiciones.get(0).getFechaRegistro().toString().split("-");
            String[] dia = fecha[2].toString().split(" ");
            params2.put("dia", dia[0]);
            params2.put("mes", fecha[1]);
            params2.put("anio", fecha[0]);
            params2.put("numeroReq",
                    listaRequisiciones.get(0).getRequisicion().getNumeroRequisicion() != null
                            ? listaRequisiciones.get(0).getRequisicion().getNumeroRequisicion() + "/"
                                    + listaRequisiciones.get(0).getRequisicion().getAnioRequisicion()
                            : "");
            params2.put("estatus", listaRequisiciones.get(0).getRequisicion().getMovRequisicion().get(0)
                    .getEstatusRequisicion().getDescEstatusRequisicion());
            listaArticulos2.add(params2);

            params.put("datasource", listaArticulos2);
            params.put("logodes", "classpath:/static/images/logodecisiones.png");
            // params.put("logoidp", "classpath:/static/images/logoidp.png");
            params.put("logoidp", "classpath:/static/images/logoido2.png");
            params.put("logoedo", "classpath:/static/images/logoedomex.png");
            params.put("footidp", "classpath:/static/images/piedepagina.jpeg");
            params.put("adscripcion", listaRequisiciones.get(0).getRequisicion().getAdscripcion().getDescAdscripcion());
            params.put("numeroReq", listaRequisiciones.get(0).getRequisicion().getNumeroRequisicion() + "/"
                    + listaRequisiciones.get(0).getRequisicion().getAnioRequisicion());
        } else {
            System.out.println("no hay detalles");
        }
        String documento = "Requisicion";

        return this.generarPdf(classpath, params, documento, listaArticulos2);

    }

    public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento,
            List<?> listaParams) throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("/opt/integral/reports/" + classpath);
        Map<String, Object> map = new HashMap<>();
        System.out.println(file.getAbsolutePath().toString());

        // map.put("creador", "Alejandro Gómez");

        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(listaParams);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, data);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        // headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        System.out.println("file: " + file.getAbsolutePath().toString());
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jp), headers,
                HttpStatus.OK);

        return response;
    }

    public void generarExcel(String classpath, Map<String, Object> params, String documento, List<?> listaParams,
            HttpServletResponse response) throws JRException, IOException {

        File file = ResourceUtils.getFile("/opt/integral/reports/" + classpath);
        Map<String, Object> map = new HashMap<>();
        System.out.println(file.getAbsolutePath().toString());

        // map.put("creador", "Alejandro Gómez");

        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(listaParams);
        JasperPrint jp = JasperFillManager.fillReport(jr, params, data);

        // headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        System.out.println("file: " + file.getAbsolutePath().toString());

        JRXlsxExporter exporter = new JRXlsxExporter();
        SimpleXlsxReportConfiguration reportConfigXLS = new SimpleXlsxReportConfiguration();
        reportConfigXLS.setSheetNames(new String[] { "sheet1" });
        exporter.setConfiguration(reportConfigXLS);
        exporter.setExporterInput(new SimpleExporterInput(jp));
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-disposition",
                "attachment; filename=reporte.xlsx");

        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(response.getOutputStream()));

        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.parseMediaType("application/octet-stream"));
        headers.add("Content-Disposition", "attachment;filename=jasperReport.xlsx");
        exporter.exportReport();
    }

    @RequestMapping("/reporteexistencias")
    // @PostMapping (value="/reportecontraloria")
    public ResponseEntity<byte[]> ReporteContraloria() throws JRException, FileNotFoundException {
        Personal per1 = new Personal();
        Personal per2 = new Personal();
        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
        String classpath = "reportecontraloria.jrxml";

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

        per1 = BuscarTitulares(83);
        per2 = BuscarTitulares(80);
        per3 = BuscarEncargados(5);
        ads = BuscarAdscripcion(80);
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(resultado);
        params2.put("lista", itemsJRBean);

        params2.put("titularFinanzas", per1.getNombre() + " " + per1.getPaterno() + " " + per1.getMaterno());
        params2.put("titularMateriales",
                per2.getTitulo() + " " + per2.getNombre() + " " + per2.getPaterno() + " " + per2.getMaterno());
        System.out.println("titular Recursos Materiales " + per2.getTitulo() + " " + per2.getNombre() + " "
                + per2.getPaterno() + " " + per2.getMaterno());
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
        params.put("footidp", "classpath:/static/images/piedepagina.jpeg");

        String documento = "reportecontraloria";

        return this.generarPdf(classpath, params, documento, listaParams);

    }

    @RequestMapping("/reporteexistencias2")
    // @PostMapping (value="/reportecontraloria")
    public ResponseEntity<byte[]> ReporteContraloria2() throws JRException, FileNotFoundException {
        Personal per1 = new Personal();
        Personal per2 = new Personal();
        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
        // JasperReportsPdfView view = new JasperReportsPdfView();
        // JasperReportsHtmlView view = new JasperReportsHtmlView();
        // JasperReportsMultiFormatView view = new JasperReportsMultiFormatView();

        String classpath = "reportecontraloria.jrxml";

        // System.out.println(view.getUrl().toString());

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();

        List<Map<String, Object>> listaParams = new ArrayList<>();

        List<ArticulosAutorizados> listaRequisiciones = detalleRequisicionesRepository.ListaArticulosAutorizados();
        List<ArticulosAutorizados> listaRequisiciones2 = detalleEntradaRepository.ListaArticulosAutorizadosEx();
        List<ArticulosAutorizados> lista = new ArrayList<>();

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
            lista.add(aa);
        }

        per1 = BuscarTitulares(83);
        per2 = BuscarTitulares(80);
        per3 = BuscarEncargados(5);
        ads = BuscarAdscripcion(80);
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);
        params2.put("lista", itemsJRBean);

        params2.put("titularFinanzas", per1.getNombre() + " " + per1.getPaterno() + " " + per1.getMaterno());
        params2.put("titularMateriales",
                per2.getTitulo() + " " + per2.getNombre() + " " + per2.getPaterno() + " " + per2.getMaterno());
        System.out.println("titular Recursos Materiales " + per2.getTitulo() + " " + per2.getNombre() + " "
                + per2.getPaterno() + " " + per2.getMaterno());
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

        // params.put("datasource", listaParams);
        params.put("logodes", "classpath:/static/images/logodecisiones.png");
        // params.put("logoidp", "classpath:/static/images/logoidp.png");
        params.put("logoidp", "classpath:/static/images/logoido2.png");
        params.put("logoedo", "classpath:/static/images/logoedomex.png");
        params.put("footidp", "classpath:/static/images/piedepagina.jpeg");
        // Map<String, Object> params = new HashMap<>();
        // InputStream jasperStream =
        // this.getClass().getResourceAsStream("reportecontraloria.jasper");
        // JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //
        // JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        // ByteArrayOutputStream os = new ByteArrayOutputStream();
        // xlsxExporter.exportReport();
        String documento = "reportecontraloria";
        return this.generarPdf(classpath, params, documento, listaParams);

    }

    @RequestMapping("/reporteexistencias2excel")
    // @PostMapping (value="/reportecontraloria")
    public void ReporteContraloria2excel(HttpServletResponse response) throws JRException, IOException {
        Personal per1 = new Personal();
        Personal per2 = new Personal();
        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
        // JasperReportsPdfView view = new JasperReportsPdfView();
        // JasperReportsHtmlView view = new JasperReportsHtmlView();
        // JasperReportsMultiFormatView view = new JasperReportsMultiFormatView();

        String classpath = "reportecontraloria.jrxml";

        // System.out.println(view.getUrl().toString());

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();

        List<Map<String, Object>> listaParams = new ArrayList<>();

        List<ArticulosAutorizados> listaRequisiciones = detalleRequisicionesRepository.ListaArticulosAutorizados();
        List<ArticulosAutorizados> listaRequisiciones2 = detalleEntradaRepository.ListaArticulosAutorizadosEx();
        List<ArticulosAutorizados> lista = new ArrayList<>();

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
            lista.add(aa);
        }

        per1 = BuscarTitulares(83);
        per2 = BuscarTitulares(80);
        per3 = BuscarEncargados(5);
        ads = BuscarAdscripcion(80);
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(lista);
        params2.put("lista", itemsJRBean);

        params2.put("titularFinanzas", per1.getNombre() + " " + per1.getPaterno() + " " + per1.getMaterno());
        params2.put("titularMateriales",
                per2.getTitulo() + " " + per2.getNombre() + " " + per2.getPaterno() + " " + per2.getMaterno());
        System.out.println("titular Recursos Materiales " + per2.getTitulo() + " " + per2.getNombre() + " "
                + per2.getPaterno() + " " + per2.getMaterno());
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

        // params.put("datasource", listaParams);
        params.put("logodes", "classpath:/static/images/logodecisiones.png");
        // params.put("logoidp", "classpath:/static/images/logoidp.png");
        params.put("logoidp", "classpath:/static/images/logoido2.png");
        params.put("logoedo", "classpath:/static/images/logoedomex.png");
        params.put("footidp", "classpath:/static/images/piedepagina.jpeg");
        // Map<String, Object> params = new HashMap<>();
        // InputStream jasperStream =
        // this.getClass().getResourceAsStream("reportecontraloria.jasper");
        // JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
        //
        // JRXlsxExporter xlsxExporter = new JRXlsxExporter();
        // ByteArrayOutputStream os = new ByteArrayOutputStream();
        // xlsxExporter.exportReport();
        String documento = "reportecontraloria";
        this.generarExcel(classpath, params, documento, listaParams, response);

    }

    // @PostMapping (value="/reportecontraloria")
    @RequestMapping(value = "/reportedistribucion", method = RequestMethod.POST)
    public ResponseEntity<byte[]> ReporteDistribucion(Integer idDetalleRequisicion)
            throws FileNotFoundException, JRException {

        System.out.println("valor recibido" + idDetalleRequisicion);

        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
        DetalleDistribucion dtd = new DetalleDistribucion();
        Distribucion dis = new Distribucion();
        dis.setIdDistribucion(idDetalleRequisicion);
        dtd.setDistribucion(dis);
        dtd.setActivo("S");

        String classpath = "distribucion.jrxml";
        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();

        List<Map<String, Object>> listaParams = new ArrayList<>();

        List<DetalleDistribucion> listadistribucion = detalleDistribucionRepository.findAll(Example.of(dtd));

        System.out.println("Lista de distribucion =>" + listadistribucion.size());

        // per2 = BuscarTitulares(80);
        per3 = listadistribucion.get(0).getDistribucion().getPersonal();
        ads = listadistribucion.get(0).getDistribucion().getAdscripcion();
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listadistribucion);

        params2.put("lista", itemsJRBean);
        System.out.println(
                listadistribucion.get(0).getPersonal().getMovAdscripciones().get(0).getPuesto().getDescPuesto());
        params2.put("titularSolicita", per3.getNombre() + " " + per3.getPaterno() + " " + per3.getMaterno());
        params2.put("observaciones", listadistribucion.get(0).getDistribucion().getObservaciones() == null ? ""
                : listadistribucion.get(0).getDistribucion().getObservaciones());
        params2.put("titularCargo", "");
        params2.put("adscripcion", ads.getDescAdscripcion());
        // params2.put("titularMateriales", per2.getNombre() + " " + per2.getPaterno() +
        // " " + per2.getMaterno());
        // params2.put("encargadoAlm", per3.getNombre() + " " + per3.getPaterno() + " "
        // + per3.getMaterno());
        // params2.put("areaRecursos", ads.getDescAdscripcion());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        String[] fecha = listadistribucion.get(0).getDistribucion().getFechaActualizacion().toString().split("-");
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
        params.put("footidp", "classpath:/static/images/piedepagina.jpeg");
        System.out.println(params.toString());
        System.out.println(params2.toString());

        String documento = "distribucion";

        return this.generarPdf(classpath, params, documento, listaParams);

    }

    public Personal BuscarTitulares(Integer cveAdscripcion) {

        List<Personal> lista = personalRepository.TitularAdscripciones(cveAdscripcion);
        if (lista.size() > 0) {
            System.out.println("nombre titular" + lista.get(0).getNombre());
            return lista.get(0);
        } else {
            return null;
        }
    }

    public Personal BuscarEncargados(Integer cvePerfil) {
        List<Personal> lista = personalRepository.ResponsablesAreas(cvePerfil);
        System.out.println("nombre titular" + lista.get(0).getNombre());
        return lista.get(0);
    }

    public Adscripciones BuscarAdscripcion(Integer cveAdscripcion) {
        Adscripciones ads = new Adscripciones();
        ads.setCveAdscripcion(cveAdscripcion);
        List<Adscripciones> lista = adscripcionesRepository.findAll(Example.of(ads));

        return lista.get(0);
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @RequestMapping(value = "/reporteexistenciasexcel", method = RequestMethod.POST)
    public ModelAndView reporteExistenciasExcel() throws JRException {
        Personal per1 = new Personal();
        Personal per2 = new Personal();
        Personal per3 = new Personal();
        Adscripciones ads = new Adscripciones();
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

        per1 = BuscarTitulares(83);
        per2 = BuscarTitulares(80);
        per3 = BuscarEncargados(5);
        ads = BuscarAdscripcion(80);
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

        try {

            String reportPath = resourceLoader.getResource("classpath:reports/reportecontraloria.jrxml").getURI()
                    .getPath();
            System.out.println("reportpath: " + reportPath);

            // Compile the Jasper report from .jrxml to .japser
            JasperReport jasperReport = JasperCompileManager.compileReport(reportPath);

            // Get your data source
            JRBeanCollectionDataSource jrBeanCollectionDataSource = new JRBeanCollectionDataSource(resultado);

            // Add parameters
            Map<String, Object> parameters = new HashMap<>();

            parameters.put("createdBy", "Websparrow.org");
            parameters.put("lista", itemsJRBean);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters,
                    jrBeanCollectionDataSource);

            // Export the report to a PDF file
            JasperExportManager.exportReportToPdfFile(jasperPrint, reportPath + "reporteexistencia.pdf");

            System.out.println("Done");

            // return "Report successfully generated @path= " + reportPath;
        } catch (Exception e) {
            e.printStackTrace();
            // return e.getMessage();
        }

        return new ModelAndView();
    }

}
