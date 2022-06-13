package com.defensoria.integral.controller.sigedepu;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Personal;
//import com.defensoria.integral.model.firma.GeneradorModel;
import com.defensoria.integral.model.sigedepu2.Cuerpo;
import com.defensoria.integral.model.sigedepu2.Documento;
import com.defensoria.integral.model.sigedepu2.Encabezado;
import com.defensoria.integral.model.sigedepu2.Leyenda;
import com.defensoria.integral.model.sigedepu2.Pie;
import com.defensoria.integral.model.sigedepu2.RepresentacionFirma;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.sigedepu2.LeyendasRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentacionFirmaRepository;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.sigedepu.model.Distrito;
import com.defensoria.sigedepu.model.Region;
import com.defensoria.sigedepu.model.Representacion;
import com.defensoria.sigedepu.model.SolicitudAtencion;
import com.defensoria.sigedepu.model.Usuario;
import com.defensoria.sigedepu.repository.DistritosRepository;
import com.defensoria.sigedepu.repository.RegionRepository;
import com.defensoria.sigedepu.repository.RepresentacionRepository;
import com.defensoria.sigedepu.repository.SolicitudAtencionRepository;
import com.defensoria.sigedepu.repository.UsuariosRepository;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
//import javax.xml.bind.JAXBException;
//import mx.gob.edomex.dgsei.generador.dto.SignatureDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@RestController
public class OficiosSigedepuRestController {

    @Autowired
    SolicitudAtencionRepository solicitudAtencionRepository;
    @Autowired
    RegionRepository regionRepository;
    @Autowired
    DistritosRepository distritosRepository;
    @Autowired
    UsuariosRepository usuariosRepository;

    @Autowired
    RepresentacionRepository representacionRepository;
    @Autowired
    RepresentacionFirmaRepository representacionFirmaRepository;

    @Autowired
    SolicitudAtencionRepository solicitudRepository;

//    @Autowired
//    GeneradorModel generadorModel;

    @Autowired
    LeyendasRepository leyendaRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    AdscripcionesRepository adscripcionesRepository;

    @SuppressWarnings("deprecation")
    @Transactional
    @PostMapping(value = "/buscaroficios")
    public ResponseEntity<?> ListaOficios(@RequestBody SolicitudAtencion solicitud) throws ParseException {
        Date date1 = new Date();
        Date date2 = new Date();
        if (solicitud.getFecha_registro() == null) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            //PRIMERA FECHA 
            Date date = new Date();
            String dateString = dateFormat.format(date);
            date = dateFormat.parse(dateString);
            //SEGUNDA FECHA
            Date dateSegunda = new Date();
            dateString = dateFormat.format(dateSegunda);
            dateSegunda = dateFormat.parse(dateString);
            System.out.println(dateSegunda.toString());

            //  solicitud.setEstatus("Patrocinio Turnado");
            date1 = date;
            date2 = dateSegunda;
        } else {
            date1 = solicitud.getFecha_registro();
            date2 = solicitud.getFecha_rechazo();
        }

        date1.setHours(0);
        date1.setMinutes(0);
        date1.setSeconds(0);
        System.out.println("fecha registro 1:" + date1);

        date2.setHours(23);
        date2.setMinutes(59);
        date2.setSeconds(59);
        System.out.println("Fecha registro 2:" + date2.toString());
        System.out.println("fecha registro 1:" + date1);

        System.out.println("fecha registro recibida:" + solicitud.getEstatus());
        System.out.println("Region Recibida:" + solicitud.getRegion().getId());

        // if you really need java.sql.Date
        // java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        List<SolicitudAtencion> listaSolicitudes = solicitudAtencionRepository.findByPendientesFechaRegistro(date1,
                date2, "%" + solicitud.getEstatus() + "%", solicitud.getRegion().getId());
       
        // List<SolicitudAtencion> listaSolicitudes = solicitudAtencionRepository.findByPendientesFechaRegistro( "%" + solicitud.getEstatus() + "%", solicitud.getRegion().getId());
       
        List<Integer> lista = new ArrayList<>();
        List<Object> listaSolicituresR = new ArrayList<>();
        
        System.out.println("TAMAÑO "+listaSolicitudes.size());
        if (listaSolicitudes.size() > 0) {
            for (SolicitudAtencion sol : listaSolicitudes) {
                HashMap<String, Object> element = new HashMap<>();
                element.put("folio", sol.getFolio());
                String nombre = sol.getPeticionario().getNombre() + " " + sol.getPeticionario().getPaterno() + " " + sol.getPeticionario().getMaterno();
                element.put("peticionario", nombre);
                element.put("fechaSolicitud", sol.getFecha_solicitud());
                element.put("distrito", sol.getAsesoria().getDistrito().getDistrito());
                element.put("estatus", sol.getEstatus());
                System.out.println("id Solicitud" + sol.getId());
                if (sol.getAsesoria().getRepresentacion().getUsuario() == null) {
                    element.put("defensor", null);
                } else {
                    String defensor = sol.getAsesoria().getRepresentacion().getUsuario().getNombre() + " " + sol.getAsesoria().getRepresentacion().getUsuario().getPaterno() + " " + sol.getAsesoria().getRepresentacion().getUsuario().getMaterno();
                    element.put("defensor", defensor);
                }
                element.put("idRepresentacion", sol.getAsesoria().getRepresentacion().getId());
                element.put("idFirma", null);
                element.put("hash", "");
                element.put("idSolicitudAtencion", sol.getId());
                lista.add(sol.getAsesoria().getRepresentacion().getId());
                listaSolicituresR.add(element);
            }
            System.out.println("El tamaño de la lista es: " + lista.size());
            if (lista.size() > 0) {
                System.out.println("Primer elemento de la lista es: " + lista.get(0));
                List<RepresentacionFirma> listaRepresentaciones = representacionFirmaRepository.findFirmas(lista);

                System.out.println("Tamaño de la lista: " + listaRepresentaciones.size());
            }
        }

        return new ResponseEntity<>(listaSolicituresR, HttpStatus.OK);

    }

    @PostMapping(value = "/turnarRepresentacion")
    public ResponseEntity<?> TurnarRepresentacion(Representacion representacion) {

        return new ResponseEntity<Representacion>(representacion, HttpStatus.OK);
    }

    @PostMapping(value = "/BuscarRepresentacion")
    public ResponseEntity<?> BuscarRepresentacion(@RequestBody Representacion representacion) {

        System.out.println("Representacion: " + representacion.getId());

        List<Representacion> listaRepresentaciones = representacionRepository.findAll(Example.of(representacion));
        if (listaRepresentaciones.size() > 0) {
            representacion = listaRepresentaciones.get(0);
        }

        return new ResponseEntity<Representacion>(representacion, HttpStatus.OK);
    }

    @PostMapping(value = "/listaRegiones")
    public ResponseEntity<?> ListaRegiones() {

        List<Region> listaRegiones = regionRepository.findAll();

        return new ResponseEntity<List<Region>>(listaRegiones, HttpStatus.OK);
    }

    @PostMapping(value = "/listaDistritos")
    public ResponseEntity<?> ListaDistritos(@RequestBody Distrito distrito) {

        List<Distrito> listaDistritos = distritosRepository.findAll(Example.of(distrito));

        return new ResponseEntity<List<Distrito>>(listaDistritos, HttpStatus.OK);
    }

    @PostMapping(value = "/listaDefensores")
    public ResponseEntity<?> ListaDefensores(@RequestBody Usuario usuario) {
        usuario.setActivo(1);

        List<Usuario> listaDefensores = usuariosRepository.findAll(Example.of(usuario));

        return new ResponseEntity<List<Usuario>>(listaDefensores, HttpStatus.OK);
    }

    @PostMapping(value = "/firmarDocumentos")
    public ResponseEntity<?> FirmarDocumentos(@RequestBody Usuario usuario) {
        usuario.setActivo(1);
       // String user = "";
       // String password = "";

        List<Usuario> listaDefensores = usuariosRepository.findAll(Example.of(usuario));

        return new ResponseEntity<List<Usuario>>(listaDefensores, HttpStatus.OK);
    }

    @PostMapping(value = "/atenciones")
    public ResponseEntity<?> ListaAtenciones(@RequestBody Region region) throws ParseException {
        System.out.println("Regiones:" + region.getId());
        SolicitudAtencion solicitud = new SolicitudAtencion();
        solicitud.setRegion(region);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //PRIMERA FECHA 
        Date date = new Date();
        String dateString = dateFormat.format(date);
        date = dateFormat.parse(dateString);
        //SEGUNDA FECHA
        Date dateSegunda = new Date();
        dateString = dateFormat.format(dateSegunda);
        dateSegunda = dateFormat.parse(dateString);
        System.out.println(dateSegunda.toString());

        Date date1 = new Date();
        Date date2 = new Date();

        date1 = date;
        date2 = dateSegunda;
       // date1.setHours(0);
        //date1.setMinutes(0);
       // date1.setSeconds(0);
        System.out.println("fecha registro 1:" + date1);

       // date2.setHours(23);
       // date2.setMinutes(59);
       // date2.setSeconds(59);
        System.out.println("Fecha registro 2:" + date2);

        System.out.println("fecha registro 1:" + date1);
        System.out.println("fecha registro recibida:" + solicitud.getFecha_registro());
        System.out.println("fecha registro recibida:" + solicitud.getFecha_rechazo());

        // if you really need java.sql.Date
        // java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        List<SolicitudAtencion> listaAtenciones = solicitudAtencionRepository.findByAtencionesFechaRegistro(date1,
                date2, region.getId());

        return new ResponseEntity<List<SolicitudAtencion>>(listaAtenciones, HttpStatus.OK);
    }

//    @PostMapping(value = "/generador/editor/firmar")
//    public ResponseEntity<?> gestor() {
//
//        List<Region> listaRegiones = regionRepository.findAll();
//
//        return new ResponseEntity<List<Region>>(listaRegiones, HttpStatus.OK);
//    }
    @RequestMapping(value = {"/editor/generaXML"}, method = {RequestMethod.POST})
    public @ResponseBody
    Response generaXML(@RequestBody SolicitudAtencion solicitud) throws NoSuchAlgorithmException, UnsupportedEncodingException, JsonProcessingException {
//        Usuario usuario = (Usuario) request.getSession().getAttribute(Constants.USUARIO_ACTIVO);
        Map<String, Object> params2 = new HashMap<>();
       // List<Map<String, Object>> listadatos = new ArrayList<>();
       // Map<String, Object> params = new HashMap<>();
        List<SolicitudAtencion> listaSolicitudes = solicitudRepository.findAll(Example.of(solicitud));
        String  xml = "";
        if (listaSolicitudes.size() > 0) {
            String defensor = listaSolicitudes.get(0).getAsesoria().getRepresentacion().getUsuario().getNombre()+" "+listaSolicitudes.get(0).getAsesoria().getRepresentacion().getUsuario().getPaterno()+" "+listaSolicitudes.get(0).getAsesoria().getRepresentacion().getUsuario().getMaterno();
            System.out.println("defensor: "
                    + defensor);
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
            // OBTENER LA LEYENDA ACTUAL PARA EL DOCUMENTO
            Leyenda leyenda = new Leyenda();
            leyenda.setAnioLeyenda(2019);
            leyenda.setActivo("S");
            leyendaRepository.findOne(Example.of(leyenda));
            String leyendaOficio = leyenda.getDescLeyenda();
            System.out.println(leyendaOficio);
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
            adscripcionesRepository.findOne(Example.of(ads));
            String municipio = ads.getMunicipio().getDescMunicipio().toUpperCase();
            String direccion = ads.getDescAdscripcion();
            Integer cveAdscripcion = ads.getCveAdscripcion();
            System.out.println("clave adscripcion" + cveAdscripcion);
            List<Personal> listaPersonal = personalRepository.TitularAdscripciones(cveAdscripcion);
            String director = listaPersonal.get(0).getNombre() + " " + listaPersonal.get(0).getPaterno() + " "
                    + listaPersonal.get(0).getMaterno();
            String titulo = listaPersonal.get(0).getTitulo();
            Integer generoDirector = listaPersonal.get(0).getCveGenero();
            String descGenero = "";
            if (generoDirector == 1) {
                descGenero = "DIRECTOR ";
            } else if (generoDirector == 2) {
                descGenero = "DIRECTORA ";
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
            System.out.println("Asesor: " + asesor);
            String atentamente = descGenero + "" + direccion.replaceAll("DIRECCIÓN", "");
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

            // OBTENER NUMERO DE OFICIO
            String numeroOficio = ads.getCodigoUnidad() + "/C/"
                    + listaSolicitudes.get(0).getAsesoria().getRepresentacion().getNumero_oficio_turnar() + "/" + year;
            System.out.println("Numero de Oficio: " + numeroOficio);
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
            String accion = listaSolicitudes.get(0).getAsesoria().getAccion_juzgado();
            System.out.println(accion);

            Documento documento = new Documento();
            Cuerpo cuerpo = new Cuerpo();
            Pie pie = new Pie();
            Encabezado encabezado = new Encabezado();
            encabezado.setLeyenda(leyendaOficio);
            encabezado.setFecha(fecha);
            encabezado.setMunicipio(municipio);
            encabezado.setOficio(numeroOficio);
            cuerpo.setDefensor("LIC. " + defensor);
            cuerpo.setPuesto(puesto);
            cuerpo.setAdscrito(adscrito);
            cuerpo.setResidencia(residencia);
            cuerpo.setPeticionario("Me permito presentar a C. <b>" + peticionario + "</b>");
            cuerpo.setAccion("a fin de que le oriente y asista para <b>" + accion + "</b>");
            cuerpo.setContinua(", en virtud de que acudió ante estas oficinas solicitando asesoria y orientación jurídica");
            cuerpo.setFundamento("Lo anterior, con fundamento en lo dispuesto por los artículos 4, fracción II, y 17, fracción IV, de la Ley de Defensoría Pública del Estado de México vigente, asi mismo, se informa que todos los servicios que presta el instituto son gratuitos.");
            cuerpo.setAtentamente(atentamente);
            cuerpo.setDirector(titulo + " " + director);

            if (!folioEstudio.equals(" ")) {
                cuerpo.setEstudio(", por haber resultado procedente el estudio socio económico <b>" + folioEstudio.toUpperCase() + "</b>");
            } else {
                cuerpo.setEstudio("");
            }

            cuerpo.setInformar("debiendo informar a esta " + direccion + " el seguimiento del citado asunto.");
            pie.setAsesor(asesor);
            documento.setCuerpo(cuerpo);
            documento.setEncabezado(encabezado);
            documento.setPie(pie);

          

//            params2.put("leyenda", leyendaOficio);
//            params2.put("municipio", municipio + ", MÉXICO");
//            params2.put("fecha", fecha);
//            params2.put("oficio", numeroOficio);
//            params2.put("defensor",);
//            params2.put("puesto", puesto);
//            params2.put("adscrito", adscrito);
//            params2.put("residencia", residencia);
//            params2.put("peticionario", "Me permito presentar a C. <b>" + peticionario + "</b>");
//            params2.put("accion", "a fin de que le oriente y asista para <b>" + accion + "</b>");
//            params2.put("continua", ", en virtud de que acudió ante estas oficinas solicitando asesoria y orientación jurídica");
//            params2.put("fundamento", "Lo anterior, con fundamento en lo dispuesto por los artículos 4, fracción II, y 17, fracción IV, de la Ley de Defensoría Pública del Estado de México vigente, asi mismo, se informa que todos los servicios que presta el instituto son gratuitos.");
//            params2.put("atentamente", atentamente);
//            params2.put("director", titulo + " " + director);
//            params2.put("asesor", asesor);
//
//            if (!folioEstudio.equals(" ")) {
//                params2.put("estudio", ", por haber resultado procedente el estudio socio económico <b>" + folioEstudio.toUpperCase() + "</b>");
//            } else {
//                params2.put("estudio", "");
//            }
//            params2.put("informar", ", debiendo informar a esta " + direccion + " el seguimiento del citado asunto.");
//
//            params2.put("informar2", ", debiendo informar a esta " + direccion + " el seguimiento del citado asunto.2");
//            params2.put("titulo", "titulo");
//            listadatos.add(params2);
//            listToString = @XmlAnyElement listadatos;
//            params.put("datasource", listadatos);
            xml=xml.replace("<Documento>", "<documento version=\"1.0\" urlEmisor=\"http://sistemas.edomex.gob.mx/\" tipo=\"oficioCanalizacion\" emisor=\"gypst\">");
            xml=xml.replace("</Documento>","</documento>");
            System.out.println("El xml es: " + xml);
        }

//        return this.generadorModel.generaXML(xml);
return null;
    }

//    @RequestMapping(value = {"/generador/editor/firmar"}, method = {RequestMethod.POST})
//    public @ResponseBody
//    Response fimar(@RequestBody SignatureDTO firma) throws NoSuchAlgorithmException, UnsupportedEncodingException, JAXBException {
////    Usuario usuario = (Usuario) request.getSession().getAttribute(Constants.USUARIO_ACTIVO);
//
//System.out.println(firma.getId());
//System.out.println(firma.getSignature());
//System.out.println(firma.getXml());
//System.out.println(firma.getCuts());
//        return this.generadorModel.firma(firma);
//    }

    
}
