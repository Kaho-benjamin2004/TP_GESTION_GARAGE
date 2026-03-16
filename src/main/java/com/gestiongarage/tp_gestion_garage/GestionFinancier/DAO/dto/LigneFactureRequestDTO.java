package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.Getter;

import java.math.BigDecimal;

@Data
public class LigneFactureRequestDTO {
    private String description;
    @Positive
    private Integer quantite;
    @NotNull
    private BigDecimal prixUnitaire;
    private BigDecimal tauxTVA; // par défaut 20.0
    private Long operationId; // si lié à une opération
    private Long pieceId;


}
