package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommandeFournisseurRequestDTO {
    @NotNull(message = "L'ID du fournisseur est obligatoire")
    private Long fournisseurId;

    private LocalDate dateCommande;

    @NotNull(message = "La liste des lignes de commande est obligatoire")
    private List<LigneCommandeRequestDTO> lignesCommande;

    private String observations;
}
