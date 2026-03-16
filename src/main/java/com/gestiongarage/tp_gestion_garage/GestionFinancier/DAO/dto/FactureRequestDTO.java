package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class FactureRequestDTO {
    @NotNull
    private Long receptionId;

    private LocalDate dateEmission;
    private LocalDate dateEcheance;

    @NotNull
    private List<LigneFactureRequestDTO> lignes;

    private String notes;
}

