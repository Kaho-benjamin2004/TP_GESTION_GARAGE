package com.gestiongarage.tp_gestion_garage.GestionFinancier.CONTROLLER;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.FactureDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.FactureRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.PaiementDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.PaiementRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.StatutFacture;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.SERVICE.FactureService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/factures")
@RequiredArgsConstructor
public class FactureController {
    private final FactureService factureService;

    @PostMapping
    public ResponseEntity<FactureDTO> creerFacture(@Valid @RequestBody FactureRequestDTO request) {
        return new ResponseEntity<>(factureService.creerFacture(request), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FactureDTO> getFacture(@PathVariable Long id) {
        return ResponseEntity.ok(factureService.getFacture(id));
    }

    @GetMapping("/numero/{numero}")
    public ResponseEntity<FactureDTO> getByNumero(@PathVariable String numero) {
        return ResponseEntity.ok(factureService.getFactureByNumero(numero));
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<FactureDTO>> getByClient(@PathVariable Long clientId) {
        return ResponseEntity.ok(factureService.getFacturesByClient(clientId));
    }

    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<FactureDTO>> getByStatut(@PathVariable StatutFacture statut) {
        return ResponseEntity.ok(factureService.getFacturesByStatut(statut));
    }

    @PostMapping("/paiement")
    public ResponseEntity<PaiementDTO> enregistrerPaiement(@Valid @RequestBody PaiementRequestDTO request) {
        return new ResponseEntity<>(factureService.enregistrerPaiement(request), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> annulerFacture(@PathVariable Long id) {
        factureService.annulerFacture(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/pdf")
    public ResponseEntity<byte[]> genererPDF(@PathVariable Long id) {
        byte[] pdf = factureService.genererPDF(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=facture.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdf);
    }
}