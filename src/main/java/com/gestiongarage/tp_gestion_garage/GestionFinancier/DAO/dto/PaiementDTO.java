package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.ModePaiement;
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
public class PaiementDTO {
    private Long id;
    private Long factureId;
    private BigDecimal montant;
    private LocalDate datePaiement;
    private ModePaiement modePaiement;
    private String reference;
    private Long caisseId;
}