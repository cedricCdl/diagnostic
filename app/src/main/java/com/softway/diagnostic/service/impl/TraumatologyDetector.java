package com.softway.diagnostic.service.impl;

import com.softway.diagnostic.model.Pathology;
import com.softway.diagnostic.service.PathologyDetector;
import org.springframework.stereotype.Component;

/**
 * Détecteur de problèmes traumatologiques
 * Respecte le principe OCP (Open/Closed) - peut être étendu sans modification
 */
@Component
public class TraumatologyDetector implements PathologyDetector {
    
    private static final int TRAUMATOLOGY_THRESHOLD = 5;
    
    @Override
    public Pathology detect(int healthIndexValue) {
        if (healthIndexValue % TRAUMATOLOGY_THRESHOLD == 0) {
            return Pathology.TRAUMATOLOGY;
        }
        return null;
    }
    
    @Override
    public Pathology getPathologyType() {
        return Pathology.TRAUMATOLOGY;
    }
}
