package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationReparationDTO {
    private Long id;
    private Long reparationId;
    private Long panneId;
    private String description;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;
    private Integer tempsPasse;
    private Long mecanicienId;
    private String commentaire;
}