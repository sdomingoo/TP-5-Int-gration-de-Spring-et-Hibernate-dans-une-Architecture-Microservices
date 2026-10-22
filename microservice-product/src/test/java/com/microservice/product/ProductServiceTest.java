package com.microservice.product;

import com.microservice.product.config.AppConfig;
import com.microservice.product.entity.Product;
import com.microservice.product.service.ProductService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests unitaires et d'intégration validant le comportement du service ProductService.
 */
public class ProductServiceTest {

    private static AnnotationConfigApplicationContext context;
    private static ProductService productService;

    @BeforeAll
    public static void setUp() {
        context = new AnnotationConfigApplicationContext(AppConfig.class);
        productService = context.getBean(ProductService.class);
    }

    @AfterAll
    public static void tearDown() {
        if (context != null) {
            context.close();
        }
    }

    @Test
    public void testAddProductValide() {
        Product product = new Product("Laptop Pro 16", 1299.99);
        productService.addProduct(product);

        assertNotNull(product.getId(), "L'ID du produit doit être généré après insertion");
        assertTrue(product.getId() > 0);
    }

    @Test
    public void testAddProductPrixNegatifDoitEchouer() {
        Product invalidProduct = new Product("Produit Erroné", -10.0);

        // La règle métier doit lever une IllegalArgumentException
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            productService.addProduct(invalidProduct);
        });

        assertEquals("Le prix ne peut pas être négatif", exception.getMessage());
    }

    @Test
    public void testGetAllProducts() {
        List<Product> products = productService.getAllProducts();
        assertNotNull(products);
        assertFalse(products.isEmpty(), "La liste des produits ne doit pas être vide");
    }
}
