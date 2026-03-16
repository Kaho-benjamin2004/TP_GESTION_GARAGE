package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.ModePaiement;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
public class PaiementRequestDTO {
    @NotNull
    private Long factureId;

    @NotNull
    @Positive
    private BigDecimal montant;

    private LocalDate datePaiement;

    @NotNull
    private ModePaiement modePaiement;

    private String reference;
    private Long caisseId;
}