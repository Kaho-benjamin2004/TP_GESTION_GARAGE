package com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanneRecurrenteDTO {
    private String modeleVehicule;
    private String descriptionPanne;
    private Long occurrence;
}