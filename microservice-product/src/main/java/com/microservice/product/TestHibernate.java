package com.microservice.product;

import com.microservice.product.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Classe de test de démarrage : vérifie que le conteneur Spring IoC,
 * la SessionFactory Hibernate et la connexion à la base de données s'initialisent correctement.
 */
public class TestHibernate {

    public static void main(String[] args) {
        System.out.println("--- Démarrage du test d'initialisation Spring / Hibernate ---");

        // Démarrage du contexte d'application Spring basé sur AppConfig
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        System.out.println("=============================================================");
        System.out.println("Contexte Spring et SessionFactory initialisés avec succès !");
        System.out.println("=============================================================");

        // Fermeture du contexte et arrêt des threads JDBC
        context.close();
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Throwable ignored) {}
    }
}
