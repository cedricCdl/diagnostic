package com.softway.diagnostic.controller;

import com.softway.diagnostic.controller.dto.DiagnosticResponse;
import com.softway.diagnostic.controller.dto.HealthIndexRequest;
import com.softway.diagnostic.exception.InvalidHealthIndexException;
import com.softway.diagnostic.model.Pathology;
import com.softway.diagnostic.service.DiagnosticService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/diagnostic")
@Tag(name = "Diagnostic Hospitalier", description = "API pour l'auto-diagnostic basé sur l'index de santé")
public class DiagnosticController {

    private final DiagnosticService diagnosticService;

    @Autowired
    public DiagnosticController(DiagnosticService diagnosticService) {
        this.diagnosticService = diagnosticService;
    }

    /**
     * Traite un index de santé et retourne les pathologies détectées
     */
    @PostMapping("/analyze")
    @Operation(
        summary = "Analyser un index de santé",
        description = "Analyse l'index de santé d'un patient et retourne les pathologies détectées"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Analyse réussie",
            content = @Content(schema = @Schema(implementation = DiagnosticResponse.class))),
        @ApiResponse(responseCode = "400", description = "Données d'entrée invalides"),
        @ApiResponse(responseCode = "500", description = "Erreur interne du serveur")
    })
    public ResponseEntity<DiagnosticResponse> analyzeHealthIndex(
            @Parameter(description = "Index de santé à analyser", required = true)
            @Valid @RequestBody HealthIndexRequest request) {
        
        try {
            List<Pathology> pathologies = diagnosticService.analyzeHealthIndex(request.getHealthIndex());
            
            DiagnosticResponse response = new DiagnosticResponse(
                    request.getHealthIndex(),
                    pathologies
            );
            
            return ResponseEntity.ok(response);
            
        } catch (InvalidHealthIndexException e) {
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
