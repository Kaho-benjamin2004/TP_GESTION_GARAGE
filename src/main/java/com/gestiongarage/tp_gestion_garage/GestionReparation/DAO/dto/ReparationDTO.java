package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinOutillageDTO;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinPieceDTO;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.OperationReparationDTO;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.StatutReparation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReparationDTO {
    private Long id;
    private Long receptionId;
    private LocalDateTime dateDebut;
    private LocalDateTime dateFinPrevue;
    private LocalDateTime dateFinReelle;
    private com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.StatutReparation statut;
    private String notes;
    private Long chefAtelierId;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private List<OperationReparationDTO> operations;
    private List<BesoinPieceDTO> besoinsPieces;
    private List<BesoinOutillageDTO> besoinsOutillage;
}
