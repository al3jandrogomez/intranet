package com.defensoria.integral.controller.apps;

import java.util.ArrayList;
import java.util.List;

import com.defensoria.integral.model.app.Queja;
import com.defensoria.integral.model.app.Registro;
import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.repository.app.QuejasRepository;
import com.defensoria.integral.repository.app.RegistroRepository;
import com.defensoria.sigedepu.model.Fase;
import com.defensoria.sigedepu.model.Peticionario;
import com.defensoria.sigedepu.model.Representacion;
import com.defensoria.sigedepu.model.SolicitudAtencion;
import com.defensoria.sigedepu.repository.FaseRepository;
import com.defensoria.sigedepu.repository.PeticionarioRepository;
import com.defensoria.sigedepu.repository.RepresentacionRepository;
import com.defensoria.sigedepu.repository.SolicitudAtencionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OperacionesAppRestController {

    @Autowired
    PeticionarioRepository peticionarioRepository;
    @Autowired
    RepresentacionRepository representacionRepository;
    @Autowired
    SolicitudAtencionRepository solicitudAtencionRepository;
    @Autowired
    FaseRepository faseRepository;
    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    QuejasRepository quejasRepository;

    @PostMapping(value = "/asuntosrepresentados")
    public ResponseEntity<?> AtenderPersona(@RequestBody Peticionario p) {

        String numero_oficio_turnar = p.getNombre();
        p.setNombre(null);

        List<Peticionario> lista = peticionarioRepository.findAll(Example.of(p));
        SolicitudAtencion sa = new SolicitudAtencion();
        sa.setPeticionario(lista.get(0));
        Representacion r = new Representacion();

        List<SolicitudAtencion> lista2 = solicitudAtencionRepository.findAll(Example.of(sa));
        Boolean encontrado = false;
        for (SolicitudAtencion so : lista2) {
            if (so.getAsesoria().getRepresentacion() != null) {
                String numero = so.getAsesoria().getRepresentacion().getNumero_oficio_turnar();
                if (numero.equals(numero_oficio_turnar)) {
                    r = so.getAsesoria().getRepresentacion();
                    r.setAsesoria(null);
                    encontrado = true;
                }
            }

        }
        List<Fase> listaFases = null;
        if (encontrado) {
            System.out.println("Representacion encontrada");
            Fase fa = new Fase();
            fa.setRepresentacion(r);
            listaFases = faseRepository.findAll(Example.of(fa));
            System.out.println("total de registros de fase " + listaFases.size());

        } else {
            System.out.println("Representacion no encontrada");
        }

        return new ResponseEntity<>(listaFases, HttpStatus.OK);

    }

    @PostMapping(value = "/buscaroficiosapp")
    public ResponseEntity<?> BuscarOficio(@RequestBody Peticionario p) {

        System.out.println("Buscando oficios de las personas");

        String numero_oficio_turnar = p.getNombre();
        System.out.println("Numero Oficio " + numero_oficio_turnar);
        System.out.println("Curp: " + p.getCurp());

        p.setNombre(null);

        List<Peticionario> lista = peticionarioRepository.findAll(Example.of(p));
        List<Representacion> respuesta = new ArrayList<>();
        for (Peticionario pe : lista) {
            SolicitudAtencion sa = new SolicitudAtencion();
            sa.setPeticionario(pe);
            Representacion r = new Representacion();

            List<SolicitudAtencion> lista2 = solicitudAtencionRepository.findAll(Example.of(sa));

            for (SolicitudAtencion so : lista2) {
                if (so.getAsesoria().getRepresentacion() != null) {
                    // String numero =
                    // so.getAsesoria().getRepresentacion().getNumero_oficio_turnar();
                    r = so.getAsesoria().getRepresentacion();
                    r.setAsesoria(null);
                    respuesta.add(r);

                }

            }

        }

        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    @PostMapping(value = "/buscarfasesapp")
    public ResponseEntity<?> BuscarFases(@RequestBody Representacion r) {

        System.out.println("Buscando oficios de las personas");

        System.out.println("Representacion " + r.getId());
        Fase f = new Fase();
        f.setRepresentacion(r);
        List<Fase> lista = faseRepository.findAll(Example.of(f));

        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping(value = "/registrarusuario")
    public ResponseEntity<?> RegistrarUsuario(@RequestBody Registro r) {

        System.out.println(r.getNombre());
        r.setActivo("S");
        String municipio = r.getMunicipio();
        String pass = r.getContrasena();
        char[] chars = pass.toCharArray();
        Integer key = 5;
        pass = "";
        for (char c : chars) {
            c += key;
            pass += c;
        }
        r.setContrasena(pass);
        // r.setMunicipio(null);
        String activo = "O";
        if (registroRepository.findAll(Example.of(r)).size() == 0) {
            registroRepository.save(r);
        } else {
            activo = "E";
        }

        r = new Registro();
        r.setActivo(activo);
        System.out.println("activo: " + activo);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/buscarusuario")
    public ResponseEntity<?> BuscarUsuario(@RequestBody Registro r) {

        System.out.println(r.getNombre());
        r.setActivo("S");
        String municipio = r.getMunicipio();
        String pass = r.getContrasena();
        char[] chars = pass.toCharArray();
        Integer key = 5;
        pass = "";
        for (char c : chars) {
            c += key;
            pass += c;
        }
        r.setContrasena(pass);
        // r.setMunicipio(null);
        String activo = "O";
        Integer idRegistro=0;
        List<Registro> lista = registroRepository.findAll(Example.of(r));
        if (lista.size() > 0) {
            idRegistro=lista.get(0).getIdRegistro();
            activo = "S";
        } else {
            activo = "E";
        }

        r = new Registro();
        r.setIdRegistro(idRegistro);
        r.setActivo(activo);
        System.out.println("activo: " + activo);

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

    @PostMapping(value = "/guardarqueja")
    public ResponseEntity<?> GuardarQueja(@RequestBody Queja r) {

       
        r.setActivo("S");
       
        quejasRepository.save(r);
       
       

        return new ResponseEntity<>(r, HttpStatus.OK);
    }

}
