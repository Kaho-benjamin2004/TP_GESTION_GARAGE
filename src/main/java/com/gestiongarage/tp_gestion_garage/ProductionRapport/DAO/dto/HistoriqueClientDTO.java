package com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HistoriqueClientDTO {
    private Long receptionId;
    private String numeroDossier;
    private LocalDateTime dateReception;
    private String vehiculeImmatriculation;
    private String descriptionPanne;
    private String statut;
}