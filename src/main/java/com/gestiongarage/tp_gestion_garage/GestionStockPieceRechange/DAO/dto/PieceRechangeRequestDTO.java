package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import jakarta.validation.constraints.NotBlank;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class PieceRechangeRequestDTO {
    @NotBlank
    private String reference;
    @NotBlank
    private String nom;
    private String description;
    private String marque;
    private String modele;
    @PositiveOrZero
    private Integer stock;
    @PositiveOrZero
    private Integer seuilAlerte;
    private BigDecimal prixAchat;
    private BigDecimal prixVente;
    private String emplacement;
    private Long fournisseurId;
}