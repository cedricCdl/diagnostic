package com.softway.diagnostic.service.impl;

import com.softway.diagnostic.model.Pathology;
import com.softway.diagnostic.service.PathologyDetector;
import org.springframework.stereotype.Component;

/**
 * Détecteur de problèmes cardiaques
 * Respecte le principe OCP (Open/Closed) - peut être étendu sans modification
 */
@Component
public class CardiologyDetector implements PathologyDetector {
    
    private static final int CARDIOLOGY_THRESHOLD = 3;
    
    @Override
    public Pathology detect(int healthIndexValue) {
        if (healthIndexValue % CARDIOLOGY_THRESHOLD == 0) {
            return Pathology.CARDIOLOGY;
        }
        return null;
    }
    
    @Override
    public Pathology getPathologyType() {
        return Pathology.CARDIOLOGY;
    }
}
