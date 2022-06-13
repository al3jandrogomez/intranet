package com.defensoria.integral;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
@Configuration
public class IntegralApplication {

    private final class WebMvcConfigurerAdapterExtension extends WebMvcConfigurerAdapter {
        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/videos/**").addResourceLocations("file:/opt/integral/static/videos/");
            registry.addResourceHandler("/fotos/**").addResourceLocations("file:/opt/integral/static/fotos/");
            registry.addResourceHandler("/revision/**").addResourceLocations("file:/opt/opt/integral/static/informes/");
            registry.addResourceHandler("/firmas/**").addResourceLocations("file:/opt/integral/static/firmas/");
            registry.addResourceHandler("/jornadas/**").addResourceLocations("file:/opt/integral/static/jornadas/");

        }
    }

    @Bean
    WebMvcConfigurer configurer() {
        return new WebMvcConfigurerAdapterExtension();
    }

    public static void main(String[] args) {
        SpringApplication.run(IntegralApplication.class, args);

    }

}
