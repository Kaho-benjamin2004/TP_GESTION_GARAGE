package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.DevisStatut;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
//
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class DevisDTO {
//    private Long id;
//    private Long diagnosticId;
//    private BigDecimal totalMainOeuvre;
//    private BigDecimal totalPieces;
//    private BigDecimal totalToutesTaxes;
//    private BigDecimal tauxHoraire;
//    private StatutDevis statut;
//    private LocalDateTime dateValidationClient;
//    private String remarques;
//    private LocalDateTime dateCreation;
//}


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DevisDTO {
    private Long id;
    private Long diagnosticId;
    private BigDecimal montantMainOeuvre;  // aligné avec l'entité
    private BigDecimal montantPieces;
    private BigDecimal montantTotal;
    private DevisStatut status;
    private LocalDateTime dateValidation;
    private String notes;
    private LocalDateTime dateCreation;
}