/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.sigedepu;

import com.defensoria.integral.model.adminpersonal.GeneryObjectCount;

import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;
import com.defensoria.integral.model.sigedepu2.Conclusion;
import com.defensoria.integral.model.sigedepu2.TiposAudiencias;
import com.defensoria.integral.model.sigedepu2.TiposConclusiones;
import com.defensoria.integral.repository.sigedepu2.AudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.ConclusionesRepository;
import com.defensoria.integral.repository.sigedepu2.TiposAudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposConclusionesRepository;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Alejandro
 */
@RestController
public class ConsultasVitralRestController {

    @Autowired
    CarpetasRepository carpetasRepository;
    @Autowired
    AudienciasRepository audienciasRepository;
    @Autowired
    ConclusionesRepository conclusionesRepository;
    @Autowired
    TiposAudienciasRepository tiposAudienciasRepository;
    @Autowired
    TiposConclusionesRepository tiposConcluionesRepository;

    @PostMapping("carpetas")
    public ResponseEntity<?> ListaCarpetasInvestigacion(@RequestBody String json) throws ParseException {

        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<CarpetaRepresentado> Lista = carpetasRepository.countCarpetas(fechaInicio, fechaFinal, idTipoCarpeta,
                cveDistrito);
        cantidad = Lista.size();
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }


    @PostMapping("carpetasEdomex")
    public ResponseEntity<?> ListaCarpetasInvestigacionEdomex(@RequestBody String json) throws ParseException {

        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<CarpetaRepresentado> Lista = carpetasRepository.countCarpetasEdomex(fechaInicio, fechaFinal, idTipoCarpeta);
        cantidad = Lista.size();
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @PostMapping("carpetaspormes")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMEs(@RequestBody String json) throws ParseException {
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMes2(fechaInicio, fechaFinal,
                idTipoCarpeta, cveDistrito);
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("mes", denun.getMes());
                conteo.put("total", denun.getTotal());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("carpetaspormesEdomex")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMesEdomex(@RequestBody String json) throws ParseException {
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
       // Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

       

                List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMesEdomex(fechaInicio, fechaFinal,
                idTipoCarpeta);                
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("mes", denun.getMes());
                conteo.put("total", denun.getTotal());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("carpetaspormescondelito")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMesConDelito(@RequestBody String json) throws ParseException {
        System.out.println("carpetaspormescondelito");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMesConDelito(fechaInicio, fechaFinal,
                idTipoCarpeta, cveMunicipio);
        System.out.println("total de registros encontrados: " + listas.size());
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("delito", denun.getDelito());
                conteo.put("total", denun.getTotal());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("carpetaspormespormunicipio")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMesPorMunicipio(@RequestBody String json)
            throws ParseException {
        System.out.println("carpetaspormespormunicipo");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMesPorMunicipio(fechaInicio,
                fechaFinal, idTipoCarpeta, cveDistrito);
        System.out.println("total de registros encontrados: " + listas.size());
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("total", denun.getTotal());
                conteo.put("mes", denun.getMes());
                conteo.put("municipio", denun.getNombre());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("carpetaspormespormunicipiopordelito")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMesPorMunicipioPorDelito(@RequestBody String json)
            throws ParseException {
        System.out.println("carpetaspormespormunicipiopordelito");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();

        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0;
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMesPorMunicipioPorDelito(fechaInicio,
                fechaFinal, idTipoCarpeta, cveMunicipio);
        System.out.println("total de registros encontrados: " + listas.size());
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("total", denun.getTotal());
                conteo.put("mes", denun.getMes());
                conteo.put("delito", denun.getDelito());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("carpetaspormescondelito2")
    public ResponseEntity<?> ListaCarpetasInvestigacionPorMesConDelito2(@RequestBody String json)
            throws ParseException {
        System.out.println("carpetaspormescondelito");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer idTipoCarpeta = 0;
        Integer cantidad = 0; 
        idTipoCarpeta = 4;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<GeneryObjectCount> listas = carpetasRepository.countCarpetasVitralPorMesConDelito(fechaInicio, fechaFinal,
                idTipoCarpeta, cveMunicipio);
        System.out.println("total de registros encontrados: " + listas.size());
        if (listas.size() > 0) {
            for (GeneryObjectCount denun : listas) {
                Map<String, Object> conteo = new HashMap<>();

                conteo.put("delito", denun.getDelito());
                conteo.put("total", denun.getTotal());
                conteo.put("mes", denun.getMes());
                conteo.put("subEtapa", "");
                conteo.put("numeroMes", denun.getNumeroMes());
                lista.add(conteo);
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("audiencias")
    public ResponseEntity<?> ListaAudiencias(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("AUDIENCIAS");
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            List<Conclusion> lista = audienciasRepository.listaAudienciasVitralIn(fechaInicio, fechaFinal, claves,
                    cveDistrito);
            cantidad = lista.size();
        }
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @PostMapping("audienciasEdomex")
    public ResponseEntity<?> ListaAudienciasEdomex(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("AUDIENCIAS");
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        //Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            List<Conclusion> lista = audienciasRepository.listaAudienciasVitralInEdomex(fechaInicio, fechaFinal, claves);
            cantidad = lista.size();
        }
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @PostMapping("audienciaspormes")
    public ResponseEntity<?> ListaAudienciasPorMes(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("listaAudienciasVitralInPorMes fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal + " cveDistrito=>" + cveDistrito);

            List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMes(fechaInicio, fechaFinal,
                    claves, cveDistrito);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("mes", denun.getMes());
                    conteo.put("total", denun.getTotal());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);   

    }
    @PostMapping("audienciaspormesEdomex")
    public ResponseEntity<?> ListaAudienciasPorMesEdomex(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
       //Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("listaAudienciasVitralInPorMes fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal);

          
                    List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMesEdomex(fechaInicio, fechaFinal,
                    claves);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("mes", denun.getMes());
                    conteo.put("total", denun.getTotal());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("audienciaspormescondelito")
    public ResponseEntity<?> ListaAudienciasPorMesConDelito(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer mes = newJson.get("mes").getAsInt();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("listaAudienciasVitralInPorMesConDelito fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal + " cveMunicipio=>" + cveMunicipio);
            List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMesPorDelito(fechaInicio,
                    fechaFinal, claves, cveMunicipio);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("audienciaspormespormunicipio")
    public ResponseEntity<?> ListaAudienciasPorMesPorMunicipio(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("audienciaspormespormunicipio");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer mes = newJson.get("mes").getAsInt();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("audienciaspormespormunicipio fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal + " cveDistrito=>" + cveDistrito);
            List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMesPorMunicipio(fechaInicio,
                    fechaFinal, claves, cveDistrito);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("municipio", denun.getNombre());
                    conteo.put("numeroMes", denun.getNumeroMes());

                    conteo.put("subEtapa", "");
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("audienciaspormespordelito")
    public ResponseEntity<?> ListaAudienciasPorMesPorDelito(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("audienciaspormespormunicipio");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer mes = newJson.get("mes").getAsInt();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("audienciaspormespormunicipio fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal + " cveMunicipio=>" + cveMunicipio);
            List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMesPorDelito(fechaInicio,
                    fechaFinal, claves, cveMunicipio);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("municipio", denun.getNombre());
                    conteo.put("numeroMes", denun.getNumeroMes());

                    conteo.put("subEtapa", "");
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("audienciaspormescondelito2")
    public ResponseEntity<?> ListaAudienciasPorMesConDelito2(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        // Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer mes = newJson.get("mes").getAsInt();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");

        List<Integer> claves = this.listaClavesAudiencias(operacion);
        if (claves.size() > 0) {
            System.out.println(claves.toString());
            System.out.println("listaAudienciasVitralInPorMesConDelito fechaInicio=>" + fechaInicio + " FechaFinal =>"
                    + fechaFinal + " cveMunicipio=>" + cveMunicipio);
            List<GeneryObjectCount> listas = audienciasRepository.listaAudienciasVitralInPorMesConDelito(fechaInicio,
                    fechaFinal, claves, cveMunicipio);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("numeroMes", denun.getNumeroMes());

                    conteo.put("subEtapa", "");
                    lista.add(conteo);
                }
            }
        }
        // String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("conclusionespormespormunicipio")
    public ResponseEntity<?> ListaConclusionesPorMunicipio(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("conclusionespormespormunicipio");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {

            List<GeneryObjectCount> listas = conclusionesRepository
                    .listaConclusionesVitralInPorMesPorMunicipio(fechaInicio, fechaFinal, claves, cveDistrito);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("municipio", denun.getNombre());
                    conteo.put("numeroMes", denun.getNumeroMes());

                    conteo.put("subEtapa", "");
                    lista.add(conteo);
                }
            }

        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("conclusiones")
    public ResponseEntity<?> ListaConclusiones(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<Conclusion> lista = conclusionesRepository.listaConclusionesVitralIn(fechaInicio, fechaFinal, claves,
                    cveDistrito);
            cantidad = lista.size();
        }
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @PostMapping("conclusionesEdomex")
    public ResponseEntity<?> ListaConclusionesEdomex(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
       // Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<Conclusion> lista = conclusionesRepository.listaConclusionesVitralInEdomex(fechaInicio, fechaFinal, claves);
            cantidad = lista.size();
        }
        String resultado = "{'cantidad':'" + cantidad + "'}";
        return new ResponseEntity<>(resultado, HttpStatus.OK);

    }

    @PostMapping("conclusionespormes")
    public ResponseEntity<?> ListaConclusionesPorMes(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<GeneryObjectCount> listas = conclusionesRepository.listaConclusionesVitralInPorMes(fechaInicio,
                    fechaFinal, claves, cveDistrito);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("mes", denun.getMes());
                    conteo.put("total", denun.getTotal());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }


    @PostMapping("conclusionespormesEdomex")
    public ResponseEntity<?> ListaConclusionesPorMesEdomex(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
      //  Integer cveDistrito = newJson.get("cveDistrito").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-01-01 00:00:00");
        fechaFinal = dateFormat.parse(anio + "-12-31 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<GeneryObjectCount> listas = conclusionesRepository.listaConclusionesVitralInPorMesEdomex(fechaInicio,
                    fechaFinal, claves);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("mes", denun.getMes());
                    conteo.put("total", denun.getTotal());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }
    

    @PostMapping("conclusionespormescondelito")
    public ResponseEntity<?> ListaConclusionesPorMesConDelito(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("conclusionespormescondelito");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<GeneryObjectCount> listas = conclusionesRepository
                    .listaConclusionesVitralInPorMesConDelito(fechaInicio, fechaFinal, claves, cveMunicipio);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    @PostMapping("conclusionespormescondelito2")
    public ResponseEntity<?> ListaConclusionesPorMesConDelito2(@RequestBody String json) throws ParseException {
        // DESCARGAR INFORMACION
        System.out.println("conclusionespormescondelito2");
        List<Map<String, Object>> lista = new ArrayList<>();
        JsonObject newJson = new JsonParser().parse(json).getAsJsonObject();
        Integer cveMunicipio = newJson.get("cveMunicipio").getAsInt();
        Integer anio = newJson.get("anio").getAsInt();
        Integer mes = newJson.get("mes").getAsInt();
        String operacion = newJson.get("operacion").getAsString();
        Integer cantidad = 0;

        // PREPARO LOS CAMPOS DE FECHA
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date fechaInicio = new Date();
        Date fechaFinal = new Date();
        fechaInicio = dateFormat.parse(anio + "-" + mes + "-01 00:00:00");
        if (mes == 2)
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-28 23:59:59");
        else
            fechaFinal = dateFormat.parse(anio + "-" + mes + "-"+obtenerUltimoDia(mes)+" 23:59:59");
        List<Integer> claves = this.listaClavesTiposConclusiones(operacion);
        if (claves.size() > 0) {
            List<GeneryObjectCount> listas = conclusionesRepository
                    .listaConclusionesVitralInPorMesConDelito(fechaInicio, fechaFinal, claves, cveMunicipio);
            if (listas.size() > 0) {
                for (GeneryObjectCount denun : listas) {
                    Map<String, Object> conteo = new HashMap<>();

                    conteo.put("delito", denun.getDelito());
                    conteo.put("total", denun.getTotal());
                    conteo.put("mes", denun.getMes());
                    conteo.put("subEtapa", "");
                    conteo.put("numeroMes", denun.getNumeroMes());
                    lista.add(conteo);
                }
            }
        }

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }

    public List<Integer> listaClavesAudiencias(String audiencias) {
        String[] titulos = audiencias.split("\\|");
        List<Integer> claves = new ArrayList<>();
        if (titulos.length > 0) {
            for (String audiencia : titulos) {
                System.out.println(audiencia);
                List<TiposAudiencias> lista = tiposAudienciasRepository
                        .findByDescTipoAudienciaLikeAndActivoEquals(audiencia, "S");
                if (lista.size() > 0) {
                    for (TiposAudiencias ta : lista) {
                        claves.add(ta.getCveTipoAudiencia());
                    }
                }

            }
        } else {
            List<TiposAudiencias> lista = tiposAudienciasRepository
                    .findByDescTipoAudienciaLikeAndActivoEquals(audiencias, "S");
            if (lista.size() > 0) {
                for (TiposAudiencias ta : lista) {
                    claves.add(ta.getCveTipoAudiencia());
                }
            }

        }
        return claves;

    }

    public List<Integer> listaClavesTiposConclusiones(String conclusiones) {
        String[] titulos = conclusiones.split("\\|");
        List<Integer> claves = new ArrayList<>();
        if (titulos.length > 0) {
            for (String conclusion : titulos) {

                List<TiposConclusiones> lista = tiposConcluionesRepository
                        .findByDescTipoConclusionesLikeAndActivoEquals(conclusion, "S");
                if (lista.size() > 0) {
                    for (TiposConclusiones ta : lista) {
                        claves.add(ta.getCveTipoConclusion());
                    }
                }

            }
        } else {
            List<TiposConclusiones> lista = tiposConcluionesRepository
                    .findByDescTipoConclusionesLikeAndActivoEquals(conclusiones, "S");
            if (lista.size() > 0) {
                for (TiposConclusiones ta : lista) {
                    claves.add(ta.getCveTipoConclusion());
                }
            }

        }
        return claves;

    }

    public Integer obtenerUltimoDia(Integer mes) {
        Integer dia = 30;
        if (mes == 1) {
            dia = 31;
        } else if (mes == 2) {
            dia = 31;
        }
        else if (mes == 3) {
            dia = 31;
        }
        else if (mes == 4) {
            dia = 30;
        }
        else if (mes == 5) {
            dia = 31;
        }
        else if (mes == 6) {
            dia = 30;
        }
        else if (mes == 7) {
            dia = 31;
        }
        else if (mes == 8) {
            dia = 31;
        }
        else if (mes == 9) {
            dia = 30;
        }
        else if (mes == 10) {
            dia = 31;
        }
        else if (mes == 11) {
            dia = 30;
        }else if (mes == 12) {
            dia = 31;
        }


        return dia;
    }

}
