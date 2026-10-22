package com.microservice.product.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration principale de l'application Spring.
 * Active le balayage automatique des composants (@Component, @Repository, @Service, @Configuration).
 */
@Configuration
@ComponentScan(basePackages = "com.microservice.product")
public class AppConfig {
    // Cette classe centralise le balayage des composants Spring
}
