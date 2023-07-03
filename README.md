# Mobility Library
Mobility Java API

## Description

Cette API gère l'aide à la mobilité de l'utilisateur selon sa position géographique.
Plus spécifiquement ici, ce sera obtenir la liste des parkings à proximité de sa position géographique.


## Details techniques

* Spring Boot
* Maven
* JUnit 5
* Lombok
* Swagger
* Eclemma (Code coverage)
* MoreUnit
* Mapstruct
* Git

## Prérequis:

* JDK 11
* Maven
* Lombok

### Guide d'installation

Clone the repository
```
git clone https://github.com/jarfi1417/mobility.git
```

Rajouter si cela n'est pas déjà fait dans le classpath : "target/generated-sources/annotations"

Compiler et lancer les tests unitaires et d'intégrations
```
mvn clean install
```

Lancer l'application
```
mvn spring-boot:run
```

La documentation Swagger peut être trouvée à cette adresse: http://localhost:8888/mobilityapi/swagger-ui/

## Comment utiliser l'API

## REST endpoints

### Mobility API

`"/parking"`: Récupère la liste des parkings à proximité selon les paramètres :

- `latitude` : la latitude depuis laquelle la recherche de parking est calculée
- `longitude` : la longitude depuis laquelle la recherche de parking est calculée
- `withAvailableSlots` : permet de déciders si le service récupère la liste des parkings avec avec places disponnibles ou non

## Description

* Récupération des données JSON depuis les URLs fournis
* Désérialisation des JSON vers les entités correspondantes avec Jackson
* Mapping des données vers les entités de l'API avec Mapstruct
* Calcule pour chaque parking (quand cela est possible) de la distance entre les coordonnées données en entrée (le cas échéant) et les coordonnées du parking
* Réorganisation des données pour n'avoir à la fin qu'une unique liste de parkings avec toutes les données nécessaires
* Présentation des données via l'end point "/parking" ordonnée de la plus proche à la plus éloignée
* Tests unitaires (Classes utilitaires, mapper, service, API)
* Tests d'intégration (API)

## Problèmes non traités / Questions
	
- URL et données dynamiques: pour ce cas d'utilisation, j'ai réfléchis à un système dynamique permettant d'avoir un fichier de configuration yaml par ville contenant:
    - les urls propres à la ville
    - les chemins d'accès JSON pour chaque donnée afin de pouvoir effectuer un mapping automatique et générique
- Pourquoi les données concernant le nombre de places disponibles des parkings en temps réel ne comportent pas la même liste que les données concernant la liste des parkings?
- Ce sont de vraies données ? pourquoi certains parkings ne comportaient pas de coordonnées ?