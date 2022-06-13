/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.defensoria.integral.model.firma.impl;
//
//import java.io.UnsupportedEncodingException;
//import java.net.URISyntaxException;
//import java.security.NoSuchAlgorithmException;
//import java.util.Date;
//import javax.xml.bind.JAXBException;
//import javax.xml.datatype.DatatypeConfigurationException;
//import mx.gob.edomex.dgsei.generador.dto.SignatureDTO;
//import mx.gob.edomex.dgsei.generador.service.FirmaService;
//import mx.gob.edomex.dgsei.generador.service.GeneradorService;
//import mx.gob.edomex.dgsei.generador.validador.exception.ValidadorException;
//import mx.gob.edomex.dgsei.generador.validador.service.ValidadorService;
//import com.defensoria.integral.controller.sigedepu.Constants;
//import com.defensoria.integral.controller.sigedepu.Response;
//
//import com.defensoria.integral.model.firma.GeneradorModel;
//import mx.gob.edomex.gestion.integracion.cp.WSException_Exception;
//import org.apache.commons.codec.binary.Base64;
//import org.apache.commons.codec.digest.DigestUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import org.springframework.web.servlet.ModelAndView;
//
//
//@Component
//public class GeneradorModelImpl implements GeneradorModel {
//  
//  private static final Logger LOGGER = Logger.getLogger(GeneradorModelImpl.class);
//  public static final String VIEW_EDITOR = "generador/editor";
//  public static final String VIEW_GENERADOR = "generador/generador";
//  
//  @Autowired
//  @Qualifier("firmaService")
//  FirmaService firmaService;
//  
//  @Autowired
//  @Qualifier("validadorService")
//  ValidadorService validadorService;
//  @Value("${ceroPapel.user}")
//  private String applicant;
//  
////  @Override
////  public ModelAndView generador(Usuario usuario) {
////    ModelAndView mav = new ModelAndView(VIEW_GENERADOR);
////    
////    return mav;
////  }
//  
////  @Override
////  public ModelAndView editor(Usuario usuario) {
////    ModelAndView mav = new ModelAndView(VIEW_EDITOR);
////    mav.addObject(Constants.DOCUMENTO, Constants.EMPTY);
////    return mav;
////  }
//  
//  @Override
//  public Response generaXML(String xml) throws JAXBException {
//    Response r = Response.getInstance();
//    
//    try {
//      
//      r.setEstatus(Response.RESPONSE_OK);
//      r.addCampo(Constants.ID, 1);
////      String xml = "<documento tipo=\"Circular_general3\" emisor=\"gestionInterna\" version=\"1.0\"><encabezado><fecha>2019-02-05T00:00:00-06:00</fecha><leyenda>2016, AÑO</leyenda></encabezado></documento>";
//      r.addCampo("xmlEncrip", DigestUtils.sha1(xml));
//      r.addCampo("xml", xml);
//    } catch (Exception ex) {
//      r.setEstatus(Response.RESPONSE_NOT_OK);
//      LOGGER.error("Error al guardar archivo", ex);
//    }
//    return r;
//  }
//  
//  @Override
//  public Response firma( SignatureDTO firma) {
//    Response r = Response.getInstance();
//    
//    try {
//      String hash = (this.firmaService.solicitaFirma(this.generaFirma(firma)));
//      this.firmaService.firma(hash, firma.getCuts(), Base64.decodeBase64(firma.getSignature()));
//      byte[] cms = this.firmaService.getCMS(hash);
//      
//      r.addCampo(Constants.CMS, Base64.encodeBase64(cms));
//      r.addCampo(Constants.PDF, this.validadorService.getPDF(cms));
//      r.addCampo(Constants.NOMBRE_ARCHIVO, firma.getSubject());
//      r.setEstatus(Response.RESPONSE_OK);
//      
//    } catch (WSException_Exception | DatatypeConfigurationException | JAXBException | UnsupportedEncodingException | URISyntaxException | ValidadorException ex) {
//      r.setEstatus(Response.RESPONSE_NOT_OK);
//      r.setRespuesta("Ocurrio un error al generar el documento firmado");
//      LOGGER.error("Ocurrio un error al generar el documento firmado", ex);
//      
//    }
//    return r;
//  }
//  
//  private SignatureDTO generaFirma(SignatureDTO firma) throws JAXBException, UnsupportedEncodingException {
//    firma.setSubject("documento");
//    firma.setCuts("GOAA820717HMCMLL00");
//    firma.setRequestDate(new Date());
//    firma.setHash(DigestUtils.sha1(firma.getXml()));
//    firma.setDocumentName("documento.xml");
//    firma.setApplication("sistemaSolicitante");
//    firma.setApplicant("aplicacionNombre");
//    return firma;
//  }
//}
