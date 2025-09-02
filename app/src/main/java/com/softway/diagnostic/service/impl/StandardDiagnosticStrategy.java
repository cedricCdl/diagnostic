package com.softway.diagnostic.service.impl;

import com.softway.diagnostic.exception.InvalidHealthIndexException;
import com.softway.diagnostic.model.Pathology;
import com.softway.diagnostic.service.DiagnosticStrategy;
import com.softway.diagnostic.service.PathologyDetector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Stratégie de diagnostic standard utilisant tous les détecteurs disponibles
 * Respecte les principes SOLID :
 * - SRP: Responsabilité unique de coordonner les détecteurs
 * - OCP: Extensible via nouveaux détecteurs sans modification
 * - DIP: Dépend des abstractions (PathologyDetector)
 */
@Service
public class StandardDiagnosticStrategy implements DiagnosticStrategy {
    
    private final List<PathologyDetector> detectors;
    
    @Autowired
    public StandardDiagnosticStrategy(List<PathologyDetector> detectors) {
        this.detectors = detectors;
    }
    
    @Override
    public List<Pathology> analyzeHealthIndex(int healthIndexValue) throws InvalidHealthIndexException {
        validateHealthIndex(healthIndexValue);
        
        List<Pathology> detectedPathologies = new ArrayList<>();
        
        for (PathologyDetector detector : detectors) {
            Pathology detectedPathology = detector.detect(healthIndexValue);
            if (detectedPathology != null) {
                detectedPathologies.add(detectedPathology);
            }
        }
        
        return detectedPathologies;
    }
    
    @Override
    public boolean canHandle(int healthIndexValue) {
        return healthIndexValue >= 1;
    }
    
    /**
     * Valide l'index de santé
     * 
     * @param healthIndexValue l'index à valider
     * @throws InvalidHealthIndexException si l'index est invalide
     */
    private void validateHealthIndex(int healthIndexValue) throws InvalidHealthIndexException {
        if (healthIndexValue < 1) {
            throw new InvalidHealthIndexException(healthIndexValue, 
                "L'index de santé doit être positif");
        }
    }
}
