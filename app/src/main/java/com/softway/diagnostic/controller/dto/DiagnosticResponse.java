package com.softway.diagnostic.controller.dto;

import com.softway.diagnostic.model.Pathology;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;

@Schema(description = "Réponse d'analyse d'index de santé")
public class DiagnosticResponse {
    
    @Schema(description = "Index de santé analysé")
    private int healthIndex;
    
    @Schema(description = "Liste des pathologies détectées")
    private List<Pathology> pathologies;

    public DiagnosticResponse() {}

    public DiagnosticResponse(int healthIndex, List<Pathology> pathologies) {
        this.healthIndex = healthIndex;
        this.pathologies = pathologies;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }

    public List<Pathology> getPathologies() {
        return pathologies;
    }

    public void setPathologies(List<Pathology> pathologies) {
        this.pathologies = pathologies;
    }
}
