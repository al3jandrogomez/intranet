package com.defensoria.integral.controller.adminpersonal;

import java.text.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.defensoria.integral.model.adminpersonal.Formularios;
import com.defensoria.integral.model.adminpersonal.Modulos;
import com.defensoria.integral.model.adminpersonal.MovAdscripciones;
import com.defensoria.integral.model.adminpersonal.NPersonalC;

import com.defensoria.integral.model.adminpersonal.PermisosPerfiles;
import com.defensoria.integral.model.adminpersonal.PermisosPersonal;
import com.defensoria.integral.model.adminpersonal.Personal;
import com.defensoria.integral.model.adminpersonal.PersonalPerfiles;
import com.defensoria.integral.repository.adminpersonal.FormulariosRepository;
import com.defensoria.integral.repository.adminpersonal.ModulosRepository;
import com.defensoria.integral.repository.adminpersonal.PerfilesRepository;
import com.defensoria.integral.repository.adminpersonal.PermisosPerfilesRepository;
import com.defensoria.integral.repository.adminpersonal.PermisosPersonalRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalPerfilesRepository;
import com.defensoria.integral.repository.adminpersonal.PersonalRepository;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.Objects;
import javax.servlet.http.HttpSession;
//import com.defensoria.sigedepu.model.Representacion;
//import com.defensoria.sigedepu.repository.RepresentacionRepository;

@RestController
public class LoginRestController {

    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PermisosPerfilesRepository permisosPerfilesRepository;
    @Autowired
    HttpSession httpSession;
    @Autowired
    PersonalPerfilesRepository personalPerfilesRepository;
    @Autowired
    ModulosRepository modulosRepository;
    @Autowired
    FormulariosRepository formulariosRepository;
    @Autowired
    PerfilesRepository perfilesRepository;
    @Autowired
    PermisosPersonalRepository permisosPersonalRepository;

//	@Autowired
//	RepresentacionRepository representacionRepository;
    @PostMapping(value = "/loginuser")
    public ResponseEntity<?> LoginUser(@RequestBody Personal personal) throws ParseException, IOException, URISyntaxException {
        Personal tempPersona = new Personal();
        Map<String, Object> modulos = new HashMap<>();

        // USUARIO NO LOGUEADO
        if (getHttpSession().getAttribute("cveUsuario") == null) {

//            try {
//                 
//                 
//                String canonicalPath = new File(".").getCanonicalPath();
//                System.out.println("Current directory path using canonical path method :- " + canonicalPath);
//
//                String usingSystemProperty = System.getProperty("user.dir");
//                System.out.println("Current directory path using system property:- " + usingSystemProperty);
//
//            } catch (IOException e) {
//                System.out.println("IOException Occured" + e.getMessage());
//            }

           

            tempPersona.setUsuario(personal.getUsuario());
            tempPersona.setContrasena(personal.getContrasena());
            tempPersona.setActivo("S");
            MovAdscripciones movAdscripciones = new MovAdscripciones();
            movAdscripciones.setActivo("S");
            List<MovAdscripciones> listaMovAdscripciones = new ArrayList<>();
            listaMovAdscripciones.add(movAdscripciones);
            tempPersona.setMovAdscripciones(listaMovAdscripciones);
            Integer defensor = 0;
            if (!personal.getUsuario().equals("") && !personal.getContrasena().equals("")) {

                List<Personal> listaPersonas = personalRepository.findAll(Example.of(tempPersona));
                // EXISTE USUARIO
                if (listaPersonas.size() > 0) {
                    List<Personal> adscripciones = personalRepository.Adscripciones(listaPersonas.get(0).getCveUsuario());
                    System.out.println("numero de movimientos =>" + adscripciones.size());
                    getHttpSession().setAttribute("cveUsuario", listaPersonas.get(0).getCveUsuario());
                    for (MovAdscripciones ads : adscripciones.get(0).getMovAdscripciones()) {
                        System.out.println("movimiento ads " + ads.getIdMovAdscripcion() + " Estatus " + ads.getActivo());
                        if (ads.getActivo().equals("S")) {
                            if ((ads.getCvePerfilPersonal() != 8 && ads.getCvePerfilPersonal() != 11) && (ads.getCvePuesto() == 9) && (ads.getCvePerfilPersonal() > 6 && ads.getCvePerfilPersonal() < 13)) {
                                defensor = 1;
                            }
                            getHttpSession().setAttribute("cveAdscripcion", ads.getAdscripcion().getCveAdscripcion());
                            getHttpSession().setAttribute("descAdscripcion", ads.getAdscripcion().getDescAdscripcion());
                            getHttpSession().setAttribute("cveRegion", ads.getAdscripcion().getCveRegion());
                            getHttpSession().setAttribute("defensor", defensor);
                        }
                    }
                    getHttpSession().setAttribute("nombre", listaPersonas.get(0).getNombre() + " "
                            + listaPersonas.get(0).getPaterno() + " " + listaPersonas.get(0).getMaterno());
                    modulos.put("cveUsuario", listaPersonas.get(0).getCveUsuario());
                    modulos.put("nombre", listaPersonas.get(0).getNombre() + " " + listaPersonas.get(0).getPaterno()
                            + " " + listaPersonas.get(0).getMaterno());

                    modulos.put("cveAdscripcion", getHttpSession().getAttribute("cveAdscripcion"));
                    modulos.put("descAdscripcion", getHttpSession().getAttribute("descAdscripcion"));
                    modulos.put("idRegion", getHttpSession().getAttribute("cveRegion"));

                    modulos.put("defensor", getHttpSession().getAttribute("defensor"));

                    modulos.put("mensaje", "");

                    modulos.put("estatus", 1);

                    List<Modulos> listaModulos = this.ListarModulos(listaPersonas.get(0).getCveUsuario());

                    modulos.put("modulos", listaModulos);
                    System.out.println("Inicio de Sesion de usuario=>" + getHttpSession().getAttribute("cveUsuario"));

                } else {
                    // NO EXISTE EL USUARIO
                    System.out.println("No existe usuario ");
                    modulos.put("cveUsuario", "");
                    modulos.put("mensaje", "Usuario o contraseña no existe favor de verificar");
                    modulos.put("modulos", "");
                    modulos.put("estatus", 2);

                }
            } else {
                modulos.put("cveUsuario", "");
                modulos.put("mensaje", "Usuario o contraseña no pueden estar vacios favor de verificar");
                modulos.put("modulos", "");
                modulos.put("estatus", 2);
            }

        } else {
            // USUARIO LOGUEADO
            modulos.put("cveUsuario", getHttpSession().getAttribute("cveUsuario"));
            modulos.put("cveAdscripcion", getHttpSession().getAttribute("cveAdscripcion"));
            modulos.put("descAdscripcion", getHttpSession().getAttribute("descAdscripcion"));
            modulos.put("idRegion", getHttpSession().getAttribute("cveRegion"));
            modulos.put("nombre", getHttpSession().getAttribute("nombre"));
            modulos.put("mensaje", "");
            modulos.put("defensor", getHttpSession().getAttribute("defensor"));
            List<Modulos> listaModulos = this.ListarModulos((Integer) getHttpSession().getAttribute("cveUsuario"));

            modulos.put("modulos", listaModulos);
            modulos.put("estatus", 3);

            System.out.println("Sesion de usuario ya iniciada =>" + getHttpSession().getAttribute("cveUsuario"));

        }

        return new ResponseEntity<Map<String, Object>>(modulos, HttpStatus.OK);

    }

    @PostMapping(value = "/logoutuser")
    public ResponseEntity<?> LogoutUser() {
        System.out.println("Cerrando Sesion de usuario=>" + getHttpSession().getAttribute("cveUsuario"));

        getHttpSession().removeAttribute("cveUsuario");
        getHttpSession().removeAttribute("cveAdscripcion");
        getHttpSession().removeAttribute("descAdscripcion");
        getHttpSession().removeAttribute("nombre");
        Personal tempPersona = new Personal();

        return new ResponseEntity<Personal>(tempPersona, HttpStatus.OK);
    }

    @PostMapping(value = "/opcionesmenu")
    public ResponseEntity<?> OpcionesMenu(@RequestBody Formularios formulario) {
        Integer cveUsuario = (Integer) getHttpSession().getAttribute("cveUsuario");
        // this.ListarModulos(cveUsuario);
        PersonalPerfiles personalPerfiles = new PersonalPerfiles();
        Personal personal = new Personal();
        personal.setCveUsuario(cveUsuario);
        personalPerfiles.setPersonal(personal);

        List<Formularios> listaFormulario = this.ListarMenu(formulario.getModulos().getCveModulo(), cveUsuario);
        
         Collections.sort(listaFormulario); 
         
        // System.out.println("primer objeto de formulario
        // "+listaFormulario.get(0).getPermisosPerfiles().get(0).getFormularios().getDescFormulario());

        // listaFormulario = new ArrayList<>();
        return new ResponseEntity<List<Formularios>>(listaFormulario, HttpStatus.OK);
    }

    @PostMapping(value = "/opcionesform")
    public ResponseEntity<?> OpcionesFormulario(@RequestBody Formularios formulario) {

        List<Formularios> listaFormulario = formulariosRepository.findAll(Example.of(formulario));
        return new ResponseEntity<List<Formularios>>(listaFormulario, HttpStatus.OK);
    }

    @PostMapping("/permisosusuario")
    public ResponseEntity<?> ListaPermisosUsuarios(@RequestBody NPersonalC npersonalC) {
        Modulos modulo = new Modulos();
        modulo.setActivo("S");
        List<Modulos> listaModulo = modulosRepository.findAll(Example.of(modulo));
        return new ResponseEntity<List<Modulos>>(listaModulo, HttpStatus.OK);

    }

    @PostMapping("/permisoeditar")
    public ResponseEntity<?> PermisoEditar(@RequestBody Formularios frm) {
        System.out.println("formulario para editar" + frm.getCveFormulario());
        PermisosPersonal permisoPersonal = new PermisosPersonal();
        permisoPersonal.setCveUsuario(Integer.parseInt(frm.getRutaFormulario()));
        frm.setRutaFormulario(null);

        permisoPersonal.setFormulario(frm);
        List<PermisosPersonal> listaPermisos = permisosPersonalRepository.findAll(Example.of(permisoPersonal));
        System.out.println("total permisos encontrados =>" + listaPermisos.size());
        if (listaPermisos.size() > 0) {
            for (PermisosPersonal pp : listaPermisos) {
                permisoPersonal = pp;
                if (pp.getCrear().equals("S")) {
                    permisoPersonal.setCrear("N");

                } else {
                    permisoPersonal.setCrear("S");
                }
                System.out.println("verificando copia de permiso " + permisoPersonal.getIdPermisoPersonal());
            }

        } else {
            permisoPersonal.setCrear("S");
            permisoPersonal.setActivo("S");
        }
        permisosPersonalRepository.save(permisoPersonal);

        return new ResponseEntity<Formularios>(frm, HttpStatus.OK);
    }

    @PostMapping("/permisoborrar")
    public ResponseEntity<?> PermisoBorrar(@RequestBody Formularios frm) {
        System.out.println("formulario para borrar" + frm.getCveFormulario());

        PermisosPersonal permisoPersonal = new PermisosPersonal();
        permisoPersonal.setCveUsuario(Integer.parseInt(frm.getRutaFormulario()));
        frm.setRutaFormulario(null);

        permisoPersonal.setFormulario(frm);
        List<PermisosPersonal> listaPermisos = permisosPersonalRepository.findAll(Example.of(permisoPersonal));
        System.out.println("total permisos encontrados =>" + listaPermisos.size());
        if (listaPermisos.size() > 0) {
            for (PermisosPersonal pp : listaPermisos) {
                permisoPersonal = pp;
                if (pp.getBorrar().equals("S")) {
                    permisoPersonal.setBorrar("N");

                } else {
                    permisoPersonal.setBorrar("S");
                }
                System.out.println("verificando copia de permiso " + permisoPersonal.getIdPermisoPersonal());
            }

        } else {
            permisoPersonal.setCrear("N");
            permisoPersonal.setBorrar("S");
            permisoPersonal.setBuscar("N");
            permisoPersonal.setActivo("S");

        }
        permisosPersonalRepository.save(permisoPersonal);

        return new ResponseEntity<Formularios>(frm, HttpStatus.OK);

    }

    @PostMapping("/permisoconsultar")
    public ResponseEntity<?> Permisoconsultar(@RequestBody Formularios frm) {
        System.out.println("formulario para consultar" + frm.getCveFormulario());
        PermisosPersonal permisoPersonal = new PermisosPersonal();
        permisoPersonal.setCveUsuario(Integer.parseInt(frm.getRutaFormulario()));
        frm.setRutaFormulario(null);

        permisoPersonal.setFormulario(frm);
        List<PermisosPersonal> listaPermisos = permisosPersonalRepository.findAll(Example.of(permisoPersonal));
        System.out.println("total permisos encontrados =>" + listaPermisos.size());
        if (listaPermisos.size() > 0) {
            for (PermisosPersonal pp : listaPermisos) {
                permisoPersonal = pp;
                if (pp.getBuscar().equals("S")) {
                    permisoPersonal.setBuscar("N");

                } else {
                    permisoPersonal.setBuscar("S");
                }
                System.out.println("verificando copia de permiso " + permisoPersonal.getIdPermisoPersonal());
            }

        } else {
            permisoPersonal.setCrear("N");
            permisoPersonal.setBorrar("N");
            permisoPersonal.setBuscar("S");
            permisoPersonal.setActivo("S");

        }
        permisosPersonalRepository.save(permisoPersonal);

        return new ResponseEntity<Formularios>(frm, HttpStatus.OK);

    }

    @PostMapping("/listaformularios")
    public ResponseEntity<?> ListaFormulariosModulo(@RequestBody NPersonalC npersonalC) {
        Modulos modulo = new Modulos();
        Formularios formulario = new Formularios();
        modulo.setCveModulo(npersonalC.getCveModulo());
       // Integer contador = 0;

        PersonalPerfiles personalPerfiles = new PersonalPerfiles();
        Personal personal = new Personal();
        System.out.println("clave Usuario =" + npersonalC.getCveUsuario());
        personal.setCveUsuario(npersonalC.getCveUsuario());
        personalPerfiles.setPersonal(personal);
        List<PersonalPerfiles> listaPersonalPerfiles = personalPerfilesRepository.findAll(Example.of(personalPerfiles));

        PermisosPersonal permisosPersonal = new PermisosPersonal();
        permisosPersonal.setCveUsuario(npersonalC.getCveUsuario());
        Formularios frm = new Formularios();
        Modulos mod = new Modulos();
        mod.setCveModulo(npersonalC.getCveModulo());
        frm.setModulos(modulo);
        permisosPersonal.setFormulario(frm);

        List<PermisosPersonal> listaPermisosPersonal = permisosPersonalRepository.findAll(Example.of(permisosPersonal));

        formulario.setModulos(modulo);
        List<Formularios> listaFormulario = formulariosRepository.findAll(Example.of(formulario));
        List<Map<String, Object>> listaParams = new ArrayList<>();
        System.out.println("|========== PERMISOS PERFILES =============|");
        String crear = "";
        String borrar = "";
        String buscar = "";
      //  String cveFormulario = "";
      //  String descFormulario = "";

        for (Formularios f : listaFormulario) {
            crear = "N";
            borrar = "N";
            buscar = "N";

            Map<String, Object> form = new HashMap<>();
           // Boolean encontrado = false;
            for (PermisosPersonal pp : listaPermisosPersonal) {
                if (Objects.equals(f.getCveFormulario(), pp.getFormulario().getCveFormulario())) {
             //       encontrado = true;
                    crear = pp.getCrear();
                    borrar = pp.getBorrar();
                    buscar = pp.getBuscar();
                    break;
                }

            }

            for (PersonalPerfiles pp : listaPersonalPerfiles) {
                for (PermisosPerfiles per : pp.getPerfil().getPermisosPerfiles()) {
                    if (Objects.equals(f.getCveFormulario(), per.getFormulario().getCveFormulario())) {
               //         encontrado = true;
                        crear = per.getCrear();
                        borrar = per.getBorrar();
                        buscar = per.getBuscar();
                        break;
                    }

                }
            }

            form.put("cveFormulario", f.getCveFormulario());
            form.put("descFormulario", f.getDescFormulario());

            form.put("crear", crear);
            form.put("borrar", borrar);
            form.put("buscar", buscar);
            listaParams.add(form);
        }

        return new ResponseEntity<>(listaParams, HttpStatus.OK);

    }
    // @PostMapping("/listamodulos")

    public List<Modulos> ListarModulos(Integer cveUsuario) {

        List<Modulos> modulos = new ArrayList<>();
        PersonalPerfiles personalPerfiles = new PersonalPerfiles();
        Personal personal = new Personal();
        personal.setCveUsuario(cveUsuario);
        personalPerfiles.setPersonal(personal);
        PermisosPersonal permisosPersonal = new PermisosPersonal();
        permisosPersonal.setCveUsuario(cveUsuario);
        List<PersonalPerfiles> listaPersonalPerfiles = personalPerfilesRepository.findAll(Example.of(personalPerfiles));
        List<PermisosPersonal> listaPermisosPersonal = permisosPersonalRepository.findAll(Example.of(permisosPersonal));
        boolean encontrado = false;
        if (listaPersonalPerfiles.size() > 0) {
            for (PersonalPerfiles personalPerfil : listaPersonalPerfiles) {
                encontrado = false;
                if (personalPerfil.getPerfil().getCvePerfil() > 0) {

                    if (personalPerfil.getPerfil().getPermisosPerfiles().size() > 0) {
                        for (PermisosPerfiles pp : personalPerfil.getPerfil().getPermisosPerfiles()) {
                            encontrado = false;
                            System.out.println("lista de modulos " + modulos.size());
                            if (modulos.size() > 0) {
                                for (Modulos mod : modulos) {
                                    System.out.println("modulo: " + mod.getDescModulo());
                                    if (mod.getCveModulo() == pp.getFormulario().getModulos().getCveModulo()) {
                                        encontrado = true;
                                        System.out.println("ya se ingreso el modulo: " + mod.getCveModulo()
                                                + "Origen PermisosPerfil");
                                        break;
                                    }

                                }
                                if (!encontrado) {
                                    modulos.add(pp.getFormulario().getModulos());
                                }
                            } else {
                                modulos.add(pp.getFormulario().getModulos());
                            }

                        }
                    }
                }

            }
        }
        System.out.println("modulos de permisos personal");

        if (listaPermisosPersonal.size() > 0) {
            for (PermisosPersonal permisopersonal : listaPermisosPersonal) {
                encontrado = false;
                // System.out.println("numero de
                // formularios"+permisopersonal.getFormularios().size());
                // if (permisopersonal.getFormularios().size() > 0) {
                System.out.println("numero de modulos" + modulos.size());
                if (modulos.size() > 0) {
                    for (Modulos modulo : modulos) {
                        System.out.println("modulo encontrado" + modulo.getDescModulo());
                        if (modulo.getCveModulo() == permisopersonal.getFormulario().getModulos().getCveModulo()) {
                            encontrado = true;
                            break;
                        }
                    }
                    if (!encontrado) {
                        modulos.add(permisopersonal.getFormulario().getModulos());
                    }
                } else {
                    modulos.add(permisopersonal.getFormulario().getModulos());
                }
                // }
            }
        }

        return modulos;

    }

    public List<Integer> ListarPerfiles(PersonalPerfiles personalPerfiles) {
        System.err.println("Generando la lista de perfiles");
        personalPerfiles.setActivo("S");

        List<PersonalPerfiles> listaPerfiles = personalPerfilesRepository.findAll(Example.of(personalPerfiles));
        List<Integer> cvePerfiles = new ArrayList<>();

        for (PersonalPerfiles perfiles : listaPerfiles) {
            cvePerfiles.add(perfiles.getPerfil().getCvePerfil());

        }
        System.err.println("lista de claves de perfil" + cvePerfiles.toString());
        return cvePerfiles;

    }

    public List<Formularios> ListarMenu(Integer cveModulo, Integer cveUsuario) {
        List<Formularios> listaFormularios = new ArrayList<>();
        PersonalPerfiles personalPerfiles = new PersonalPerfiles();
        Personal personal = new Personal();
        personal.setCveUsuario(cveUsuario);
        personalPerfiles.setPersonal(personal);
        personalPerfiles.setActivo("S");

        PermisosPersonal permisosPersonal = new PermisosPersonal();
        permisosPersonal.setActivo("S");
        permisosPersonal.setCveUsuario(cveUsuario);
        List<PersonalPerfiles> listaPersonalPerfiles = personalPerfilesRepository.findAll(Example.of(personalPerfiles));
        List<PermisosPersonal> listaPermisosPersonal = permisosPersonalRepository.findAll(Example.of(permisosPersonal));
        boolean encontrado = false;
        if (listaPersonalPerfiles.size() > 0) {
            for (PersonalPerfiles personalPerfil : listaPersonalPerfiles) {
                encontrado = false;
                //if (personalPerfil.getPerfil().size() > 0) {

                if (personalPerfil.getPerfil().getPermisosPerfiles().size() > 0) {
                    for (PermisosPerfiles pp : personalPerfil.getPerfil().getPermisosPerfiles()) {

                        encontrado = false;
                        if (listaFormularios.size() > 0) {
                            if (pp.getFormulario().getModulos().getCveModulo() == cveModulo) {
                                for (Formularios frm2 : listaFormularios) {
                                    if (frm2.getCveFormulario() == pp.getFormulario().getCveFormulario() && pp.getActivo().equals("S")) {
                                        encontrado = true;
                                        break;
                                    }
                                }
                            }
                            if (!encontrado) {
                                if (pp.getFormulario().getModulos().getCveModulo() == cveModulo && pp.getActivo().equals("S")) {
                                    listaFormularios.add(pp.getFormulario());
                                }
                            }
                        } else {
                            if (pp.getFormulario().getModulos().getCveModulo() == cveModulo) {
                                listaFormularios.add(pp.getFormulario());
                            }
                        }

                    }
                }
                //}

            }
        }
        System.out.println("modulos de permisos personal");

        if (listaPermisosPersonal.size() > 0) {
            for (PermisosPersonal permisopersonal : listaPermisosPersonal) {
                encontrado = false;
                if (listaFormularios.size() > 0) {
                    if (permisopersonal.getFormulario().getModulos().getCveModulo() == cveModulo) {
                        for (Formularios frm2 : listaFormularios) {
                            if (frm2.getCveFormulario() == permisopersonal.getFormulario().getCveFormulario() && permisopersonal.getActivo().equals("S")) {
                                encontrado = true;
                                break;
                            }

                        }
                    }
                    if (!encontrado) {
                        if (permisopersonal.getFormulario().getModulos().getCveModulo() == cveModulo) {
                            listaFormularios.add(permisopersonal.getFormulario());
                        }
                    }
                } else {
                    if (permisopersonal.getFormulario().getModulos().getCveModulo() == cveModulo) {
                        listaFormularios.add(permisopersonal.getFormulario());
                    }
                }

                // }
            }
        }

        return listaFormularios;

    }

    @PostMapping("/validarsesion")
    public ResponseEntity<?> ValidarSesion() {

        Map<String, Object> params2 = new HashMap<>();
        if (getHttpSession().getAttribute("cveUsuario") == null) {
            params2.put("errorType", "1");
            params2.put("mensaje", "Su sesión a finalizado por inactividad");
        } else {
            params2.put("errorType", "2");
            params2.put("mensaje", "");
        }

//            params2.put("participante","" );
//            params2.put("motivo","" );
//            params2.put("introduccion", "");
        List<Map<String, Object>> listaParams = new ArrayList<>();
        listaParams.add(params2);

        return new ResponseEntity<>(listaParams, HttpStatus.OK);

    }
    

    @PostMapping("/buscarusuariologueado")
    public ResponseEntity<?> BuscarUsuarioLogueado() {

        Map<String, Object> params2 = new HashMap<>();
        if (getHttpSession().getAttribute("cveUsuario") == null) {
            params2.put("errorType", "1");
            params2.put("mensaje", "Su sesión a finalizado por inactividad");
        } else {
            params2.put("cveUsuario", getHttpSession().getAttribute("cveUsuario"));
            params2.put("cveAdscripcion", getHttpSession().getAttribute("cveAdscripcion"));
            params2.put("descAdscripcion", getHttpSession().getAttribute("descAdscripcion"));
            params2.put("idRegion", getHttpSession().getAttribute("cveRegion"));
            params2.put("nombre", getHttpSession().getAttribute("nombre"));
            params2.put("mensaje", "");
            params2.put("defensor", getHttpSession().getAttribute("defensor"));
        }
//            params2.put("participante","" );
//            params2.put("motivo","" );
//            params2.put("introduccion", "");
        List<Map<String, Object>> listaParams = new ArrayList<>();
        listaParams.add(params2);

        return new ResponseEntity<>(listaParams, HttpStatus.OK);

    }

    public HttpSession getHttpSession() {
        return httpSession;
    }

    public void setHttpSession(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

}
