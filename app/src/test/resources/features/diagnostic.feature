Feature: Diagnostic Automatique Hospitalier
  En tant que cabine d'auto-diagnostic
  Je veux analyser l'index de santé des patients
  Afin de les rediriger vers les unités médicales appropriées

  Background:
    Given la cabine d'auto-diagnostic est opérationnelle

  Scenario: Détection d'un problème cardiaque
    Given un patient avec un index de santé de 33
    When j'analyse l'index de santé
    Then le système doit détecter un problème cardiaque
    And la réponse doit indiquer "Cardiologie"

  Scenario: Détection d'une fracture
    Given un patient avec un index de santé de 55
    When j'analyse l'index de santé
    Then le système doit détecter une fracture
    And la réponse doit indiquer "Traumatologie"

  Scenario: Détection de pathologies multiples
    Given un patient avec un index de santé de 15
    When j'analyse l'index de santé
    Then le système doit détecter un problème cardiaque
    And le système doit détecter une fracture
    And la réponse doit indiquer "Cardiologie, Traumatologie"

  Scenario: Aucune pathologie détectée
    Given un patient avec un index de santé de 7
    When j'analyse l'index de santé
    Then le système ne doit détecter aucune pathologie
    And la réponse doit indiquer ""

  Scenario: Index de santé invalide - trop bas
    Given un patient avec un index de santé de 0
    When j'analyse l'index de santé
    Then le système doit rejeter l'index comme invalide

  Scenario: Index de santé avec valeur élevée
    Given un patient avec un index de santé de 150
    When j'analyse l'index de santé
    Then le système doit détecter un problème cardiaque
    And le système doit détecter une fracture
    And la réponse doit indiquer "Cardiologie, Traumatologie"

  Scenario: Index de santé critique
    Given un patient avec un index de santé de 15
    When j'analyse l'index de santé
    Then le système doit détecter un problème cardiaque
    And le système doit détecter une fracture
    And la réponse doit indiquer "Cardiologie, Traumatologie"
