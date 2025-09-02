#!/bin/bash

# Script de test pour l'API de diagnostic médical
# Assurez-vous que l'application est en cours d'exécution sur le port 8080

echo "=== Test de l'API de Diagnostic Médical ==="
echo ""

# Test 1: Vérifier que l'API est opérationnelle
echo "1. Test de l'API..."
curl -s http://localhost:8080/api/diagnostic/test | jq '.'
echo ""

# Test 2: Analyser un index de santé simple
echo "2. Analyse d'un index de santé (9)..."
curl -s -X POST http://localhost:8080/api/diagnostic/analyser \
  -H "Content-Type: application/json" \
  -d '{"indexSante": 9}' | jq '.'
echo ""

# Test 3: Analyser un index avec deux pathologies
echo "3. Analyse d'un index avec deux pathologies (15)..."
curl -s -X POST http://localhost:8080/api/diagnostic/analyser \
  -H "Content-Type: application/json" \
  -d '{"indexSante": 15}' | jq '.'
echo ""

# Test 4: Analyser plusieurs indices
echo "4. Analyse de plusieurs indices..."
curl -s -X POST http://localhost:8080/api/diagnostic/analyser-multiple \
  -H "Content-Type: application/json" \
  -d '{"indices": [33, 55, 15, 7]}' | jq '.'
echo ""

# Test 5: Ajouter des patients
echo "5. Ajout de patients..."
curl -s -X POST http://localhost:8080/api/diagnostic/patient \
  -H "Content-Type: application/json" \
  -d '{"indexSante": 9}' | jq '.'
echo ""

curl -s -X POST http://localhost:8080/api/diagnostic/patient \
  -H "Content-Type: application/json" \
  -d '{"indexSante": 25}' | jq '.'
echo ""

curl -s -X POST http://localhost:8080/api/diagnostic/patient \
  -H "Content-Type: application/json" \
  -d '{"indexSante": 30}' | jq '.'
echo ""

# Test 6: Voir les statistiques
echo "6. Statistiques des queues..."
curl -s http://localhost:8080/api/diagnostic/statistiques | jq '.'
echo ""

# Test 7: Récupérer le prochain patient de cardiologie
echo "7. Prochain patient en cardiologie..."
curl -s http://localhost:8080/api/diagnostic/cardiologie/prochain | jq '.'
echo ""

# Test 8: Récupérer le prochain patient de traumatologie
echo "8. Prochain patient en traumatologie..."
curl -s http://localhost:8080/api/diagnostic/traumatologie/prochain | jq '.'
echo ""

# Test 9: Voir les statistiques après traitement
echo "9. Statistiques après traitement..."
curl -s http://localhost:8080/api/diagnostic/statistiques | jq '.'
echo ""

echo "=== Tests terminés ==="
