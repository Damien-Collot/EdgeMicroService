# EdgeMicroService

Ce projet illustre une architecture de microservices utilisant Spring Boot, Spring Cloud Gateway, Eureka pour la découverte de services, et une base de données PostgreSQL.

## Prérequis

Assurez-vous d'avoir installé les logiciels suivants :

- Docker et Docker Compose
- Java JDK 11 ou supérieur
- Un client SQL comme DataGrip ou pgAdmin
- Node.js et npm (pour le projet front-end)

## Configuration et démarrage de la base de données

### Lancement de PostgreSQL avec Docker Compose

1. Ouvrez un terminal et naviguez jusqu'au dossier contenant votre fichier `docker-compose.yml`.
2. Exécutez la commande suivante pour démarrer PostgreSQL :
   docker-compose up -d
    Voici les détails de connexion pour la base de données :

    Utilisateur: root
    Mot de passe: root
    Base de données: Edge
    Port: 5432

### Exécution du Script SQL

Connectez-vous à la base de données via DataGrip ou pgAdmin.
Exécutez le script SQL fourni dans le fichier bdd.sql pour préparer votre schéma de base de données.

## Lancement du backend

En premier lieu il faut lancer le projet Eureka

Ensuite il faudra lancer le projet SpringGateway

Enfin vous pourrez lancer tous les microservices

## Lancement du frontend

Tout d'abord installer toutes les dépendances en utilisant la commande :

npm i

de là vous pourrez éxécuter le front en lançant

npm start