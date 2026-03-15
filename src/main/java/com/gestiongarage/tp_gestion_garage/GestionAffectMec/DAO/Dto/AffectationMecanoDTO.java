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
public class AffectationMecanoDTO {
    private Long id;
    private Long receptionId;
    private Long mecanicienId;
    private String mecanicienNom;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer tempsPasse;
    private String description;
}