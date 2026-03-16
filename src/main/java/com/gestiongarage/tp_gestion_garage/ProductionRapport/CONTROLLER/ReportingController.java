package com.gestiongarage.tp_gestion_garage.ProductionRapport.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto.*;
import com.gestiongarage.tp_gestion_garage.ProductionRapport.SERVICE.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/rapports")
@RequiredArgsConstructor
public class ReportingController {

    private final ReportingService reportingService;

    // Opérationnels
    @GetMapping("/vehicules-en-attente")
    public ResponseEntity<List<VehiculeEnAttenteDTO>> getVehiculesEnAttente() {
        return ResponseEntity.ok(reportingService.getVehiculesEnAttente());
    }

    // Financiers
    @GetMapping("/chiffre-affaires")
    public ResponseEntity<List<ChiffreAffairesDTO>> getChiffreAffaires(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate debut,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {
        return ResponseEntity.ok(reportingService.getChiffreAffairesParPeriode(debut, fin));
    }

    @GetMapping("/factures-impayees")
    public ResponseEntity<List<FactureImpayeeDTO>> getFacturesImpayees() {
        return ResponseEntity.ok(reportingService.getFacturesImpayees());
    }

    // Stock
    @GetMapping("/etat-stock")
    public ResponseEntity<List<EtatStockDTO>> getEtatStock() {
        return ResponseEntity.ok(reportingService.getEtatStock());
    }

    // Clients
    @GetMapping("/client/{clientId}/historique")
    public ResponseEntity<List<HistoriqueClientDTO>> getHistoriqueClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(reportingService.getHistoriqueClient(clientId));
    }

    // Techniques
    @GetMapping("/pannes-recurrentes")
    public ResponseEntity<List<PanneRecurrenteDTO>> getPannesRecurrentes() {
        return ResponseEntity.ok(reportingService.getPannesRecurrentesParModele());
    }
}