package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisDTO;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisRequest;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticDTO;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DiagnosticRequest;

public interface DiagnosticService {
    DiagnosticDTO createDiagnostic(DiagnosticRequest request);
    DiagnosticDTO getDiagnosticByReception(Long receptionId);
    DiagnosticDTO updateDiagnostic(Long id, DiagnosticRequest request);
    DevisDTO accepterDevis(Long diagnosticId, DevisRequest request); // pour valider le devis
    DevisDTO getDevis(Long diagnosticId);
}