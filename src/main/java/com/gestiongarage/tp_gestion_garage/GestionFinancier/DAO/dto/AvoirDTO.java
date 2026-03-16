package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AvoirDTO {
    private Long id;
    private String numeroAvoir;
    private Long factureSourceId;      // ID de la facture d'origine
    private String factureSourceNumero; // Numéro de la facture (optionnel, pour affichage)
    private Long clientId;
    private String clientNom;           // Optionnel, pour affichage
    private LocalDate dateEmission;
    private BigDecimal montant;
    private String raison;
    private Boolean utilise;
    private LocalDateTime dateCreation;
}