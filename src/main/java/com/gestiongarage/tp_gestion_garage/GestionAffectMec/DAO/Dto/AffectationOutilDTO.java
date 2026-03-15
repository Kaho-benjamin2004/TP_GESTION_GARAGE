package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffectationOutilDTO {
    private Long id;
    private Long receptionId;
    private Long outilId;
    private String outilNom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private String observations;
}