package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AffecterOutilRequest {
    @NotNull
    private Long receptionId;
    @NotNull
    private Long outilId;
    private LocalDateTime dateDebut;
    private String observations;
}
