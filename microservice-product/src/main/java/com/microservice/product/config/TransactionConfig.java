package com.microservice.product.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * Configuration du gestionnaire de transactions déclaratif (@Transactional) de Spring.
 * proxyTargetClass = true permet l'injection directe par classe (ex: ProductServiceImpl) ou par interface.
 */
@Configuration
@EnableTransactionManagement(proxyTargetClass = true)
public class TransactionConfig {

    /**
     * Associe la SessionFactory Hibernate au TransactionManager de Spring.
     * Permet la gestion automatique des commits et rollbacks.
     */
    @Bean
    public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager txManager = new HibernateTransactionManager();
        txManager.setSessionFactory(sessionFactory);
        return txManager;
    }
}
