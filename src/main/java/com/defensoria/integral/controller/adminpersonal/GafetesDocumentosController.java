/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.adminpersonal;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.EstatusGafetes;
import com.defensoria.integral.model.adminpersonal.Gafetes;
import com.defensoria.integral.model.adminpersonal.MovAdscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.adminpersonal.PersonalCorreos;
import com.defensoria.integral.model.adminpersonal.PuestoFuncional;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.GafetesRepository;
import com.defensoria.integral.repository.adminpersonal.MovAdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.adminpersonal.PuestoFuncionalRepository;
import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author alejandro.gomez
 */
@Controller
public class GafetesDocumentosController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    AdscripcionesRepository adscripcionesRepository;

    @Autowired
    MovAdscripcionesRepository movAdscripcionesRepository;
    @Autowired
    GafetesRepository gafetesRepository;
    @Autowired
    PuestoFuncionalRepository puestoFuncionalRepository;

    @RequestMapping(value = "/gafetes", method = RequestMethod.POST)
    public ResponseEntity<byte[]> Gafetes(Integer cveUsuario, Integer cveAdscripcion, Integer cvePuesto,
            Integer cveRegion, Integer cvePuestoNominal)
            throws JRException, FileNotFoundException {

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
            Gafetes g = new Gafetes();
            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);

            if (cveAdscripcion > 0) {
                tempAds.setCveAdscripcion(cveAdscripcion);
                g.setAdscripcion(tempAds);
            }
            if (cveUsuario > 0) {
                tempPer.setCveUsuario(cveUsuario);
                g.setPersonal(tempPer);
            }

            List<Gafetes> listaGafetes = gafetesRepository.findAll(Example.of(g));

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
                        System.out.println("Ruta fotografia " + pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("rutaFotografia", pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("vigencia", year + "");
                        String correos = "";
                        correos = pr.getEmail() + "<br>\n";
                        if (pr.getPersonalCorreos().size() > 0) {
                            for (PersonalCorreos pc : pr.getPersonalCorreos()) {
                                correos += pc.getCorreoElectronico();
                            }

                        }
                        persona.put("correos", correos);
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
                        Gafetes gaf = new Gafetes();
                        Boolean encontrado = false;
                        if (listaGafetes.size() > 0) {
                            for (Gafetes ga : listaGafetes) {
                                if (pr.getCveUsuario() == ga.getPersonal().getCveUsuario()) {
                                    encontrado = true;
                                    folio = ga.getIdGafete() + "";
                                }

                            }
                        }
                        if (!encontrado) {
                            gaf.setPersonal(pr);
                            gaf.setAdscripcion(mov1.getAdscripcion());
                            EstatusGafetes estatusGafete = new EstatusGafetes();
                            estatusGafete.setCveEstatusGafete(1);
                            gaf.setEstatusGafete(estatusGafete);
                            gaf.setActivo("S");
                            gaf = gafetesRepository.save(gaf);
                            folio = gaf.getIdGafete() + "";
                        }

                        persona.put("folio", "IDP-" + folio);

                        persona.put("id",
                                "http://intranet.idpedomex.gob.mx/verificar?id=" + pr.getCveUsuario().toString());
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
        params2.put("myList", itemsJRBean);

        listaArticulos2.add(params2);

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        params.put("hints", hints);

        params.put("datasource", itemsJRBean);
        params.put("director", director);
        params.put("gafete", "classpath:static/images/gafete.png");

        params.put("logoidp", "classpath:static/images/logoido2.png");
        String documento = "gafete";
        String classpath = "gafete.jrxml";

        return this.generarPdf(classpath, params, documento, listaPersonas);

    }

    @RequestMapping(value = "/gafetespvc", method = RequestMethod.POST)
    public ResponseEntity<byte[]> GafetesPVC(Integer cveUsuario, Integer cveAdscripcion, Integer cvePuesto)
            throws JRException, FileNotFoundException {

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
        if (cveUsuario > 0) {
            tempPer.setCveUsuario(cveUsuario);
            mov.setPersonal(tempPer);
        }
        if (cvePuesto > 0) {
            mov.setCvePuesto(cvePuesto);
        }

        mov.setActivo("S");
        List<MovAdscripciones> listaMovimientos1 = movAdscripcionesRepository.findAll(Example.of(mov));
        // List<Personal> listapersonal = personalRepository.findAll(Example.of(p));

        if (listaMovimientos1.size() > 0) {
            System.out.println("cantidad de personas encontradas " + listaMovimientos1.size());
            Gafetes g = new Gafetes();

            Date date = new Date();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);

            if (cveAdscripcion > 0) {
                tempAds.setCveAdscripcion(cveAdscripcion);
                g.setAdscripcion(tempAds);
            }
            if (cveUsuario > 0) {
                tempPer.setCveUsuario(cveUsuario);
                g.setPersonal(tempPer);
            }

            List<Gafetes> listaGafetes = gafetesRepository.findAll(Example.of(g));

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
                            persona.put("sizeNombre", "8");
                        else if (nombre.length() > 20)
                            persona.put("sizeNombre", "9");
                        else
                            persona.put("sizeNombre", "10");
                        persona.put("cveServidor", pr.getCveServidorPub());
                        System.out.println("Ruta fotografia " + pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("rutaFotografia", pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("vigencia", year + "");
                        persona.put("firmas", pr.getFirmasPersonal().get(0).getRutaFirma());
                        String correos = "";
                        correos = pr.getEmail() + "<br>\n";
                        if (pr.getPersonalCorreos().size() > 0) {
                            for (PersonalCorreos pc : pr.getPersonalCorreos()) {
                                correos += pc.getCorreoElectronico();
                            }

                        }
                        persona.put("correos", correos);
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
                                            size = "6";
                                        } else if (puestoFuncional.length() > 20) {
                                            size = "8";
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
                        Gafetes gaf = new Gafetes();
                        Boolean encontrado = false;
                        if (listaGafetes.size() > 0) {
                            for (Gafetes ga : listaGafetes) {
                                if (pr.getCveUsuario() == ga.getPersonal().getCveUsuario()) {
                                    encontrado = true;
                                    folio = ga.getIdGafete() + "";

                                }

                            }
                        }
                        if (!encontrado) {
                            gaf.setPersonal(pr);
                            gaf.setAdscripcion(mov1.getAdscripcion());
                            EstatusGafetes estatusGafete = new EstatusGafetes();
                            estatusGafete.setCveEstatusGafete(1);
                            gaf.setEstatusGafete(estatusGafete);
                            gaf.setActivo("S");
                            gaf = gafetesRepository.save(gaf);
                            folio = gaf.getIdGafete() + "";
                        }

                        persona.put("folio", "IDP-" + folio);

                        persona.put("id",
                                "http://intranet.idpedomex.gob.mx/verificar?id=" + pr.getCveUsuario().toString());
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
        params2.put("myList", itemsJRBean);

        listaArticulos2.add(params2);

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        params.put("hints", hints);

        params.put("datasource", itemsJRBean);
        params.put("director", director);
        params.put("gafete", "classpath:/static/images/frente.png");
        params.put("firmad", "classpath:/static/images/firma2.png");
        params.put("logoidp", "classpath:/static/images/logoido2.png");
        params.put("reverso", "classpath:/static/images/reverso.png");

        String documento = "gafetepvc";
        String classpath = "gafetepvc.jrxml";

        return this.generarPdf(classpath, params, documento, listaPersonas);

    }

    public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento,
            List<?> params2) throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("/opt/integral/reports/" + classpath);
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(params2);
        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jp = JasperFillManager.fillReport(jr, params, data);

        JasperExportManager.exportReportToPdf(jp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jp), headers,
                HttpStatus.OK);

        return response;

    }

    public void Respuesta() {
        String fileName = "contract" + ".pdf";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

    }

    @RequestMapping(value = "/gafetespvc2", method = RequestMethod.POST)
    public ResponseEntity<byte[]> GafetesPVC2(Integer cveUsuario, Integer cveAdscripcion)
            throws FileNotFoundException, JRException {

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> listaPersonas = new ArrayList<>();

        List<Map<String, Object>> listaArticulos2 = new ArrayList<>();
        // Personal p = new Personal();
        MovAdscripciones mov = new MovAdscripciones();
        Adscripciones tempAds = new Adscripciones();
        Personal tempPer = new Personal();

        System.out.println("datos recibidos cveUsuario=>" + cveUsuario + " cveAdscripcion=>" + cveAdscripcion);
        if (cveAdscripcion > 0) {
            tempAds.setCveAdscripcion(cveAdscripcion);
            mov.setAdscripcion(tempAds);
        }
        if (cveUsuario > 0) {
            tempPer.setCveUsuario(cveUsuario);
            mov.setPersonal(tempPer);
        }

        mov.setActivo("S");
        List<MovAdscripciones> listaMovimientos1 = movAdscripcionesRepository.findAll(Example.of(mov));
        // List<Personal> listapersonal = personalRepository.findAll(Example.of(p));

        if (listaMovimientos1.size() > 0) {
            System.out.println("cantidad de personas encontradas " + listaMovimientos1.size());
            Gafetes g = new Gafetes();

            if (cveAdscripcion > 0) {
                tempAds.setCveAdscripcion(cveAdscripcion);
                g.setAdscripcion(tempAds);
            }
            if (cveUsuario > 0) {
                tempPer.setCveUsuario(cveUsuario);
                g.setPersonal(tempPer);
            }

            List<Gafetes> listaGafetes = gafetesRepository.findAll(Example.of(g));

            for (MovAdscripciones mov1 : listaMovimientos1) {
                Personal pr = new Personal();
                Map<String, Object> persona = new HashMap<>();

                if (mov1.getCveTipoMovimientoPersonal() != 4) {

                    pr = mov1.getPersonal();
                    if (pr.getFotografiasPersonal().size() > 0) {
                        System.out
                                .println("nombre =>" + pr.getNombre() + " " + pr.getPaterno() + " " + pr.getMaterno());
                        persona.put("nombreCompleto", pr.getNombre() + " " + pr.getPaterno() + " " + pr.getMaterno());
                        persona.put("cveServidor", pr.getCveServidorPub());
                        System.out.println("Ruta fotografia " + pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("rutaFotografia", pr.getFotografiasPersonal().get(0).getRutaFotografia());
                        persona.put("firmas", pr.getFirmasPersonal().get(0).getRutaFirma());
                        persona.put("vigencia", "2020");
                        String correos = "";
                        correos = pr.getEmail() + "<br>\n";
                        if (pr.getPersonalCorreos().size() > 0) {
                            for (PersonalCorreos pc : pr.getPersonalCorreos()) {
                                correos += pc.getCorreoElectronico();
                            }

                        }
                        persona.put("correos", correos);
                        String cargo = "";
                        if (pr.getMovAdscripciones().size() > 0) {
                            for (MovAdscripciones mv : pr.getMovAdscripciones()) {
                                if (mv.getActivo().equals("S")) {
                                    cargo = mv.getPuesto().getDescPuesto();
                                }
                            }

                        }
                        persona.put("cargo", cargo);
                        String folio = "";
                        Gafetes gaf = new Gafetes();
                        Boolean encontrado = false;
                        if (listaGafetes.size() > 0) {
                            for (Gafetes ga : listaGafetes) {
                                if (pr.getCveUsuario() == ga.getPersonal().getCveUsuario()) {
                                    encontrado = true;
                                    folio = ga.getIdGafete() + "";
                                }

                            }
                        }
                        if (!encontrado) {
                            gaf.setPersonal(pr);
                            gaf.setAdscripcion(mov1.getAdscripcion());
                            EstatusGafetes estatusGafete = new EstatusGafetes();
                            estatusGafete.setCveEstatusGafete(1);
                            gaf.setEstatusGafete(estatusGafete);
                            gaf.setActivo("S");
                            gaf = gafetesRepository.save(gaf);
                            folio = gaf.getIdGafete() + "";
                        }

                        persona.put("folio", "IDP-" + folio);

                        persona.put("id",
                                "http://intranet.idpedomex.gob.mx/verificar?id=" + pr.getCveUsuario().toString());
                        listaPersonas.add(persona);
                    }
                }
            }

        }
        Adscripciones ads = new Adscripciones();
        ads.setCveadsPadre(1);
        adscripcionesRepository.findOne(Example.of(ads));
        String director = "";
        if (ads != null) {
            MovAdscripciones ma = new MovAdscripciones();
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
        params2.put("myList", itemsJRBean);

        listaArticulos2.add(params2);

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        params.put("hints", hints);

        params.put("datasource", itemsJRBean);
        params.put("director", director);
        params.put("gafete", "classpath:/static/images/frente.png");
        params.put("firmad", "classpath:/static/images/firma2.png");
        params.put("logoidp", "classpath:/static/images/logoido2.png");
        params.put("reverso", "classpath:/static/images/reverso.png");

        String documento = "gafetepvc";
        String classpath = "gafetepvc.jrxml";

        return this.generarPdf(classpath, params, documento, listaPersonas);

    }
}
