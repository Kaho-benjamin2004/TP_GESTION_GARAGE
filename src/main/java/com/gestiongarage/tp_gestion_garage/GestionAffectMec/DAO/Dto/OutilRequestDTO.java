package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OutilRequestDTO {
    @NotBlank
    private String nom;

    private String reference;
    private String marque;
    private String description;
    private EtatOutil etat;
    private String emplacement;
    private LocalDateTime dateProchaineMaintenance;
}