package com.gestiongarage.tp_gestion_garage.GestionReception.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.NouvelleReceptionRequest;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ReceptionDTO;

import com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE.ReceptionServiceImpl;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping
public class reception{
private final ReceptionServiceImpl receptionService;

    public reception(ReceptionServiceImpl receptionService) {
        this.receptionService = receptionService;
    }

    @PostMapping
    public ResponseEntity<ReceptionDTO> createReception(@Valid @RequestBody NouvelleReceptionRequest request) {
        ReceptionDTO created = receptionService.createReception(request);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReceptionDTO> getReception(@PathVariable Long id) {
        ReceptionDTO reception = receptionService.getReception(id);
        return ResponseEntity.ok(reception);
    }

    @GetMapping
    public ResponseEntity<List<ReceptionDTO>> getAllReceptions() {
        return ResponseEntity.ok(receptionService.getAllReceptions());
    }

    @PatchMapping("/{id}/statut")
    public ResponseEntity<ReceptionDTO> updateStatut(@PathVariable Long id, @RequestParam StatutReception statut) {
        ReceptionDTO updated = receptionService.updateStatut(id, statut);
        return ResponseEntity.ok(updated);
    }
}