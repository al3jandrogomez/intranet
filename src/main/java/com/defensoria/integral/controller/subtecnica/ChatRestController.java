/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.subtecnica;

import com.defensoria.integral.model.adminpersonal.Municipios;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.sigedepu2.Representados;
import com.defensoria.integral.repository.subtecnica.ChatRepository;
import com.defensoria.integral.model.subtecnica.Chat;
import com.defensoria.integral.model.subtecnica.TipoChat;
import com.defensoria.integral.repository.sigedepu2.MunicipiosRepository;
import com.defensoria.integral.repository.sigedepu2.RepresentadosRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;
import com.defensoria.sigedepu.model.Region;
import java.util.List;
import javax.servlet.http.HttpSession;
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
 * @author yo
 */
@RestController
public class ChatRestController {

    @Autowired
    ChatRepository chatRepository;
    @Autowired
    private HttpSession httpSession;

    @Autowired
    MunicipiosRepository municipiosRepository;
    @Autowired
    RepresentadosRepository representadosRepository;
    @Autowired
    PersonalRepository personalRepository;

    @PostMapping("/listachat")
    public ResponseEntity<?> ListaChats(@RequestBody Chat chat) {

        return new ResponseEntity<>(chatRepository.findAll(Example.of(chat)), HttpStatus.OK);

    }

    @PostMapping("/registrarrepresentadochat")
    public ResponseEntity<?> registrarrepresentado(@RequestBody Representados r) {
        r.setActivo("S");
        Municipios m = new Municipios();
        m.setCveMunicipio(r.getMunicipio().getIdMunicipio());
        List<Municipios> lista =municipiosRepository.findAll(Example.of(m));
        r=representadosRepository.save(r);
        if(lista.size()>0){
         m=lista.get(0);
        }
        TipoChat tc = new TipoChat();
        tc.setCveTipoChat(1);
        Chat c = new Chat();
        c.setRepresentado(r);
        c.setActivo("E");
        c.setTipoChat(tc);
        c.setCveRegion(m.getCveRegion());
        System.out.println("cveRegion=> "+m.getCveRegion());
        chatRepository.save(c);

        return new ResponseEntity<>(c, HttpStatus.OK);

    }

    @PostMapping("/listarepresentadoschat")
    public ResponseEntity<?> ListaRepresentadosChat(@RequestBody Chat c) {
        
       List<Chat> lista = chatRepository.findAll(Example.of(c));

        return new ResponseEntity<>(lista, HttpStatus.OK);

    }
    @PostMapping("/terminarasesoria")
    public ResponseEntity<?> TerminarAsesoria(@RequestBody Chat c) {
        Integer idChat = c.getIdChat();
        String  observaciones = c.getObservaciones();
        Chat c1 = new Chat();
        c1.setIdChat(idChat);
       List<Chat> lista = chatRepository.findAll(Example.of(c1));
       if(lista.size()>0){
           c=lista.get(0);
       }
        c.setObservaciones(observaciones);
        c.setActivo("T");
        chatRepository.save(c);
        return new ResponseEntity<>(c, HttpStatus.OK);

    }

    @PostMapping("/atenderpersona")
    public ResponseEntity<?> AtenderPersona(@RequestBody Region r) {
        Chat c = new Chat();
        c.setActivo("E");
        c.setCveRegion(r.getId());
        List<Chat> lista = chatRepository.findAll(Example.of(c),Sort.by(Sort.Direction.ASC, "idChat"));

        Chat c2 = new Chat();
        if (lista.size() > 0) {
            Integer cveUsuario = (Integer) getHttpSession().getAttribute("cveUsuario");
            c2 = lista.get(0);
            c2.setActivo("A");
            Personal p = new Personal();
            p.setCveUsuario(cveUsuario);
            List<Personal> lista2 = personalRepository.findAll(Example.of(p));
            if(lista2.size()>0){
                p=lista2.get(0);
            }
            c2.setPersonal(p);
            chatRepository.save(c2);

        }

        return new ResponseEntity<>(c2, HttpStatus.OK);
    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
