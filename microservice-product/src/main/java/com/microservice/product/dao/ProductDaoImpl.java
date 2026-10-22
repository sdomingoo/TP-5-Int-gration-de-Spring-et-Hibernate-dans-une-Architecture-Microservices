package com.microservice.product.dao;

import com.microservice.product.entity.Product;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Implémentation concrète du DAO pour l'entité Product.
 * Utilise la SessionFactory d'Hibernate et participe aux transactions gérées par Spring.
 */
@Repository
public class ProductDaoImpl implements IDao<Product> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Product product) {
        // Enregistre un nouveau produit dans la session courante
        sessionFactory.getCurrentSession().save(product);
    }

    @Override
    public void update(Product product) {
        // Met à jour les informations du produit
        sessionFactory.getCurrentSession().update(product);
    }

    @Override
    public void delete(Product product) {
        // Supprime le produit de la base
        sessionFactory.getCurrentSession().delete(product);
    }

    @Override
    public Product findById(Long id) {
        // Recherche un produit par sa clé primaire ID
        return sessionFactory.getCurrentSession().get(Product.class, id);
    }

    @Override
    public List<Product> findAll() {
        // Récupère tous les produits via une requête HQL
        return sessionFactory.getCurrentSession().createQuery("FROM Product", Product.class).list();
    }
}
