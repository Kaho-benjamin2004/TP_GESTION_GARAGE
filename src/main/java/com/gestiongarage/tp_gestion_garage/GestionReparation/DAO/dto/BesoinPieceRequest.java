package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.StatutBesoin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class BesoinPieceRequest {
    private Long pieceId;

    @NotBlank
    private String designation;

    @NotNull @Positive
    private Integer quantiteDemandee;

    private Integer quantiteUtilisee;
    private StatutBesoin statut;
    private String remarques;
}