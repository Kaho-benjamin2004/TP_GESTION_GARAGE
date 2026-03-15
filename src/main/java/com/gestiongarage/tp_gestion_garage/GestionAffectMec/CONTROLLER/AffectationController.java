package com.gestiongarage.tp_gestion_garage.GestionAffectMec.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto.*;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.SERVICE.AffectationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/affectations")
@RequiredArgsConstructor
public class AffectationController {

    private final AffectationService affectationService;

    // ========== MECANICIENS ==========
    @PostMapping("/mecaniciens")
    public ResponseEntity<MecanicienDTO> createMecanicien(@Valid @RequestBody MecanicienRequestDTO request) {
        return new ResponseEntity<>(affectationService.createMecanicien(request), HttpStatus.CREATED);
    }

    @PutMapping("/mecaniciens/{id}")
    public ResponseEntity<MecanicienDTO> updateMecanicien(@PathVariable Long id, @Valid @RequestBody MecanicienRequestDTO request) {
        return ResponseEntity.ok(affectationService.updateMecanicien(id, request));
    }

    @GetMapping("/mecaniciens/{id}")
    public ResponseEntity<MecanicienDTO> getMecanicien(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getMecanicien(id));
    }

    @GetMapping("/mecaniciens")
    public ResponseEntity<List<MecanicienDTO>> getAllMecaniciens() {
        return ResponseEntity.ok(affectationService.getAllMecaniciens());
    }

    @GetMapping("/mecaniciens/disponibles")
    public ResponseEntity<List<MecanicienDTO>> getMecaniciensDisponibles() {
        return ResponseEntity.ok(affectationService.getMecaniciensDisponibles());
    }

    @DeleteMapping("/mecaniciens/{id}")
    public ResponseEntity<Void> deleteMecanicien(@PathVariable Long id) {
        affectationService.deleteMecanicien(id);
        return ResponseEntity.noContent().build();
    }

    // ========== OUTILS ==========
    @PostMapping("/outils")
    public ResponseEntity<OutilDTO> createOutil(@Valid @RequestBody OutilRequestDTO request) {
        return new ResponseEntity<>(affectationService.createOutil(request), HttpStatus.CREATED);
    }

    @PutMapping("/outils/{id}")
    public ResponseEntity<OutilDTO> updateOutil(@PathVariable Long id, @Valid @RequestBody OutilRequestDTO request) {
        return ResponseEntity.ok(affectationService.updateOutil(id, request));
    }

    @GetMapping("/outils/{id}")
    public ResponseEntity<OutilDTO> getOutil(@PathVariable Long id) {
        return ResponseEntity.ok(affectationService.getOutil(id));
    }

    @GetMapping("/outils")
    public ResponseEntity<List<OutilDTO>> getAllOutils() {
        return ResponseEntity.ok(affectationService.getAllOutils());
    }

    @GetMapping("/outils/disponibles")
    public ResponseEntity<List<OutilDTO>> getOutilsDisponibles() {
        return ResponseEntity.ok(affectationService.getOutilsDisponibles());
    }

    @DeleteMapping("/outils/{id}")
    public ResponseEntity<Void> deleteOutil(@PathVariable Long id) {
        affectationService.deleteOutil(id);
        return ResponseEntity.noContent().build();
    }

    // ========== AFFECTATIONS MECANICIENS ==========
    @PostMapping("/mecaniciens/affecter")
    public ResponseEntity<AffectationMecanoDTO> affecterMecanicien(@Valid @RequestBody AffecterMecanoRequest request) {
        return new ResponseEntity<>(affectationService.affecterMecanicien(request), HttpStatus.CREATED);
    }

    @PutMapping("/mecaniciens/terminer/{affectationId}")
    public ResponseEntity<AffectationMecanoDTO> terminerAffectationMecano(
            @PathVariable Long affectationId,
            @RequestParam Integer tempsPasse) {
        return ResponseEntity.ok(affectationService.terminerAffectationMecano(affectationId, tempsPasse));
    }

    @GetMapping("/mecaniciens/reception/{receptionId}")
    public ResponseEntity<List<AffectationMecanoDTO>> getAffectationsMecanoByReception(@PathVariable Long receptionId) {
        return ResponseEntity.ok(affectationService.getAffectationsMecanoByReception(receptionId));
    }

    // ========== AFFECTATIONS OUTILS ==========
    @PostMapping("/outils/affecter")
    public ResponseEntity<AffectationOutilDTO> affecterOutil(@Valid @RequestBody AffecterOutilRequest request) {
        return new ResponseEntity<>(affectationService.affecterOutil(request), HttpStatus.CREATED);
    }

    @PutMapping("/outils/terminer/{affectationId}")
    public ResponseEntity<AffectationOutilDTO> terminerAffectationOutil(@PathVariable Long affectationId) {
        return ResponseEntity.ok(affectationService.terminerAffectationOutil(affectationId));
    }

    @GetMapping("/outils/reception/{receptionId}")
    public ResponseEntity<List<AffectationOutilDTO>> getAffectationsOutilByReception(@PathVariable Long receptionId) {
        return ResponseEntity.ok(affectationService.getAffectationsOutilByReception(receptionId));
    }
}