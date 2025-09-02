package com.softway.diagnostic.bdd.steps;

import com.softway.diagnostic.exception.InvalidHealthIndexException;
import com.softway.diagnostic.model.Pathology;
import com.softway.diagnostic.service.DiagnosticService;
import io.cucumber.java.fr.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DiagnosticSteps {

    private static final Logger logger = LoggerFactory.getLogger(DiagnosticSteps.class);

    @Autowired
    private DiagnosticService diagnosticService;
    private int healthIndex;
    private List<Pathology> pathologies;
    private Exception lastException;

    @Étantdonné("la cabine d'auto-diagnostic est opérationnelle")
    public void laCabineDAutoDiagnosticEstOperationnelle() {
        logger.info("🔧 Initialisation de la cabine d'auto-diagnostic...");
        assertNotNull(diagnosticService, "Le service doit être injecté par Spring");
        logger.info("✅ Cabine opérationnelle !");
    }

    @Étantdonné("un patient avec un index de santé de {int}")
    public void unPatientAvecUnIndexDeSanteDe(int index) {
        this.healthIndex = index;
        this.lastException = null;
        logger.info("👤 Patient créé avec index de santé: {}", index);
    }

    @Quand("j'analyse l'index de santé")
    public void jAnalyseLIndexDeSante() {
        logger.info("🔍 Analyse de l'index de santé: {}", healthIndex);
        
        try {
            // Test du VRAI service qu'on a créé
            pathologies = diagnosticService.analyzeHealthIndex(healthIndex);
            logger.info("📊 Résultat de l'analyse: {}", pathologies);
        } catch (InvalidHealthIndexException e) {
            this.lastException = e;
            logger.warn("⚠️ Index de santé invalide: {}", e.getMessage());
        } catch (Exception e) {
            this.lastException = e;
            logger.error("❌ Erreur lors de l'analyse: {}", e.getMessage());
        }
    }

    @Alors("le système doit détecter un problème cardiaque")
    public void leSystemeDoitDetecterUnProblemeCardiaque() {
        logger.info("❤️ Vérification problème cardiaque...");
        assertTrue(pathologies.contains(Pathology.CARDIOLOGY));
        logger.info("✅ Problème cardiaque détecté !");
    }

    @Alors("le système doit détecter une fracture")
    public void leSystemeDoitDetecterUneFracture() {
        logger.info("🦴 Vérification fracture...");
        assertTrue(pathologies.contains(Pathology.TRAUMATOLOGY));
        logger.info("✅ Fracture détectée !");
    }

    @Alors("le système ne doit détecter aucune pathologie")
    public void leSystemeNeDoitDetecterAucunePathologie() {
        logger.info("🔍 Vérification aucune pathologie...");
        assertTrue(pathologies.isEmpty());
        logger.info("✅ Aucune pathologie détectée !");
    }

    @Alors("la réponse doit indiquer {string}")
    public void laReponseDoitIndiquer(String expectedDepartments) {
        logger.info("📋 Vérification réponse: {}", expectedDepartments);
        
        if (expectedDepartments.isEmpty()) {
            assertTrue(pathologies.isEmpty());
        } else {
            String[] expected = expectedDepartments.split(", ");
            for (String dept : expected) {
                // Convertir le nom du département en enum Pathology
                Pathology expectedPathology = "Cardiologie".equals(dept) ? Pathology.CARDIOLOGY : 
                                            "Traumatologie".equals(dept) ? Pathology.TRAUMATOLOGY : null;
                assertNotNull(expectedPathology, "Pathologie inconnue: " + dept);
                assertTrue(pathologies.contains(expectedPathology), "Pathologie attendue: " + dept);
            }
        }
        logger.info("✅ Réponse correcte !");
    }
    
    @Alors("le système doit rejeter l'index comme invalide")
    public void leSystemeDoitRejeterLIndexCommeInvalide() {
        logger.info("🚫 Vérification rejet de l'index invalide...");
        assertNotNull(lastException, "Une exception doit avoir été levée");
        assertTrue(lastException instanceof InvalidHealthIndexException, 
            "L'exception doit être de type InvalidHealthIndexException");
        logger.info("✅ Index rejeté correctement !");
    }
}
