package com.softway.diagnostic.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

@Schema(description = "Requête d'analyse d'index de santé")
public class HealthIndexRequest {
    
    @Schema(description = "Index de santé du patient", example = "15", required = true)
    @Min(value = 1, message = "L'index de santé doit être positif")
    private int healthIndex;

    // Constructeur par défaut requis pour la désérialisation JSON
    public HealthIndexRequest() {}

    public HealthIndexRequest(int healthIndex) {
        this.healthIndex = healthIndex;
    }

    public int getHealthIndex() {
        return healthIndex;
    }

    public void setHealthIndex(int healthIndex) {
        this.healthIndex = healthIndex;
    }
}
