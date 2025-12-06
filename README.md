# ExitSmart

Prototype Spring Boot (Java 17) pour optimiser les trajets autoroutiers en combinant tronçons d'autoroute et nationales afin de réduire le coût des péages sans trop rallonger le temps de parcours.

## Démarrer l'API

```bash
mvn spring-boot:run
```

Un endpoint `POST /optimize-route` accepte la route autoroutière de référence, une liste de routes candidates (avec tronçons mixtes) et un identifiant d'utilisateur non personnel. L'API répond avec la route recommandée, le classement des alternatives et le profil utilisateur ajusté en mémoire.

Un endpoint `POST /feedback` permet au client d'envoyer un résumé du trajet autoroute, du trajet proposé et une réaction (ACCEPTED, IGNORED ou ABANDONED). L'API renvoie le profil utilisateur mis à jour selon des heuristiques simples, sans stocker aucune donnée personnelle.

## Modèle et algorithme

- Modèle de graphe minimal : `Location`, `RoadSegment`, `Route`, `UserProfile`, `OptimizationContext`.
- Score multi-critères via `RouteScoringService`: coût de péage, temps supplémentaire valorisé (€/h), complexité (sorties), détour et contraintes personnalisables par utilisateur.
- Apprentissage local (`LearningService`) : les préférences sont ajustées en mémoire à chaque optimisation, sans données personnelles.

## Tests

```bash
mvn test
```
