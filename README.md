# 🏥 Système d'Auto-Diagnostic Demo

## 📋 Description

Ce projet implémente un système d'auto-diagnostic hospitalier moderne utilisant une architecture SOLID et des tests BDD. Le système analyse l'index de santé des patients capturé par une cabine automatisée et les redirige vers les unités médicales appropriées.

## 🏗️ Architecture SOLID

### Principes SOLID Respectés

| Principe | Implémentation                            | Justification                                                                    |
| -------- | ----------------------------------------- | -------------------------------------------------------------------------------- |
| **SRP**  | Chaque classe a une responsabilité unique | `HealthIndex` stocke, `DiagnosticService` coordonne, `PathologyDetector` détecte |
| **OCP**  | Extensible sans modification              | Strategy Pattern permet d'ajouter de nouveaux détecteurs                         |
| **LSP**  | Substitution transparente                 | Tous les détecteurs implémentent `PathologyDetector`                             |
| **ISP**  | Interfaces focalisées                     | `DiagnosticStrategy` et `PathologyDetector` séparées                             |
| **DIP**  | Dépendances inversées                     | Services dépendent d'abstractions, pas d'implémentations                         |

### Design Patterns Implémentés

- **Strategy Pattern** : `DiagnosticStrategy` + implémentations
- **Dependency Injection** : Spring Framework
- **Factory Pattern** : Auto-détection des composants Spring
- **Exception Handling** : Gestion d'erreurs métier structurée

### Architecture en Couches

```
┌─────────────────────────────────────┐
│           Controller Layer          │ ← API REST
├─────────────────────────────────────┤
│            Service Layer            │ ← Logique Métier
├─────────────────────────────────────┤
│          Strategy Layer             │ ← Algorithmes
├─────────────────────────────────────┤
│          Detector Layer             │ ← Logique Spécifique
├─────────────────────────────────────┤
│            Model Layer              │ ← Données
└─────────────────────────────────────┘
```

## 🚀 Technologies

- **Spring Boot 3.3.5** avec Java 17
- **Spring Web** pour l'API REST
- **Swagger/OpenAPI** pour la documentation
- **Gradle** pour la gestion des dépendances
- **Cucumber** pour les tests BDD
- **SLF4J** pour le logging professionnel

## 📁 Structure du Projet

```
com.softway.diagnostic/
├── MyApplication.java                    # Classe principale Spring Boot
├── config/
│   └── SwaggerConfig.java               # Configuration Swagger
├── controller/
│   ├── DiagnosticController.java        # API REST
│   └── dto/
│       ├── DiagnosticResponse.java      # DTO de réponse
│       └── HealthIndexRequest.java      # DTO de requête
├── exception/
│   └── InvalidHealthIndexException.java # Exception métier
├── model/
│   ├── HealthIndex.java                 # Modèle avec validation
│   ├── Pathology.java                   # Énumération des pathologies
│   └── Patient.java                     # Modèle patient
└── service/
    ├── DiagnosticService.java           # Service principal
    ├── DiagnosticStrategy.java          # Interface stratégie
    ├── PathologyDetector.java           # Interface détecteur
    └── impl/
        ├── CardiologyDetector.java      # Détecteur cardiaque
        ├── StandardDiagnosticStrategy.java # Stratégie standard
        └── TraumatologyDetector.java    # Détecteur traumatologie
```

## 🔬 Logique de Diagnostic

### Règles de Détection

- **Index multiple de 3** → Problème cardiaque → Redirection vers Cardiologie
- **Index multiple de 5** → Fracture → Redirection vers Traumatologie
- **Index multiple de 3 ET 5** → Les deux pathologies → Redirection vers les deux unités

### Exemples de Diagnostic

| Index | Pathologies Détectées | Départements               |
| ----- | --------------------- | -------------------------- |
| 33    | Problème cardiaque    | Cardiologie                |
| 55    | Fracture              | Traumatologie              |
| 15    | Les deux              | Cardiologie, Traumatologie |
| 150   | Les deux              | Cardiologie, Traumatologie |
| 7     | Aucune                | Aucun                      |

## 🌐 API REST

### Endpoint Principal

- `POST /api/diagnostic/analyze` - Analyse un index de santé

### Documentation Swagger

L'API est documentée avec Swagger/OpenAPI accessible à :

- **URL** : `http://localhost:8080/swagger-ui.html`
- **OpenAPI JSON** : `http://localhost:8080/v3/api-docs`

### Exemple d'Utilisation

```bash
# Analyser un index de santé
curl -X POST http://localhost:8080/api/diagnostic/analyze \
  -H "Content-Type: application/json" \
  -d '{"healthIndex": 15}'

# Réponse attendue
{
  "healthIndex": 15,
  "pathologies": ["CARDIOLOGY", "TRAUMATOLOGY"]
}
```

## 🧪 Tests BDD avec Cucumber

### Scénarios Testés

1. **Détection d'un problème cardiaque** (index 33)
2. **Détection d'une fracture** (index 55)
3. **Détection de pathologies multiples** (index 15)
4. **Aucune pathologie détectée** (index 7)
5. **Index de santé invalide - trop bas** (index 0)
6. **Index de santé avec valeur élevée** (index 150)
7. **Index de santé critique** (index 15)

### Structure des Tests BDD

```
com.softway.diagnostic.bdd/
├── CucumberTest.java                    # Suite de tests Cucumber
├── CucumberSpringConfiguration.java     # Configuration Spring pour tests
└── steps/
    └── DiagnosticSteps.java             # Step definitions
```

### Exemple de Scénario BDD

```gherkin
Scenario: Détection de pathologies multiples
  Given un patient avec un index de santé de 15
  When j'analyse l'index de santé
  Then le système doit détecter un problème cardiaque
  And le système doit détecter une fracture
  And la réponse doit indiquer "Cardiologie, Traumatologie"
```

## 🚀 Démarrage Rapide

### Prérequis

- Java 17+
- Gradle 8.0+

### Installation et Lancement

````bash
# Cloner le projet
git clone git@github.com:cedricCdl/diagnostic.git
cd diagnostic

# Construire le projet
./gradlew clean build

# Lancer les tests avec logs détaillés
./gradlew test --rerun-tasks --info --console=verbose -Dlogging.level.com.softway.diagnostic=DEBUG

# Lancer l'application
./gradlew bootRun

## 📊 Qualité et Tests

### Couverture de Tests

- **Tests BDD** : 7 scénarios complets
- **Tests d'intégration** : Spring Boot Test
- **Gestion d'erreurs** : Tests des cas d'échec
- **Logging** : Traçabilité complète

### Métriques de Qualité

- **Architecture SOLID** : 100% respectée
- **Design Patterns** : Strategy, DI, Factory implémentés
- **Gestion d'erreurs** : Exceptions métier appropriées
- **Documentation** : API documentée avec Swagger
- **Tests** : BDD avec Cucumber

### Logs Visibles

Avec la commande complète, vous verrez :

- ✅ Démarrage Spring Boot avec logo
- ✅ Logs avec emojis : `🔧 Initialisation de la cabine...`, `✅ Cabine opérationnelle !`
- ✅ Logs d'erreur et warnings détaillés
- ✅ Tous les détails des tests BDD

## 🎯 Points Forts pour l'Entretien

### Architecture

- **SOLID** : Chaque principe documenté et implémenté
- **Extensibilité** : Ajout de pathologies sans modification
- **Maintenabilité** : Code modulaire et testé
- **Performance** : Gestion efficace des ressources

### Tests

- **BDD** : Scénarios métier
- **Couverture** : Cas de succès et d'échec
- **Intégration** : Tests avec Spring Boot
- **Documentation** : Tests auto-documentés
