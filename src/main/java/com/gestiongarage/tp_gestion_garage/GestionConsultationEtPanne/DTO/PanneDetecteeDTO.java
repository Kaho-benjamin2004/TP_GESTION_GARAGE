package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.NiveauUrgence;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.UrgencePanne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class PanneDetecteeDTO {
//    private String description;
//    private UrgencePanne urgence;
//    private Integer tempsReparationEstime; // au lieu de tempsEstime
//    private String piecesNecessaires;
//    private String codePanne;
//
//}

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanneDetecteeDTO {
    private Long id;
    private Long diagnosticId;
    private String description;
    private UrgencePanne urgence;
    private Integer tempsEstime;
    private String piecesNecessaires;
    private String codePanne;
}