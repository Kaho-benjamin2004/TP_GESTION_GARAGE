package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OperationReparationRequest {
    private Long panneId;

    @NotBlank
    private String description;

    @NotNull
    private LocalDateTime dateDebut;

    private LocalDateTime dateFin;

    @Positive
    private Integer tempsPasse;

    @NotNull
    private Long mecanicienId;

    private String commentaire;
}