package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.MouvementStockDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE.PieceRechangeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pieces")
@RequiredArgsConstructor
public class PieceRechangeController {
    private final PieceRechangeService pieceService;

    @PostMapping
    public ResponseEntity<PieceRechangeDTO> createPiece(@Valid @RequestBody PieceRechangeRequestDTO request) {
        return new ResponseEntity<>(pieceService.createPiece(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PieceRechangeDTO> updatePiece(@PathVariable Long id, @Valid @RequestBody PieceRechangeRequestDTO request) {
        return ResponseEntity.ok(pieceService.updatePiece(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PieceRechangeDTO> getPiece(@PathVariable Long id) {
        return ResponseEntity.ok(pieceService.getPiece(id));
    }

    @GetMapping("/reference/{reference}")
    public ResponseEntity<PieceRechangeDTO> getPieceByReference(@PathVariable String reference) {
        return ResponseEntity.ok(pieceService.getPieceByReference(reference));
    }

    @GetMapping
    public ResponseEntity<List<PieceRechangeDTO>> getAllPieces() {
        return ResponseEntity.ok(pieceService.getAllPieces());
    }

    @GetMapping("/alerte")
    public ResponseEntity<List<PieceRechangeDTO>> getPiecesEnAlerte() {
        return ResponseEntity.ok(pieceService.getPiecesEnAlerte());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePiece(@PathVariable Long id) {
        pieceService.deletePiece(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/entree")
    public ResponseEntity<MouvementStockDTO> entreeStock(
            @PathVariable Long id,
            @RequestParam Integer quantite,
            @RequestParam String motif,
            @RequestParam(required = false) Long documentId) {
        return new ResponseEntity<>(pieceService.entreeStock(id, quantite, motif, documentId), HttpStatus.CREATED);
    }

    @PostMapping("/{id}/sortie")
    public ResponseEntity<MouvementStockDTO> sortieStock(
            @PathVariable Long id,
            @RequestParam Integer quantite,
            @RequestParam String motif,
            @RequestParam(required = false) Long documentId) {
        return new ResponseEntity<>(pieceService.sortieStock(id, quantite, motif, documentId), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/mouvements")
    public ResponseEntity<List<MouvementStockDTO>> getMouvements(@PathVariable Long id) {
        return ResponseEntity.ok(pieceService.getMouvements(id));
    }
}