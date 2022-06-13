/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.subtecnica;

import com.defensoria.integral.model.subtecnica.AplicacionEncuesta;
import com.defensoria.integral.model.subtecnica.Encuesta;
import com.defensoria.integral.model.subtecnica.Pregunta;
import com.defensoria.integral.model.subtecnica.PreguntaRespuesta;
import com.defensoria.integral.model.subtecnica.Respuesta;
import com.defensoria.integral.model.subtecnica.TipoRespuesta;
import com.defensoria.integral.repository.subtecnica.AplicacionEncuestasRepository;
import com.defensoria.integral.repository.subtecnica.EncuestasRepository;
import com.defensoria.integral.repository.subtecnica.PreguntasRepository;
import com.defensoria.integral.repository.subtecnica.PreguntasRespuestasRepository;
import com.defensoria.integral.repository.subtecnica.RespuestasRepository;
import com.defensoria.integral.repository.subtecnica.TiposRespuestaRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alejandro.gomez
 */
@RestController
public class EncuestasRestController {

    @Autowired
    EncuestasRepository encuestasRepository;

    @Autowired
    PreguntasRespuestasRepository preguntasRespuestaRepository;

    @Autowired
    RespuestasRepository respuestasRepository;

    @Autowired
    PreguntasRepository preguntasRepository;

    @Autowired
    TiposRespuestaRepository tiposRespuestaRepository;
    @Autowired
    AplicacionEncuestasRepository aplicacionEncuestasRepository;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/listaencuestas")
    public ResponseEntity<?> ListaEncuestas(@RequestBody Encuesta encuesta) {

        System.out.println(encuesta.getIdEncuesta());

        return new ResponseEntity<>(encuestasRepository.findAll(Example.of(encuesta)), HttpStatus.OK);

    }

    @PostMapping("/listatiposrespuestas")

    public ResponseEntity<?> ListaTiposPreguntas(@RequestBody TipoRespuesta tipoRespuesta) {

        return new ResponseEntity<>(tiposRespuestaRepository.findAll(Example.of(tipoRespuesta)), HttpStatus.OK);

    }

    @PostMapping("/guardarencuesta")
    public ResponseEntity<?> GuardarEncuesta(@RequestBody Encuesta encuesta) {

        encuestasRepository.save(encuesta);
        return new ResponseEntity<>(encuesta, HttpStatus.OK);

    }

    @PostMapping("/guardarpregunta")
    public ResponseEntity<?> GuardarPregunta(@RequestBody Pregunta pregunta) {

        preguntasRepository.save(pregunta);
        return new ResponseEntity<>(pregunta, HttpStatus.OK);

    }

    @PostMapping("/eliminarpregunta")
    public ResponseEntity<?> EliminarPregunta(@RequestBody Pregunta pregunta) {

        List<Pregunta> listaPreguntas = preguntasRepository.findAll(Example.of(pregunta));
        listaPreguntas.get(0).setActivo("N");

        preguntasRepository.save(listaPreguntas.get(0));
        return new ResponseEntity<>(pregunta, HttpStatus.OK);

    }

    @PostMapping("/listapreguntas")
    public ResponseEntity<?> ListaPreguntas(@RequestBody Pregunta pregunta) {

        System.out.println("IdEncuesta recibido" + pregunta.getEncuesta());

        return new ResponseEntity<>(preguntasRepository.findAll(Example.of(pregunta)), HttpStatus.OK);

    }

    @PostMapping("/guardarrespuesta")
    public ResponseEntity<?> GuardarRespuesta(@RequestBody Respuesta respuesta) {

        if (respuesta.getPadre() != null) {
            Respuesta padre = new Respuesta();
            respuesta.setActivo("S");
            padre.setIdRespuesta(respuesta.getPadre().getIdRespuesta());
            respuestasRepository.findOne(Example.of(padre));
            respuesta.setPadre(padre);
            respuestasRepository.save(respuesta);

        } else {
            respuestasRepository.save(respuesta);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @PostMapping("/guardaraplicacion")
    public ResponseEntity<?> GuardaAplicacion(@RequestBody AplicacionEncuesta apencuesta) {

        Encuesta encuesta = new Encuesta();
        encuesta.setIdEncuesta(apencuesta.getEncuesta().getIdEncuesta());

        List<Encuesta> lista = encuestasRepository.findAll(Example.of(encuesta));
        if (lista.size() > 0)
            encuesta = lista.get(0);
        Integer cveUsuario = null;
        System.out.println("estatus de la encuesta" + encuesta.getPublicar());
        // if (!encuesta.getPublicar().equals("I") &&
        // !encuesta.getPublicar().equals("N"))
        if (getHttpSession().getAttribute("cveUsuario") != null)
            cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());

        apencuesta.setActivo("S");
        apencuesta.setCveUsuario(cveUsuario);

        aplicacionEncuestasRepository.save(apencuesta);

        return new ResponseEntity<>(apencuesta, HttpStatus.OK);

    }

    @PostMapping("/guardarrespuestasencuesta")
    public ResponseEntity<?> GuardarRespuestasEncuesta(@RequestBody List<PreguntaRespuesta> listaPreguntaRespuetas) {

        for (PreguntaRespuesta pre : listaPreguntaRespuetas) {
            System.out.println("idaplicacionEncuesta" + pre.getPregunta().getIdPregunta());
            pre.setActivo("S");
            preguntasRespuestaRepository.save(pre);
        }

        return new ResponseEntity<>(listaPreguntaRespuetas.get(0), HttpStatus.OK);

    }

    @PostMapping("/graficapreguntas")
    public ResponseEntity<?> GraficaPreguntas(@RequestBody AplicacionEncuesta ae) {


        /*DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");
*/

System.out.println(ae.getFechaActualizacion());

        return new ResponseEntity<>(
                aplicacionEncuestasRepository.graficaPreguntas(ae.getEncuesta().getIdEncuesta(), ae.getFechaRegistro(),
                        ae.getFechaActualizacion()),
                HttpStatus.OK);

        // return new ResponseEntity
        // <>(aplicacionEncuestasRepository.findAll(Example.of(ae)), HttpStatus.OK);

    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
