/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.constancias;

import com.defensoria.integral.controller.sigedepu.Response;
import com.defensoria.integral.model.constancias.Cargo;
import com.defensoria.integral.model.constancias.DocumentoEvento;
import com.defensoria.integral.model.constancias.Evento;
import com.defensoria.integral.model.constancias.EventoInstitucion;
import com.defensoria.integral.model.constancias.EventoParticipante;
import com.defensoria.integral.model.constancias.Instituciones;
import com.defensoria.integral.model.constancias.Participantes;
import com.defensoria.integral.repository.constancias.CargoRepository;
import com.defensoria.integral.repository.constancias.DocumentoEventoRepository;
import com.defensoria.integral.repository.constancias.EventoParticipanteRepository;
import com.defensoria.integral.repository.constancias.EventosInstitucionesRepository;
import com.defensoria.integral.repository.constancias.EventosRepository;
import com.defensoria.integral.repository.constancias.InstitucionesRepository;
import com.defensoria.integral.repository.constancias.ParticipanteRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.hibernate.boot.model.relational.AuxiliaryDatabaseObject.Expandable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alejandro.gomez
 */
@RestController
public class ConstanciasRestController {

    @Autowired
    EventosRepository eventosRepository;
    @Autowired
    InstitucionesRepository institucionesRepository;
    @Autowired
    EventosInstitucionesRepository eventosInstitucionesRepository;
    @Autowired
    CargoRepository cargoRepository;
    @Autowired
    ParticipanteRepository participanteRepository;
    @Autowired
    EventoParticipanteRepository eventoParticipanteRepository;
    @Autowired
    JavaMailSender mailSender;

    @Autowired
    DocumentoEventoRepository documentoEventoRepository;

    @PostMapping("/listarinstituciones")
    public ResponseEntity<?> ListarInstituciones(@RequestBody Instituciones institucion) {

        institucion.setActivo("S");
        return new ResponseEntity<>(institucionesRepository.findAll(Example.of(institucion)), HttpStatus.OK);

    }

    @PostMapping("/buscarinstituciones")
    public ResponseEntity<?> buscarInstituciones(@RequestBody Instituciones institucion) {

        institucion.setActivo("S");
        return new ResponseEntity<>(institucionesRepository.findByDescInstituciones(institucion.getDescInstitucion()),
                HttpStatus.OK);

    }

    @PostMapping("/buscarparticipantesasis")
    public ResponseEntity<?> BuscarParticipantesASis(@RequestBody Participantes participante) {
        return new ResponseEntity<>(eventoParticipanteRepository.findByParticipante(participante.getNombre(),
                participante.getIdParticipante()), HttpStatus.OK);

    }

    @PostMapping("/buscarparticipanteregistro")
    // public ResponseEntity<?> BuscarParticipantesRegistro(@RequestBody
    // Participantes participante) {
    // return new
    // ResponseEntity<>(participanteRepository.findAll(Example.of(participante)),
    // HttpStatus.OK);
    //
    // }

    public ResponseEntity<?> BuscarParticipantesRegistro(@RequestBody EventoParticipante eventoParticipante) {

        eventoParticipante.setActivo("S");
        return new ResponseEntity<>(eventoParticipanteRepository.findAll(Example.of(eventoParticipante),
                Sort.by(Sort.Direction.DESC, "idEventoParticipante")), HttpStatus.OK);

    }

    @PostMapping("/eliminarEvento")
    public ResponseEntity<?> EliminarEvento(@RequestBody EventoInstitucion eventoInstitucion) {

        eventoInstitucion.setActivo("N");

        return new ResponseEntity<>(eventosInstitucionesRepository.save(eventoInstitucion), HttpStatus.OK);

    }

    @PostMapping("/eliminarparticipante")
    public ResponseEntity<?> EliminarParticipante(@RequestBody EventoParticipante eventoParticipante) {

        System.out.println(eventoParticipante.getIdEventoParticipante());
        List<EventoParticipante> lista = eventoParticipanteRepository.findAll(Example.of(eventoParticipante));

        EventoParticipante eventoRegistrado = new EventoParticipante();
        if (lista.size() > 0) {
            eventoRegistrado = lista.get(0);
            System.out.println(eventoRegistrado.getIdEventoParticipante());
            eventoRegistrado.setActivo("N");
            eventoRegistrado.getParticipante().setActivo("N");
            eventoParticipanteRepository.save(eventoRegistrado);
        }

        return new ResponseEntity<>(eventoRegistrado, HttpStatus.OK);

    }

    @PostMapping("/buscarEvento")
    public ResponseEntity<?> BuscarEvento(@RequestBody EventoInstitucion eventoInstitucion) {

        eventoInstitucion.setActivo("S");
        return new ResponseEntity<>(eventosInstitucionesRepository.findOne(Example.of(eventoInstitucion)),
                HttpStatus.OK);

    }

    @PostMapping("/buscarParticipante")
    public ResponseEntity<?> BuscarParticipante(@RequestBody EventoParticipante eventoParticipante) {

        eventoParticipante.setActivo("S");
        return new ResponseEntity<>(eventoParticipanteRepository.findOne(Example.of(eventoParticipante)),
                HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/guardarInstituciones")
    public ResponseEntity<?> GuardarInstituciones(@RequestBody Instituciones institucion) {

        institucion.setActivo("S");
        institucionesRepository.save(institucion);

        return new ResponseEntity<>(institucion, HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/guardarEvento")
    public ResponseEntity<?> GuardarEvento(@RequestBody EventoInstitucion eventoInstitucion) {
        Instituciones institucion = new Instituciones();
        eventoInstitucion.setActivo("S");
        Evento evento = new Evento();
        evento = eventoInstitucion.getEventoI();
        evento.setActivo("S");
        evento.setImagen("/resources/images/logoido2.png");
        evento.getDocumentoEvento().setActivo("S");

        DocumentoEvento documentoEvento = new DocumentoEvento();
        documentoEvento = evento.getDocumentoEvento();
        List<DocumentoEvento> lista = documentoEventoRepository.findAll(Example.of(documentoEvento));
        if (lista.size() > 0)
            evento.setDocumentoEvento(lista.get(0));
        if (evento.getIdEvento() == 0) {

            evento = eventosRepository.save(evento);
            eventoInstitucion.setEventoI(evento);
        }

        institucion = eventoInstitucion.getInstitucion();
        institucion.setActivo("S");
        if (institucion.getIdInstitucion() == 0) {

            institucion = institucionesRepository.save(institucion);
            eventoInstitucion.setInstitucion(institucion);
        }

        eventoInstitucion.setActivo("S");
        eventoInstitucion = eventosInstitucionesRepository.save(eventoInstitucion);

        return new ResponseEntity<>(eventoInstitucion, HttpStatus.OK);

    }

    @PostMapping("/listareventos")
    public ResponseEntity<?> ListarEventos() {

        EventoInstitucion buscarEvento = new EventoInstitucion();
        buscarEvento.setActivo("S");

        return new ResponseEntity<>(eventosInstitucionesRepository.findAll(Example.of(buscarEvento),
                Sort.by(Sort.Direction.DESC, "idEventoInstitucion")), HttpStatus.OK);

    }

    @PostMapping("/buscarcargos")
    public ResponseEntity<?> BuscarCargos(@RequestBody Cargo cargo) {

        cargo.setActivo("S");

        return new ResponseEntity<>(cargoRepository.findByDescCargos(cargo.getDescCargo()), HttpStatus.OK);

    }

    @Transactional
    @PostMapping("/guardarparticipante")
    public ResponseEntity<?> GuardarParticipante(@RequestBody EventoParticipante eventoParticipante) {

        Participantes participante = new Participantes();
        participante = eventoParticipante.getParticipante();
        participante.setActivo("S");
        participante.setNombre(participante.getNombre().toUpperCase());
        participante.setPaterno(participante.getPaterno().toUpperCase());
        participante.setMaterno(participante.getMaterno().toUpperCase());
        // // if (participante.getIdParticipante() == 0) {
        // if (eventoParticipante.getAsistencia() == null) {
        // eventoParticipante.setAsistencia("N");
        // }
        participante = participanteRepository.save(participante);
        eventoParticipante.setParticipante(participante);
        // //}
        //
        Cargo cargo = new Cargo();
        cargo = eventoParticipante.getCargo();
        cargo.setActivo("S");
        //
        if (cargo.getIdCargo() == null) {
            cargo = cargoRepository.save(cargo);
            eventoParticipante.setCargo(cargo);
        }
        Instituciones institucion = new Instituciones();
        institucion = eventoParticipante.getInstitucion();
        institucion.setActivo("S");
        institucion.setDescInstitucion(institucion.getDescInstitucion().toUpperCase());
        if (institucion.getIdInstitucion() == null) {
            institucion = institucionesRepository.save(institucion);
            eventoParticipante.setInstitucion(institucion);
        }
        eventoParticipante.setActivo("S");
        if (eventoParticipante.getAsistencia() == null) {
            eventoParticipante.setAsistencia("N");
        }
        eventoParticipante = eventoParticipanteRepository.save(eventoParticipante);

        return new ResponseEntity<>(eventoParticipante, HttpStatus.OK);

    }

    @PostMapping("/listarparticipantes")
    public ResponseEntity<?> ListarParticipante(@RequestBody EventoParticipante evento) {
        evento.setActivo("S");
        return new ResponseEntity<>(eventoParticipanteRepository.findAll(Example.of(evento),
                Sort.by(Sort.Direction.DESC, "idEventoParticipante")), HttpStatus.OK);

    }

    @PostMapping("/confirmarasistencia")
    public ResponseEntity<?> ConfirmarAsistencia(@RequestBody EventoParticipante eventoParticipante) {

        System.out.println(eventoParticipante.getIdEventoParticipante());
        List<EventoParticipante> lista = eventoParticipanteRepository.findAll(Example.of(eventoParticipante));
        EventoParticipante eventoRegistrado = new EventoParticipante();
        if (lista.size() > 0) {
            eventoRegistrado = lista.get(0);
            System.out.println(eventoRegistrado.getIdEventoParticipante());
            eventoRegistrado.setAsistencia("E");
            eventoParticipanteRepository.save(eventoRegistrado);
        }

        return new ResponseEntity<>(eventoRegistrado, HttpStatus.OK);

    }

    @PostMapping("/confirmarsalida")
    public ResponseEntity<?> ConfirmarSalida(@RequestBody EventoParticipante eventoParticipante) {

        System.out.println(eventoParticipante.getIdEventoParticipante());
        eventoParticipanteRepository.findOne(Example.of(eventoParticipante));
        EventoParticipante eventoRegistrado = eventoParticipante;
        System.out.println(eventoRegistrado.getIdEventoParticipante());
        eventoRegistrado.setAsistencia("S");

        return new ResponseEntity<>(eventoParticipanteRepository.save(eventoRegistrado), HttpStatus.OK);

    }

    @PostMapping("/buscareventospub")
    public ResponseEntity<?> BuscarEventosPub() {
        Date date = new Date();
        // DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        Evento evento = new Evento();
        evento.setPublicar("S");
        List<Evento> listaEvento = eventosRepository.findAll(Example.of(evento));
        for (Evento even : listaEvento) {
            if (date.compareTo(even.getFechaEvento()) > 0) {
                even.setActivo("P");
            }

        }
        return new ResponseEntity<>(listaEvento, HttpStatus.OK);
    }

    @PostMapping("/buscarporcorreo")
    public ResponseEntity<?> BuscarPorCorreo(@RequestBody Participantes participante) {
        List<Participantes> listaParticipante = participanteRepository
                .findByCorreo(participante.getCorreoElectronico());

        return new ResponseEntity<>(listaParticipante, HttpStatus.OK);
    }

    @PostMapping("/buscarporcorreo2")
    public ResponseEntity<?> BuscarPorCorreo2(@RequestBody Participantes participante) {
        List<Participantes> listaParticipante = participanteRepository.findAll(Example.of(participante));
        return new ResponseEntity<>(listaParticipante, HttpStatus.OK);
    }

    @PostMapping("/enviarconstancia")
    public ResponseEntity<?> EnviarCorreo() throws UnsupportedEncodingException, MessagingException {
        Response response = new Response();

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        String subject = "Prueba de envio";
        String mailContent = "se envia prueba";

        helper.setFrom("413jan4ndr0.g0m3z@gmail.com","Alejandro");
        helper.setTo("alec1707@live.com.mx");
        helper.setSubject(subject);
        helper.setText(mailContent,true);

        mailSender.send(message);

        

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/buscardocumentos")
    public ResponseEntity<?> BuscarDocumentos() {
        DocumentoEvento documento = new DocumentoEvento();
        documento.setActivo("S");
        List<DocumentoEvento> listaDocumento = documentoEventoRepository.findAll(Example.of(documento));
        return new ResponseEntity<>(listaDocumento, HttpStatus.OK);
    }

    @PostMapping("/registrarparticipante")
    public void RegistrarParticipantesWebService(@RequestBody String json){

        System.out.println(json);

        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        JsonObject resultado = newJson.get("resultado").getAsJsonObject();
        Integer idEvento = newJson.get("idEvento").getAsInt();
        Evento ev = new Evento();
        ev.setIdEvento(idEvento);
        List<Evento> lista = eventosRepository.findAll(Example.of(ev));
        JsonArray listaPartipantes = resultado.get("participantes").getAsJsonArray();

        System.out.println("número de participantes =>"+listaPartipantes.size());

        for(JsonElement jo : listaPartipantes){
            JsonObject par = (JsonObject) jo;

            // SE BUSCA LA INSTITUCION A LA QUE PERTECENE
            Instituciones inst = new Instituciones();

            List<Instituciones> listaInst = institucionesRepository.findByDescInstituciones(par.get("dependencia").getAsString());
            if(listaInst.size()>0){
                inst = listaInst.get(0);
                System.out.println("Dependencia encontrada => "+listaInst.get(0).getDescInstitucion());

            }
            else{

               
                inst.setActivo("S");
                inst.setDescInstitucion(par.get("dependencia").getAsString());
                inst = institucionesRepository.save(inst);

                System.out.println("Se registra la dependencia => "+par.get("dependencia").getAsString() );
            }

           
            // SE LLENA EL PARTICIPANTE CON LOS DATOS
            Participantes p = new Participantes();

            p.setActivo("S");
            p.setNombre(par.get("nombre").getAsString());
            p.setPaterno(par.get("paterno").getAsString());
            p.setMaterno(par.get("materno").getAsString());

            p = participanteRepository.save(p);

            // SE PREPARA EL OBJETO EVENTO PARTICIPANTE PARA GUARDARLO

            EventoParticipante ep = new EventoParticipante();
            ep.setActivo("S");
            ep.setAsistencia(par.get("Asistencia").getAsString());
            ep.setEvento(lista.get(0));
            ep.setParticipante(p);
            ep.setInstitucion(inst);

            eventoParticipanteRepository.save(ep);
            

        }

    }
}
