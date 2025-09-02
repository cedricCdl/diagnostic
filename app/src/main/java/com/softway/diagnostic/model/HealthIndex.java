package com.softway.diagnostic.model;

import com.softway.diagnostic.exception.InvalidHealthIndexException;

/**
 * Modèle de données pour l'index de santé
 * Respecte le principe SRP (Single Responsibility) - ne fait que stocker et valider les données
 */
public class HealthIndex {
    
    private static final int MIN_VALUE = 1;
    
    private final int value;
    
    public HealthIndex(int value) {
        validateValue(value);
        this.value = value;
    }
    
    public int getValue() {
        return value;
    }
    
    /**
     * Valide la valeur de l'index de santé
     * 
     * @param value la valeur à valider
     * @throws InvalidHealthIndexException si la valeur est invalide
     */
    private void validateValue(int value) {
        if (value < MIN_VALUE) {
            throw new InvalidHealthIndexException(value, 
                String.format("L'index de santé doit être positif (>= %d)", MIN_VALUE));
        }
    }
    
    /**
     * Vérifie si l'index est dans une plage critique
     * 
     * @return true si l'index est critique
     */
    public boolean isCritical() {
        return value <= 20;
    }
    
    /**
     * Vérifie si l'index est dans une plage normale
     * 
     * @return true si l'index est normal
     */
    public boolean isNormal() {
        return value > 20 && value <= 80;
    }
    
    /**
     * Vérifie si l'index est dans une plage excellente
     * 
     * @return true si l'index est excellent
     */
    public boolean isExcellent() {
        return value > 80;
    }
}
