package com.gestiongarage.tp_gestion_garage.GestionReparation.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinOutillageRequest;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinPieceRequest;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.ReparationDTO;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.ReparationRequest;
import com.gestiongarage.tp_gestion_garage.GestionReparation.SERVICE.ReparationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reparations")
@RequiredArgsConstructor
public class ReparationController {

    private final ReparationService reparationService;

    @PostMapping
    public ResponseEntity<ReparationDTO> demarrerReparation(@Valid @RequestBody ReparationRequest request) {
        ReparationDTO created = reparationService.demarrerReparation(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReparationDTO> getReparation(@PathVariable Long id) {
        return ResponseEntity.ok(reparationService.getReparation(id));
    }

    @GetMapping("/reception/{receptionId}")
    public ResponseEntity<ReparationDTO> getByReception(@PathVariable Long receptionId) {
        return ResponseEntity.ok(reparationService.getReparationByReception(receptionId));
    }

    @GetMapping
    public ResponseEntity<List<ReparationDTO>> listAll() {
        return ResponseEntity.ok(reparationService.listAll());
    }

    @PostMapping("/{reparationId}/operations")
    public ResponseEntity<ReparationDTO> addOperation(
            @PathVariable Long reparationId,
            @Valid @RequestBody com.garage.reception.dto.OperationReparationRequest request) {
        return ResponseEntity.ok(reparationService.ajouterOperation(reparationId, request));
    }

    @PostMapping("/{reparationId}/besoins-pieces")
    public ResponseEntity<ReparationDTO> addBesoinPiece(
            @PathVariable Long reparationId,
            @Valid @RequestBody BesoinPieceRequest request) {
        return ResponseEntity.ok(reparationService.ajouterBesoinPiece(reparationId, request));
    }

    @PostMapping("/{reparationId}/besoins-outillage")
    public ResponseEntity<ReparationDTO> addBesoinOutillage(
            @PathVariable Long reparationId,
            @Valid @RequestBody BesoinOutillageRequest request) {
        return ResponseEntity.ok(reparationService.ajouterBesoinOutillage(reparationId, request));
    }

    @PatchMapping("/{reparationId}/terminer")
    public ResponseEntity<ReparationDTO> terminer(@PathVariable Long reparationId) {
        return ResponseEntity.ok(reparationService.terminerReparation(reparationId));
    }

    @PatchMapping("/{reparationId}/suspendre")
    public ResponseEntity<ReparationDTO> suspendre(@PathVariable Long reparationId) {
        return ResponseEntity.ok(reparationService.suspendreReparation(reparationId));
    }

    @PatchMapping("/{reparationId}/statut")
    public ResponseEntity<ReparationDTO> updateStatut(
            @PathVariable Long reparationId,
            @RequestParam String statut) {
        return ResponseEntity.ok(reparationService.mettreAJourStatut(reparationId, statut));
    }
}