/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.defensoria.integral.controller.subtecnica;

import com.google.zxing.EncodeHintType;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Alejandro documentos/liga
 */
@Controller
public class SubTecnicaController {

    @Autowired
    ApplicationContext applicationContext;

    public ResponseEntity<byte[]> generarPdf(String classpath, Map<String, Object> params, String documento)
            throws FileNotFoundException, JRException {

        File file = ResourceUtils.getFile("/opt/integral/reports/" + classpath);
        JasperReport jr = JasperCompileManager.compileReport(file.getAbsolutePath());
        JasperPrint jp = JasperFillManager.fillReport(jr, params);

        JasperExportManager.exportReportToPdf(jp);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Content-Disposition", "inline; filename=" + "example.pdf");
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(JasperExportManager.exportReportToPdf(jp), headers,
                HttpStatus.OK);

        return response;

    }

    @RequestMapping(value = "/liga", method = RequestMethod.POST)
    public ResponseEntity<byte[]> Gafetes(String liga) throws FileNotFoundException, JRException {
        String classpath = "cualquierqr.jrxml";
        String documento = "liga";

        // JasperReportsHtmlView view = new JasperReportsHtmlView();

        Map<String, Object> params = new HashMap<>();
        Map<String, Object> params2 = new HashMap<>();
        Map<String, Object> persona = new HashMap<>();
        List<Map<String, Object>> listaPersonas = new ArrayList<>();
        persona.put("id", liga);
        listaPersonas.add(persona);

        System.out.println("lista para reporte=>" + listaPersonas.size());
        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(listaPersonas);
        params2.put("myList", itemsJRBean);

        Map<EncodeHintType, ErrorCorrectionLevel> hints = new HashMap<>();
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
        params.put("hints", hints);

        params.put("datasource", itemsJRBean);

        return this.generarPdf(classpath, params, documento);

    }

}
