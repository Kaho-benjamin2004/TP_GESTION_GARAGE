package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE.CommandeFournisseurService;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE.CommandeFournisseurServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandes-fournisseurs")
@RequiredArgsConstructor
public class CommandeFournisseurController {

    private final CommandeFournisseurServiceImpl commandeService;

    @GetMapping
    public ResponseEntity<List<CommandeFournisseurDTO>> getAllCommandes() {
        return ResponseEntity.ok(commandeService.getAllCommandes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeFournisseurDTO> getCommande(@PathVariable Long id) {
        return ResponseEntity.ok(commandeService.getCommande(id));
    }

    @PostMapping
    public ResponseEntity<CommandeFournisseurDTO> createCommande(@Valid @RequestBody CommandeFournisseurRequestDTO request) {
        CommandeFournisseurDTO created = commandeService.createCommande(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/valider")
    public ResponseEntity<CommandeFournisseurDTO> validerCommande(@PathVariable Long id) {
        CommandeFournisseurDTO updated = commandeService.validerCommande(id);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/recevoir")
    public ResponseEntity<CommandeFournisseurDTO> recevoirCommande(@PathVariable Long id) {
        CommandeFournisseurDTO updated = commandeService.recevoirCommande(id);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCommande(@PathVariable Long id) {
        commandeService.deleteCommande(id);
        return ResponseEntity.noContent().build();
    }
}