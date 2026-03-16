package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE.OutillageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/outillages")
@RequiredArgsConstructor
public class OutillageController {

    private final OutillageService outillageService;

    @GetMapping
    public ResponseEntity<List<OutillageDTO>> getAllOutillages() {
        return ResponseEntity.ok(outillageService.getAllOutillages());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OutillageDTO> getOutillage(@PathVariable Long id) {
        return ResponseEntity.ok(outillageService.getOutillage(id));
    }

    @GetMapping("/disponibles")
    public ResponseEntity<List<OutillageDTO>> getOutillagesDisponibles() {
        return ResponseEntity.ok(outillageService.getOutillagesDisponibles());
    }

    @PostMapping
    public ResponseEntity<OutillageDTO> createOutillage(@Valid @RequestBody OutillageRequestDTO request) {
        OutillageDTO created = outillageService.createOutillage(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OutillageDTO> updateOutillage(@PathVariable Long id, @Valid @RequestBody OutillageRequestDTO request) {
        OutillageDTO updated = outillageService.updateOutillage(id, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOutillage(@PathVariable Long id) {
        outillageService.deleteOutillage(id);
        return ResponseEntity.noContent().build();
    }
}