package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;

//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class DiagnosticDTO {
//    private Long id;
//    private Long receptionId;
//    private Long mechanicienId;
//    private LocalDateTime dateDiagnostic;
//    private String observations;
//    private String recommandations;
//    private List<PanneDetecteeDTO>pannesDetectees;
//    private DevisDTO devis;
//    private LocalDateTime dateCreation;
//    private LocalDateTime dateModification;
//}


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
public class DiagnosticDTO {
    private Long id;
    private Long receptionId;
    private LocalDateTime dateDiagnostic;
    private Long diagnostiqueurId;
    private String notesDiagnostic;
    private List<PanneDetecteeDTO> pannesDetectees;
    private DevisDTO devis;
    private LocalDateTime dateCreation;
}