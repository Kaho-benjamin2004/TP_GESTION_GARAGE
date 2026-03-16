package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FournisseurDTO {
    private Long id;
    private String nom;
    private String contact;
    private String telephone;
    private String email;
    private String adresse;
    private String siteWeb;
    private String numeroSiret;
    private String notes;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}