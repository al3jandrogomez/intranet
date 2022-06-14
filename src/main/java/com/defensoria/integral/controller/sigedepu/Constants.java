/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.sigedepu;


import java.text.SimpleDateFormat;
import java.util.Date;
//import org.apache.commons.lang.builder.ToStringBuilder;

public class Constants {

    /* ------------------------------------------------------------------------- */
    public static final String LOGIN_TITLE_VAR = "loginTitle";
    public static final String LOGIN_TITLE = "Acceso al sistema de gestión interna del Estado de México 3.2";
    public static final String GESTION_TITLE_VAR = "title";
    public static final String GESTION_TITLE = "Gestión interna del Estado de México 3.2";
    public static final String GESTION_MENU = "menu";
    public static final String USUARIO_ACTIVO = "usuario";
    public static final String PERFIL = "perfil";
    public static final String PERFIL_CAPTURISTA = "capturista";
    public static final String ACTIVE_STATUS = "Activo";
    public static final String INACTIVE_STATUS = "Inactivo";
    public static final String USER_NOT_FOUND = "El usuario no se ha encontrado";
    public static final String USUARIO = "usuario";
    public static final String UNIDADES = "UNIDADES";
    public static final String ESTATUS_SOLICITUD = "ESTATUS_SOLICITUD";
    public static final String PRIORIDAD = "PRIORIDAD";
    public static final String CONFIDENCIALIDAD = "CONFIDENCIALIDAD";
    public static final String TIPO_ANEXO = "TIPO_ANEXO";
    public static final String ACCION = "ACCION";
    public static final String SEXOS = "SEXOS";
    public static final String TIPO_DESTINATARIO = "TIPO_DESTINATARIO";
    public static final String REVISORES = "REVISORES";
    public static final String DESTINATARIO = "DESTINATARIO";
    public static final String DESTINATARIO_WS = "DESTINATARIO_WS";
    public static final String DESTINATARIO_NO_OFICIAL = "DESTINATARIO_NO_OFICIAL";
    public static final long DIA = 86400;
    public static final String SERVIDORES_PUBLICOS = "servidoresPublicos";
    public static final String SOLICITUD = "solicitud";
    public static final String SOLICITUDES = "solicitudes";
    public static final String ASUNTOS = "asuntos";
    public static final String ASUNTO = "asunto";
    public static final String TURNOS = "turnos";
    public static final String TURNO = "turno";
    public static final String TIPO_DOCUMENTO = "TIPODOCUMENTO";
    public static final String TIPO_REGISTRO = "TIPOREGISTRO";
    public static final String REMITENTES = "REMITENTE";
    public static final String REMITENTE = "remitente";
    public static final String SOLICITUD_REFERENCIA = "solicitudReferencia";
    public static final byte OFICIAL = 1;
    public static final byte NO_OFICIAL = 2;
    public static final Integer EXTERNO = 1;
    public static final boolean INTERNO = false;
    public static final Integer UNIDAD_RAIZ = 0;
    public static final String UNIDADES_DESTINATARIAS = "UNIDADES_DESTINATARIAS";
    public static final String SERVICIO = "SERVICIO";
    public static final String VO_BO_OK = "OK";
    public static final String VO_BO_NO_OK = "NO";
    public static final String FOLIO_SEPARATOR = "/";
    public static final String PAGE_COUNT = "pageCount";
    public static final Integer NUMERO_SOLICITUDES = 50;
    public static final Integer NUMERO_TURNOS = 50;
    public static final Integer NUMERO_ASUNTOS = 50;
    public static final String COUNT = "count";
    public static final String PAGE = "page";
    public static final String SOLICITUD_VOBO = "solicitudVoBo";
    public static final boolean DESTINATARIO_ACTIVO = true;
    public static final String MENSAJE_ERROR = "mensaje";
    public static final String MOVIMIENTO = "movimiento";
    public static final String FILTRO = "filtro";
    public static final long MINUTOS_15 = 900;
    public static final long MINUTOS_MILLISEGUNDOS_1 = 60000;
    public static final long MINUTOS_MILLISEGUNDOS_15 = 900000;
    public static final String INSTRUCCIONES = "instrucciones";
    public static final String ASUNTOS_ACTIVOS = "ASUNTOS_ACTIVOS";
    public static final String ESTATUS_TURNO = "estatus_turno";
    public static final String UNIDAD_RECEPTORA_CONFIDENCIAL_EXCEPTION_MESSAGE = "La unidad de recepción de asuntos confidenciales no se ha configurado";
    public static final String JUSTIFICACION_RECHAZO = "justificacionRechazo";
    public static final Long AVANCE_CERO = 0L;
    public static final Long AVANCE_CINCO = 5L;
    public static final Long AVANCE_NOVENTA = 90L;
    public static final Long AVANCE_NOVENTA_Y_CINCO = 95L;
    public static final Long AVANCE_CIEN = 100L;
    public static final String ERROR_MESSAGE = "error";
    public static final String FOLIO = "folio";
    public static final String ID_FOLIO = "IdFolio";    
    public static final String ALLOWED_CONTENT_TYPES = "allowedContentTypes";
    public static final String ALLOWED_EXTENSIONS = "allowedExtensions";
    public static final String ALLOWED_SIZE = "allowedSize";
    public static final String CURRENT_SIZE = "currentSize";
    public static final String NOMBRE_SISTEMA = "Sistema de gestión interna";
    public static final String MENSAJE_EVIDENCIA = "Creado de solicitud no oficial";
    public static final String VACIO = "";
    public static final String PRORROGA = "prorroga";
    public static final String FECHA_HORA = "fechaHora";
    public static final String USER_DATA = "userData";
    public static final String ES_RESPONSABLE = "responsable";
    public static final String VALIDACION_TURNOS = "validacionTurnos";
    public static final String SERVICIOS = "servicios";
    public static final String RESPONSABLE_VAR = "responsable";
    public static final String USUARIO_SIN_ASIGNACION_EXCEPTION = "El usuario no cuenta con una unidad asignada para acceder al sistema";
    public static final String UNIDADES_SUBORDINADAS = "unidadesSubordinadas";
    public static final String UNIDADES_SUPERVISADAS = "unidadesSupervisadas";
    public static final String ERROR = "error";
    public static final String TAMANO = "tamano";
    public static final String TIPOS_ANEXO = "tiposAnexo";
    public static final String ACCIONES = "acciones";
    public static final String CACHE = "GESTION_CACHE";
    public static final Integer VENCIDO_AMBOS = 2;
    public static final String EDICION_OFICIO = "edicionOficio";
    public static final String HOJA_TURNO = "hojaTurno";
    public static final String CONTROL_DOCUMENTACION = "controlDocumentacion";
    public static final String EXCEPTION = "exception";
    public static final String MENSAJE = "mensaje";
    public static final String REGISTRO = "registro";
    public static final String SEGUIMIENTO = "seguimiento";
    public static final Integer NUMERO_EXPEDIENTES = 15;
    public static final String DOCUMENTOS = "documentos";
    public static final String DOCUMENTO = "documento";
    public static final String TIPOS_DOCUMENTO = "tiposDocumento";
    public static final String EMPTY = "";
    public static final String ID = "id";
    public static final String XML = "xml";
    public static final String CMS = "cms";
    public static final String PDF = "pdf";
    public static final String NOMBRE_ARCHIVO = "nombre";
    public static final String URL = "url";
    public static final String CMS_EXTENSION = ".cms";
    public static final String PDF_EXTENSION = ".pdf";
    public static final String XML_EXTENSION = ".xml";
    public static final String TIPO = "tipo";
    public static final String DIAGONAL = "/";
    public static final String CARRIER_RETURN = "\n";
    public static final String GUION = "-";
    public static final String FOLIO_ACTIVO="folioActivo";
    

    public enum sourceConsultaGeneral {
        consultaGeneral, consultaExpediente
    }

    public enum tipoVencimiento {
        resolutorView, supervisorView, viewAll
    }

    /**
     * Reflexión del objeto enviado (imprime en consola el contenido del objeto)
     *
     * @param object
     */
    public static void printObject(Object object) {
        System.out.println("------------------------------------------------------------------------------");
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        System.out.println(className + ":" + lineNumber);
        if (object != null) {
        //    System.out.println(ToStringBuilder.reflectionToString(object));
        } else {
            System.out.println("Objeto Nulo");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    /**
     * Reflexión del objeto enviado (imprime en consola el contenido del objeto)
     *
     * @param object
     * @param message
     */
    public static void printObject(Object object, String message) {
        System.out.println("------------------------------------------------------------------------------");
        int lineNumber = Thread.currentThread().getStackTrace()[2].getLineNumber();
        String className = Thread.currentThread().getStackTrace()[2].getClassName();
        System.out.println(className + ":" + lineNumber);
        if (message != null) {
            System.out.println(message);
        }
        if (object != null) {
         //   System.out.println(ToStringBuilder.reflectionToString(object));
        } else {
            System.out.println("Objeto Nulo");
        }
        System.out.println("------------------------------------------------------------------------------");
    }

    public static void printMessage(String message) {

        System.out.println("------------------------------------------------------------------------------");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/M/yyyy hh:mm:ss");
        String date = sdf.format(new Date());
        System.out.println(date);
        System.out.println(message);
        System.out.println("------------------------------------------------------------------------------");
    }
}

