package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto;

import lombok.*;

import java.math.BigDecimal;


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneFactureDTO {
    private Long id;
    private String description;
    private Integer quantite;
    private BigDecimal prixUnitaire;
    private BigDecimal montantTotal;
    private BigDecimal tauxTVA;
    private Long operationId;
    private Long pieceId;

}