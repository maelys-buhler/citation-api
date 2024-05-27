# citation-api
## Local
Pour le lancement en local, il est nécessaire d’avoir mis en place un serveru MQ et une base de
donnée MySQL. Pour simplifier cette mise en place, un fichier compose-mq.yaml permet de lancer
ces deux dépendances en local à l’aide de container Docker. Docker doit donc être déjà installé sur
la machine. Pour le lancer, il suffit donc d’utiliser la commande :
```docker compose -f compose-mq.yaml up -d```
au niveau du fichier. On peut ensuite commencer à build et lancer les services. L’ordre conseillé
est :
1. service-discovery
2. api-gateway
3. simple-quote
4. ponctual-quote
Il est nécessaire d’attendre que l’élément précédent ait été build et lancé pour lancer le build
suivante. La commande pour build est :
```mvn clean install```
et celle pour run est :
```mvn spring-boot:run```
Il faut lancer ces commandes à l’intérieur des dossiers portant le nom du service à lancer, au niveau
du fichier pom.xml de chaque service.
## Docker
Pour éviter d’avoir à faire toutes les étapes précédentes, un fichier docker-compose.yaml lançant
l’intégralité des services est mis en place. Pour le lancer, on peut utiliser la commande :
```docker compose up -d```
au niveau du fichier. Étant donné qu’un service attend que l’autre ait fini de se lancer pour se
lancer à son tour, il est possible que le lancement prenne du temps. Lors des tests, le lancement
9
intégral prenait environ 1 minute. Il est aussi possible que l’application ne soit pas immédiatement
fonctionnelle après la fin du lancement de Docker, car les services peuvent mettre un peu de temps
pour s’inscrire vers le service registry, ce qui bloque les différentes dépendances.
### Tests
Afin de simplifier le tests de l’application, une collection et un environnement Postman à utiliser
ensemble sont fournis dans la forge Github, dans le dossier Postman.
De plus, la citation à l’endpoint /hourly de Ponctual Quote devrait se mettre à jour toutes les
heures, mais pour en simplifier l’utilisation, elle se met à jour toutes les 10 secondes.