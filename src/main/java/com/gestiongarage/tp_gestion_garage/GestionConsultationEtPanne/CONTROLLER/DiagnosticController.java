package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.CONTROLLER;//package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.CONTROLLER;
//
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisDTO;
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisRequest;
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticDTO;
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticRequest;
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE.DiagnosticService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/diagnostics")
//@RequiredArgsConstructor
//public class DiagnosticController {
//    private final DiagnosticService diagnosticService;
//
//    @PostMapping
//    public ResponseEntity<DiagnosticDTO> createDiagnostic(@Valid @RequestBody DiagnosticRequest request) {
//        DiagnosticDTO created = diagnosticService.createDiagnostic(request);
//        return new ResponseEntity<>(created, HttpStatus.CREATED);
//    }
//
//    @GetMapping("/reception/{receptionId}")
//    public ResponseEntity<DiagnosticDTO> getDiagnosticByReception(@PathVariable Long receptionId) {
//        DiagnosticDTO diagnostic = diagnosticService.getDiagnosticByReception(receptionId);
//        return ResponseEntity.ok(diagnostic);
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<DiagnosticDTO> updateDiagnostic(@PathVariable Long id, @Valid @RequestBody DiagnosticRequest request) {
//        DiagnosticDTO updated = diagnosticService.updateDiagnostic(id, request);
//        return ResponseEntity.ok(updated);
//    }
//
//    @GetMapping("/{diagnosticId}/devis")
//    public ResponseEntity<DevisDTO> getDevis(@PathVariable Long diagnosticId) {
//        DevisDTO devis = diagnosticService.getDevis(diagnosticId);
//        return ResponseEntity.ok(devis);
//    }
//
//    @PatchMapping("/{diagnosticId}/devis")
//    public ResponseEntity<DevisDTO> accepterDevis(@PathVariable Long diagnosticId, @Valid @RequestBody DevisRequest request) {
//        DevisDTO devis = diagnosticService.accepterDevis(diagnosticId, request);
//        return ResponseEntity.ok(devis);
//    }
//}

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisDTO;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisRequest;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticDTO;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticRequest;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE.DiagnosticService;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE.service;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/diagnostics")
@RequiredArgsConstructor
public class DiagnosticController {

    private final service diagnosticService;

    /**
     * Crée un nouveau diagnostic pour une réception.
     * @param request les données du diagnostic (réception, pannes, etc.)
     * @return le diagnostic créé
     */
    @PostMapping
    public ResponseEntity<DiagnosticDTO> createDiagnostic(@Valid @RequestBody DiagnosticRequest request) {
        DiagnosticDTO created = diagnosticService.createDiagnostic(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    /**
     * Récupère le diagnostic associé à une réception.
     * @param receptionId l'identifiant de la réception
     * @return le diagnostic trouvé
     */
    @GetMapping("/reception/{receptionId}")
    public ResponseEntity<DiagnosticDTO> getDiagnosticByReception(@PathVariable Long receptionId) {
        DiagnosticDTO diagnostic = diagnosticService.getDiagnosticByReception(receptionId);
        return ResponseEntity.ok(diagnostic);
    }

    /**
     * Met à jour un diagnostic existant (remplace les pannes).
     * @param id l'identifiant du diagnostic
     * @param request les nouvelles données
     * @return le diagnostic mis à jour
     */
    @PutMapping("/{id}")
    public ResponseEntity<DiagnosticDTO> updateDiagnostic(
            @PathVariable Long id,
            @Valid @RequestBody DiagnosticRequest request) {
        DiagnosticDTO updated = diagnosticService.updateDiagnostic(id, request);
        return ResponseEntity.ok(updated);
    }

    /**
     * Accepte un devis pour un diagnostic donné (le devis est créé et marqué comme accepté).
     * @param diagnosticId l'identifiant du diagnostic
     * @param request les montants du devis
     * @return le devis créé
     */
    @PostMapping("/{diagnosticId}/devis/accept")
    public ResponseEntity<DevisDTO> acceptDevis(
            @PathVariable Long diagnosticId,
            @Valid @RequestBody DevisRequest request) {
        DevisDTO devis = diagnosticService.accepterDevis(diagnosticId, request);
        return new ResponseEntity<>(devis, HttpStatus.CREATED);
    }

    /**
     * Récupère le devis associé à un diagnostic.
     * @param diagnosticId l'identifiant du diagnostic
     * @return le devis trouvé
     */
    @GetMapping("/{diagnosticId}/devis")
    public ResponseEntity<DevisDTO> getDevis(@PathVariable Long diagnosticId) {
        DevisDTO devis = diagnosticService.getDevis(diagnosticId);
        return ResponseEntity.ok(devis);
    }
}