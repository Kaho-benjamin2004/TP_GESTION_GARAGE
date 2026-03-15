package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.StatutBesoin;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BesoinPieceDTO {
    private Long id;
    private Long reparationId;
    private Long pieceId;
    private String designation;
    private Integer quantiteDemandee;
    private Integer quantiteUtilisee;
    private StatutBesoin statut;
    private LocalDateTime dateDemande;
    private LocalDateTime dateReception;
    private String remarques;
}