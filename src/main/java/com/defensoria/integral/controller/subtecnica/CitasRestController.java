/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.subtecnica;

import com.defensoria.integral.model.subtecnica.Cita;
import com.defensoria.integral.model.subtecnica.ConfiguracionCita;
import com.defensoria.integral.model.subtecnica.CountHoras;
import com.defensoria.integral.model.subtecnica.Hora;
import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.repository.sigedepu2.RepresentadosRepository;
import com.defensoria.integral.repository.subtecnica.CitasRepository;
import com.defensoria.integral.repository.subtecnica.ConfiguracionCitaRepository;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alec1_000
 */
@RestController
public class CitasRestController {

    @Autowired
    CitasRepository citasRepository;
    @Autowired
    ConfiguracionCitaRepository configuracionCitaRepository;
    @Autowired
    RepresentadosRepository representadosRepository;

    @PostMapping("/listaCitas")
    public ResponseEntity<?> ListaChats(@RequestBody Cita c) throws ParseException {
        List<Hora> horarios = new ArrayList<>();
        if (DiasInhabiles(c.getFechaCita())) {
            // GENERAR LISTA DE HORAS DISPONIBLES
            horarios = (List<Hora>) GenerarHora(c.getFechaCita(), c.getCveRegion());
            // OBTENER LAS CITAS DEL DIA
            List<CountHoras> citas = FiltrarHorarios(c.getFechaCita(), c.getCveRegion());

            for (CountHoras cita : citas) {
                boolean encontrado = false;
                Hora horario = new Hora();

                for (Hora h : horarios) {

                    if (h.getHora().toString().equals(cita.getHoras().toString())) {
                        System.out.println("Horario generado: " + h.getHora() + " Hora de la cita " + cita.getHoras());
                        System.out.println("Horario numero Asesores: " + h.getNumAsesores() + " Numero de citas "
                                + cita.getConteo());
                        if (cita.getConteo() >= h.getNumAsesores()) {
                            encontrado = true;
                            horario = h;

                        }

                    }
                    if (encontrado) {
                        break;
                    }

                }
                if (encontrado) {
                    horarios.remove(horario);
                }

            }

        }

        return new ResponseEntity<>(horarios, HttpStatus.OK);

    }

    public Boolean DiasInhabiles(Date dia) {
        // BUSCAR DIA INHABIL EN CONFIGURACION CITA
        Boolean disponible = true;
        ConfiguracionCita cc = new ConfiguracionCita();
        cc.setFechaAplicacion(dia);
        cc.setActivo("S");
        cc.setNumAsesores(0);
        List<ConfiguracionCita> lista = configuracionCitaRepository.findAll(Example.of(cc));

        if (lista.size() == 0) {
            // VERIFICAR SI EL DIA ES SABADO O DOMINGO
            Calendar cal = Calendar.getInstance();
            cal.setTime(dia);
            Integer numeroDia = cal.get(Calendar.DAY_OF_WEEK);

            if (numeroDia == 1 || numeroDia == 7) {
                System.out.println("DIA NO DISPONIBLE POR SER SABADO O DOMINGO");
                disponible = false;
            }
        } else {
            System.out.println("DIA NO DISPONIBLE POR NO TENER ASESORES");
            disponible = false;
        }

        return disponible;

    }

    public Boolean HorarioDisponible(Date dia, Time hora, Integer cveRegion) {

        Boolean disponible = true;

        Cita c = new Cita();

        c.setFechaCita(dia);
        c.setHoraCita(hora);
        c.setCveRegion(cveRegion);

        List<Cita> lista = citasRepository.findAll(Example.of(c));
        if (lista.size() == 0) {
            disponible = true;
        } else {
            List<ConfiguracionCita> listaConfiguracion = configuracionCitaRepository
                    .findConfiguracionCitaByFechaAplicacionPorHora(dia, cveRegion, hora);
            if (listaConfiguracion.size() > 0) {
                System.out.println(listaConfiguracion.get(0).getIdConfiguracionCita());
            }
        }

        return disponible;

    }

    public List<CountHoras> FiltrarHorarios(Date dia, Integer cveRegion) {

        Boolean disponible = true;

        Cita c = new Cita();

        c.setFechaCita(dia);

        c.setCveRegion(cveRegion);

        List<CountHoras> lista = citasRepository.countTotalCitasHorasPorFecha(dia, cveRegion);

        return lista;

    }

    public List<?> GenerarHora(Date fecha, Integer cveRegion) throws ParseException {

        List<ConfiguracionCita> lista = configuracionCitaRepository.findConfiguracionCitaParaHoras(fecha, cveRegion);
        List<Hora> listaHoras = new ArrayList<>();
        Boolean soloUno = false;

       
        if (lista.size() > 0) {
            DateTime fechaRecibida = new DateTime(fecha);
            SimpleDateFormat sdformat = new SimpleDateFormat("dd-MM-yyyy");
            Date date1 = sdformat.parse(fechaRecibida.toString());
            Date date2 = sdformat.parse(lista.get(0).getFechaAplicacion().toString());
            if (date1.equals(date2)) {
                soloUno = true;
            }
            for (ConfiguracionCita cc2 : lista) {
                DateTime h1 = new DateTime(cc2.getHoraInicio());
                DateTime h2 = new DateTime(cc2.getHoraFinal());
                //
                Duration duration = new Duration(h1, h2);

                long mt = duration.getStandardMinutes();
                System.out.println("diferencia de minutos" + mt);
                Long nc = mt / cc2.getDuracionCita();
                System.out.println("duracion en minutos " + nc);
                int minutos = 0;
                int horasNum = 0;
                String minuto = "";
                String horaT = "";

                System.out.println("Horario disponible, generando");
                // disponible = true;
                for (int i = 0; i < nc; i++) {
                    horaT = "";
                    minutos = h1.getMinuteOfHour();
                    horasNum = h1.getHourOfDay();
                    if (minutos == 0) {
                        minuto = "00";
                    } else {
                        minuto = minutos + "";
                    }
                    if (horasNum < 10) {
                        horaT = "0" + horasNum;
                    } else {
                        horaT = horasNum + "";
                    }
                    System.out.println("hora de cita " + h1.getHourOfDay() + ":" + minuto + ":00");
                    String hora = horaT + ":" + minuto + ":00";

                    System.out.println("se agregara la hora => " + hora);
                    Hora hour = new Hora();
                    hour.setActivo("S");
                    hour.setNumAsesores(cc2.getNumAsesores());
                    hour.setHora(hora);

                    var buscar = false;

                    for (Hora horaadd : listaHoras) {
                        if (horaadd.getHora().toString().equals(hour.getHora().toString())) {
                            buscar = true;
                            break;

                        }

                    }
                    if (buscar == false) {
                        listaHoras.add(hour);
                    }

                    h1 = h1.plusMinutes(cc2.getDuracionCita());

                }

                if (soloUno)
                    break;

            }
        }

        return listaHoras;

    }

    @PostMapping("/guardarCita")
    public ResponseEntity<?> GuardarCita(@RequestBody Cita c) {
        // citasRepository.save(c);
        System.out.println(c.getFechaCita() + " " + c.getHoraCita() + c.getCveRegion());
        Boolean disponible = false;
        disponible = HorarioDisponible(c.getFechaCita(), c.getHoraCita(), c.getCveRegion());
        if (disponible) {
            citasRepository.save(c);
        }

        return new ResponseEntity<>(c, HttpStatus.OK);

    }

    @PostMapping("/actualizarCita")
    public ResponseEntity<?> ActualizarCita(@RequestBody Cita c) {
        Cita c1 = new Cita();
        c1.setIdCita(c.getIdCita());
        List<Cita> listaCitas = citasRepository.findAll(Example.of(c1));
        if (listaCitas.size() > 0) {
            c1 = listaCitas.get(0);
        }
        c1.setEstatus(c.getEstatus());
        c1.setRepresentado(c.getRepresentado());
        citasRepository.save(c1);

        return new ResponseEntity<>(c1, HttpStatus.OK);

    }

    @PostMapping("/guardarRepresentadoCita")
    public ResponseEntity<?> GuardarRepresentadoCita(@RequestBody Representados r) {
        r.setActivo("S");

        Representados r2 = new Representados();
        r2.setCurp(r.getCurp());
        List<Representados> list = representadosRepository.findAll(Example.of(r2));
        System.out.println("Encontrados " + list.size());
        if (list.size() == 0) {
            representadosRepository.save(r);
        } else {

            Boolean encontrado = false;
            for (Representados r3 : list) {
                Cita c = new Cita();
                c.setRepresentado(r3);
                c.setEstatus("G");

                List<Cita> listaCitas = citasRepository.findAll(Example.of(c), Sort.by(Direction.DESC, "idCita"));
                if (listaCitas.size() > 0)
                    c = listaCitas.get(0);
                System.out.println("representado" + r3.getIdRepresentado() + "cita=" + c.getIdCita());
                if (c.getIdCita() != null) {
                    r.setIdRepresentado(c.getIdCita());
                    r.setActivo("R");
                    return new ResponseEntity<>(r, HttpStatus.OK);
                }
            }
            representadosRepository.save(r);

        }

        return new ResponseEntity<>(r, HttpStatus.OK);

    }

    @PostMapping("/buscarCitasActivas")
    public ResponseEntity<?> BuscarCitasActivas(@RequestBody Cita c) throws ParseException {
        if (c.getFechaCita() == null) {
            System.out.println("obtenet fecha");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            // PRIMERA FECHA
            Date date = new Date();
            String dateString = dateFormat.format(date);
            date = dateFormat.parse(dateString);
            System.out.println("la fecha de hoy es: " + date.toString());
            c.setFechaCita(date);
        }
        List<Cita> listaCitas = citasRepository.findAll(Example.of(c));

        return new ResponseEntity<>(listaCitas, HttpStatus.OK);

    }

    @PostMapping("/buscarrepresentado")
    public ResponseEntity<?> BuscarRepresentados(@RequestBody Cita c) throws ParseException {

        List<Cita> listaCitas = citasRepository.listaRepresentadosCitas(c.getRepresentado().getNombre());

        return new ResponseEntity<>(listaCitas, HttpStatus.OK);

    }

}
