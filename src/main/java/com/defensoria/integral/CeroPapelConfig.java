/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package com.defensoria.integral;
//
//import mx.gob.edomex.dgsei.generador.service.impl.FirmaServiceImpl;
//import mx.gob.edomex.dgsei.generador.validador.service.impl.ValidadorServiceImpl;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.PropertySource;

/**
 *
 * @author alejandro.gomez
 */
//@Configuration
//@PropertySource(value = {"classpath:application.properties"})
//public class CeroPapelConfig {
//
//    @Value(value = "${ceroPapel.user}")
//    private String user;
//    @Value(value = "${ceroPapel.password}")
//    private String password;
//
//    @Value(value = "${ceroPapel.endpoint}")
//    private String endpoint;
//    
//     @Value( value="${validador.endpoint}")
//    private String endpoint2;
//
//    @Bean(name = "firmaService")
//    public FirmaServiceImpl firmaService() {
//
//        return new FirmaServiceImpl(user, password, endpoint);
//    }
//    
//    @Bean(name = "validadorService")
//    public ValidadorServiceImpl validadorService() {
//
//        return new ValidadorServiceImpl(endpoint2);
//    }
//
//}
