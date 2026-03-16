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
public class VehiculeEnAttenteDTO {
    private Long receptionId;
    private String numeroDossier;
    private String clientNom;
    private String vehiculeImmatriculation;
    private String vehiculeModele;
    private LocalDateTime dateReception;
    private String descriptionPanne;
}