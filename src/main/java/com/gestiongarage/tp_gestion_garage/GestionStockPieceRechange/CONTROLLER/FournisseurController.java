package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE.FournisseurService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fournisseurs")
@RequiredArgsConstructor
public class FournisseurController {

    private final FournisseurService fournisseurService;

    @GetMapping
    public ResponseEntity<List<FournisseurDTO>> getAllFournisseurs() {
        return ResponseEntity.ok(fournisseurService.getAllFournisseurs());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FournisseurDTO> getFournisseur(@PathVariable Long id) {
        return ResponseEntity.ok(fournisseurService.getFournisseur(id));
    }

    @PostMapping
    public ResponseEntity<FournisseurDTO> createFournisseur(@Valid @RequestBody FournisseurRequestDTO request) {
        FournisseurDTO created = fournisseurService.createFournisseur(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FournisseurDTO> updateFournisseur(@PathVariable Long id, @Valid @RequestBody FournisseurRequestDTO request) {
        FournisseurDTO updated = fournisseurService.updateFournisseur(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFournisseur(@PathVariable Long id) {
        fournisseurService.deleteFournisseur(id);
        return ResponseEntity.noContent().build();
    }
}