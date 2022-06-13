/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.sigedepu;

import com.defensoria.integral.model.adminpersonal.Adscripciones;
import com.defensoria.integral.model.adminpersonal.Municipios;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.sigedepu2.AdscripcionesCarpetas;
import com.defensoria.integral.model.sigedepu2.Audiencias;
import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;

import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.model.sigedepu2.TiposAudiencias;
import com.defensoria.integral.model.sigedepu2.TiposCarpetas;
import com.defensoria.integral.repository.adminpersonal.AdscripcionesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.sigedepu2.AdscripcionesCarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.AudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.TipoCarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposAudienciasRepository;
import com.defensoria.sigedepu.model.Defensa;

import com.defensoria.sigedepu.model.Usuario;
import com.defensoria.sigedepu.repository.DefensaRepository;
import com.defensoria.sigedepu.repository.UsuariosRepository;

import java.text.ParseException;

import java.util.ArrayList;

import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
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
public class ControlOralRestController {

    @Autowired
    UsuariosRepository usuariosRepository;
    @Autowired
    DefensaRepository defensaRepository;
    @Autowired
    AdscripcionesRepository adscripcionesRepository;
    @Autowired
    TipoCarpetasRepository tiposCarpetasRepository;
    @Autowired
    TiposAudienciasRepository tiposAudienciasRepository;
    @Autowired
    AdscripcionesCarpetasRepository adscripcionesCarpetasRepository;
    @Autowired
    RepresentadosRepository representadosRepository;
    @Autowired
    CarpetasRepresentadosRepository carpetasRepresentadosRepository;
    @Autowired
    PersonalRepository personalRepository;

    @Autowired
    CarpetasRepository carpetasRepository;

    @Autowired
    AudienciasRepository audienciasRepository;

    @Autowired
    private HttpSession httpSession;

    @PostMapping(value = "/buscarusuariossigedepu")
    public ResponseEntity<?> BuscarUsuarios(@RequestBody Usuario usuario) {
        System.out.println("nombre completo " + usuario.getNombre_completo());
        List<Usuario> listaUsuarios = usuariosRepository.findByUsuario(usuario.getNombre_completo());

        return new ResponseEntity<>(listaUsuarios, HttpStatus.OK);
    }

    @PostMapping(value = "/buscardefensassigedepu")
    public ResponseEntity<?> BuscarDefensas() throws ParseException {
       
        Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        Personal p = new Personal();
        p.setCveUsuario(cveUsuario);
        personalRepository.findOne(Example.of(p));
        Integer usuario_id = 0;

        usuario_id = p.getUsuario_id();
        Audiencias a = new Audiencias();
        a.setPersonal(p);
        List<Integer> claveDefensas = new ArrayList<>();

        List<Audiencias> listaAudiencias = audienciasRepository.findAll(Example.of(a));

        for (Audiencias au : listaAudiencias) {
            claveDefensas.add(au.getDefensa_id());
            usuario_id = au.getCveDefensorSig();
        }
        if (claveDefensas.size() == 0) {
            claveDefensas.add(0);
        }
        List<Defensa> listaDefensas = defensaRepository.listaDefensasPendientes(usuario_id, claveDefensas);

        return new ResponseEntity<>(listaDefensas, HttpStatus.OK);

    }

    @PostMapping(value = "/listajuzgados")
    public ResponseEntity<?> ListaJuzgados(@RequestBody Adscripciones ads) throws ParseException {

        List<Adscripciones> listaAds = adscripcionesRepository.findDescAdscripcion("%" + ads.getDescAdscripcion() + "%");

        return new ResponseEntity<>(listaAds, HttpStatus.OK);

    }

    @PostMapping(value = "/listajuzgadostipo")
    public ResponseEntity<?> ListaJuzgadosTipo(@RequestBody AdscripcionesCarpetas ac) throws ParseException {

        List<AdscripcionesCarpetas> listaAds = adscripcionesCarpetasRepository.findDescAdscripcion(ac.getAdscripcion().getDescAdscripcion(), ac.getTipoCarpeta().getIdTipoCarpeta());

        return new ResponseEntity<>(listaAds, HttpStatus.OK);

    }

    @PostMapping(value = "/listatipocarpeta")
    public ResponseEntity<?> ListaTipoCarpeta(@RequestBody TiposCarpetas tc) throws ParseException {

        List<TiposCarpetas> listaAds = tiposCarpetasRepository.findDescTipoCarpeta(tc.getDescTipoCarpeta());

        return new ResponseEntity<>(listaAds, HttpStatus.OK);

    }

    @PostMapping(value = "/sincronizarusuariosigedepu")
    public void SincronizarUsuarios() throws ParseException {
        Personal p = new Personal();
        p.setActivo("S");
        List<Personal> listaPersonal = personalRepository.findAll(Example.of(p));
        String nombrePersonal = "";
        String nombreUsuario = "";
        Usuario u = new Usuario();
        u.setActivo(1);
        List<Usuario> listaUsuario = usuariosRepository.findAll(Example.of(u));

        if (listaPersonal.size() > 0 && listaUsuario.size() > 0) {
            for (Personal pe : listaPersonal) {
                if (pe.getUsuario_id() == null) {
                    nombrePersonal = pe.getNombre() + " " + pe.getPaterno() + " " + pe.getMaterno();

                    for (Usuario us : listaUsuario) {
                        nombreUsuario = us.getNombre() + " " + us.getPaterno() + " " + us.getMaterno();

                        if (nombrePersonal.equals(nombreUsuario)) {
                            pe.setUsuario_id(us.getId());
                            listaUsuario.remove(us);
                            personalRepository.save(pe);
                            break;
                        }

                    }
                }

            }

        }
    }

    @Transactional
    @PostMapping(value = "/guardarCarpetasigedepu2")
    public ResponseEntity<?> GuardarCarpetaSigedepu(@RequestBody Carpeta cr) throws ParseException {
        Integer defensa_id = cr.getPersonal().getCveUsuario();
        Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        cr.setPersonal(null); 

        Defensa d = new Defensa();
        d.setId(defensa_id);
        //SE BUSCA EL REGISTRO EN  SIGEDEPU
       defensaRepository.findOne(Example.of(d));
       Defensa de = d;

        if (!de.equals(null)) {
// BUSCAMOS LA CARPETA
            List<Carpeta> listaCarpetas = carpetasRepository.findAll(Example.of(cr));
            if (listaCarpetas.size() > 0) {
                cr = listaCarpetas.get(0);

            } else {
                //EN CASO DE NO EXISTIR SE GENERA UNA NUEVA
                cr.setActivo("S");
                cr.getPersonal().setCveUsuario(cveUsuario);
                cr.setNuc(de.getNuc());
                carpetasRepository.save(cr);

            }
// OBTENER LISTA DE REPRESENTADOS DE LA CARPETA
            CarpetaRepresentado car = new CarpetaRepresentado();
            car.setCarpeta(cr);
            List<CarpetaRepresentado> listaRepresentados = carpetasRepresentadosRepository.findAll(Example.of(car));
            Representados r = new Representados();

            Municipios municipio = new Municipios();
            Integer cveSexo = 1;

            if (listaRepresentados.size() == 0) {

                r.setNombre(de.getSolicitud().getPeticionario().getNombre());
                r.setPaterno(de.getSolicitud().getPeticionario().getPaterno());
                r.setMaterno(de.getSolicitud().getPeticionario().getMaterno());
                if (de.getSolicitud().getPeticionario().getMunicipio_id() != null) {
                    municipio.setIdMunicipio(de.getSolicitud().getPeticionario().getMunicipio_id());
                    r.setMunicipio(municipio);
                }
                r.setActivo("S");
                r.setFechaNacimiento(de.getSolicitud().getPeticionario().getFecha_nacimiento());
                if (de.getSolicitud().getPeticionario().getSexo().equals("MUJER")) {
                    cveSexo = 2;
                }
                r.setCveSexo(cveSexo);
                r.setEdad(de.getSolicitud().getPeticionario().getEdad());
//                representadosRepository.save(r);
                car.setRepresentado(r);
                car.setActivo("S");
                carpetasRepresentadosRepository.save(car);

            } else {

                String nombre = de.getSolicitud().getPeticionario().getNombre() + " "
                        + de.getSolicitud().getPeticionario().getPaterno() + " "
                        + de.getSolicitud().getPeticionario().getMaterno();
                Boolean encontrado = false;
                for (CarpetaRepresentado res : listaRepresentados) {
                    String nombreRepresentado = res.getRepresentado().getNombre() + " " + res.getRepresentado().getPaterno() + " " + res.getRepresentado().getMaterno();
                    if (nombreRepresentado.equals(nombre)) {
                        encontrado = true;
                        break;
                    }

                }
                if (!encontrado) {
                    r.setNombre(de.getSolicitud().getPeticionario().getNombre());
                    r.setPaterno(de.getSolicitud().getPeticionario().getPaterno());
                    r.setMaterno(de.getSolicitud().getPeticionario().getMaterno());
                    if (de.getSolicitud().getPeticionario().getMunicipio_id() != null) {
                        municipio.setIdMunicipio(de.getSolicitud().getPeticionario().getMunicipio_id());
                        r.setMunicipio(municipio);
                    }
                    r.setFechaNacimiento(de.getSolicitud().getPeticionario().getFecha_nacimiento());
                    if (de.getSolicitud().getPeticionario().getSexo().equals("MUJER")) {
                        cveSexo = 2;
                    }
                    r.setCveSexo(cveSexo);
                    r.setEdad(de.getSolicitud().getPeticionario().getEdad());
                    r.setActivo("S");
                    car.setRepresentado(r);
                    car.setActivo("S");
                    carpetasRepresentadosRepository.save(car);
                }

            }

//OBTENER LA CLAVE PARA LA AUDIENCIA
            Audiencias a = new Audiencias();
            String desTipoAudiencia = de.getTipo_audiencia();
//            List<TiposAudiencias> listaAudiencias = tiposAudienciasRepository.findTipoAudiencia("%" + desTipoAudiencia + "%");
                       List<TiposAudiencias> listaAudiencias = new ArrayList<TiposAudiencias>() ;
            TiposAudiencias ta = new TiposAudiencias();
            if (listaAudiencias.size() == 0) {

                ta.setDescTipoAudiencia(desTipoAudiencia);
                ta.setActivo("S");
                tiposAudienciasRepository.save(ta);
            } else {
                ta = listaAudiencias.get(0);
            }
            a.setDefensa_id(defensa_id);
            a.setFechaAudiencia(de.getFecha_presentacion());
            a.setTipoAudiencia(ta);
            a.setCarpeta(cr);
            a.setActivo("S");
            a.setObservaciones("");
            a.setCveDefensorSig(de.getUsuario_id());
//            a.setPersonal(cveUsuario);

            audienciasRepository.save(a);
        }
        return new ResponseEntity<>(cr, HttpStatus.OK);

    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
