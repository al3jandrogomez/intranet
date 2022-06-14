/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.constancias;

import com.defensoria.integral.controller.sigedepu.Response;
import com.defensoria.integral.model.constancias.Evento;
import com.defensoria.integral.model.constancias.EventoParticipante;
import com.defensoria.integral.model.constancias.Participantes;
import com.defensoria.integral.repository.constancias.EventoParticipanteRepository;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpSession;

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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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
public class ConstanciasController {

        @Autowired
        private HttpSession httpSession;
        @Autowired
        ApplicationContext applicationContext;
        @Autowired  
        EventoParticipanteRepository eventoParticipanteRepository;
        @Autowired
        JavaMailSender mailSender;

        @RequestMapping(value = "/eventos/documento", method = RequestMethod.POST)
        public ResponseEntity<byte[]> ReporteDistribucion(Integer idEventoParticipante)
                        throws FileNotFoundException, JRException {

                Map<String, Object> params = new HashMap<>();
                Map<String, Object> params2 = new HashMap<>();

                List<Map<String, Object>> listaParams = new ArrayList<>();
                EventoParticipante eventoParticipante = new EventoParticipante();
                eventoParticipante.setIdEventoParticipante(idEventoParticipante);

                List<EventoParticipante> listaParticipantes = eventoParticipanteRepository
                                .findAll(Example.of(eventoParticipante));
                String classpath = "classpath:reports"
                                + listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaReporte();

                String nombre = listaParticipantes.get(0).getParticipante().getNombre() + " "
                                + listaParticipantes.get(0).getParticipante().getPaterno() + " "
                                + listaParticipantes.get(0).getParticipante().getMaterno();

                params2.put("institucion", listaParticipantes.get(0).getEvento().getIntroduccion());
                params2.put("background",
                                listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaDocumento());
                params2.put("participante", nombre.toUpperCase());
                params2.put("motivo", listaParticipantes.get(0).getEvento().getMotivoEvento());
                params2.put("introduccion", listaParticipantes.get(0).getEvento().getIntroduccion());

                System.out.println("introduccion =>" + params2.get("introduccion"));
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMMM");
                SimpleDateFormat simpleDateformat2 = new SimpleDateFormat("YYYY");
                String mes = simpleDateformat.format(listaParticipantes.get(0).getEvento().getFechaEvento());
                String anio = simpleDateformat2.format(listaParticipantes.get(0).getEvento().getFechaEvento());
                params2.put("fecha", "Toluca, Estado de México, " + mes + " de " + anio);
                listaParams.add(params2);

                String background = listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaDocumento();
                System.out.println("background: " + background);
                params.put("background", background);
                params.put("datasource", listaParams);
                String documento = "constancia";
                return this.generarPdf(classpath, params, documento, listaParams);
        }

        public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento,
                        List<?> listaParams) throws FileNotFoundException, JRException {

                File file = ResourceUtils.getFile(classpath);
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
                ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jp),
                                headers, HttpStatus.OK);

                return response;

        }

        @RequestMapping(value = "/eventos/comprobante", method = RequestMethod.POST)
        public ResponseEntity<byte[]> ReporteDistribucion(Integer idEvento, String correo)
                        throws FileNotFoundException, JRException {

                Map<String, Object> params = new HashMap<>();

                List<Map<String, Object>> listaParams = new ArrayList<>();
                Participantes p = new Participantes();
                p.setCorreoElectronico(correo);
                Evento e = new Evento();
                e.setIdEvento(idEvento);
                EventoParticipante eventoParticipante = new EventoParticipante();
                eventoParticipante.setEvento(e);
                eventoParticipante.setParticipante(p);

                List<EventoParticipante> listaParticipantes = eventoParticipanteRepository
                                .findAll(Example.of(eventoParticipante));
                String classpath = "classpath:reports/comprobanteIns.jrxml";

                List<Map<String, Object>> tempListaParticipantes = new ArrayList<>();
                System.out.println("Total de personas encontradas" + listaParticipantes.size());
                for (EventoParticipante pa : listaParticipantes) {

                        Map<String, Object> tempParticipante = new HashMap<>();
                        String nombre = pa.getParticipante().getNombre() + " " + pa.getParticipante().getPaterno() + " "
                                        + pa.getParticipante().getMaterno();
                        System.out.println(nombre);
                        tempParticipante.put("nombreCompleto", nombre.toUpperCase());
                        tempParticipante.put("cargo", pa.getCargo().getDescCargo());
                        tempListaParticipantes.add(tempParticipante);
                }
                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(tempListaParticipantes);

                params.put("descEvento", listaParticipantes.get(0).getEvento().getDescEvento());

                SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMMM");
                SimpleDateFormat simpleDateformat2 = new SimpleDateFormat("YYYY");
                String mes = simpleDateformat.format(listaParticipantes.get(0).getEvento().getFechaEvento());
                String anio = simpleDateformat2.format(listaParticipantes.get(0).getEvento().getFechaEvento());

                params.put("idplogo", "classpath:static/images/formato2.png");
                params.put("lista", itemsJRBean);
                params.put("fechaRegistro", listaParticipantes.get(0).getParticipante().getFechaRegistro().toString());
                String documento = "comprobante";
                return this.generarPdf(classpath, params, documento, listaParams);

        }

        @RequestMapping(value = "/eventos/paquete", method = RequestMethod.POST)
        public ResponseEntity<byte[]> DescargaPaquete(Integer idEvento) throws JRException, FileNotFoundException {

                Map<String, Object> params = new HashMap<>();
                Map<String, Object> params2 = new HashMap<>();

                List<Map<String, Object>> listaParams = new ArrayList<>();
                EventoParticipante eventoParticipante = new EventoParticipante();
                eventoParticipante.setAsistencia("S");
                Participantes participante = new Participantes();
                participante.setActivo("S");
                eventoParticipante.setParticipante(participante);
                Evento evento = new Evento();
                evento.setIdEvento(idEvento);
                eventoParticipante.setEvento(evento);

                List<EventoParticipante> listaParticipantes = eventoParticipanteRepository
                                .findAll(Example.of(eventoParticipante));
                String classpath = listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaReporte();

                String nombre = listaParticipantes.get(0).getParticipante().getNombre() + " "
                                + listaParticipantes.get(0).getParticipante().getPaterno() + " "
                                + listaParticipantes.get(0).getParticipante().getMaterno();

                params2.put("institucion", listaParticipantes.get(0).getEvento().getIntroduccion());
                params2.put("background",
                                listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaDocumento());
                params2.put("participante", nombre.toUpperCase());
                // params2.put("motivo",
                // listaParticipantes.get(0).getEvento().getMotivoEvento());
                params2.put("introduccion", listaParticipantes.get(0).getEvento().getIntroduccion());
                JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaParticipantes);

                params2.put("participanteDataSource", itemsJRBean);

                System.out.println("introduccion" + params2.get("introduccion"));
                SimpleDateFormat simpleDateformat = new SimpleDateFormat("MMMM");
                SimpleDateFormat simpleDateformat2 = new SimpleDateFormat("YYYY");
                String mes = simpleDateformat.format(listaParticipantes.get(0).getEvento().getFechaEvento());
                String anio = simpleDateformat2.format(listaParticipantes.get(0).getEvento().getFechaEvento());
                params2.put("fecha", "Toluca, Estado de México, " + mes + " de " + anio);
                listaParams.add(params2);
                params.put("background", listaParticipantes.get(0).getEvento().getDocumentoEvento().getRutaDocumento());
                params.put("datasource", listaParams);

                JasperReport jasperReport = JasperCompileManager.compileReport(
                                System.getProperty("user.dir") + "\\src\\main\\resources\\reports\\constancia2.jrxml");
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, itemsJRBean);
                JasperExportManager.exportReportToPdfFile(jasperPrint, "\\Emp-Rpt.pdf");
                String documento = "constanciaspaquete";
                return this.generarPdf(classpath, params, documento, listaParams);
        }

        @RequestMapping(value = "/eventos/paquetes", method = RequestMethod.POST)
        public ModelAndView DescargaPaquetes(Integer idEvento) throws JRException, IOException {

               try {
                this.GenerarConstancia(idEvento);
        } catch (MessagingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
                return new ModelAndView("view");

        }

        public String GenerarConstancia(Integer idEvento) throws JRException, IOException, MessagingException {

                Map<String, Object> params = new HashMap<>();

                Evento evento = new Evento();
                evento.setIdEvento(idEvento);

             

                EventoParticipante eventoParticipante = new EventoParticipante();
                eventoParticipante.setEvento(evento);
                eventoParticipante.setAsistencia("S");
                eventoParticipante.setActivo("S");

                List<EventoParticipante> listaParticipantes = eventoParticipanteRepository
                                .findAll(Example.of(eventoParticipante));

                System.out.println("Total de participantes en taller "
                                + listaParticipantes.get(0).getEvento().getDescEvento() + " "
                                + listaParticipantes.size());
              

                for (EventoParticipante ep : listaParticipantes) {
                        if (ep.getAsistencia().equals("S") ) {
                                String nombre = ep.getParticipante().getNombre() + " "
                                                + ep.getParticipante().getPaterno() + " "
                                                + ep.getParticipante().getMaterno();
                                Map<String, Object> params2 = new HashMap<>();
                                String path = "/opt/integral/reports/"
                                                + ep.getEvento().getDocumentoEvento().getRutaReporte();
                                JasperReport jasperReport = JasperCompileManager.compileReport(path);
                                System.out.println("Current relative path is: " + path.toString());

                                params2.put("background", ep.getEvento().getDocumentoEvento().getRutaDocumento());
                                params2.put("participante", nombre.toUpperCase());

                                List<Map<String, Object>> listaParams = new ArrayList<>();

                                params.put("background", ep.getEvento().getDocumentoEvento().getRutaDocumento());
                                // params.put("datasource", listaParams);
                              

                                String folder =  "/opt/integral/reports/"
                                                + ep.getInstitucion().getDescInstitucion().trim() + "\\"
                                                + ep.getEvento().getDescEvento();
                                File files = new File(folder);
                                if (!files.exists()) {
                                        if (files.mkdirs()) {

                                                System.out.println("Multiple directories are created!");
                                        } else {

                                                System.out.println("Failed to create multiple directories!");
                                        }
                                }

                                File file = ResourceUtils.getFile(path);
                            
                                System.out.println(file.getAbsolutePath().toString());
                                List<Map<String, Object>> listaArticulos2 = new ArrayList<>();
                                listaArticulos2.add(params2);
                               

                                // map.put("creador", "Alejandro Gómez");

                                JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
                                JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(listaArticulos2);
                                JasperPrint jp = JasperFillManager.fillReport(jr, params, data);
                                JasperExportManager.exportReportToPdfFile(jp,
                                                folder + "\\" + nombre.toUpperCase() + ".pdf");
                                                String archivo = folder + "\\" + nombre.toUpperCase() + ".pdf";
                                                String nomArchivo =nombre.toUpperCase() + ".pdf";
                                              //  this.EnviarCorreo(ep.getParticipante().getCorreoElectronico(), "constancia", "envio constancia", file,nomArchivo);

                        }
                }

                return "";

        }
        public Boolean EnviarCorreo(String para,String subject, String mailContent,File archivo,String nomArchivo) throws UnsupportedEncodingException, MessagingException  {
                Boolean enviado = false;
                Response response = new Response();
        
                MimeMessage message = mailSender.createMimeMessage();
                MimeMessageHelper helper = new MimeMessageHelper(message, true);
              
              
        
                helper.setFrom("413jan4ndr0.g0m3z@gmail.com","Alejandro");
                helper.setTo(para);
                helper.setSubject(subject);
                helper.setText(mailContent,true);
                helper.addAttachment(nomArchivo,archivo);
        
               // mailSender.send(message);
        
                
        
                return enviado;
            }

            @GetMapping(value = "/buscarparticipanteqr")
    public ModelAndView VerificarPersonal(@RequestParam Integer id) {

        System.out.println("El id es:"+id);
        EventoParticipante ep = new EventoParticipante();
        ep.setIdEventoParticipante(id);
        ep.setActivo("S");
         List<EventoParticipante> lista = eventoParticipanteRepository.findAll(Example.of(ep));
         ep= lista.get(0);
        Map<String, Object> params = new HashMap<>();  
        params.put("nombre", ep.getParticipante().getNombre());
        params.put("cargo",ep.getCargo().getDescCargo() );
        params.put("municipio", ep.getInstitucion().getDescInstitucion());

        

        return new ModelAndView("subtecnica/buscarparticipante", params);
    }
}
