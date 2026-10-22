# Microservice Product - Spring ORM & Hibernate

Ce projet implémente l'architecture d'un microservice de gestion de catalogue produit (**microservice-product**) utilisant **Spring Context**, **Spring ORM**, **Hibernate 5.6**, et une base de données relationnelle **MySQL / MariaDB**.

---

## 🎯 Architecture en Couches

```
src/main/java/com/microservice/product/
├── config/
│   ├── AppConfig.java         <- Configuration principale & ComponentScan
│   ├── HibernateConfig.java   <- Définition DataSource & LocalSessionFactoryBean
│   └── TransactionConfig.java   <- Gestionnaire de transactions déclaratives (@EnableTransactionManagement)
├── dao/
│   ├── IDao.java              <- Interface générique CRUD avec paramètre de type <T>
│   └── ProductDaoImpl.java    <- Implémentation DAO concrète utilisant la SessionFactory Hibernate
├── entity/
│   └── Product.java           <- Entité JPA mappée à la table "products"
├── service/
│   ├── ProductService.java    <- Contrat métier
│   └── ProductServiceImpl.java<- Logique métier & transactions (@Transactional)
├── MainApp.java               <- Programme exécutable (Scénario d'utilisation)
└── TestHibernate.java         <- Test d'initialisation du conteneur Spring
```

---

## ⚙️ Configuration Base de Données

Le fichier `src/main/resources/application.properties` est configuré pour communiquer avec votre base MySQL/MariaDB locale :
```properties
jdbc.driver=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://localhost:3306/product_db?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true
jdbc.username=root
jdbc.password=1234

hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
hibernate.show_sql=true
hibernate.format_sql=true
hibernate.hbm2ddl.auto=update
```

---

## 🚀 Commandes d'Exécution

Depuis le répertoire du projet `C:\Users\pc\.gemini\antigravity-ide\scratch\microservice-product` :

### 1. Lancer le scénario principal :
```bash
mvn exec:java
```

### 2. Tester l'initialisation du contexte Spring / Hibernate :
```bash
mvn exec:java -Dexec.mainClass="com.microservice.product.TestHibernate"
```

### 3. Exécuter les tests unitaires JUnit 5 :
```bash
mvn test
```
