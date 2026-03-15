package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.StatutBesoinOutillage;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BesoinOutillageRequest {
    private Long outillageId;

    @NotBlank
    private String designation;

    private LocalDateTime dateReservation;
    private LocalDateTime dateRetour;
    private StatutBesoinOutillage statut;
}