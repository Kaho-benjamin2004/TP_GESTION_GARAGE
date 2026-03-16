package com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FactureImpayeeDTO {
    private Long factureId;
    private String numeroFacture;
    private String clientNom;
    private LocalDate dateEmission;
    private LocalDate dateEcheance;
    private BigDecimal montantRestant;
}