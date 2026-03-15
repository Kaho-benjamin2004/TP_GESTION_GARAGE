package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OutilDTO {
    private Long id;
    private String nom;
    private String reference;
    private String marque;
    private String description;
    private EtatOutil etat;
    private Boolean disponible;
    private String emplacement;
    private LocalDateTime dateProchaineMaintenance;
    private LocalDateTime dateCreation;
}