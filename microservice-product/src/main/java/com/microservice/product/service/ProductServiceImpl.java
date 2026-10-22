package com.microservice.product.service;

import com.microservice.product.dao.IDao;
import com.microservice.product.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Couche service isolant la logique métier et les règles de validation.
 * Utilise @Transactional pour gérer automatiquement le cycle de vie des transactions.
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private IDao<Product> productDao;

    /**
     * Ajoute un produit après vérification de la règle métier (prix >= 0).
     */
    @Override
    @Transactional
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Le produit ne peut pas être null");
        }
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        productDao.save(product);
    }

    /**
     * Récupère la liste de tous les produits (transaction en lecture seule).
     */
    @Override
    @Transactional(readOnly = true)
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    /**
     * Recherche un produit par son ID.
     */
    @Override
    @Transactional(readOnly = true)
    public Product getProductById(Long id) {
        return productDao.findById(id);
    }

    /**
     * Met à jour un produit existant.
     */
    @Override
    @Transactional
    public void updateProduct(Product product) {
        if (product.getPrice() < 0) {
            throw new IllegalArgumentException("Le prix ne peut pas être négatif");
        }
        productDao.update(product);
    }

    /**
     * Supprime un produit par son ID.
     */
    @Override
    @Transactional
    public void deleteProduct(Long id) {
        Product product = productDao.findById(id);
        if (product != null) {
            productDao.delete(product);
        }
    }
}
