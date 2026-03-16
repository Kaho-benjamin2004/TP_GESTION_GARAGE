package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.StatutFacture;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureDTO {
    private Long id;
    private String numeroFacture;
    private Long receptionId;
    private Long clientId;
    private String clientNom;
    private LocalDate dateEmission;
    private LocalDate dateEcheance;
    private StatutFacture statut;
    private BigDecimal montantHT;
    private BigDecimal montantTVA;
    private BigDecimal montantTTC;
    private BigDecimal montantRegle;
    private BigDecimal montantRestant;
    private String notes;
    private List<LigneFactureDTO> lignes;
    private List<PaiementDTO> paiements;
    private LocalDateTime dateCreation;
}