package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

//@Data
//public class DiagnosticRequest {
//    private Long receptionId;
//    private LocalDateTime dateDiagnostic;
//    private Long diagnostiqueurId;
//    private String notesDiagnostic;
//    private List<PanneDetecteeRequestDTO> pannesDetectees;
//}


import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class DiagnosticRequest {
    @NotNull
    private Long receptionId;

    private LocalDateTime dateDiagnostic;

    @NotNull
    private Long diagnostiqueurId;

    private String notesDiagnostic;

    @Valid
    private List<PanneDetecteeRequestDTO> pannesDetectees;
}

