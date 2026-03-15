package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReparationRequest {
    @NotNull
    private Long receptionId; // la réception pour laquelle on démarre la réparation

    private LocalDateTime dateDebut;

    private LocalDateTime dateFinPrevue;

    private Long chefAtelierId;

    private String notes;
}