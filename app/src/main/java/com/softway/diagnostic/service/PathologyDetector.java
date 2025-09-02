package com.softway.diagnostic.service;

import com.softway.diagnostic.model.Pathology;

/**
 * Interface pour la détection de pathologies spécifiques
 * Respecte le principe ISP (Interface Segregation)
 */
public interface PathologyDetector {
    
    /**
     * Détecte si une pathologie spécifique est présente
     * 
     * @param healthIndexValue l'index de santé à analyser
     * @return la pathologie détectée ou null si aucune
     */
    Pathology detect(int healthIndexValue);
    
    /**
     * Retourne le type de pathologie que ce détecteur peut identifier
     * 
     * @return le type de pathologie
     */
    Pathology getPathologyType();
}
