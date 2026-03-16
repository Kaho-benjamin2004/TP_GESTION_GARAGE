package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutillageDTO {
    private Long id;
    private String code;
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