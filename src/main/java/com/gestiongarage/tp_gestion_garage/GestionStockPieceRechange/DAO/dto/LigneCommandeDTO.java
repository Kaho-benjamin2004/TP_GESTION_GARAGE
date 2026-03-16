package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandeDTO {
    private Long id;
    private Long pieceId;
    private String pieceReference;
    private String pieceNom;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private Integer quantiteRecue;
}