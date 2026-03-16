package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutillageRequestDTO {
    @NotBlank(message = "Le code est obligatoire")
    @Size(max = 50)
    private String code;

    @NotBlank(message = "Le nom est obligatoire")
    private String nom;

    private String marque;
    private String modele;
    private String numeroSerie;
    private String emplacement;
    private EtatOutil etat;
    private LocalDate dateAchat;
    private LocalDate prochaineMaintenance;
    private Boolean disponible;
    private String notes;
}