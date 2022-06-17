/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.adminpersonal;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.MovAdscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.adminpersonal.PuestoFuncional;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.MovAdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.adminpersonal.PuestoFuncionalRepository;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author alejandro.gomez
 */
@Controller
public class PersonalController {

    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    MovAdscripcionesRepository movimientosRepository;
    @Autowired
    PuestoFuncionalRepository puestoFuncionalRepository;
    @Autowired
    MovAdscripcionesRepository movAdscripcionesRepository;
    @Autowired
    AdscripcionesRepository adscripcionesRepository;

    @GetMapping(value = "/verificar")
    public ModelAndView VerificarPersonal(@RequestParam Integer id) {

        Personal p = new Personal();
        p.setCveUsuario(id);
        p.setActivo("S");
        MovAdscripciones mv = new MovAdscripciones();
        mv.setPersonal(p);
        mv.setActivo("S");
        List<MovAdscripciones> lista = movimientosRepository.findAll(Example.of(mv));

        Map<String, Object> params = new HashMap<>();
        String nombre = lista.get(0).getPersonal().getNombre() + " " + lista.get(0).getPersonal().getPaterno() + " " + lista.get(0).getPersonal().getMaterno();
        Personal pr = lista.get(0).getPersonal();
        params.put("nombreCompleto", nombre);
        params.put("cveServidor", lista.get(0).getPersonal().getCveServidorPub());
        params.put("correo", lista.get(0).getPersonal().getEmail());
        if (lista.get(0).getPersonal().getPersonalCorreos().size() > 0) {
            params.put("correos", lista.get(0).getPersonal().getPersonalCorreos().get(0).getCorreoElectronico());
        }
        String descPuesto = "";
        Integer tipoMov = 0;
        MovAdscripciones mv2= new MovAdscripciones();
        for (MovAdscripciones puesto : lista.get(0).getPersonal().getMovAdscripciones()) {

            if (puesto.getActivo().equals("S")) {
                mv2=puesto;
                tipoMov = puesto.getCveTipoMovimientoPersonal();
                descPuesto = puesto.getPuesto().getDescPuesto();
                if (puesto.getPuestoFuncional() != null) {
                    descPuesto = puesto.getPuesto().getDescPuesto();
                }
            }
        }

        String estatus = "Servidor Público Activo";
        String imagen = "../resources/images/listo.png";
        String color = "#58ba47";
        String cargo = "";
        String puestoFuncional = "";
        if (tipoMov == 4) {
            estatus = "Servidor Público Dado de Baja";
            imagen = "../resources/images/tache.png";
            color = "#e81615";
        }

        cargo = mv2.getPuesto().getDescPuesto();

        if (mv2.getPuestoFuncional() != null) {
            puestoFuncional = mv.getPuestoFuncional().getDescPuestoFuncional();

        } else {
            System.out.println("Cargo" + cargo + " contains " + cargo.contains("JF"));
            if (mv2.getTipoTitular() != null) {
                if (mv2.getTipoTitular().equals("E")) {
                    if (pr.getCveGenero() == 1) {
                        cargo = "ENCARGADO DEL ";
                    } else {
                        cargo = "ENCARGADA DEL ";
                    }
                    cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();
                }
            }
            if (cargo.contains("JF")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "JEFE DEL ";
                } else {
                    cargo = "JEFA DEL ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else if (cargo.contains("GENERAL")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "DIRECTOR DEL ";
                } else {
                    cargo = "DIRECTORA DEL ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else if (cargo.contains("DIRECTOR")) {
                if (pr.getCveGenero() == 1) {
                    cargo = "DIRECTOR DE LA ";
                } else {
                    cargo = "DIRECTORA DE LA ";
                }
                cargo += mv2.getAdscripcion().getDescAdscripcion().toUpperCase();

            } else {
                cargo = mv2.getPuesto().getDescPuesto();
            }

        }

        params.put("nombreCompleto", nombre);
        params.put("cveServidor", lista.get(0).getPersonal().getCveServidorPub());
        params.put("correo", lista.get(0).getPersonal().getEmail());
        params.put("puesto", cargo);
        params.put("estatus", estatus);
        params.put("imagen", imagen);
        params.put("color", color);

        return new ModelAndView("adminpersonal/verificar", params);
    }


    @RequestMapping(value = "/reportePersonal", method = RequestMethod.POST)
    public ResponseEntity<byte[]> ReportesPersonal(Integer cveUsuario, Integer cveAdscripcion, Integer cvePuesto,
            Integer cveRegion, Integer cvePuestoNominal)
            throws JRException, IOException {

        // ""

        // JasperReportsHtmlView view = new JasperReportsHtmlView();

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> listaPersonas = new ArrayList<>();

        List<Map<String, Object>> listaArticulos2 = new ArrayList<>();
        // Personal p = new Personal();
        MovAdscripciones mov = new MovAdscripciones();
        Adscripciones tempAds = new Adscripciones();
        Personal tempPer = new Personal();

        System.out.println("datos recibidos cveUsuario=>" + cveUsuario + " cveAdscripcion=>" + cveAdscripcion
                + " cvePuesto=>" + cvePuesto);
        if (cveAdscripcion > 0) {
            tempAds.setCveAdscripcion(cveAdscripcion);
            mov.setAdscripcion(tempAds);
        }
        if (cveRegion > 0) {
            tempAds.setCveRegion(cveRegion);
            mov.setAdscripcion(tempAds);
        }
        if (cveUsuario > 0) {
            tempPer.setCveUsuario(cveUsuario);
            mov.setPersonal(tempPer);
        }
        if (cvePuesto > 0) {
            mov.setCvePuesto(cvePuesto);
        }
        if (cvePuestoNominal > 0) {
            System.out.println("cvePuestoFuncional " + cvePuestoNominal);
            PuestoFuncional pf = new PuestoFuncional();
            pf.setCvePuestoFuncional(cvePuestoNominal);
           List<PuestoFuncional>list = puestoFuncionalRepository.findAll(Example.of(pf));
            System.out.println("lista de puestos funcionales "+list.size());
           if(list.size()>0)
            mov.setPuestoFuncional(list.get(0));
        }

        mov.setActivo("S");
        List<MovAdscripciones> listaMovimientos1 = movAdscripcionesRepository.findAll(Example.of(mov),
                Sort.by("adscripcion").ascending());
        // List<Personal> listapersonal = personalRepository.findAll(Example.of(p));
        System.out.println("lista de movimientos " + listaMovimientos1.size());
        if (listaMovimientos1.size() > 0) {
            System.out.println("cantidad de personas encontradas " + listaMovimientos1.size());
      

            
            // DETERMINAR ULTIMA ADSCRIPCION ACTIVA DEL PERSONAL ACTIVO
            for (MovAdscripciones mov1 : listaMovimientos1) {
                Personal pr = new Personal();
                Map<String, Object> persona = new HashMap<>();

                if (mov1.getCveTipoMovimientoPersonal() != 4) {

                    pr = mov1.getPersonal();
                    if (pr.getFotografiasPersonal().size() > 0) {
                        String nombre = pr.getNombre() + " " + pr.getPaterno() + " " + pr.getMaterno();
                        System.out
                                .println("nombre =>" + nombre + " tamanio: " + nombre.length());
                        persona.put("nombreCompleto", nombre);
                        if (nombre.length() > 25)
                            persona.put("sizeNombre", "12");
                        else if (nombre.length() > 20)
                            persona.put("sizeNombre", "14");
                        else
                            persona.put("sizeNombre", "16");
                        persona.put("cveServidor", pr.getCveServidorPub());
                        String [] partes = pr.getFotografiasPersonal().get(0).getRutaFotografia().split("/");
                        System.out.println("Ruta fotografia "+partes[partes.length-1]  );
                        //persona.put("rutaFotografia", "Y:/"+pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("rutaFotografia", "W://"+partes[partes.length-1]);
                      
                       
                        String cargo = "";
                        String size = "18";
                        String puestoFuncional = "";
                        if (pr.getMovAdscripciones().size() > 0) {
                            for (MovAdscripciones mv : pr.getMovAdscripciones()) {
                                if (mv.getActivo().equals("S")) {
                                    cargo = mv.getPuesto().getDescPuesto();

                                    if (mv.getPuestoFuncional() != null) {
                                        puestoFuncional = mv.getPuestoFuncional().getDescPuestoFuncional();
                                         if (puestoFuncional.length() > 25) {
                                            size = (18 / 3) + "";
                                        }else if (puestoFuncional.length() > 20) {
                                            size = (18 / 2) + "";
                                        }
                                    } else {
                                        System.out.println("Cargo" + cargo + " contains " + cargo.contains("JF"));
                                        if (mv.getTipoTitular() != null) {
                                            if (mv.getTipoTitular().equals("E")) {
                                                if (pr.getCveGenero() == 1) {
                                                    cargo = "ENCARGADO DEL ";
                                                } else {
                                                    cargo = "ENCARGADA DEL ";
                                                }
                                                cargo += mv.getAdscripcion().getDescAdscripcion().toUpperCase();
                                            }
                                        }
                                        if (cargo.contains("JF")) {
                                            if (pr.getCveGenero() == 1) {
                                                cargo = "JEFE DEL ";
                                            } else {
                                                cargo = "JEFA DEL ";
                                            }
                                            cargo += mv.getAdscripcion().getDescAdscripcion().toUpperCase();

                                        } else if (cargo.contains("GENERAL")) {
                                            if (pr.getCveGenero() == 1) {
                                                cargo = "DIRECTOR DEL ";
                                            } else {
                                                cargo = "DIRECTORA DEL ";
                                            }
                                            cargo += mv.getAdscripcion().getDescAdscripcion().toUpperCase();

                                        } else if (cargo.contains("SUBDIRECTOR")) {
                                            if (pr.getCveGenero() == 1) {
                                                cargo = "SUBDIRECTOR ";
                                            } else {
                                                cargo = "SUBDIRECTORA DE ";
                                            }
                                            String oficina = mv.getAdscripcion().getDescAdscripcion().toUpperCase();
                                            if (oficina.contains("TECNICA"))
                                                cargo = "Subdirectora Tecnica y de Igualdad de Genero".toUpperCase();
                                            else {
                                                cargo += oficina.replace("SUBDIRECCION", "");
                                            }

                                        } else if (cargo.contains("DIRECTOR")) {
                                            if (pr.getCveGenero() == 1) {
                                                cargo = "DIRECTOR ";
                                            } else {
                                                cargo = "DIRECTORA ";
                                            }
                                            cargo += mv.getAdscripcion().getDescAdscripcion().toUpperCase()
                                                    .replace("DIRECCIÓN", "");

                                        } else {
                                            cargo = mv.getPuesto().getDescPuesto();
                                        }

                                        if (cargo.length() < 20) {
                                            size = (18 / 2) + "";
                                        } else if (cargo.length() > 20) {
                                            size = (18 / 3) + "";
                                        }
                                    }
                                }
                            }

                        }
                        if (puestoFuncional.equals("")) {
                            persona.put("cargo", cargo);
                            persona.put("size", size);
                        } else {
                            persona.put("cargo", puestoFuncional);
                            persona.put("size", size);
                        }
                        String folio = "";
                                         
                       
                        listaPersonas.add(persona);
                    }
                }
            }

        }
        Adscripciones ads = new Adscripciones();
        ads.setCveadsPadre(1);
        List<Adscripciones> listaAds = adscripcionesRepository.findAll(Example.of(ads));
        String director = "";
        if (listaAds.size() > 0) {
            MovAdscripciones ma = new MovAdscripciones();
            ads = listaAds.get(0);
            ma.setAdscripcion(ads);
            ma.setActivo("S");
            List<MovAdscripciones> listaMovimientos = movAdscripcionesRepository.findAll(Example.of(ma));
            if (listaMovimientos.size() > 0) {
                for (MovAdscripciones maa : listaMovimientos) {
                    if (maa.getActivo().equals("S") && maa.getTitular().equals("S")) {
                        director = maa.getPersonal().getTitulo() + " " + maa.getPersonal().getNombre() + " "
                                + maa.getPersonal().getPaterno() + " " + maa.getPersonal().getMaterno();
                    }
                }
            }

        }
        System.out.println("lista para reporte=>" + listaPersonas.size());
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaPersonas);
       // params2.put("myList", itemsJRBean);

      //  listaArticulos2.add(params2);

       /*  Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        params.put("hints", hints);*/
        params.put("personal", itemsJRBean);

 //       params.put("datasource", itemsJRBean);
        params.put("director", director);
        params.put("gafete", "classpath:static/images/gafete.png");

        params.put("logoidp", "classpath:static/images/logoido2.png");
        String documento = "reportePersonal";
        String classpath = "reportePersonal.jrxml";

      
            return this.generarPdf(classpath, params, documento, listaPersonas);
      

    }

    public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento,
            List<?> params2) throws JRException, IOException {
        System.out.println(classpath);
        File file = ResourceUtils.getFile("/opt/integral/reports/"+classpath);
      
      
        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(params2);
        JasperPrint jp = JasperFillManager.fillReport(jr, params,data);

        JasperExportManager.exportReportToPdf(jp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jp), headers,
                HttpStatus.OK);

        return response;

    }

}
