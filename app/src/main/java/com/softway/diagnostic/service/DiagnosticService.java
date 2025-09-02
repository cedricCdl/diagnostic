package com.softway.diagnostic.service;

import com.softway.diagnostic.exception.InvalidHealthIndexException;
import com.softway.diagnostic.model.Pathology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Service principal de diagnostic
 * Respecte les principes SOLID :
 * - SRP: Responsabilité unique de coordonner le diagnostic
 * - DIP: Dépend de l'abstraction DiagnosticStrategy
 * - OCP: Extensible via nouvelles stratégies
 */
@Service
public class DiagnosticService {
    
    private static final Logger logger = LoggerFactory.getLogger(DiagnosticService.class);
    
    private final DiagnosticStrategy diagnosticStrategy;
    
    @Autowired
    public DiagnosticService(DiagnosticStrategy diagnosticStrategy) {
        this.diagnosticStrategy = diagnosticStrategy;
    }
    
    /**
     * Analyse un index de santé et retourne les pathologies détectées
     * 
     * @param healthIndexValue l'index de santé du patient
     * @return liste des pathologies détectées
     * @throws InvalidHealthIndexException si l'index est invalide
     */
    public List<Pathology> analyzeHealthIndex(int healthIndexValue) throws InvalidHealthIndexException {
        logger.info("Début de l'analyse de l'index de santé: {}", healthIndexValue);
        
        try {
            List<Pathology> pathologies = diagnosticStrategy.analyzeHealthIndex(healthIndexValue);
            
            logger.info("Analyse terminée. Pathologies détectées: {}", pathologies);
            return pathologies;
            
        } catch (InvalidHealthIndexException e) {
            logger.error("Index de santé invalide: {}", healthIndexValue, e);
            throw e;
        } catch (Exception e) {
            logger.error("Erreur lors de l'analyse de l'index: {}", healthIndexValue, e);
            throw new InvalidHealthIndexException(healthIndexValue, 
                "Erreur interne lors de l'analyse", e);
        }
    }
}
