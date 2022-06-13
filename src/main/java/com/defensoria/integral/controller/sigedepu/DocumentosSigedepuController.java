package com.defensoria.integral.controller.sigedepu;

import java.io.File;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.MovAdscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.sigedepu2.Leyenda;
import com.defensoria.integral.model.sigedepu2.Parrafo;
import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.model.subtecnica.Cita;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.sigedepu2.LeyendasRepository;
import com.defensoria.integral.repository.sigedepu2.ParrafoRepository;
import com.defensoria.integral.repository.subtecnica.CitasRepository;
import com.defensoria.sigedepu.model.SolicitudAtencion;
import com.defensoria.sigedepu.model.Usuario;
import com.defensoria.sigedepu.repository.SolicitudAtencionRepository;

@Controller
@RequestMapping("documentos")
public class DocumentosSigedepuController {

    @Autowired
    ApplicationContext applicationContext;

    @Autowired
    SolicitudAtencionRepository solicitudRepository;

    @Autowired
    LeyendasRepository leyendaRepository;

    @Autowired
    AdscripcionesRepository adscripcionesRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    CitasRepository citasRepository;
    @Autowired
    ParrafoRepository parrafoRepository;

    public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento,
            List<?> params2) throws JRException, IOException {
        System.out.println(classpath);
        File file = ResourceUtils.getFile("/opt/integral/reports/"+classpath);
      
      
        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JRBeanCollectionDataSource data = new JRBeanCollectionDataSource(params2);
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

    @RequestMapping(value = "/oficio", method = RequestMethod.POST)

    public ResponseEntity<byte[]> ReporteDistribucion(Integer idSolicitudAtencion)
            throws JRException, IOException {

        System.out.println("IdSolicitudAtencion :" + idSolicitudAtencion);

        SolicitudAtencion solicitud = new SolicitudAtencion();
        solicitud.setId(idSolicitudAtencion);
        List<SolicitudAtencion> listaSolicitudes = new ArrayList<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> listadatos = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        listaSolicitudes = solicitudRepository.findAll(Example.of(solicitud));

        String classpath = "oficio.jrxml";

        if (listaSolicitudes.size() > 0) {
            Usuario usu = new Usuario();
            usu = listaSolicitudes.get(0).getAsesoria().getRepresentacion().getUsuario();
            String defensor = usu.getNombre() + " " + usu.getPaterno() + " " + usu.getMaterno();
            System.out.println("defensor: " + defensor);
            String genero = listaSolicitudes.get(0).getAsesoria().getRepresentacion().getUsuario().getGenero();
            String puesto = "";
            String adscrito = "ADSCRITO AL DISTRITO JUDICIAL DE "
                    + listaSolicitudes.get(0).getAsesoria().getDistrito().getDistrito();
            if (genero.equals("HOMBRE")) {
                puesto = "DEFENSOR PÚBLICO";
            }
            if (genero.equals("MUJER")) {
                puesto = "DEFENSORA PÚBLICA";
            }
            String residencia = "CON RESIDENCIA EN "
                    + listaSolicitudes.get(0).getAsesoria().getResidencia().getNombre_residencia().toUpperCase()
                    + " CON DOMICILIO EN "
                    + listaSolicitudes.get(0).getAsesoria().getResidencia().getDomicilio().toUpperCase();
            System.out.println("defensor: " + genero + " PUESTO: " + puesto + " " + adscrito + residencia);
            // OBTENER EL MUNICIPIO PARA EL DOCUMENTO
            Integer cveRegion = 0;
            System.out.println("REGION: " + listaSolicitudes.get(0).getRegion().getNombre() + " "
                    + listaSolicitudes.get(0).getRegion().getId());

            if (listaSolicitudes.get(0).getRegion().getId() == 1) {
                cveRegion = 3;
            } else if (listaSolicitudes.get(0).getRegion().getId() == 2) {
                cveRegion = 1;
            } else if (listaSolicitudes.get(0).getRegion().getId() == 3) {
                cveRegion = 2;
            }
            Adscripciones ads = new Adscripciones();
            ads.setCveRegion(cveRegion);
            ads.setCveadsPadre(0);
            Adscripciones ads2 = new Adscripciones();

            List<Adscripciones> listaAds = adscripcionesRepository.findAll(Example.of(ads));
            if (listaAds.size() > 0)
                ads = listaAds.get(0);
            String municipio = ads.getMunicipio().getDescMunicipio().toUpperCase();
            String direccion = ads.getDescAdscripcion();
            Integer cveAdscripcion = ads.getCveAdscripcion();
            System.out.println("clave adscripcion" + cveAdscripcion);
            List<Personal> listaPersonal = personalRepository.TitularAdscripciones(cveAdscripcion);
            String director = listaPersonal.get(0).getNombre() + " " + listaPersonal.get(0).getPaterno() + " "
                    + listaPersonal.get(0).getMaterno();
            String titulo = listaPersonal.get(0).getTitulo();
            System.out.println("Director: "+director);
            Integer generoDirector = listaPersonal.get(0).getCveGenero();
            String tipoTitular = "";
            for (MovAdscripciones movimiento : listaPersonal.get(0).getMovAdscripciones()) {
                if (movimiento.getActivo().equals("S")) {
                    tipoTitular = movimiento.getTipoTitular();
                    break;
                }
            }
            // String tipoTitular =
            // listaPersonal.get(0).getMovAdscripciones().get(0).getTipoTitular();
            System.out.println("$%&NOMBRE" + listaPersonal.get(0).getCveUsuario() + " "
                    + listaPersonal.get(0).getMovAdscripciones().size());
            String descGenero = "";
            if (generoDirector == 1) {
                if (tipoTitular.equals("T")) {
                    descGenero = "DIRECTOR ";
                } else {
                    descGenero = "ENCARGADO DEL DESPACHO DE LA ";
                }
            } else if (generoDirector == 2) {
                if (tipoTitular.equals("T")) {
                    descGenero = "DIRECTORA ";
                } else {
                    descGenero = "ENCARGADA DEL DESPACHO DE LA ";
                }
            }

            System.out.println("Municipio: " + municipio + " Direccion: " + direccion + " director: " + descGenero + " "
                    + titulo + " " + director);
            String peticionario = listaSolicitudes.get(0).getPeticionario().getNombre() + " "
                    + listaSolicitudes.get(0).getPeticionario().getPaterno() + " "
                    + listaSolicitudes.get(0).getPeticionario().getMaterno();
            System.out.println("peticionario " + peticionario);

            String asesor = listaSolicitudes.get(0).getAsesoria().getUsuario().getNombre() + " "
                    + listaSolicitudes.get(0).getAsesoria().getUsuario().getPaterno() + " "
                    + listaSolicitudes.get(0).getAsesoria().getUsuario().getMaterno();
          
            Integer cveAsesor = listaSolicitudes.get(0).getAsesoria().getUsuario().getId();
            System.out.println("Asesor: " + asesor+" clave= "+cveAsesor);
            String atentamente = "";
            if (tipoTitular.equals("T")) {
                atentamente = descGenero + "" + direccion.replaceAll("DIRECCIÓN", "");
            } else {
                atentamente = descGenero + "" + direccion;
            }
            System.out.println("Atentamente " + atentamente);
            // OBTENER FECHA DE REGISTRO

            Date date = listaSolicitudes.get(0).getAsesoria().getRepresentacion().getFecha_creacion();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String month = new SimpleDateFormat("MMMM").format(date.getTime());
            String fecha = day + " de " + month + " de " + year;
            System.out.println("fecha Registro: " + fecha);

            // OBTENER LA LEYENDA ACTUAL PARA EL DOCUMENTO
            Leyenda leyenda = new Leyenda();
            leyenda.setAnioLeyenda(year);
            leyenda.setActivo("S");
            List<Leyenda> listaLeyenda = leyendaRepository.findAll(Example.of(leyenda));
            if (listaLeyenda.size() > 0)
                leyenda = listaLeyenda.get(0);
            String leyendaOficio = leyenda.getDescLeyenda();
            System.out.println(leyendaOficio);

            // OBTENER PARRAFOS ADICIONALES
            Parrafo pa = new Parrafo();
            pa.setActivo("S");
            pa.setAnio(year);
            List<Parrafo> parrafos = parrafoRepository.findAll(Example.of(pa));
            String otro = "";
            if (parrafos.size() > 0) {
                System.out.println("Recuperando parrrafos adicinales");
                for (Parrafo p : parrafos) {
                    otro += p.getDescParrafo() + "\n";
                }
            }
            params2.put("otro", otro);

            // OBTENER NUMERO DE OFICIO
            // OBTENER ESTUDIO SOCIOECONOMICO
            String folioEstudio = " ";
            String fechaEstudio = "";
            params2.put("fechaEstudio", "");
            if (listaSolicitudes.get(0).getAsesoria().getConclusionEstudio() != null) {
                
               
                folioEstudio = listaSolicitudes.get(0).getAsesoria().getConclusionEstudio().getEstudio().getFolio();
                System.out.println("folio: " + folioEstudio);
                Date dateEstudio = listaSolicitudes.get(0).getAsesoria().getConclusionEstudio().getFecha();

                cal.setTime(dateEstudio);
                fechaEstudio = day + " de " + month + " de " + year;
                System.out.println("fecha Registro: " + fechaEstudio);
                params2.put("fechaEstudio", "de fecha " + "<b>" + fechaEstudio + "</b>");
            }
            else{
                System.out.println("no tienen estudio socio economico");
            }
            String accion = listaSolicitudes.get(0).getAsesoria().getAccion_juzgado();
            System.out.println(accion);
            // OBTENER EL PERFIL DEL ASESOR
            System.out.println("Asesor: " + asesor+" clave= "+cveAsesor);
            Personal p = new Personal();
            p.setUsuario_id(cveAsesor);
            p.setActivo("S");
            Integer cvePerfilPersonal = 0;
            List<Personal> listap = personalRepository.findAll(Example.of(p));
            System.out.println("numero de asesores encontrados ="+listap.size());
            if (listap.size() > 0)
                p = listap.get(0);

            for (MovAdscripciones m : p.getMovAdscripciones()) {
                if (m.getActivo().equals("S")) {
                    cvePerfilPersonal = m.getCvePerfilPersonal();
                }
            }
            System.out.println("CLAVE PERFIL PERSONAL " + cvePerfilPersonal);
            String numeroOficio = "";
            if (cvePerfilPersonal == 15) {
                numeroOficio = ads.getCodigoUnidad() + "/C/"
                        + listaSolicitudes.get(0).getAsesoria().getRepresentacion().getNumero_oficio_turnar() + "CJ/"
                        + year;
                System.out.println("Numero de Oficio: " + numeroOficio);

                // params2.put("director", titulo + " " + director);
                // params2.put("asesor", asesor);
                params2.put("continua", ", en virtud de que acudió a la <b>caravana por la justicia ciudadana</b>");
            } else {
                numeroOficio = ads.getCodigoUnidad() + "/C/"
                        + listaSolicitudes.get(0).getAsesoria().getRepresentacion().getNumero_oficio_turnar() + "/"
                        + year;
                System.out.println("Numero de Oficio: " + numeroOficio);

                params2.put("continua",
                        ", en virtud de que acudió ante estas oficinas solicitando asesoria y orientación jurídica");
            }

            // AGREGAR OTROS PARRAFOS
            params2.put("oficio", numeroOficio);
            params2.put("director", titulo + " " + director);
            params2.put("asesor", asesor);

            params2.put("leyenda", leyendaOficio);
            params2.put("municipio", municipio + ", MÉXICO");
            params2.put("fecha", fecha);

            params2.put("defensor", "LIC. " + defensor.toUpperCase());
            params2.put("puesto", puesto);
            params2.put("adscrito", adscrito);
            params2.put("residencia", residencia);
            params2.put("peticionario", "Me permito presentar a C. <b>" + peticionario + "</b>");
            params2.put("accion", "a fin de que le oriente y asista para <b>" + accion + "</b>");

            params2.put("fundamento",
                    "Lo anterior, con fundamento en lo dispuesto por los artículos 4, fracción II, y 17, fracción IV, de la Ley de Defensoría Pública del Estado de México vigente, asimismo, se informa que todos los servicios que presta el instituto son gratuitos.");
            params2.put("atentamente", atentamente);

            if (!folioEstudio.equals(" ")) {
                params2.put("estudio", ", por haber resultado procedente el estudio socio económico <b>"
                        + folioEstudio.toUpperCase() + "</b>");
            } else {
                params2.put("estudio", "");
            }
            params2.put("informar", ", debiendo informar a esta " + direccion + " el seguimiento del citado asunto.");

            params2.put("informar2", ", debiendo informar a esta " + direccion + " el seguimiento del citado asunto.2");
            params2.put("titulo", "titulo");
            System.out.println("TIpo Titular: " + tipoTitular);
            listadatos.add(params2);
            params.put("datasource", listadatos);
        }

        params.put("background", "classpath:/static/images/logooficio.png");
        params.put("SUBREPORT_DIR", "classpath:/reports/");
        String documento = "oficio";
        return this.generarPdf(classpath, params, documento, listadatos);

    }

    @RequestMapping(value = "/cita", method = RequestMethod.POST)
    public ResponseEntity<byte[]> DocumentoCita(Integer idCita) throws JRException, IOException {

        System.out.println("IdCita :" + idCita);
        String texto = "";
        Cita c = new Cita();
        c.setIdCita(idCita);
        List<Cita> listaCitas = new ArrayList<>();
        Map<String, Object> params2 = new HashMap<>();
        List<Map<String, Object>> listadatos = new ArrayList<>();
        Map<String, Object> params = new HashMap<>();
        listaCitas = citasRepository.findAll(Example.of(c));

        String classpath = "documentocita.jrxml";

        if (listaCitas.size() > 0) {
            c = listaCitas.get(0);

            // OBTENER EL MUNICIPIO PARA EL DOCUMENTO
            Adscripciones ads = new Adscripciones();
            ads.setCveRegion(c.getCveRegion());
            ads.setCveadsPadre(0);
            List<Adscripciones> lista = adscripcionesRepository.findAll(Example.of(ads));
            if (lista.size() > 0)
                ads = lista.get(0);
            String municipio = ads.getMunicipio().getDescMunicipio().toUpperCase();
            String direccion = ads.getDescAdscripcion();
            String ubicacion = ads.getDomicilio();
            Integer cveAdscripcion = ads.getCveAdscripcion();
            System.out.println("clave adscripcion" + cveAdscripcion);
            Representados r = new Representados();
            r = c.getRepresentado();
            String peticionario = r.getNombre() + " " + r.getPaterno() + " " + r.getMaterno();
            System.out.println("peticionario " + peticionario);
            String curp = r.getCurp();
            String email = r.getCorreo();

            Date date = c.getFechaCita();
            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DAY_OF_MONTH);
            String month = new SimpleDateFormat("MMMM").format(date.getTime());
            String fecha = day + " de " + month + " de " + year;
            System.out.println("fecha Registro: " + fecha + " " + c.getHoraCita());

            Date dateRegistro = c.getFechaActualizacion();
            Calendar cal2 = Calendar.getInstance();
            cal2.setTime(dateRegistro);
            int year2 = cal2.get(Calendar.YEAR);
            int day2 = cal2.get(Calendar.DAY_OF_MONTH);
            String month2 = new SimpleDateFormat("MMMM").format(dateRegistro.getTime());
            String fecha2 = day2 + " de " + month2 + " de " + year2;
            String hora = c.getHoraCita() + "";

            String leyendaOficio = this.LeyendaDocumentos(c.getFechaCita());
            String numeroOficio = c.getIdCita() + "";
            texto = "Tu cita ha sido agendada con número de folio:" + c.getIdCita() + " para el dia: " + fecha
                    + " a las: " + c.getHoraCita() + ",<br> Preséntate en la " + direccion + " ubicada en: <b>"
                    + ubicacion
                    + "</b>, 10 minutos antes de la hora para registrarse en el modulo de recepción y cuenta con una tolerancia de 15 minutos. <BR><BR> Consulta en el siguiente PDF los documentos que deberás presentar, obligatoriamente, a tu cita.";
            System.out.println("texto" + texto);
            params2.put("folio", numeroOficio);
            params2.put("municipio", municipio + ", MÉXICO");
            params2.put("texto", texto);
            params2.put("leyenda", leyendaOficio);
            params2.put("fecha", fecha);
            params2.put("fechaR", fecha2);
            params2.put("email", email);
            params2.put("curp", curp);
            params2.put("peticionario", peticionario.toUpperCase());
            params2.put("hora", hora);
            params2.put("direccion", direccion);
            params2.put("ubicacion", ubicacion);
            // params2.put("director", titulo + " " + director);
            // params2.put("asesor", asesor);

            listadatos.add(params2);
            params.put("datasource", listadatos);
        }

        params.put("background", "classpath:/static/images/logooficio.png");
        String documento = "cita";
        return this.generarPdf(classpath, params, documento, listadatos);
    }

    public String LeyendaDocumentos(Date fecha) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        int year = cal.get(Calendar.YEAR);
        Leyenda leyenda = new Leyenda();
        leyenda.setAnioLeyenda(year);
        leyenda.setActivo("S");
        List<Leyenda> lista = leyendaRepository.findAll(Example.of(leyenda));
        if (lista.size() > 0)
            leyenda = lista.get(0);

        String leyendaOficio = leyenda.getDescLeyenda();
        System.out.println(leyendaOficio);
        return leyendaOficio;
    }

}
