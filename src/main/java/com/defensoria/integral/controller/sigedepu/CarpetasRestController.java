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
import com.defensoria.integral.model.sigedepu2.AudienciaMedidaCautelar;
import com.defensoria.integral.model.sigedepu2.Audiencias;

import com.defensoria.integral.model.sigedepu2.Carpeta;
import com.defensoria.integral.model.sigedepu2.CarpetaRepresentado;
import com.defensoria.integral.model.sigedepu2.Conclusion;
import com.defensoria.integral.model.sigedepu2.Delitos;
import com.defensoria.integral.model.sigedepu2.DelitosRepresentados;
import com.defensoria.integral.model.sigedepu2.Etnia;
import com.defensoria.integral.model.sigedepu2.GruposVulnerables;
import com.defensoria.integral.model.sigedepu2.MedidaCautelares;
import com.defensoria.integral.model.sigedepu2.Recurso;
import com.defensoria.integral.model.sigedepu2.RepresentadoGruposVulnerables;
import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.model.sigedepu2.TiposAudiencias;
import com.defensoria.integral.model.sigedepu2.TiposConclusiones;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.integral.repository.sigedepu2.AdscripcionesCarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.AudienciasMedidasCautelaresRepository;
import com.defensoria.integral.repository.sigedepu2.AudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.CarpetasRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.ConclusionesRepository;
import com.defensoria.integral.repository.sigedepu2.DelitosRepository;
import com.defensoria.integral.repository.sigedepu2.DelitosRepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.EtniasRepository;
import com.defensoria.integral.repository.sigedepu2.GruposVulnerablesRepository;
import com.defensoria.integral.repository.sigedepu2.MedidasCautelaresRepository;
import com.defensoria.integral.repository.sigedepu2.MunicipiosRepository;
import com.defensoria.integral.repository.sigedepu2.RecursosRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentadosGruposVulnerablesRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentadosRepository;
import com.defensoria.integral.repository.sigedepu2.TipoCarpetasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposAudienciasRepository;
import com.defensoria.integral.repository.sigedepu2.TiposConclusionesRepository;
import java.util.List;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author alejandro.gomez alvarado
 */
@RestController
public class CarpetasRestController {

    @Autowired
    CarpetasRepository carpetasRepository;
    @Autowired
    TipoCarpetasRepository tiposCarpetasRepository;
    @Autowired
    DelitosRepository delitosRepository;
    @Autowired
    EtniasRepository etniasRepository;
    @Autowired
    private HttpSession httpSession;
    @Autowired
    CarpetasRepresentadosRepository carpetasRepresentadosRepository;

    @Autowired
    MunicipiosRepository municipiosRepository;

    @Autowired
    RepresentadosRepository representadosRepository;

    @Autowired
    DelitosRepresentadosRepository delitosRepresentadosRepository;

    @Autowired
    TiposAudienciasRepository tiposAudienciasRepository;

    @Autowired
    TiposConclusionesRepository tiposConclusionesRepository;

    @Autowired
    AudienciasRepository audienciasRepository;

    @Autowired
    ConclusionesRepository conclusionesRepository;
    @Autowired
    PersonalRepository personalRepositoty;

    @Autowired
    GruposVulnerablesRepository gruposVulnerablesRepositoty;
    @Autowired
    RecursosRepository recursosRepository;

    @Autowired
    AdscripcionesCarpetasRepository adscripcionesCarpetasRepository;

    @Autowired
    RepresentadosGruposVulnerablesRepository representadosGruposVulnerablesRepository;
     @Autowired
     MedidasCautelaresRepository medidasCautelaresRepository;
     @Autowired
     AudienciasMedidasCautelaresRepository audienciasMedidasCautelaresRepository;

    @PostMapping("/consultarcarpetas")
    public ResponseEntity<?> ConsultaCarpetas(@RequestBody Carpeta c) {
        c.setActivo("S");
        List<Carpeta> listaCarpetas = carpetasRepository.findAll(Example.of(c));
        //System.err.print("listaCarpetas >=>" + c.getAdscripcion().getCveAdscripcion());
        return new ResponseEntity<List<?>>(listaCarpetas, HttpStatus.OK);

    }

    @PostMapping("/guardarcarpetamanual")
    public ResponseEntity<?> GuardarCarpetas(@RequestBody Carpeta c) {
         Integer cveUsuario  = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        if (c.getIdCarpeta() == null) {
           
            Personal personal = new Personal();
            personal.setCveUsuario(cveUsuario);
            c.setPersonal(personal);
        } else {
            Carpeta ct = new Carpeta();
            ct.setIdCarpeta(c.getIdCarpeta());
            List<Carpeta> lista = carpetasRepository.findAll(Example.of(ct));
            if(lista.size()>0)
            ct = lista.get(0);
            c.setPersonal(ct.getPersonal());
        }
        c.setActivo("S");
        carpetasRepository.save(c);

        return new ResponseEntity<>(c, HttpStatus.OK);

    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @PostMapping("/consultarmunicipios")
    public ResponseEntity<?> CargarMunicipios(@RequestBody Municipios m) {

        System.out.println("Nombre municipio => " + m.getDescMunicipio());

        List<Municipios> listaMunicipios = municipiosRepository.findDescMunicipios(m.getDescMunicipio());

        return new ResponseEntity<>(listaMunicipios, HttpStatus.OK);

    }

    @PostMapping("/buscarrepresentados")
    public ResponseEntity<?> BuscarRepresentados(@RequestBody CarpetaRepresentado cr) {

        cr.setActivo("S");
        if (cr.getRepresentado() != null) {
            System.out.println("idRepresentado: " + cr.getRepresentado().getIdRepresentado());
        }

        List<CarpetaRepresentado> listaRepresentados = carpetasRepresentadosRepository.findAll(Example.of(cr));

        return new ResponseEntity<>(listaRepresentados, HttpStatus.OK);

    }

    @PostMapping("/buscaretnias")
    public ResponseEntity<?> BuscarEtnias(@RequestBody Etnia e) {

        List<Etnia> listaEtnias = etniasRepository.findEtnias(e.getDescEtnia());

        return new ResponseEntity<>(listaEtnias, HttpStatus.OK);

    }

    @PostMapping("/buscardelitos")
    public ResponseEntity<?> BuscarDelitos(@RequestBody Delitos d) {

        List<Delitos> listDelitos = delitosRepository.findDelitos(d.getDescDelito());

        return new ResponseEntity<>(listDelitos, HttpStatus.OK);

    }

    @PostMapping("/guardarrepresentado")
    @Transactional
    public ResponseEntity<?> GuardarRepresentado(@RequestBody Representados r) {

        r.setActivo("S");

        representadosRepository.save(r);

        return new ResponseEntity<>(r, HttpStatus.OK);

    }

    @PostMapping("/guardardelitosrepresentado")
    @Transactional
    public ResponseEntity<?> GuardarDelitosRepresentado(@RequestBody List<DelitosRepresentados> dr) {

        delitosRepresentadosRepository.saveAll(dr);

        return new ResponseEntity<>(dr, HttpStatus.OK);

    }

    @PostMapping("/buscaraudiencias")
    public ResponseEntity<?> BuscarAudiencias(@RequestBody TiposAudiencias ta) {
        ta.setActivo("S");

        List<TiposAudiencias> listaTiposAudiencias = tiposAudienciasRepository.findTipoAudiencia(ta.getDescTipoAudiencia(),ta.getIdTipoCarpeta());

        return new ResponseEntity<>(listaTiposAudiencias, HttpStatus.OK);

    }

    @PostMapping("/buscartiposconclusiones")
    public ResponseEntity<?> BuscrTiposConclusiones(@RequestBody TiposConclusiones tc) {
        tc.setActivo("S");

        List<TiposConclusiones> listaTiposConclusiones = tiposConclusionesRepository.findTipoConclusiones(tc.getDescTipoConclusiones(), tc.getIdTipoCarpeta());

        return new ResponseEntity<>(listaTiposConclusiones, HttpStatus.OK);

    }

    @PostMapping("/guardaraudiencia")
    public ResponseEntity<?> GuardarAudiencia(@RequestBody Audiencias a) {
        Integer cveUsuario = Integer.parseInt(getHttpSession().getAttribute("cveUsuario").toString());
        Personal p = new Personal();
        p.setCveUsuario(cveUsuario);
        personalRepositoty.findOne(Example.of(p));
        Integer usuario_id = 0;

        usuario_id = p.getUsuario_id();
        a.setActivo("S");
        a.setCveDefensorSig(usuario_id);
       a.setPersonal(p);

        audienciasRepository.save(a);

        return new ResponseEntity<>(a, HttpStatus.OK);

    }

    @PostMapping("/guardarconclusiones")
    public ResponseEntity<?> GuardarAudiencia(@RequestBody List<Conclusion> conclusiones) {

        conclusionesRepository.saveAll(conclusiones);

        return new ResponseEntity<>(conclusiones, HttpStatus.OK);

    }

    @PostMapping("/buscaraudienciascarpetas")
    public ResponseEntity<?> BuscarAudienciasCarpetas(@RequestBody Audiencias a) {

        List<Audiencias> listaAudiencias = audienciasRepository.findAll(Example.of(a),  Sort.by(Sort.Direction.DESC, "fechaAudiencia"));

        return new ResponseEntity<>(listaAudiencias, HttpStatus.OK);

    }

    @PostMapping("/buscargruposvulnerables")
    public ResponseEntity<?> BuscarGruposVulnerables(@RequestBody GruposVulnerables g) {

        List<GruposVulnerables> listaGruposVulnerables = gruposVulnerablesRepositoty.findGruposVulnerables(g.getDescGrupoVulnerable());

        return new ResponseEntity<>(listaGruposVulnerables, HttpStatus.OK);

    }

    @PostMapping("/guardarrepresentadogrupovulnerable")
    public ResponseEntity<?> GuardarRepresentadoVulnerables(@RequestBody List<RepresentadoGruposVulnerables> rg) {

        representadosGruposVulnerablesRepository.saveAll(rg);

        return new ResponseEntity<>(rg, HttpStatus.OK);

    }

    @PostMapping("/guardarrepresentadocarpeta")
    public ResponseEntity<?> GuardarRepresentadoCarpeta(@RequestBody CarpetaRepresentado cr) {

        carpetasRepresentadosRepository.save(cr);

        return new ResponseEntity<>(cr, HttpStatus.OK);

    }

    @PostMapping("/buscarrecursos")
    public ResponseEntity<?> BuscarRecursos(@RequestBody Recurso r) {

        List<Recurso> listaRecursos = recursosRepository.findDescRecurso(r.getDescRecurso());

        return new ResponseEntity<>(listaRecursos, HttpStatus.OK);

    }

    @PostMapping("/buscaradscripciones2")
    public ResponseEntity<?> BuscarAdscripciones(@RequestBody Adscripciones adscripcion) {
        System.out.println("%" + adscripcion.getDescAdscripcion() + "%");
        List<AdscripcionesCarpetas> result = adscripcionesCarpetasRepository.findDescAdscripcion("%" + adscripcion.getDescAdscripcion() + "%", adscripcion.getCveAdscripcion());

        return new ResponseEntity<List<AdscripcionesCarpetas>>(result, HttpStatus.OK);
    }
    
    @PostMapping("/buscarmedidascautelares")
    public ResponseEntity<?> BuscarMedidasCautelares(@RequestBody MedidaCautelares n) {
        System.out.println("%" + n.getDescMedidaCautelar()+ "%");
        List<MedidaCautelares> result =medidasCautelaresRepository.findDescMedidasCautelares("%" + n.getDescMedidaCautelar() + "%");

        return new ResponseEntity<List<MedidaCautelares>>(result, HttpStatus.OK);
    }
    
    @PostMapping("/guardarmedidas")
    public ResponseEntity<?> GuardarMedidasCautelares(@RequestBody List<AudienciaMedidaCautelar> medidas) {

        audienciasMedidasCautelaresRepository.saveAll(medidas);

        return new ResponseEntity<>(medidas, HttpStatus.OK);

    }

}
