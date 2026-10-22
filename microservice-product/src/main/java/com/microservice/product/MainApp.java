package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductServiceImpl;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Classe principale simulant le scénario d'utilisation complet du microservice Product :
 * - Démarrage du conteneur Spring IoC
 * - Récupération du service ProductServiceImpl
 * - Insertion d'un produit avec validation métier
 * - Consultation de la liste des produits
 */
public class MainApp {

    public static void main(String[] args) {
        System.out.println("=============================================================");
        System.out.println("   MICROSERVICE PRODUCT - DEMONSTRATION SPRING & HIBERNATE");
        System.out.println("=============================================================");

        // 1. Initialisation du conteneur Spring
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // 2. Récupération du bean Service
        ProductServiceImpl productService = context.getBean(ProductServiceImpl.class);

        // 3. Création et ajout d'un nouveau produit
        Product p = new Product("Microservice Gateway", 149.99);
        productService.addProduct(p);

        System.out.println("-> Produit ajouté avec succès : " + p);

        // 4. Consultation des produits enregistrés
        List<Product> products = productService.getAllProducts();
        System.out.println("=============================================================");
        System.out.println("Total de produits en base : " + products.size());
        for (Product prod : products) {
            System.out.println("  * [ID: " + prod.getId() + "] " + prod.getName() + " - " + prod.getPrice() + " €");
        }
        System.out.println("=============================================================");

        // 5. Fermeture propre du contexte Spring et des threads JDBC
        context.close();
        try {
            com.mysql.cj.jdbc.AbandonedConnectionCleanupThread.checkedShutdown();
        } catch (Throwable ignored) {}
    }
}
