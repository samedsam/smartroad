# SmartRoad

Prototype Spring Boot (Java 17) pour optimiser les trajets autoroutiers en combinant tronçons d'autoroute et nationales afin de réduire le coût des péages sans trop rallonger le temps de parcours.

## Démarrer l'API

```bash
mvn spring-boot:run
```

Un endpoint `POST /optimize-route` accepte la route autoroutière de référence, une liste de routes candidates (avec tronçons mixtes) et un identifiant d'utilisateur non personnel. L'API répond avec la route recommandée, le classement des alternatives et le profil utilisateur ajusté en mémoire.

## Démo clé en main

- Réseau mémoire : un axe autoroutier CITY_A → CITY_F avec péages, plus deux détours nationaux sans péage (petite économie de 10€ pour ~+20 min et grande économie de 25€ pour ~+45 min).
- Profil utilisateur démo : valeur du temps 10€/h, 30 minutes supplémentaires tolérées, 50 km de détour, facteur de complexité neutre.
- Endpoint prêt à l'emploi :

```bash
curl http://localhost:8080/demo/optimize/SCENARIO_1
curl http://localhost:8080/demo/optimize/SCENARIO_2
```

L'API construit les routes candidates en mémoire, applique la logique d'optimisation existante puis retourne un `OptimizationResponse` complet (meilleure route, classement, économies de péage/durée). Les logs indiquent le scénario joué pour simplifier la démonstration.

## Modèle et algorithme

- Modèle de graphe minimal : `Location`, `RoadSegment`, `Route`, `UserProfile`, `OptimizationContext`.
- Score multi-critères via `RouteScoringService`: coût de péage, temps supplémentaire valorisé (€/h), complexité (sorties), détour et contraintes personnalisables par utilisateur.
- Apprentissage local (`LearningService`) : les préférences sont ajustées en mémoire à chaque optimisation, sans données personnelles.

## Tests

```bash
mvn test
```
