package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LigneCommandeRequestDTO {
    @NotNull(message = "L'ID de la pièce est obligatoire")
    private Long pieceId;

    @NotNull(message = "La quantité est obligatoire")
    @Positive(message = "La quantité doit être positive")
    private Integer quantite;

    private Double prixUnitaire;
}
