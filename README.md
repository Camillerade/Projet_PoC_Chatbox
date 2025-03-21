# MDD

Ce projet contient une application Front-End en Angular ainsi qu'une API Back-End en Spring Boot.

## Structure du projet

Le projet est divisé en deux parties principales :

- **Front-End** : Application Angular
- **Back-End** : API REST en Java avec Spring Boot

---

## Front-End : Angular

### Prérequis

- [Node.js](https://nodejs.org/) (version 17 ou supérieure)
- [Angular CLI](https://angular.io/cli) (version 14.1 ou supérieure)

### Installation et démarrage

1. **Cloner le projet**

    Clonez le dépôt depuis GitHub :

    ```bash
    git clone https://github.com/Camillerade/Developpez-le-back-end-en-utilisant-Java-et-Spring
    ```

2. **Accéder au répertoire**

    Allez dans le dossier du projet :

    ```bash
    cd Developpez-le-back-end-en-utilisant-Java-et-Spring/front
    ```

3. **Installer les dépendances**

    Installez toutes les dépendances nécessaires :

    ```bash
    npm install
    ```

4. **Lancer l'application**

    Démarrez l'application Angular sur [http://localhost:4200](http://localhost:4200) :

    ```bash
    npm run start
    ```

---

## Back-End : Spring Boot

### Prérequis

- [JDK 17 ou supérieur](https://openjdk.java.net/)
- [Maven](https://maven.apache.org/)
- [MySQL](https://www.mysql.com/)

### Configuration

#### Base de données MySQL

1. **Créer la base de données**

    Exécutez le script SQL fourni pour créer le schéma de la base de données :

    ```bash
    cd /path/to/your/project
    mysql -u root -p
    CREATE DATABASE mdd;
    USE mdd;
    SOURCE /path/to/your/project/ressources/sql/script.sql;
    ```

2. **Configurer la base de données dans `application.properties`**

    Ouvrez `src/main/resources/application.properties` et configurez les informations de la base de données comme suit :

    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/mdd
    spring.datasource.username=root
    spring.datasource.password=root
    spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.format_sql=true
    spring.jpa.open-in-view=false
    ```

#### Démarrer le Back-End

1. **Lancer le serveur Spring Boot**

    Vous pouvez démarrer le serveur en exécutant la commande suivante à partir du répertoire du projet :

    ```bash
    mvn spring-boot:run
    ```

2. **Accéder à l'API**

    Une fois le serveur démarré, l'API sera accessible à l'adresse suivante :

    ```bash
    http://localhost:8080
    ```

---

### MySQL

Le script SQL pour créer la base de données et les tables nécessaires est disponible dans `ressources/sql/script.sql`. Il suffit de l'exécuter dans votre serveur MySQL pour créer le schéma de la base de données.

---


