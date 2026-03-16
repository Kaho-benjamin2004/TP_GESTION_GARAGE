package com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EtatStockDTO {
    private Long pieceId;
    private String reference;
    private String nom;
    private Integer stock;
    private Integer seuilAlerte;
    private String emplacement;
}