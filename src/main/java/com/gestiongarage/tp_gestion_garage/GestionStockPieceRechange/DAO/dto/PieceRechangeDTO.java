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
public class PieceRechangeDTO {
    private Long id;
    private String reference;
    private String nom;
    private String description;
    private String marque;
    private String modele;
    private Integer stock;
    private Integer seuilAlerte;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private String emplacement;
    private Long fournisseurId;
    private String fournisseurNom;
}