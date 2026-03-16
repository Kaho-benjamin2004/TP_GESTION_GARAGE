package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.StatutCommande;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeFournisseurDTO {
    private Long id;
    private Long fournisseurId;
    private String fournisseurNom;
    private LocalDate dateCommande;
    private StatutCommande statut;
    private List<LigneCommandeDTO> lignesCommande;
    private LocalDate dateReceptionPrevue;
    private LocalDate dateReceptionReelle;
    private String observations;
    private LocalDateTime dateCreation;
}