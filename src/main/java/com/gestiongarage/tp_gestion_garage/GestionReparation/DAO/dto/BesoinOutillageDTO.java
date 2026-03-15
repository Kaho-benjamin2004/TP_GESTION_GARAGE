package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.StatutBesoinOutillage;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BesoinOutillageDTO {
    private Long id;
    private Long reparationId;
    private Long outillageId;
    private String designation;
    private LocalDateTime dateReservation;
    private LocalDateTime dateRetour;
    private StatutBesoinOutillage statut;
}