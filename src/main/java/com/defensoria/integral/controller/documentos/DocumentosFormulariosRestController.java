/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.documentos;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Formularios;
import com.defensoria.integral.model.adminpersonal.Municipios;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.documentos.DocumentoFormulario;
import com.defensoria.integral.model.documentos.UploadForm;
import com.defensoria.integral.model.informes.AccionRepresentado;
import com.defensoria.integral.model.informes.AsesoriaInf;
import com.defensoria.integral.model.informes.AsesoriaInforme;
import com.defensoria.integral.model.informes.CarpetaInforme;
import com.defensoria.integral.model.informes.Expediente;
import com.defensoria.integral.model.informes.Informe;
import com.defensoria.integral.model.informes.VisitaRepresentado;
import com.defensoria.integral.model.informes.Visitacprs;
import com.defensoria.integral.model.sigedepu2.Accion;

import com.defensoria.integral.model.sigedepu2.Audiencias;
import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;
import com.defensoria.integral.model.sigedepu2.Conclusion;
import com.defensoria.integral.model.sigedepu2.Delitos;
import com.defensoria.integral.model.sigedepu2.DelitosRepresentados;
import com.defensoria.integral.model.sigedepu2.Etnia;
import com.defensoria.integral.model.sigedepu2.GruposVulnerables;

import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.model.sigedepu2.TiposAudiencias;
import com.defensoria.integral.model.sigedepu2.TiposCarpetas;
import com.defensoria.integral.model.sigedepu2.TiposConclusiones;

import com.defensoria.integral.model.subtecnica.Materia;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.documentos.DocumentosFormulariosRepository;
import com.defensoria.integral.repository.informes.AccionesRepresentadosRepository;
import com.defensoria.integral.repository.informes.AsesoriaInfRepository;
import com.defensoria.integral.repository.informes.AsesoriasInformesRepository;
import com.defensoria.integral.repository.informes.CarpetasInformesRepository;
import com.defensoria.integral.repository.informes.ExpedientesRepository;
import com.defensoria.integral.repository.informes.InformesRepository;
import com.defensoria.integral.repository.informes.VisitacprsRepository;
import com.defensoria.integral.repository.informes.VisitasRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.AccionesRepository;
import com.defensoria.integral.repository.sigedepu2.AudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.ConclusionesRepository;
import com.defensoria.integral.repository.sigedepu2.DelitosRepository;
import com.defensoria.integral.repository.sigedepu2.DelitosRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.EtniasRepository;
import com.defensoria.integral.repository.sigedepu2.GruposVulnerablesRepository;
import com.defensoria.integral.repository.sigedepu2.MateriasRepository;
import com.defensoria.integral.repository.sigedepu2.MunicipiosRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.TipoCarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposAudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposConclusionesRepository;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ibm.icu.text.SimpleDateFormat;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Alejandro
 */
@RestController
public class DocumentosFormulariosRestController {
    @Autowired
    DocumentosFormulariosRepository documentosFormulariosRepository;
    @Autowired
    InformesRepository informesRepository;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    CarpetasRepository carpetasRepository;
    @Autowired
    RepresentadosRepository representadosRepository;
    @Autowired
    MunicipiosRepository municipiosRepository;
    @Autowired
    GruposVulnerablesRepository gruposVulnerablesRepository;
    @Autowired
    EtniasRepository etniasRepository;
    @Autowired
    AudienciasRepository audienciasRepository;
    @Autowired
    ConclusionesRepository conclusionesRepository;
    @Autowired
    TiposConclusionesRepository tiposConclusionesRepository;
    @Autowired
    TiposAudienciasRepository tiposAudienciasRepository;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    CarpetasRepresentadosRepository carpetasRepresentadosRepository;
    @Autowired
    CarpetasInformesRepository carpetasInformesRepository;
    @Autowired
    DelitosRepository delitosRepository;
    @Autowired
    DelitosRepresentadosRepository delitosRepresentadosRepository;
    @Autowired
    MateriasRepository materiasRepository;
    @Autowired
    AsesoriasInformesRepository asesoriasInformesRepository;
    @Autowired
    AdscripcionesRepository adscripcionesRepository;
    @Autowired
    AccionesRepository accionesRepository;
    @Autowired
    AccionesRepresentadosRepository accionesRepresentadosRepository;
    @Autowired
    TipoCarpetasRepository tipoCarpetasRepository;
    @Autowired
    VisitasRepresentadosRepository visitasRepresentadosRepository;
    @Autowired
    ExpedientesRepository expedientesRepository;
    @Autowired
    AsesoriaInfRepository asesoriaInfRepository;
    @Autowired
    VisitacprsRepository visitacprsRepository;

    @PostMapping("listadocumentosformularios")
    public ResponseEntity<?> ListaDocumentosFormularios(Formularios f) {

        DocumentoFormulario df = new DocumentoFormulario();
        df.setFormulario(f);
        List<DocumentoFormulario> lista = documentosFormulariosRepository.findAll(Example.of(df),
                Sort.by(Direction.ASC, "orden"));

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/rest/uploadMultiFiles")
    public ResponseEntity<?> multiUploadFileModel(@ModelAttribute UploadForm form) {

        Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        Map<String, String> respuesta = new HashMap<>();

        String result = null;
        try {
            Personal p = new Personal();
            Adscripciones ads = new Adscripciones();
            Integer cveMes = form.getNumMes();
            Integer anio = form.getAnio();
            ads.setCveAdscripcion(form.getCveAdscripcion());
            p.setCveUsuario(cveUsuario);
            Informe inf = new Informe();
            inf.setPersonal(p);
            inf.setAdscripcion(ads);
            inf.setCveMes(cveMes);
            inf.setAnio(anio);
            inf.setEstatusInforme("C");
            inf.setActivo("S");
            List<Informe> lista = informesRepository.findAll(Example.of(inf));
            if (lista.size() == 0) {
                informesRepository.save(inf);
                result = this.saveUploadedFiles(form.getFiles(), inf);
                respuesta = new HashMap<>();
                respuesta.put("mensaje", "Se subio el archivo correctamente");
                respuesta.put("estatus", "OK");

            } else {
                respuesta = new HashMap<>();
                respuesta.put("mensaje", "El archivo ya existe");
                respuesta.put("estatus", "EX");
            }

        }
        // Here Catch IOException only.
        // Other Exceptions catch by RestGlobalExceptionHandler class.
        catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error: " + e.getMessage(), HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    @PostMapping("listapendientes")
    public ResponseEntity<?> ListaPendientes() throws ParseException {
        String mensaje = "";
        Informe i = new Informe();
        i.setEstatusInforme("C");
        i.setActivo("S");
         i.setCveMes(6);
        i.setAnio(2022);
        List<Informe> lista = informesRepository.findAll(Example.of(i));
        System.out.println("total de pendientes: " + lista.size());
        if (lista.size() > 0)
            for (Informe inf : lista) {
                String ruta = inf.getRutaArchivo() + "";

                String[] archivo = new String[] { inf.getRutaArchivo(),
                        inf.getAdscripcion().getMunicipio().getCveDistrito2() + "",
                        inf.getAdscripcion().getMunicipio().getCveRegion() + "" };

                Integer tam = archivo.length;
                // procesar archivos por python
                mensaje = this.WebServicePython(archivo);

                JsonObject estatus = new JsonParser().parse(mensaje).getAsJsonObject();
                String estatusInforme = estatus.get("estatus").getAsString();
                System.out.println(ruta);
                if (estatusInforme.equals("E")) {
                    inf.setEstatusInforme(estatusInforme);
                    informesRepository.save(inf);
                } else if (estatusInforme.equals("V")) {

                    String validacion = estatus.get("validacion").getAsString();
                    System.out.println(validacion);

                    // BUSCAR QUE VAMOS A REGISTRAR
                    JsonObject resultado = estatus.get("resultado").getAsJsonObject();
                    Integer tipoExpediente = resultado.get("tipoExpediente").getAsInt();

                    System.out.println("Tipo de Expediente " + tipoExpediente);
                    
                    if (tipoExpediente == 1) {
                        RegistrarCarpeta(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }
                    if (tipoExpediente == 2) {
                        RegistrarPatrocinios(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }

                    if (tipoExpediente == 3) {
                        RegistrarControl(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                        RegistrarVisitas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());

                    }

                    if (tipoExpediente == 4)
                        RegistrarAdministrativas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());

                    if (tipoExpediente == 5) {
                        RegistrarSentencias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                        RegistrarVisitas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }

                    if (tipoExpediente == 6) {
                        RegistrarMpAdolescentes(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }
                    if (tipoExpediente == 7) {
                        RegistrarSalas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }
                    if (tipoExpediente == 8) {
                        RegistrarPrimera(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                        RegistrarVisitas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }

                    if (tipoExpediente == 9) {
                        RegistrarAdolescentes(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                        RegistrarVisitas(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }
                    if (tipoExpediente == 10) {
                        RegistrarSalasAdolescentes(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),
                                inf.getAdscripcion().getCveAdscripcion(),inf.getPersonal().getCveUsuario());
                        RegistrarAsesorias(resultado, tipoExpediente, inf.getCveMes(), inf.getAnio(),inf.getPersonal().getCveUsuario());
                    }

                    // BuscarTipoInforme(estatus, validacion, inf);
                    inf.setEstatusInforme(estatusInforme);
                    informesRepository.save(inf);

                }

            }
        Map<String, Object> respuesta = new HashMap<>();
        return new ResponseEntity<>(respuesta, HttpStatus.OK);

    }

    public void RegistrarVisitas(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveUsuario) {
        JsonArray carpetas = json.get("visitas").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numero_carpeta = "";
            String ano_carpeta = "";
            String tipo_carpeta = "";

            String nombre_representado = "";
            String paterno_representado = ""; 
            String materno_representado = "";
            String sexo = "";
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;

            if (carpeta.has("numero_carpeta/causa")) {
                numero_carpeta = carpeta.get("numero_carpeta/causa").getAsString();
            }
            if (carpeta.has("ano_carpeta/causa")) {
                ano_carpeta = carpeta.get("ano_carpeta/causa").getAsString();
            }
            if (carpeta.has("tipo_carpeta")) {
                tipo_carpeta = carpeta.get("tipo_carpeta").getAsString();
            }

            if (carpeta.has("nombre_representado")) {
                nombre_representado = carpeta.get("nombre_representado").getAsString();
            }
            if (carpeta.has("paterno_representado")) {
                paterno_representado = carpeta.get("paterno_representado").getAsString();
            }
            if (carpeta.has("materno_representado")) {
                materno_representado = carpeta.get("materno_representado").getAsString();
            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();
            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();
            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();
            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();
            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();
            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();
            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();
            }

            if (!numero_carpeta.equals("None") && !ano_carpeta.equals("None") && !nombre_representado.equals("None")
                    && !paterno_representado.equals("None")
                    && !materno_representado.equals("None")) {

                Visitacprs asInf = new Visitacprs();
                asInf.setNumero_carpeta(numero_carpeta);
                asInf.setAno_carpeta(ano_carpeta);
                asInf.setTipo_carpeta(tipo_carpeta);
                asInf.setNombre_representado(nombre_representado);
                asInf.setPaterno_representado(paterno_representado);
                asInf.setMaterno_representado(materno_representado);
                asInf.setSexo(sexo);
                asInf.setEdad(edad);
                asInf.setGrupo_vulnerable(grupo_vulnerable);
                asInf.setEtnia(etnia);
                asInf.setDiscapacidad(discapacidad);
                asInf.setCveDistrito(cveDistrito);
                asInf.setCveRegion(cveRegion);
                asInf.setTipoExpediente(tipoExpediente);
                asInf.setCveMes(cveMes);
                asInf.setAnio(anio);
                asInf.setCveUsuario(cveUsuario);

                visitacprsRepository.save(asInf);
            }

        }

    }

    public void RegistrarAsesorias(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio,Integer cveUsuario) {
        JsonArray carpetas = json.get("asesorias").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String materia = "";
            String nombre_juzgado = "";
            String nombre_representado = "";
            String paterno_representado = "";
            String materno_representado = "";
            String sexo = "";
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;

            if (carpeta.has("materia")) {
                materia = carpeta.get("materia").getAsString();
            }
            if (carpeta.has("nombre_juzgado")) {
                nombre_juzgado = carpeta.get("nombre_juzgado").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre_representado = carpeta.get("nombre_representado").getAsString();
            }
            if (carpeta.has("paterno_representado")) {
                paterno_representado = carpeta.get("paterno_representado").getAsString();
            }
            if (carpeta.has("materno_representado")) {
                materno_representado = carpeta.get("materno_representado").getAsString();
            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();
            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();
            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();
            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();
            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();
            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();
            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();
            }

            if (!materia.equals("None") && !nombre_representado.equals("None") && !paterno_representado.equals("None")
                    && !materno_representado.equals("None")) {

                AsesoriaInf asInf = new AsesoriaInf();
                asInf.setMateria(materia);
                asInf.setNombre_juzgado(nombre_juzgado);
                asInf.setNombre_representado(nombre_representado);
                asInf.setPaterno_representado(paterno_representado);
                asInf.setMaterno_representado(materno_representado);
                asInf.setSexo(sexo);
                asInf.setEdad(edad);
                asInf.setGrupo_vulnerable(grupo_vulnerable);
                asInf.setEtnia(etnia);
                asInf.setDiscapacidad(discapacidad);
                asInf.setCveDistrito(cveDistrito);
                asInf.setCveRegion(cveRegion);
                asInf.setTipoExpediente(tipoExpediente);
                asInf.setCveMes(cveMes);
                asInf.setAnio(anio);
                asInf.setCveUsuario(cveUsuario);

                asesoriaInfRepository.save(asInf);
            }

        }

    }

    public void RegistrarSalasAdolescentes(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio,
            Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";

            if (carpeta.has("numero_toca")) {
                numeroExpediente = carpeta.get("numero_toca").getAsString();
            }
            if (carpeta.has("ano_toca")) {
                anioExpediente = carpeta.get("ano_toca").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarAdolescentes(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio,Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";

            String delito = "";
            String delito2 = "";
            String delito3 = "";
            String municipio = "";
            String conclusion = "";
            String tipo_audiencia = "";
            String interno_libertad = "";

            if (carpeta.has("numero_causa")) {
                numeroExpediente = carpeta.get("numero_causa").getAsString();
            }
            if (carpeta.has("ano_causa")) {
                anioExpediente = carpeta.get("ano_causa").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }
            if (carpeta.has("delito")) {
                delito = carpeta.get("delito").getAsString();

            }
            if (carpeta.has("delito2")) {
                delito2 = carpeta.get("delito2").getAsString();

            }
            if (carpeta.has("delito3")) {
                delito3 = carpeta.get("delito3").getAsString();

            }
            if (carpeta.has("conclusion")) {
                conclusion = carpeta.get("conclusion").getAsString();

            }

            if (carpeta.has("municipio")) {
                municipio = carpeta.get("municipio").getAsString();

            }
            if (carpeta.has("tipo_audiencia")) {
                tipo_audiencia = carpeta.get("tipo_audiencia").getAsString();

            }
            if (carpeta.has("Interno/Libertad")) {
                interno_libertad = carpeta.get("Interno/Libertad").getAsString();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setDelito(delito);
            ex.setDelito2(delito2);
            ex.setDelito3(delito3);
            ex.setMunicipio(municipio);
            ex.setTipo_audiencia(tipo_audiencia);
            ex.setConclusion(conclusion);
            ex.setInterno_libertad(interno_libertad);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);
            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarPrimera(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";

            if (carpeta.has("numero_causa")) {
                numeroExpediente = carpeta.get("numero_causa").getAsString();
            }
            if (carpeta.has("ano_causa")) {
                anioExpediente = carpeta.get("ano_causa").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);
            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarSalas(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";

            if (carpeta.has("numero_toca")) {
                numeroExpediente = carpeta.get("numero_toca").getAsString();
            }
            if (carpeta.has("ano_toca")) {
                anioExpediente = carpeta.get("ano_toca").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarMpAdolescentes(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio , Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String numCarpeta = "";
            String determinacion = "";
            System.out.println("Tiene carpeta " + carpeta.has("No Carpeta Investigacion"));
            if (carpeta.has("No Carpeta Investigacion")) {
                numCarpeta = carpeta.get("No Carpeta Investigacion").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }
            if (carpeta.has("determinacion")) {
                determinacion = carpeta.get("determinacion").getAsString();

            }
            Expediente ex = new Expediente();
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setNumCarpetaInvestigacion(numCarpeta);
            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setDeterminacion(determinacion);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numCarpeta.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarSentencias(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";
            String delito = "";
            String delito2 = "";
            String delito3 = "";
            String municipio = "";
            String conclusion = "";
            String tipo_audiencia = "";
            String interno_libertad = "";
            if (carpeta.has("delito")) {
                delito = carpeta.get("delito").getAsString();

            }
            if (carpeta.has("delito2")) {
                delito2 = carpeta.get("delito2").getAsString();

            }
            if (carpeta.has("delito3")) {
                delito3 = carpeta.get("delito3").getAsString();

            }
            if (carpeta.has("conclusion")) {
                conclusion = carpeta.get("conclusion").getAsString();

            }

            if (carpeta.has("municipio")) {
                municipio = carpeta.get("municipio").getAsString();

            }
            if (carpeta.has("tipo_audiencia")) {
                tipo_audiencia = carpeta.get("tipo_audiencia").getAsString();

            }
            if (carpeta.has("Interno/Libertad")) {
                interno_libertad = carpeta.get("Interno/Libertad").getAsString();

            }

            if (carpeta.has("numero_expediente")) {
                numeroExpediente = carpeta.get("numero_expediente").getAsString();
            }
            if (carpeta.has("ano_expediente")) {
                anioExpediente = carpeta.get("ano_expediente").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);
            ex.setDelito(delito);
            ex.setDelito2(delito2);
            ex.setDelito3(delito3);
            ex.setMunicipio(municipio);
            ex.setTipo_audiencia(tipo_audiencia);
            ex.setConclusion(conclusion);
            ex.setInterno_libertad(interno_libertad);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);
            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarPatrocinios(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String numCarpeta = "";
            String accion = "";
            String linea = "";
            String juzgado = "";

            System.out.println("Tiene carpeta " + carpeta.has("No Carpeta Investigacion"));
            if (carpeta.has("no_expediente")) {
                numeroExpediente = carpeta.get("no_expediente").getAsString();
            }
            if (carpeta.has("anio_expediente")) {
                anioExpediente = carpeta.get("anio_expediente").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }
            if (carpeta.has("accion")) {
                accion = carpeta.get("accion").getAsString();

            }
            if (carpeta.has("nombre_juzgado")) {
                juzgado = carpeta.get("nombre_juzgado").getAsString();

            }
            if (carpeta.has("Caravanas por la Justicia (sí o No)")) {
                linea = carpeta.get("Caravanas por la Justicia (sí o No)").getAsString();

            }
            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setAccion(accion);
            ex.setLinea(linea);
            ex.setJuzgado(juzgado);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarControl(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";
            String delito = "";
            String delito2 = "";
            String delito3 = "";
            String municipio = "";
            String conclusion = "";
            String tipo_audiencia = "";
            String interno_libertad = "";

            System.out.println("Tiene carpeta " + carpeta.has("No Carpeta Investigacion"));
            if (carpeta.has("numero_carpeta/causa")) {
                numeroExpediente = carpeta.get("numero_carpeta/causa").getAsString();
            }
            if (carpeta.has("ano_carpeta/causa")) {
                anioExpediente = carpeta.get("ano_carpeta/causa").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }
            if (carpeta.has("tipo_carpeta")) {
                tipoCarpeta = carpeta.get("tipo_carpeta").getAsString();

            }
            if (carpeta.has("delito")) {
                delito = carpeta.get("delito").getAsString();

            }
            if (carpeta.has("delito2")) {
                delito2 = carpeta.get("delito2").getAsString();

            }
            if (carpeta.has("delito3")) {
                delito3 = carpeta.get("delito3").getAsString();

            }
            if (carpeta.has("conclusion")) {
                conclusion = carpeta.get("conclusion").getAsString();

            }

            if (carpeta.has("municipio")) {
                municipio = carpeta.get("municipio").getAsString();

            }
            if (carpeta.has("tipo_audiencia")) {
                tipo_audiencia = carpeta.get("tipo_audiencia").getAsString();

            }
            if (carpeta.has("Interno/Libertad")) {
                interno_libertad = carpeta.get("Interno/Libertad").getAsString();

            }
            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);
            ex.setDelito(delito);
            ex.setDelito2(delito2);
            ex.setDelito3(delito3);
            ex.setMunicipio(municipio);
            ex.setTipo_audiencia(tipo_audiencia);
            ex.setConclusion(conclusion);
            ex.setInterno_libertad(interno_libertad);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarAdministrativas(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("expedientes").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String tipoCarpeta = "";

            if (carpeta.has("numero_expediente")) {
                numeroExpediente = carpeta.get("numero_expediente").getAsString();
            }
            if (carpeta.has("ano_expediente")) {
                anioExpediente = carpeta.get("ano_expediente").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }

            Expediente ex = new Expediente();
            ex.setNumeroExpediente(numeroExpediente);
            ex.setAnioExpediente(anioExpediente);
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setTipoCarpeta(tipoCarpeta);

            ex.setCveMes(cveMes);
            ex.setAnio(anio);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numeroExpediente.equals("None") && !anioExpediente.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    public void RegistrarCarpeta(JsonObject json, Integer tipoExpediente, Integer cveMes, Integer anio, Integer cveAdscripcion,Integer cveUsuario) {

        JsonArray carpetas = json.get("carpetas").getAsJsonArray();

        for (JsonElement c : carpetas) {
            JsonObject carpeta = c.getAsJsonObject();
            String numeroExpediente = "";
            String anioExpediente = "";

            String nombre = "";
            String paterno = "";
            String materno = "";
            Integer cveDistrito = 0;
            Integer cveRegion = 0;
            String edad = "";
            String grupo_vulnerable = "";
            String etnia = "";
            String discapacidad = "";
            String sexo = "";
            String numCarpeta = "";
            String delito = "";
            String delito2 = "";
            String delito3 = "";
            String determinacion = "";

            System.out.println("Tiene carpeta " + carpeta.has("No Carpeta Investigacion"));
            if (carpeta.has("No Carpeta Investigacion")) {
                numCarpeta = carpeta.get("No Carpeta Investigacion").getAsString();
            }
            if (carpeta.has("nombre_representado")) {
                nombre = carpeta.get("nombre_representado").getAsString();

            }
            if (carpeta.has("paterno_representado")) {
                paterno = carpeta.get("paterno_representado").getAsString();

            }
            if (carpeta.has("materno_representado")) {
                materno = carpeta.get("materno_representado").getAsString();

            }
            if (carpeta.has("sexo")) {
                sexo = carpeta.get("sexo").getAsString();

            }
            if (carpeta.has("edad")) {
                edad = carpeta.get("edad").getAsString();

            }
            if (carpeta.has("etnia")) {
                etnia = carpeta.get("etnia").getAsString();

            }
            if (carpeta.has("discapacidad")) {
                discapacidad = carpeta.get("discapacidad").getAsString();

            }
            if (carpeta.has("grupo_vulnerable")) {
                grupo_vulnerable = carpeta.get("grupo_vulnerable").getAsString();

            }
            if (carpeta.has("cveDistrito")) {
                cveDistrito = carpeta.get("cveDistrito").getAsInt();

            }
            if (carpeta.has("cveRegion")) {
                cveRegion = carpeta.get("cveRegion").getAsInt();

            }
            if (carpeta.has("delito")) {
                delito = carpeta.get("delito").getAsString();

            }
            if (carpeta.has("delito2")) {
                delito2 = carpeta.get("delito2").getAsString();

            }
            if (carpeta.has("delito2")) {
                delito3 = carpeta.get("delito2").getAsString();

            }
            if (carpeta.has("determinacion")) {
                determinacion = carpeta.get("determinacion").getAsString();

            }

            Expediente ex = new Expediente();
            ex.setTipoExpediente(tipoExpediente);
            ex.setNombre(nombre);
            ex.setPaterno(paterno);
            ex.setMaterno(materno);
            ex.setCveDistrito(cveDistrito);
            ex.setCveRegion(cveRegion);
            ex.setEdad(edad);
            ex.setGrupo_vulnerable(grupo_vulnerable);
            ex.setEtnia(etnia);
            ex.setDiscapacidad(discapacidad);
            ex.setSexo(sexo);
            ex.setNumCarpetaInvestigacion(numCarpeta);
            ex.setCveMes(cveMes);
            ex.setAnio(anio);

            ex.setDelito(delito);
            ex.setDelito2(delito2);
            ex.setDelito3(delito3);
            ex.setDeterminacion(determinacion);
            ex.setCveAdscripcion(cveAdscripcion);
            ex.setCveUsuario(cveUsuario);

            if (!nombre.equals("None") && !paterno.equals("None") && !materno.equals("None")
                    && !numCarpeta.equals("None")) {
                expedientesRepository.save(ex);
            }

        }

    }

    @Transactional
    public void BuscarTipoInforme(JsonObject estatus, String validacion, Informe inf) throws ParseException {
        Integer idTipoCarpeta = 0;
        // OK

        if (validacion.contains("investigacion")) {
            idTipoCarpeta = 4;
            // GuardarCarpeta(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);
        } else if (validacion.contains("familiares")) { // OK
            idTipoCarpeta = 14;
            // this.GuardarPatrocinios(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);
        } else if (validacion.contains("primera_instancia")) {
            idTipoCarpeta = 6;
            // this.GuardarPrimeraInstancia(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);
            // GuardarVisitas(estatus, inf,idTipoCarpeta);

        } else if (validacion.contains("control")) {
            idTipoCarpeta = 1;
            System.out.println("*********** GUARDA CARPETAS DE CONTROL ******************************");
            // Carpeta c = this.GuardarControl(estatus, inf, idTipoCarpeta);
            System.out.println("*********** TERMINA GUARDA CARPETAS DE CONTROL ******************************");
            // GuardarVisitas(estatus, inf);
            // // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("sentencias")) {
            idTipoCarpeta = 3;
            // this.GuardarSentencias(estatus, inf, idTipoCarpeta);
            // GuardarVisitas(estatus, inf,idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("salas_penales_adolescentes")) {
            idTipoCarpeta = 10;
            // this.GuardarSalasAdolescentes(estatus, inf, idTipoCarpeta);
            // GuardarVisitas(estatus, inf,idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("salas_penales")) {
            idTipoCarpeta = 5;
            // this.GuardarSalasPenales(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("mp_adolescentes")) {
            idTipoCarpeta = 11;
            // this.GuardarMpAdolescentes(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("adolescentes")) {
            idTipoCarpeta = 9;
            // this.GuardarAdolescentes(estatus, inf, idTipoCarpeta);
            // GuardarVisitas(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);

        } else if (validacion.contains("administrativas")) {
            idTipoCarpeta = 16;

            this.GuardarAdministrativas(estatus, inf, idTipoCarpeta);

        } else if (validacion.contains("adicciones")) {
            idTipoCarpeta = 8;
            // this.GuardarAdicciones(estatus, inf, idTipoCarpeta);
            // GuardarAsesoria(estatus, inf);
        }

    }

    public Carpeta GuardarPrimeraInstancia(JsonObject estatus, Informe inf, Integer idTipoCarpeta)
            throws ParseException {

        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_causa") != null && temp.get("ano_causa") != null) {
                    if (!temp.get("numero_causa").getAsString().equals("None")
                            && !temp.get("ano_causa").getAsString().equals("None")) {
                        Integer numero = Integer
                                .parseInt(temp.get("numero_causa").getAsString().replace("Exhorto", "").trim());
                        Integer anio = temp.get("ano_causa").getAsInt();
                        Date fecha = new Date();
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        List<Carpeta> lista = carpetasRepository.findAll(Example.of(c));
                        if (lista.size() > 0) {
                            c = lista.get(0);
                            String txtFecha = temp.get("fecha_audiencia").getAsString();
                            String infMes = inf.getCveMes().toString();
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                        } else {
                            c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");
                            fecha = c.getFechaRadicacion();
                        }

                        inf.setEstatusInforme("I");
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }
            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    @Transactional
    public Carpeta GuardarControl(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_carpeta/causa") != null && temp.get("ano_carpeta/causa") != null) {
                    if ((!temp.get("numero_carpeta/causa").getAsString().equals("None")
                            && !temp.get("ano_carpeta/causa").getAsString().equals("None"))
                            && (!temp.get("numero_carpeta/causa").getAsString().equals(" ")
                                    && !temp.get("ano_carpeta/causa").getAsString().equals(" "))
                            && (!temp.get("numero_carpeta/causa").getAsString().equals("\"\"")
                                    && !temp.get("ano_carpeta/causa").getAsString().equals("\"\""))) {
                        Integer numero = 0;
                        Integer anio = 0;
                        Date fecha = new Date();
                        if (temp.get("numero_carpeta/causa").getAsString().contains("/")) {
                            String[] partes = temp.get("numero_carpeta/causa").getAsString().split("/");
                            numero = Integer.parseInt(partes[0].replace(".0", "").trim());
                        } else {
                            String num = temp.get("numero_carpeta/causa").getAsString().replace(" ", "")
                                    .replace(".0", "").trim();
                            numero = Integer.parseInt(num);
                        }

                        if (temp.get("ano_carpeta/causa").getAsString().contains("/")) {
                            String[] partes = temp.get("ano_carpeta/causa").getAsString().split("/");
                            anio = Integer.parseInt(partes[1].replace(".0", "").trim());
                        } else {
                            String num = temp.get("ano_carpeta/causa").getAsString().replace(" ", "").replace(".0", "")
                                    .trim();
                            anio = Integer.parseInt(num);
                        }

                        String tipoCarpeta = temp.get("tipo_carpeta").getAsString();
                        System.out.println("TIPO CARPETA DEL ARCHIVO: " + tipoCarpeta);

                        TiposCarpetas tc = new TiposCarpetas();
                        if (tipoCarpeta.contains("None")) {
                            tc = BuscarTiposCarpetas("CARPETA ADMINISTRATIVA");
                        } else {
                            tc = BuscarTiposCarpetas(tipoCarpeta);
                        }

                        idTipoCarpeta = tc.getIdTipoCarpeta();
                        System.out.println("OBJETO TIPO CARPETA DEL ARCHIVO: " + tc.getIdTipoCarpeta() + " "
                                + tc.getDescTipoCarpeta());

                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        c.setTipoCarpeta(tc);
                        System.out.println("BUSCAR CARPETA CON LOS SIGUIENTES DATOS");
                        System.out.println(c.getNumero());
                        System.out.println(c.getAnio());
                        System.out.println(c.getActivo());
                        System.out.println(c.getAdscripcion().getDescAdscripcion());
                        System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());

                        List<Carpeta> lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                c.getAdscripcion().getCveAdscripcion(), c.getTipoCarpeta().getIdTipoCarpeta(), "S");
                        if (lista.size() > 0) {
                            c = lista.get(0);
                            System.out.println("ENCONTRADA");
                            System.out.println(c.getIdCarpeta());
                            System.out.println(c.getNumero());
                            System.out.println(c.getAnio());
                            System.out.println(c.getActivo());
                            System.out.println(c.getAdscripcion().getDescAdscripcion());
                            System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                            inf.setEstatusInforme("I");
                            String txtFecha = "";
                            if (temp.get("fecha_audiencia") != null)
                                txtFecha = temp.get("fecha_audiencia").getAsString();
                            else if (temp.get("fecha_audiencia0") != null)
                                txtFecha = temp.get("fecha_audiencia0").getAsString();
                            String infMes = inf.getCveMes().toString();
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                        } else {
                            lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                    c.getAdscripcion().getCveAdscripcion(), c.getTipoCarpeta().getIdTipoCarpeta(), "P");
                            if (lista.size() > 0) {
                                c = lista.get(0);
                                inf.setEstatusInforme("I");

                            } else {
                                c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                                System.out.println("REGISTRADA");
                                System.out.println(c.getIdCarpeta());
                                System.out.println(c.getNumero());
                                System.out.println(c.getAnio());
                                System.out.println(c.getActivo());
                                System.out.println(c.getAdscripcion().getDescAdscripcion());
                                System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                                inf.setEstatusInforme("I");

                                fecha = c.getFechaRadicacion();

                            }

                        }
                        inf.setEstatusInforme("I");
                        /*
                         * Representados r = RegistrarRepresentados(temp);
                         * registrarCarpetaRepresentado(c, r);
                         * 
                         * List<Delitos> ltsDelito = this.BuscarDelito(temp); for (Delitos d :
                         * ltsDelito) { this.RegistrarDelitosRepresentados(r, d);
                         * 
                         * }
                         */

                        // RegistrarCarpetasInformes(c, inf);
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);
                        // RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            // SE MARCA CON D PARA INDICAR QUE ESTA SIN NOVEDAD
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    @Transactional
    public Carpeta GuardarSentencias(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_expediente") != null && temp.get("ano_expediente") != null) {
                    if ((!temp.get("numero_expediente").getAsString().equals("None")
                            && !temp.get("ano_expediente").getAsString().equals("None"))
                            && (!temp.get("numero_expediente").getAsString().equals(" ")
                                    && !temp.get("ano_expediente").getAsString().equals(" "))
                            && (!temp.get("numero_expediente").getAsString().equals("\"\"")
                                    && !temp.get("ano_expediente").getAsString().equals("\"\""))) {
                        Integer numero = 0;
                        Integer anio = 0;
                        Date fecha = new Date();
                        if (temp.get("numero_expediente").getAsString().contains("/")) {
                            String[] partes = temp.get("numero_expediente").getAsString().split("/");
                            numero = Integer.parseInt(partes[0].replace(".0", "").trim());
                        } else {
                            String num = temp.get("numero_expediente").getAsString().replace(" ", "").replace(".0", "")
                                    .replace("TRES", "3").trim();
                            numero = Integer.parseInt(num);
                        }

                        if (temp.get("ano_expediente").getAsString().contains("/")) {
                            String[] partes = temp.get("ano_expediente").getAsString().split("/");
                            anio = Integer.parseInt(partes[1].replace(".0", "").trim());
                        } else {
                            String num = temp.get("ano_expediente").getAsString().replace(" ", "").replace(".0", "")
                                    .trim();
                            anio = Integer.parseInt(num);
                        }
                        // TiposCarpetas tc = new TiposCarpetas();
                        // tc.setIdTipoCarpeta(idTipoCarpeta);
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        // c.setTipoCarpeta(tc);
                        System.out.println("BUSCAR CARPETA CON LOS SIGUIENTES DATOS");
                        System.out.println(c.getNumero());
                        System.out.println(c.getAnio());
                        System.out.println(c.getActivo());
                        System.out.println(c.getAdscripcion().getDescAdscripcion());

                        List<Carpeta> lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "S");
                        if (lista.size() > 0) {
                            c = lista.get(0);
                            System.out.println("ENCONTRADA");
                            System.out.println(c.getIdCarpeta());
                            System.out.println(c.getNumero());
                            System.out.println(c.getAnio());
                            System.out.println(c.getActivo());
                            System.out.println(c.getAdscripcion().getDescAdscripcion());
                            System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                            inf.setEstatusInforme("I");
                            String txtFecha = "";
                            if (temp.get("fecha_actuación") != null)
                                txtFecha = temp.get("fecha_actuación").getAsString();
                            else if (temp.get("fecha_actuación") != null)
                                txtFecha = temp.get("fecha_actuación").getAsString();
                            String infMes = inf.getCveMes().toString();
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                        } else {
                            lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                    c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "P");
                            if (lista.size() > 0) {
                                c = lista.get(0);
                                inf.setEstatusInforme("I");

                            } else {
                                c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                                System.out.println("REGISTRADA");
                                System.out.println(c.getIdCarpeta());
                                System.out.println(c.getNumero());
                                System.out.println(c.getAnio());
                                System.out.println(c.getActivo());
                                System.out.println(c.getAdscripcion().getDescAdscripcion());
                                System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                                inf.setEstatusInforme("I");

                                fecha = c.getFechaRadicacion();

                            }

                        }
                        inf.setEstatusInforme("I");
                        /*
                         * Representados r = RegistrarRepresentados(temp);
                         * registrarCarpetaRepresentado(c, r);
                         * 
                         * List<Delitos> ltsDelito = this.BuscarDelito(temp); for (Delitos d :
                         * ltsDelito) { this.RegistrarDelitosRepresentados(r, d);
                         * 
                         * }
                         */

                        // RegistrarCarpetasInformes(c, inf);
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);
                        // RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    @Transactional
    public Carpeta GuardarSalasPenales(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_toca") != null && temp.get("ano_toca") != null) {
                    if ((!temp.get("numero_toca").getAsString().equals("None")
                            && !temp.get("ano_toca").getAsString().equals("None"))
                            && (!temp.get("numero_toca").getAsString().equals(" ")
                                    && !temp.get("ano_toca").getAsString().equals(" "))
                            && (!temp.get("numero_toca").getAsString().equals("\"\"")
                                    && !temp.get("ano_toca").getAsString().equals("\"\""))) {
                        Integer numero = 0;
                        Integer anio = 0;
                        Date fecha = new Date();
                        if (temp.get("numero_toca").getAsString().contains("/")) {
                            String[] partes = temp.get("numero_toca").getAsString().split("/");
                            numero = Integer.parseInt(partes[0].replace(".0", "").trim());
                        } else {
                            String num = temp.get("numero_toca").getAsString().replace(" ", "").replace(".0", "")
                                    .replace("TRES", "3").trim();
                            numero = Integer.parseInt(num);
                        }

                        if (temp.get("ano_toca").getAsString().contains("/")) {
                            String[] partes = temp.get("ano_toca").getAsString().split("/");
                            anio = Integer.parseInt(partes[1].replace(".0", "").trim());
                        } else {
                            String num = temp.get("ano_toca").getAsString().replace(" ", "").replace(".0", "").trim();
                            anio = Integer.parseInt(num);
                        }
                        // TiposCarpetas tc = new TiposCarpetas();
                        // tc.setIdTipoCarpeta(idTipoCarpeta);
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        // c.setTipoCarpeta(tc);
                        System.out.println("BUSCAR CARPETA CON LOS SIGUIENTES DATOS");
                        System.out.println(c.getNumero());
                        System.out.println(c.getAnio());
                        System.out.println(c.getActivo());
                        System.out.println(c.getAdscripcion().getDescAdscripcion());

                        List<Carpeta> lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "S");
                        if (lista.size() > 0) {
                            c = lista.get(0);
                            System.out.println("ENCONTRADA");
                            System.out.println(c.getIdCarpeta());
                            System.out.println(c.getNumero());
                            System.out.println(c.getAnio());
                            System.out.println(c.getActivo());
                            System.out.println(c.getAdscripcion().getDescAdscripcion());
                            System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                            inf.setEstatusInforme("I");
                            String txtFecha = "";

                            String infMes = inf.getCveMes().toString();
                            txtFecha = "2021-" + infMes + "-01";
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                        } else {
                            lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                    c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "P");
                            if (lista.size() > 0) {
                                c = lista.get(0);
                                inf.setEstatusInforme("I");

                            } else {
                                c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                                System.out.println("REGISTRADA");
                                System.out.println(c.getIdCarpeta());
                                System.out.println(c.getNumero());
                                System.out.println(c.getAnio());
                                System.out.println(c.getActivo());
                                System.out.println(c.getAdscripcion().getDescAdscripcion());
                                System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                                inf.setEstatusInforme("I");

                                fecha = c.getFechaRadicacion();

                            }

                        }
                        inf.setEstatusInforme("I");
                        /*
                         * Representados r = RegistrarRepresentados(temp);
                         * registrarCarpetaRepresentado(c, r);
                         * 
                         * List<Delitos> ltsDelito = this.BuscarDelito(temp); for (Delitos d :
                         * ltsDelito) { this.RegistrarDelitosRepresentados(r, d);
                         * 
                         * }
                         */

                        // RegistrarCarpetasInformes(c, inf);
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);
                        // RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    @Transactional
    public Carpeta GuardarMpAdolescentes(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;

                c.setActivo("S");
                Boolean registrar = true;
                List<Carpeta> lista = new ArrayList<>();

                if (temp.get("No Carpeta Investigacion") != null) {

                    String nuc = temp.get("No Carpeta Investigacion").getAsString();
                    if (!nuc.equals("None")) {
                        c.setNuc(nuc);
                        TiposCarpetas tc2 = new TiposCarpetas();
                        tc2.setIdTipoCarpeta(idTipoCarpeta);
                        lista = carpetasRepository.findByNucLikeAndActivoEqualsAndTipoCarpetaEquals(nuc, "S", tc2);
                        System.out.println("nuc de la carpeta: " + nuc);
                        if (lista.size() > 0) {
                            for (Carpeta tc : lista) {
                                c = tc;
                                System.out.println(tc.getNuc());
                            }
                        } else {

                            System.out.println("Registrando la carpeta");

                            c = this.RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");

                        }
                    } else {
                        registrar = false;
                    }
                }

                if (registrar) {
                    Date fechaAudiencia = new Date();
                    if (temp.get("fecha_entrevista") != null) {
                        String fecha = temp.get("fecha_entrevista").getAsString();
                        String infMes = inf.getCveMes().toString();

                        String tempFecha = this.ProcesarFechas(fecha, infMes);
                        fechaAudiencia = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                    }

                    RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fechaAudiencia, idTipoCarpeta);

                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    @Transactional
    public Carpeta GuardarAdolescentes(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_causa") != null && temp.get("ano_causa") != null) {
                    if (!temp.get("numero_causa").getAsString().equals("None")
                            && !temp.get("ano_causa").getAsString().equals("None")) {
                        Integer numero = Integer
                                .parseInt(temp.get("numero_causa").getAsString().replace("Exhorto", "").trim());
                        Integer anio = temp.get("ano_causa").getAsInt();
                        Date fecha = new Date();
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        List<Carpeta> lista = carpetasRepository.findAll(Example.of(c));
                        if (lista.size() > 0) {
                            c = lista.get(0);

                            String infMes = inf.getCveMes().toString();
                            String txtFecha = "2021-" + infMes + "-01";
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                        } else {
                            c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            String infMes = inf.getCveMes().toString();
                            String txtFecha = "2021-" + infMes + "-01";
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                            inf.setEstatusInforme("I");

                        }

                        inf.setEstatusInforme("I");
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);
                    } else {
                        inf.setEstatusInforme("D");
                    }
                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    public Carpeta GuardarAdministrativas(JsonObject estatus, Informe inf, Integer idTipoCarpeta)
            throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        TiposCarpetas tc = new TiposCarpetas();
        tc.setIdTipoCarpeta(idTipoCarpeta);
        inf.setTipoCarpeta(tc);
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_expediente") != null && temp.get("ano_expediente") != null) {
                    if (!temp.get("numero_expediente").getAsString().equals("None")
                            && !temp.get("ano_expediente").getAsString().equals("None")) {
                        String numAdmin = temp.get("numero_expediente").getAsString();
                        c.setNumAdmin(numAdmin);
                        c.setActivo("S");
                        List<Carpeta> lista = carpetasRepository.findByNumAdminLikeAndActivoEquals(numAdmin, "S");
                        if (lista.size() > 0) {
                            c = lista.get(0);
                        } else {
                            c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");
                        }
                        inf.setEstatusInforme("I");
                        Representados r = RegistrarRepresentados(temp);
                        registrarCarpetaRepresentado(c, r);
                        Date fecha = c.getFechaRadicacion();
                        Audiencias a = RegistrarAudiencias(temp, fecha, c);
                        RegistrarCarpetasInformes(c, inf);
                        RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }
            }
        } else {

            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    public Carpeta GuardarSalasAdolescentes(JsonObject estatus, Informe inf, Integer idTipoCarpeta)
            throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_toca") != null && temp.get("ano_toca") != null) {
                    if ((!temp.get("numero_toca").getAsString().equals("None")
                            && !temp.get("ano_toca").getAsString().equals("None"))
                            && (!temp.get("numero_toca").getAsString().equals(" ")
                                    && !temp.get("ano_toca").getAsString().equals(" "))
                            && (!temp.get("numero_toca").getAsString().equals("\"\"")
                                    && !temp.get("ano_toca").getAsString().equals("\"\""))) {
                        Integer numero = 0;
                        Integer anio = 0;
                        Date fecha = new Date();
                        if (temp.get("numero_toca").getAsString().contains("/")) {
                            String[] partes = temp.get("numero_toca").getAsString().split("/");
                            numero = Integer.parseInt(partes[0].replace(".0", "").trim());
                        } else {
                            String num = temp.get("numero_toca").getAsString().replace(" ", "").replace(".0", "")
                                    .replace("TRES", "3").trim();
                            numero = Integer.parseInt(num);
                        }

                        if (temp.get("ano_toca").getAsString().contains("/")) {
                            String[] partes = temp.get("ano_toca").getAsString().split("/");
                            anio = Integer.parseInt(partes[1].replace(".0", "").trim());
                        } else {
                            String num = temp.get("ano_toca").getAsString().replace(" ", "").replace(".0", "").trim();
                            anio = Integer.parseInt(num);
                        }
                        // TiposCarpetas tc = new TiposCarpetas();
                        // tc.setIdTipoCarpeta(idTipoCarpeta);
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        // c.setTipoCarpeta(tc);
                        System.out.println("BUSCAR CARPETA CON LOS SIGUIENTES DATOS");
                        System.out.println(c.getNumero());
                        System.out.println(c.getAnio());
                        System.out.println(c.getActivo());
                        System.out.println(c.getAdscripcion().getDescAdscripcion());

                        List<Carpeta> lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "S");
                        if (lista.size() > 0) {
                            c = lista.get(0);
                            System.out.println("ENCONTRADA");
                            System.out.println(c.getIdCarpeta());
                            System.out.println(c.getNumero());
                            System.out.println(c.getAnio());
                            System.out.println(c.getActivo());
                            System.out.println(c.getAdscripcion().getDescAdscripcion());
                            System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                            inf.setEstatusInforme("I");
                            String txtFecha = "";

                            String infMes = inf.getCveMes().toString();
                            txtFecha = "2021-" + infMes + "-01";
                            String tempFecha = this.ProcesarFechas(txtFecha, infMes);
                            tempFecha = tempFecha.replace("\\.", "");
                            fecha = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                        } else {
                            lista = carpetasRepository.findByCarpetasPenales(c.getNumero(), c.getAnio(),
                                    c.getAdscripcion().getCveAdscripcion(), idTipoCarpeta, "P");
                            if (lista.size() > 0) {
                                c = lista.get(0);
                                inf.setEstatusInforme("I");

                            } else {
                                c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                                System.out.println("REGISTRADA");
                                System.out.println(c.getIdCarpeta());
                                System.out.println(c.getNumero());
                                System.out.println(c.getAnio());
                                System.out.println(c.getActivo());
                                System.out.println(c.getAdscripcion().getDescAdscripcion());
                                System.out.println(c.getTipoCarpeta().getDescTipoCarpeta());
                                inf.setEstatusInforme("I");

                                fecha = c.getFechaRadicacion();

                            }

                        }
                        inf.setEstatusInforme("I");
                        /*
                         * Representados r = RegistrarRepresentados(temp);
                         * registrarCarpetaRepresentado(c, r);
                         * 
                         * List<Delitos> ltsDelito = this.BuscarDelito(temp); for (Delitos d :
                         * ltsDelito) { this.RegistrarDelitosRepresentados(r, d);
                         * 
                         * }
                         */

                        // RegistrarCarpetasInformes(c, inf);
                        RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fecha, idTipoCarpeta);
                        // RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }

            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    public Carpeta GuardarAdicciones(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        TiposCarpetas tc = new TiposCarpetas();
        tc.setIdTipoCarpeta(idTipoCarpeta);
        inf.setTipoCarpeta(tc);
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                if (temp.get("numero_carpeta/causa") != null && temp.get("ano_carpeta/causa") != null) {
                    if (!temp.get("numero_carpeta/causa").getAsString().equals("None")
                            && !temp.get("ano_carpeta/causa").getAsString().equals("None")) {
                        Integer numero = temp.get("numero_carpeta/causa").getAsInt();
                        Integer anio = temp.get("ano_carpeta/causa").getAsInt();
                        c.setNumero(numero);
                        c.setAnio(anio);
                        c.setActivo("S");
                        c.setAdscripcion(inf.getAdscripcion());
                        List<Carpeta> lista = carpetasRepository.findAll(Example.of(c));
                        if (lista.size() > 0) {
                            c = lista.get(0);
                        } else {
                            c = RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");
                        }
                        inf.setEstatusInforme("I");
                        Representados r = RegistrarRepresentados(temp);
                        registrarCarpetaRepresentado(c, r);
                        Date fecha = c.getFechaRadicacion();

                        List<Delitos> ltsDelito = this.BuscarDelito(temp);
                        for (Delitos d : ltsDelito) {
                            this.RegistrarDelitosRepresentados(r, d);

                        }
                        Audiencias a = RegistrarAudiencias(temp, fecha, c);
                        RegistrarCarpetasInformes(c, inf);
                        RegistrarConclusiones(temp, idTipoCarpeta, a, r);

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }
            }
        } else {

            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;
    }

    public Carpeta GuardarPatrocinios(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();
        JsonArray listaCarpetas = resultado.getAsJsonArray("expedientes");
        Carpeta c = new Carpeta();
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;

                c.setActivo("S");
                Boolean registrar = false;
                List<Carpeta> lista = new ArrayList<>();

                if (temp.get("no_expediente") != null && temp.get("anio_expediente") != null
                        && temp.get("nombre_juzgado") != null) {
                    if (!temp.get("no_expediente").getAsString().equals("None")
                            && !temp.get("anio_expediente").getAsString().equals("None")) {
                        Integer numero = 0;
                        Integer anio = 0;
                        String[] numeros = temp.get("no_expediente").getAsString().split("/");
                        if (numeros.length < 2) {
                            String num = temp.get("no_expediente").getAsString().trim();
                            if (num.contains("VEINTICINCO")) {
                                numero = 25;
                            } else {
                                numero = temp.get("no_expediente").getAsInt();
                                anio = temp.get("anio_expediente").getAsInt();

                            }

                        } else {
                            numero = Integer.parseInt(numeros[0]);
                            anio = Integer.parseInt(numeros[1]);
                        }
                        c.setNumero(numero);
                        c.setAnio(anio);
                        String descAdscripcion = temp.get("nombre_juzgado").getAsString() + " "
                                + inf.getAdscripcion().getDescAdscripcion();
                        Adscripciones ads = this.BuscarRegistrarAdscripcion(descAdscripcion, inf);
                        inf.setAdscripcion(ads);
                        c.setAdscripcion(ads);

                        c.setMateria(this.BuscarMaterias(temp.get("cat_materia").getAsString().trim()));
                        lista = carpetasRepository.findAll(Example.of(c));
                        if (lista.size() > 0) {
                            c = lista.get(0);
                        } else {
                            c = this.RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");
                            registrar = true;
                        }

                    } else {
                        inf.setEstatusInforme("D");
                    }
                }
                if (registrar) {
                    RegistrarRepresentadosAcciones(temp, c, inf, c.getFechaRadicacion(), idTipoCarpeta);
                }
            }
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            inf.setTipoCarpeta(tc);
            inf.setEstatusInforme("D");

        }
        informesRepository.save(inf);

        return c;

    }

    public void GuardarAsesoria(JsonObject resultado, Informe inf) throws ParseException {
        if (resultado.getAsJsonArray("asesorias") != null) {
            JsonArray listaAsesorias = resultado.getAsJsonArray("asesorias");
            if (listaAsesorias.size() > 0) {
                for (JsonElement jo : listaAsesorias) {
                    JsonObject temp = (JsonObject) jo;
                    Representados r = RegistrarRepresentados(temp);
                    this.RegistrarAsesoriaInforme(temp, inf, r);
                }
            } else {
                System.out.println("Asesorias sin novedad");
            }
        } else {
            System.out.println("lista Asesorias sin novedad");
        }
    }

    public void GuardarVisitas(JsonObject resultado, Informe inf, Integer idTipoCarpeta) throws ParseException {

        if (resultado.getAsJsonArray("visitas") != null) {
            JsonArray listaAsesorias = resultado.getAsJsonArray("visitas");
            if (listaAsesorias.size() > 0) {
                for (JsonElement jo : listaAsesorias) {
                    JsonObject temp = (JsonObject) jo;
                    VisitaRepresentado vr = new VisitaRepresentado();

                    Representados r = RegistrarRepresentados(temp);
                    vr.setInforme(inf);
                    vr.setRepresentado(r);
                    vr.setActivo("S");
                    Integer numero = temp.get("numero_carpeta/causa").getAsInt();
                    Integer anio = temp.get("ano_carpeta/causa").getAsInt();
                    vr.setCarpeta(BuscarCarpeta(numero, anio, idTipoCarpeta, inf.getAdscripcion()));
                    if (temp.get("fecha_visita") != null) {
                        String fechaVisita = temp.get("fecha_visita").getAsString();
                        String infMes = inf.getCveMes().toString();

                        String tempFecha = this.ProcesarFechas(fechaVisita, infMes);
                        Date fechav = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                        vr.setFechaVisita(fechav);
                    } else {
                        String tempFecha = "2021-" + inf.getCveMes() + "-01";
                        Date fechav = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                        vr.setFechaVisita(fechav);
                    }

                    visitasRepresentadosRepository.save(vr);

                }
            } else {
                System.out.println("Visitas sin novedda sin novedad");
            }
        } else {
            System.out.println("visitas Asesorias sin novedad");
        }

    }

    public Carpeta BuscarCarpeta(Integer numero, Integer anio, Integer idTipoCarpeta, Adscripciones ads) {

        Carpeta c = new Carpeta();
        List<Carpeta> lista = carpetasRepository.findByCarpetasPenales(numero, anio, ads.getCveAdscripcion(),
                idTipoCarpeta, "S");
        if (lista.size() > 0) {
            c = lista.get(0);
        } else {
            TiposCarpetas tc = new TiposCarpetas();
            tc.setIdTipoCarpeta(idTipoCarpeta);
            c.setAdscripcion(ads);
            c.setNumero(numero);
            c.setAnio(anio);
            carpetasRepository.save(c);

        }

        return c;

    }

    public AsesoriaInforme RegistrarAsesoriaInforme(JsonObject temp, Informe inf, Representados r)
            throws ParseException {
        AsesoriaInforme ai = new AsesoriaInforme();
        if (temp.get("materia") != null) {
            String descMateria = temp.get("materia").getAsString();
            if (!descMateria.contains("NOVEDAD")) {
                Materia m = this.BuscarMaterias(descMateria);
                String fecha = temp.get("fecha_asesoria").getAsString();
                String infMes = inf.getCveMes().toString();
                String tempFecha = this.ProcesarFechas(fecha, infMes);
                Date fechaAsesoria = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                ai.setMateria(m);
                ai.setRepresentando(r);
                ai.setInforme(inf);
                ai.setFecha_asesoria(fechaAsesoria);
                ai.setActivo("S");
                asesoriasInformesRepository.save(ai);

            }

        }

        return ai;

    }

    public Materia BuscarMaterias(String descMateria) {
        System.out.println("MAteria: " + descMateria);
        Materia m = new Materia();

        List<Materia> lista = materiasRepository.findByDescMateriaLike("%" + descMateria.trim() + "%");
        if (lista.size() > 0)
            m = lista.get(0);
        else {
            m.setActivo("S");
            m.setDescMateria(descMateria);
            materiasRepository.save(m);
        }
        return m;

    }

    public String GuardarCarpeta(JsonObject estatus, Informe inf, Integer idTipoCarpeta) throws ParseException {
        JsonObject resultado = estatus.get("resultado").getAsJsonObject();

        JsonArray listaCarpetas = resultado.getAsJsonArray("carpetas");
        if (listaCarpetas.size() > 0) {

            for (JsonElement jo : listaCarpetas) {
                JsonObject temp = (JsonObject) jo;
                Carpeta c = new Carpeta();
                c.setActivo("S");
                Boolean registrar = true;
                List<Carpeta> lista = new ArrayList<>();

                if (temp.get("No Carpeta Investigacion") != null) {

                    String nuc = temp.get("No Carpeta Investigacion").getAsString();
                    if (!nuc.equals("None")) {
                        c.setNuc(nuc);
                        TiposCarpetas tc = new TiposCarpetas();
                        tc.setIdTipoCarpeta(idTipoCarpeta);
                        lista = carpetasRepository.findByNucLikeAndActivoEqualsAndTipoCarpetaEquals(nuc, "S", tc);
                        System.out.println("nuc de la carpeta: " + nuc);
                        if (lista.size() > 0) {
                            for (Carpeta tc2 : lista) {
                                c = tc2;
                                System.out.println(tc2.getNuc());
                            }
                        } else {

                            System.out.println("Registrando la carpeta");

                            c = this.RegistrarCarpeta(temp, idTipoCarpeta, inf);
                            inf.setEstatusInforme("I");

                        }
                    } else {
                        registrar = false;
                    }
                }

                if (registrar) {
                    Date fechaAudiencia = new Date();
                    if (temp.get("fecha_entrevista") != null) {
                        String fecha = temp.get("fecha_entrevista").getAsString();
                        String infMes = inf.getCveMes().toString();

                        String tempFecha = this.ProcesarFechas(fecha, infMes);
                        fechaAudiencia = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);

                    }

                    RegistrarRepresentadoAudienciasConclusiones(temp, c, inf, fechaAudiencia, idTipoCarpeta);

                }

            }
        } else {
            System.out.println("Informe sin novedad");
            inf.setEstatusInforme("D");

            informesRepository.save(inf);
        }
        return "";
    }

    public Accion BuscarAcciones(String descAccion) {
        Accion a = new Accion();
        if (descAccion.contains("None"))
            descAccion = "OTRA";
        List<Accion> lista = accionesRepository.findByDescAccionLike("%" + descAccion + "%");
        if (lista.size() > 0) {
            a = lista.get(0);
        } else {
            a.setActivo("S");
            a.setDescAccion(descAccion);
            accionesRepository.save(a);
        }
        return a;

    }

    public void RegistrarRepresentadosAcciones(JsonObject temp, Carpeta c, Informe inf, Date fechaAudiencia,
            Integer idTipoCarpeta) throws ParseException {
        // REGISTRAR REPRESENTADOS
        Representados r = this.RegistrarRepresentados(temp);
        // REGISTRARCARPETAS Y REPRESENTADOS
        this.registrarCarpetaRepresentado(c, r);
        // REGISTRAR LA CARPETA EN EL INFORME
        this.RegistrarCarpetasInformes(c, inf);
        // REGISTRAR LAS ACCIONES DE LOS REPRESENTADOS
        Accion ac = new Accion();
        if (temp.get("accion") != null) {
            String descAccion = temp.get("accion").getAsString();
            ac = this.BuscarAcciones(descAccion);
        }
        AccionRepresentado ar = new AccionRepresentado();
        ar.setAccion(ac);
        ar.setRepresentado(r);
        ar.setActivo("S");
        accionesRepresentadosRepository.save(ar);

    }

    @Transactional
    public void RegistrarRepresentadoAudienciasConclusiones(JsonObject temp, Carpeta c, Informe inf,
            Date fechaAudiencia, Integer idTipoCarpeta) throws ParseException {
        // REGISTRAR REPRESENTADOS
        Representados r = this.RegistrarRepresentados(temp);
        // REGISTRARCARPETAS Y REPRESENTADOS
        this.registrarCarpetaRepresentado(c, r);
        // REGISTRAR LA CARPETA EN EL INFORME
        this.RegistrarCarpetasInformes(c, inf);
        // REGISTRAR LOS DELITOS DE LOS REPRESENTADOS
        List<Delitos> ltsDelito = this.BuscarDelito(temp);
        for (Delitos d : ltsDelito) {
            this.RegistrarDelitosRepresentados(r, d);

        }
        // REGISTRAR LAS AUDIENCIAS
        System.out.println("Fecha para audiencia: " + c.getFechaRadicacion());

        if (c.getFechaRadicacion() != null) {
            fechaAudiencia = c.getFechaRadicacion();

        }
        Audiencias a = this.RegistrarAudiencias(temp, fechaAudiencia, c);
        // REGISTRAR AUDIENCIAS REPRESENTADOS

        // REGISTRAR LAS CONCLUSIONES
        this.RegistrarConclusiones(temp, idTipoCarpeta, a, r);
    }

    public DelitosRepresentados RegistrarDelitosRepresentados(Representados r, Delitos d) {

        DelitosRepresentados dr = new DelitosRepresentados();
        dr.setDelito(d);
        dr.setRepresentado(r);
        dr.setActivo("S");
        delitosRepresentadosRepository.save(dr);

        return dr;

    }

    public CarpetaInforme RegistrarCarpetasInformes(Carpeta c, Informe i) {

        CarpetaInforme ci = new CarpetaInforme();
        ci.setCarpeta(c);
        ci.setInforme(i);
        ci.setActivo("S");
        carpetasInformesRepository.save(ci);

        return ci;

    }

    public CarpetaRepresentado registrarCarpetaRepresentado(Carpeta c, Representados r) {
        CarpetaRepresentado cr = new CarpetaRepresentado();
        cr.setCarpeta(c);
        cr.setRepresentado(r);
        cr.setActivo("S");
        carpetasRepresentadosRepository.save(cr);

        return cr;

    }

    public Personal BuscarDefensor(Integer cveUsuario) {
        Personal p = new Personal();
        p.setCveUsuario(cveUsuario);
        List<Personal> lista = personalRepository.findAll(Example.of(p));
        if (lista.size() > 0) {
            p = lista.get(0);
        }

        return p;

    }

    @Transactional
    public Carpeta RegistrarCarpeta(JsonObject temp, Integer idTipoCarpeta, Informe inf) throws ParseException {
        System.out.println("No existe la Carpeta");
        Carpeta c = new Carpeta();
        System.out.println("Tipo Carpeta recibida: " + idTipoCarpeta);
        if (idTipoCarpeta == 1 || idTipoCarpeta == 2) {
            Integer numero = 0;
            Integer anio = 0;
            if (temp.get("numero_carpeta/causa").getAsString().contains("/")) {
                String[] partes = temp.get("numero_carpeta/causa").getAsString().split("/");
                numero = Integer.parseInt(partes[0].replace(".0", "").trim());
            } else {
                String num = temp.get("numero_carpeta/causa").getAsString().replace(" ", "").replace(".0", "").trim();
                numero = Integer.parseInt(num);
            }

            if (temp.get("ano_carpeta/causa").getAsString().contains("/")) {
                String[] partes = temp.get("ano_carpeta/causa").getAsString().split("/");
                anio = Integer.parseInt(partes[1].replace(".0", "").trim());
            } else {
                String num = temp.get("ano_carpeta/causa").getAsString().replace(" ", "").replace(".0", "").trim();
                anio = Integer.parseInt(num);
            }

            c.setNumero(numero);
            c.setAnio(anio);
            c.setActivo("S");
            String fecha = "";
            if (temp.get("fecha_audiencia") != null) {
                fecha = temp.get("fecha_audiencia").getAsString();
            } else if (temp.get("fecha_audiencia0") != null) {
                fecha = temp.get("fecha_audiencia0").getAsString();
            }

            String infMes = inf.getCveMes().toString();
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            tempFecha = tempFecha.replace("\\.", "");
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        } else if (idTipoCarpeta == 8) {

            Integer numero = Integer.parseInt(temp.get("numero_carpeta/causa").getAsString().trim());
            Integer anio = Integer.parseInt(temp.get("ano_carpeta/causa").getAsString().trim());
            c.setNumero(numero);
            c.setAnio(anio);
            c.setActivo("S");
            String fecha = temp.get("Fecha de vinculacion a proceso").getAsString();
            String infMes = inf.getCveMes().toString();
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        } else if (idTipoCarpeta == 5) {
            Integer numero = 0;
            if (temp.get("numero_toca").getAsString().contains("/")) {
                String[] partes = temp.get("numero_toca").getAsString().split("/");
                numero = Integer.parseInt(partes[0].replace(".0", "").trim());
            } else {
                numero = Integer.parseInt(temp.get("numero_toca").getAsString().trim());

            }

            Integer anio = Integer.parseInt(temp.get("ano_toca").getAsString().trim());
            c.setNumero(numero);
            c.setAnio(anio);
            c.setActivo("S");
            String fecha = "";
            String infMes = inf.getCveMes().toString();
            fecha = "2021-" + infMes + "-01";
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        } else if (idTipoCarpeta == 16) {
            String numAdmin = temp.get("numero_expediente").getAsString().trim();
            Integer anio = Integer.parseInt(temp.get("ano_expediente").getAsString());
            c.setNumAdmin(numAdmin);
            c.setAnio(anio);
            String fecha = temp.get("FECHA DE ASISTENCIA").getAsString();
            String infMes = inf.getCveMes().toString();
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        } else if (idTipoCarpeta == 6) {
            String numero = temp.get("numero_causa").getAsString().trim();
            Integer anio = Integer.parseInt(temp.get("ano_causa").getAsString().trim());
            c.setNumAdmin(numero);
            c.setAnio(anio);
            String fecha = "2021-" + BuscarMes(inf.getCveMes() + "01");
            String infMes = inf.getCveMes().toString();
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        } else if (temp.get("No Carpeta Investigacion") != null) {
            String nuc = temp.get("No Carpeta Investigacion").getAsString();
            if (!nuc.equals("None"))
                c.setNuc(nuc);
            else {

            }
        } else if (idTipoCarpeta == 3) {
            if (temp.get("numero_expediente") != null && temp.get("ano_expediente") != null) {
                String[] numeros = temp.get("numero_expediente").getAsString().split("/");
                Integer numero = 0;
                Integer anio = 0;
                System.out.println("numero de partes: " + numeros.length + " " + numeros[0]);

                if (numeros.length < 2) {
                    String num = temp.get("numero_expediente").getAsString().trim();
                    if (num.contains("VEINTICINCO")) {
                        numero = 25;
                    } else if (num.contains("TRES")) {
                        numero = 3;
                    } else {
                        numero = Integer.parseInt(temp.get("numero_expediente").getAsString());
                        anio = temp.get("ano_expediente").getAsInt();

                    }
                    c.setNumero(numero);
                    c.setAnio(anio);

                } else {
                    numero = Integer.parseInt(numeros[0]);
                    anio = Integer.parseInt(numeros[1]);
                    c.setNumero(numero);
                    c.setAnio(anio);

                }
                String fecha = temp.get("fecha_actuacion").getAsString();
                String infMes = inf.getCveMes().toString();
                String tempFecha = this.ProcesarFechas(fecha, infMes);
                Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
                c.setFechaRadicacion(fechaRadicacion);

            }
        } else if (temp.get("no_expediente") != null && temp.get("anio_expediente") != null) {
            String[] numeros = temp.get("no_expediente").getAsString().split("/");
            Integer numero = 0;
            Integer anio = 0;
            System.out.println("numero de partes: " + numeros.length + " " + numeros[0]);

            if (numeros.length < 2) {
                String num = temp.get("no_expediente").getAsString().trim();
                if (num.contains("VEINTICINCO")) {
                    numero = 25;
                } else {
                    numero = Integer.parseInt(temp.get("no_expediente").getAsString());
                    anio = temp.get("anio_expediente").getAsInt();

                }
                c.setNumero(numero);
                c.setAnio(anio);

            } else {
                numero = Integer.parseInt(numeros[0]);
                anio = Integer.parseInt(numeros[1]);
                c.setNumero(numero);
                c.setAnio(anio);

            }
            String descAdscripcion = temp.get("nombre_juzgado").getAsString() + " "
                    + inf.getAdscripcion().getDescAdscripcion();
            Adscripciones ads = this.BuscarRegistrarAdscripcion(descAdscripcion, inf);
            inf.setAdscripcion(ads);
            c.setAdscripcion(ads);

            c.setMateria(this.BuscarMaterias(temp.get("cat_materia").getAsString()));
            if (temp.get("Caravanas por la Justicia (sí o No)") != null) {
                String caravana = temp.get("Caravanas por la Justicia (sí o No)").getAsString().toUpperCase();
                if (caravana.contains("SI"))
                    c.setCaravana("S");
                else if (caravana.contains("NO"))
                    c.setCaravana("N");
            } else {
                c.setCaravana("N");
            }

            String linea = temp.get("En linea").getAsString();
            if (linea.contains("si")) {
                c.setLinea("S");
            } else if (linea.contains("no")) {
                c.setLinea("N");
            }
            String fecha = temp.get("fecha_ingreso_juzgado").getAsString();
            String infMes = inf.getCveMes().toString();
            String tempFecha = this.ProcesarFechas(fecha, infMes);
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);

        }
        if (temp.get("fecha_entrevista") != null) {
            String tempFecha = temp.get("fecha_entrevista").getAsString();
            tempFecha = this.ProcesarFechas(tempFecha, inf.getCveMes().toString());
            Date fechaRadicacion = new SimpleDateFormat("yyyy-MM-dd").parse(tempFecha);
            c.setFechaRadicacion(fechaRadicacion);
        }

        TiposCarpetas tc = new TiposCarpetas();
        tc.setIdTipoCarpeta(idTipoCarpeta);
        List<TiposCarpetas> lista = tipoCarpetasRepository.findAll(Example.of(tc));
        if (lista.size() > 0)
            tc = lista.get(0);
        c.setAdscripcion(inf.getAdscripcion());
        c.setActivo("S");
        c.setPersonal(inf.getPersonal());
        c.setTipoCarpeta(tc);
        if (temp.get("Se ofrecen Pruebas") != null) {
            if (temp.get("Se ofrecen Pruebas").getAsString().equals("SI"))
                c.setPruebas("S");
            else if (temp.get("Se ofrecen Pruebas").getAsString().equals("NO"))
                c.setPruebas("N");
        }

        System.out.println(c.toString());
        carpetasRepository.save(c);

        return c;

    }

    public List<Delitos> BuscarDelito(JsonObject temp) {

        Delitos d = null;
        List<Delitos> listaDelitos = new ArrayList<>();
        String descDelito = "";
        if (temp.get("delito") != null) {
            descDelito = temp.get("delito").getAsString();
            List<Delitos> lista = delitosRepository.findDelitos("%" + descDelito + "%");
            if (lista.size() > 0)
                listaDelitos.add(lista.get(0));

        }
        if (temp.get("delito2") != null) {
            descDelito = temp.get("delito2").getAsString();
            List<Delitos> lista1 = delitosRepository.findDelitos("%" + descDelito + "%");
            if (lista1.size() > 0)
                listaDelitos.add(lista1.get(0));
        }

        if (temp.get("delito3") != null) {
            descDelito = temp.get("delito3").getAsString();
            List<Delitos> lista2 = delitosRepository.findDelitos("%" + descDelito + "%");
            if (lista2.size() > 0)
                listaDelitos.add(lista2.get(0));

        }

        return listaDelitos;

    }

    public Representados RegistrarRepresentados(JsonObject temp) {
        Representados r = new Representados();
        String nombre = "";
        String paterno = "";
        String materno = "";
        if (temp.get("nombre_representado") != null)
            nombre = temp.get("nombre_representado").getAsString();
        if (temp.get("paterno_representado") != null)
            paterno = temp.get("paterno_representado").getAsString();
        if (temp.get("nombre_representado") != null)
            materno = temp.get("materno_representado").getAsString();

        String nombreCompleto = nombre + " " + paterno + " " + materno;
        List<Representados> representados = this.ListaRepresentados(nombreCompleto);

        if (representados.size() > 0) {
            r = representados.get(0);
        } else {
            if (temp.get("municipio") != null) {
                String descMunicipio = temp.get("municipio").getAsString();
                System.out.println("descMunicipio: " + descMunicipio);
                if (!descMunicipio.equals("") && !descMunicipio.equals("None")) {
                    Municipios m = new Municipios();
                    System.out.println();
                    m.setDescMunicipio(descMunicipio);
                    m = this.BuscarMunicipio(m);
                    if (m.getCveMunicipio() != null) {
                        r.setMunicipio(m);
                    }
                }
            }
            String sexo = temp.get("sexo").getAsString();
            if (sexo.equals("HOMBRE"))
                r.setCveSexo(1);
            else if (sexo.equals("MUJER"))
                r.setCveSexo(2);

            if (!temp.get("edad").getAsString().isEmpty() && !temp.get("edad").getAsString().equals("None")
                    && !temp.get("edad").getAsString().equals("NOSETIENE")) {
                Integer edad = 0;
                String edades = temp.get("edad").getAsString().replace(".0", "");
                String fin = edades.replace("ANOS", "").replace("y", "").replace(" ", "").replace("anos", "")
                        .replace("NOSETIENE", "0").trim();
                if (fin.length() <= 3) {
                    edad = Integer.parseInt(fin);
                } else {
                    edad = 0;
                }
                r.setEdad(edad);
            }

            r.setNombre(nombre);
            r.setPaterno(paterno);
            r.setMaterno(materno);
            r.setActivo("S");
            String discapacidad = "";
            if (temp.get("discapacidad").getAsString().equals("None"))
                discapacidad = "";
            else
                discapacidad = temp.get("discapacidad").getAsString();
            r.setDescDiscapacidad(discapacidad);

            if (temp.get("etnia") != null) {
                if (!temp.get("etnia").getAsString().equals("None") && !temp.get("etnia").getAsString().equals("")
                        && !temp.get("etnia").getAsString().equals("Ninguna")) {
                    Etnia e = this.Etnias(temp.get("etnia").getAsString());
                    if (e.getCveEtnia() != null)
                        r.setEtnia(e);
                }
            }

            representadosRepository.save(r);

        }
        return r;

    }

    public Audiencias RegistrarAudiencias(JsonObject temp, Date fecha, Carpeta c) throws ParseException {

        Audiencias a = new Audiencias();
        Integer idTipoCarpeta = c.getTipoCarpeta().getIdTipoCarpeta();
        System.out.println("Tipo de carpeta Recibida" + idTipoCarpeta);
        System.out.println("idCarpeta" + c.getIdCarpeta());
        String observaciones = "";
        if (temp.get("Detenido") != null) {
            String detenido = temp.get("Detenido").getAsString();
            if (detenido.equals("SI"))
                a.setInterno("S");
            else if (detenido.equals("NO"))
                a.setInterno("N");

        }
        if (c.getTipoCarpeta().getIdTipoCarpeta() == 16) {
            observaciones = temp.get("AUTORIDAD SUSTANCIADORA").getAsString();
            observaciones += " " + temp.get("OBSERVACIONES").getAsString();
            a.setInterno("N");
            String tipoAudiencia = temp.get("TIPO DE AUDIENCIA").getAsString();
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);

            a.setTipoAudiencia(ta);
        } else if (c.getTipoCarpeta().getIdTipoCarpeta() == 4) {
            TiposAudiencias ta = this.BuscarTiposAudiencias("entrevista");
            a.setTipoAudiencia(ta);
            observaciones = "REGISTRO MEDIANTE PROCESO AUTOMATIZADO";
        } else if (c.getTipoCarpeta().getIdTipoCarpeta() == 8) {
            observaciones = "REGISTRO MEDIANTE PROCESO AUTOMATIZADO";

            a.setInterno("S");
            String tipoAudiencia = "TRATAMIENTO";
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);
            a.setTipoAudiencia(ta);

        } else if (c.getTipoCarpeta().getIdTipoCarpeta() == 5) {
            observaciones = "REGISTRO MEDIANTE PROCESO AUTOMATIZADO";

            a.setInterno("S");
            String tipoAudiencia = "ACTUACION EN SALA";
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);
            a.setTipoAudiencia(ta);

        } else if (c.getTipoCarpeta().getIdTipoCarpeta() == 10) {
            observaciones = "REGISTRO MEDIANTE PROCESO AUTOMATIZADO";

            a.setInterno("S");
            String tipoAudiencia = "ACTUACION EN SALA ADOLESCENTES";
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);
            a.setTipoAudiencia(ta);

        } else if (idTipoCarpeta == 1 || idTipoCarpeta == 2 || idTipoCarpeta == 7) {

            a.setInterno("S");
            String tipoAudiencia = temp.get("tipo_audiencia").getAsString();
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);

            a.setTipoAudiencia(ta);
        } else if (idTipoCarpeta == 3) {
            observaciones = "REGISTRO MEDIANTE PROCESO AUTOMATIZADO";

            String tipoAudiencia = temp.get("tipoactuacion").getAsString();
            TiposAudiencias ta = this.BuscarTiposAudiencias(tipoAudiencia);
            a.setTipoAudiencia(ta);

        }

        a.setFechaAudiencia(fecha);

        a.setCarpeta(c);
        a.setPersonal(c.getPersonal());

        a.setObservaciones(observaciones);
        a.setActivo("S");

        audienciasRepository.save(a);

        return a;

    }

    public TiposAudiencias BuscarTiposAudiencias(String descTipoAudiencia) {
        TiposAudiencias ta = new TiposAudiencias();
        List<TiposAudiencias> lista = tiposAudienciasRepository
                .findByDescTipoAudienciaLikeAndActivoEquals("%" + descTipoAudiencia + "%", "S");
        if (lista.size() > 0) {
            ta = lista.get(0);
        } else {
            ta.setActivo("S");
            ta.setDescTipoAudiencia(descTipoAudiencia);
            tiposAudienciasRepository.save(ta);
        }
        return ta;

    }

    public Conclusion RegistrarConclusiones(JsonObject temp, Integer idTipoCarpeta, Audiencias a, Representados r) {
        Conclusion c = new Conclusion();
        Boolean encontrado = false;
        if (temp.get("tipo_determinación") != null) {
            String determinacion = temp.get("tipo_determinación").getAsString();
            if (!determinacion.equals("") && !determinacion.equals("None")) {
                String descTipoConclusiones = temp.get("tipo_determinación").getAsString();
                TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
                c.setTipoConclusion(tc);
                encontrado = true;
            }
        }
        if (temp.get("determinación") != null) {
            String determinacion = temp.get("determinación").getAsString();
            if (!determinacion.equals("") && !determinacion.equals("None")) {
                String descTipoConclusiones = temp.get("determinación").getAsString();
                TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
                c.setTipoConclusion(tc);
                encontrado = true;
            }
        }
        if (temp.get("DETERMINACIÓN") != null) {
            String determinacion = temp.get("DETERMINACIÓN").getAsString();
            if (!determinacion.equals("") && !determinacion.equals("None")) {
                String descTipoConclusiones = temp.get("DETERMINACIÓN").getAsString();
                TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
                c.setTipoConclusion(tc);
                encontrado = true;
            }
        }
        if (idTipoCarpeta == 8) {

            String descTipoConclusiones = "NINGUNA";
            TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
            c.setTipoConclusion(tc);
            encontrado = true;

        } else if (idTipoCarpeta == 5) {

            String descTipoConclusiones = "NINGUNA";
            TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
            c.setTipoConclusion(tc);
            encontrado = true;

        }

        if (!encontrado) {
            String descTipoConclusiones = "NINGUNA";
            TiposConclusiones tc = this.buscarTiposConclusiones(descTipoConclusiones, idTipoCarpeta);
            c.setTipoConclusion(tc);

        }

        c.setAudiencia(a);
        c.setRepresentado(r);
        c.setActivo("S");

        conclusionesRepository.save(c);

        return c;

    }

    public TiposConclusiones buscarTiposConclusiones(String descTipoConclusiones, Integer idTipoCarpeta) {

        TiposConclusiones tc = new TiposConclusiones();
        List<TiposConclusiones> lista = tiposConclusionesRepository.findTipoConclusiones(descTipoConclusiones,
                idTipoCarpeta);
        if (lista.size() > 0) {
            tc = lista.get(0);
        } else {
            tc.setDescTipoConclusiones(descTipoConclusiones);
            tc.setActivo("S");
            tc.setIdTipoCarpeta(idTipoCarpeta);
            tiposConclusionesRepository.save(tc);
        }

        return tc;

    }

    public Etnia Etnias(String descEtnia) {
        System.out.println("descEtnia: " + descEtnia);
        Etnia e = new Etnia();
        List<Etnia> lista = etniasRepository.findEtnias("%" + descEtnia + "%");
        if (lista.size() > 0) {
            e = lista.get(0);
        }
        return e;

    }

    public GruposVulnerables GruposVulnerables(String descGrupoVulnerable) {
        GruposVulnerables g = new GruposVulnerables();
        List<GruposVulnerables> lista = gruposVulnerablesRepository
                .findGruposVulnerables("%" + descGrupoVulnerable + "%");
        if (lista.size() > 0) {
            g = lista.get(0);
        }

        return g;

    }

    public Municipios BuscarMunicipio(Municipios m) {

        List<Municipios> lista = municipiosRepository.findDescMunicipios("%" + m.getDescMunicipio() + "%");

        if (lista.size() > 0) {
            return lista.get(0);
        }
        return m;
    }

    public List<Representados> ListaRepresentados(String nombre) {

        return representadosRepository.findRepresentadoPorNombre(nombre);

    }

    @PostMapping("listainformes")
    public ResponseEntity<?> listaInformes() {

        Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        Personal p = new Personal();
        p.setCveUsuario(cveUsuario);
        Informe inf = new Informe();
        inf.setActivo("S");
        inf.setPersonal(p);
        List<Informe> lista = informesRepository.findAll(Example.of(inf));

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    public String WebServicePython(String[] ruta) {
        RestTemplate conector = new RestTemplate();

        String[] json = ruta;
        ResponseEntity<?> respuesta = conector.postForEntity("http://127.0.0.1:5000/ruta", json, String.class);
        // System.out.println("La respuesta es :" + respuesta);

        return respuesta.getBody().toString();

    }

    // Save Files
    private String saveUploadedFiles(MultipartFile[] files, Informe inf) throws IOException {

        // Make sure directory exists!
        String directorio = "opt/integral/static/informes";
        File uploadDir = new File(directorio);
        uploadDir.mkdirs();
        String uploadFilePath = "";
        String nombreArchivo = "";

        StringBuilder sb = new StringBuilder();

        for (MultipartFile file : files) {

            if (file.isEmpty()) {
                continue;
            }
            nombreArchivo = +inf.getIdInforme() + "_" + inf.getPersonal().getCveUsuario() + "_"
                    + inf.getAdscripcion().getCveAdscripcion() + "_" + inf.getCveMes() + ".xlsx";
            uploadFilePath = directorio + "/" + nombreArchivo;
            nombreArchivo = file.getOriginalFilename();

            byte[] bytes = file.getBytes();
            Path path = Paths.get(uploadFilePath);
            Files.write(path, bytes);
            inf.setRutaArchivo(uploadFilePath);
            inf.setEstatusInforme("C");
            sb.append(uploadFilePath).append("<br/>");
            informesRepository.save(inf);
        }
        return nombreArchivo;
    }

    public void uploadFile(MultipartFile file) {
        String path = "reports/";
        if (file.isEmpty()) {

            // throw new StorageException("Failed to store empty file");
        }

        try {
            var fileName = file.getOriginalFilename();
            var is = file.getInputStream();

            Files.copy(is, Paths.get(path + fileName), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {

            var msg = String.format("Failed to store file %f", file.getName());

            // throw new StorageException(msg, e);
        }
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public String ProcesarFechas(String fecha, String infMes) {
        System.out.println("fecha: " + fecha);
        fecha = fecha.replace("virtual", "").replace("|", "").trim();
        if (fecha.contains("ABOGADO PARTICULAR")) {
            fecha = "2021-" + infMes + "-01";
        } else if (fecha.contains("NO SE GENERA")) {
            fecha = "2021-" + infMes + "-01";
        }
        System.out.println("fecha: " + fecha);
        String nuevaFecha = "";
        if (infMes.length() == 1)
            infMes = "0" + infMes;
        if (fecha.contains("/")) {
            String[] partes = fecha.split("/");
            System.out.println("tamaño de fechas: " + partes.length);
            if (partes.length == 2) {
                fecha = "2021/" + infMes + "/01";
                partes = fecha.split("/");

            }
            if (partes.length > 2) {
                nuevaFecha = "2021" + "-" + this.BuscarMes(partes[1].replace(".", "")) + "-"
                        + partes[0];
            } else {
                String[] maspartes = partes[1].split(" ");
                if (maspartes.length > 2)
                    nuevaFecha = "2021" + "-" + this.BuscarMes(maspartes[1].replace(".", ""))
                            + "-" + partes[0];
                else
                    nuevaFecha = "2021-" + this.BuscarMes(maspartes[1].replace(".", "")) + "-" + partes[0];
            }

        } else if (fecha.contains("DE")) {
            String[] partes = fecha.split("DE");
            if (partes.length == 2) {
                fecha = "2021DE" + infMes + "DE01";
                partes = fecha.split("DE");

            }
            if (partes.length > 2) {
                nuevaFecha = partes[2].replace("L", "").replace(".", "") + "-" + this.BuscarMes(partes[1]) + "-"
                        + partes[0];
            } else {
                String[] maspartes = partes[1].split(" ");
                if (maspartes.length > 2)
                    nuevaFecha = maspartes[2].replace("L", "").replace(".", "") + "-" + this.BuscarMes(maspartes[1])
                            + "-" + partes[0];
                else
                    nuevaFecha = "2021-" + this.BuscarMes(maspartes[1]) + "-" + partes[0];
            }

        } else if (fecha.contains("de")) {
            String[] partes = fecha.split("de");
            if (partes.length == 2) {
                fecha = "2021de" + infMes + "Dde01";
                partes = fecha.split("de");

            }
            if (partes.length > 2) {
                nuevaFecha = partes[2].replace(".", "") + "-" + this.BuscarMes(partes[1]) + "-" + partes[0];
            } else {
                String[] maspartes = partes[1].split(" ");
                if (maspartes.length > 2)
                    nuevaFecha = maspartes[2].replace(".", "") + "-" + this.BuscarMes(maspartes[1]) + "-" + partes[0];
                else
                    nuevaFecha = "2021-" + this.BuscarMes(maspartes[1]) + "-" + partes[0];
            }

        } else if (fecha.contains("-")) {
            String[] partes = fecha.split("-");
            if (partes.length == 2) {
                fecha = "2021-" + infMes + "-01";
                partes = fecha.split("-");

            }
            if (partes.length < 3) {
                String[] maspartes = partes[0].split(" ");
                System.out.println("mas partes con - " + maspartes.length);
                if (maspartes.length < 3) {
                    nuevaFecha = "2021" + "-" + infMes + "-01";
                } else if (maspartes.length == 0) {
                    nuevaFecha = "2021" + "-" + this.BuscarMes(maspartes[0]) + "-" + partes[1];

                } else {
                    nuevaFecha = "2021" + "-" + this.BuscarMes(maspartes[0]) + "-"
                            + maspartes[1];
                }

            } else {
                nuevaFecha = fecha;
            }

        } else if (fecha.contains(".")) {
            String[] partes = fecha.split("\\.");
            if (partes.length == 2) {
                fecha = "2021." + infMes + ".01";
                partes = fecha.split("\\.");

            }
            if (partes.length < 3) {
                String[] maspartes = partes[0].split(" ");
                if (maspartes.length < 3)
                    nuevaFecha = partes[1] + "-" + this.BuscarMes(maspartes[0]) + "-" + maspartes[1];
                else
                    nuevaFecha = partes[1] + "-" + this.BuscarMes(maspartes[0]) + "-" + maspartes[1];

            } else {
                nuevaFecha = partes[2] + "-" + this.BuscarMes(partes[1]) + "-" + partes[0];
            }

        } else if (fecha.contains(" ")) {
            String[] partes = fecha.split(" ");

            nuevaFecha = partes[2] + "-" + this.BuscarMes(partes[1]) + "-" + partes[0];

        } else {
            nuevaFecha = "2021-" + infMes + "-01";
        }
        return nuevaFecha;

    }

    public Adscripciones BuscarRegistrarAdscripcion(String descAdscripcion, Informe inf) {

        Adscripciones ads = new Adscripciones();
        List<Adscripciones> lista = adscripcionesRepository.findDescAdscripcion("%" + descAdscripcion + "%");
        if (lista.size() > 0) {
            ads = lista.get(0);
        } else {
            ads.setActivo("S");
            ads.setDescAdscripcion(descAdscripcion);
            ads.setMunicipio(inf.getAdscripcion().getMunicipio());
            ads.setCveDistrito(inf.getAdscripcion().getMunicipio().getCveDistrito());
            ads.setCveRegion(inf.getAdscripcion().getMunicipio().getCveRegion());
            adscripcionesRepository.save(ads);
        }

        return ads;

    }

    public String BuscarMes(String mes) {
        Map<String, String> months = new HashMap<>();
        mes = mes.replace(" ", "");
        if (mes.trim().equals("FREBERO"))
            mes = "FEBRERO";
        else if (mes.trim().equals("01D"))
            mes = "ENERO";
        else if (mes.trim().equals("02D"))
            mes = "FEBRERO";
        else if (mes.trim().equals("I"))
            mes = "ENERO";
        else if (mes.trim().equals("II"))
            mes = "FEBRERO";
        else if (mes.trim().equals("III"))
            mes = "MARZO";
        else if (mes.trim().equals("dieciembre"))
            mes = "DICIEMBRE";
        else if (mes.trim().equals("FEBE"))
            mes = "FEBRERO";
        else if (mes.trim().equals("FEBRRO"))
            mes = "FEBRERO";
        else if (mes.trim().equals("ERERO"))
            mes = "ENERO";

        months.put("01", "enero");
        months.put("02", "febrero");
        months.put("03", "marzo");
        months.put("04", "abril");
        months.put("05", "mayo");
        months.put("06", "junio");
        months.put("07", "julio");
        months.put("08", "agosto");
        months.put("09", "septiembre");
        months.put("10", "octubre");
        months.put("11", "noviembre");
        months.put("12", "diciembre");

        for (Map.Entry<String, String> entry : months.entrySet()) {
            String k = entry.getKey();
            String v = entry.getValue();
            System.out.println("k:" + k + " v: " + v.toUpperCase() + "mes: " + mes);
            if (v.toUpperCase().trim().contains(mes.trim().toUpperCase().trim())) {
                mes = k;
                break;
            } else if (mes.trim().toUpperCase().contains(v.toUpperCase().trim())) {
                mes = k;
                break;
            }

        }

        return mes;

    }

    public TiposCarpetas BuscarTiposCarpetas(String descTipoCarpeta) {

        TiposCarpetas tc = new TiposCarpetas();
        System.out.println("BUSCAR TIPO CARPETA: " + descTipoCarpeta);
        List<TiposCarpetas> lista = tipoCarpetasRepository.findDescTipoCarpeta("%" + descTipoCarpeta + "%");

        if (lista.size() > 0) {
            tc = lista.get(0);
        }

        return tc;

    }

}
