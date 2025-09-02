package com.softway.diagnostic.service;

import com.softway.diagnostic.exception.InvalidHealthIndexException;
import com.softway.diagnostic.model.Pathology;
import java.util.List;

/**
 * Interface pour les stratégies de diagnostic
 * Respecte le principe ISP (Interface Segregation) et DIP (Dependency Inversion)
 */
public interface DiagnosticStrategy {
    
    /**
     * Analyse un index de santé et retourne les pathologies détectées
     * 
     * @param healthIndexValue l'index de santé à analyser
     * @return liste des pathologies détectées
     * @throws InvalidHealthIndexException si l'index est invalide
     */
    List<Pathology> analyzeHealthIndex(int healthIndexValue) throws InvalidHealthIndexException;
    
    /**
     * Vérifie si cette stratégie peut traiter l'index donné
     * 
     * @param healthIndexValue l'index à vérifier
     * @return true si cette stratégie peut traiter l'index
     */
    boolean canHandle(int healthIndexValue);
}
