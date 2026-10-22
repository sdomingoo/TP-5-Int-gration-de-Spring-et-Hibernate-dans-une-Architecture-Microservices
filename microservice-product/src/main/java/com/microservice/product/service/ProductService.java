package com.microservice.product.service;

import com.microservice.product.entity.Product;
import java.util.List;

/**
 * Interface définissant les opérations métier sur les produits.
 */
public interface ProductService {
    void addProduct(Product product);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    void updateProduct(Product product);
    void deleteProduct(Long id);
}
