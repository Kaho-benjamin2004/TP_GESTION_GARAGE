package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Devis;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Diagnostic;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository.DevisRepository;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository.DiagnosticRepository;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository.PanneDetecteeRepository;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.*;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.DevisStatut;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.SERVICE.DiagnosticService;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class service implements DiagnosticService {

    private final DiagnosticRepository diagnosticRepository;
    private final PanneDetecteeRepository panneRepository;
    private final DevisRepository devisRepository;
    private final ReceptionRepository receptionRepository;

    @Override
    @Transactional
    public DiagnosticDTO createDiagnostic(DiagnosticRequest request) {
        Reception reception = receptionRepository.findById(request.getReceptionId())
                .orElseThrow(() -> new RuntimeException("Réception non trouvée"));

        if (diagnosticRepository.existsByReceptionId(request.getReceptionId())) {
            throw new IllegalStateException("Un diagnostic existe déjà pour cette réception");
        }

        Diagnostic diagnostic = Diagnostic.builder()
                .reception(reception)
                .dateDiagnostic(request.getDateDiagnostic() != null ? request.getDateDiagnostic() : LocalDateTime.now())
                .diagnostiqueurId(request.getDiagnostiqueurId())
                .notesDiagnostic(request.getNotesDiagnostic())
                .build();

        if (request.getPannesDetectees() != null) {
            List<PanneDetectee> pannes = request.getPannesDetectees().stream()
                    .map(p -> PanneDetectee.builder()
                            .diagnostic(diagnostic)
                            .description(p.getDescription())
                            .urgence(p.getUrgence())
                            .tempsEstime(p.getTempsEstime())
                            .piecesNecessaires(p.getPiecesNecessaires())
                            .codePanne(p.getCodePanne())
                            .build())
                    .collect(Collectors.toList());
            diagnostic.getPannesDetectees().addAll(pannes);
        }

        reception.setStatut(StatutReception.diagnostic);

        Diagnostic saved = diagnosticRepository.save(diagnostic);
        return mapToDTO(saved);
    }

    @Override
    public DiagnosticDTO getDiagnosticByReception(Long receptionId) {
        Diagnostic diagnostic = diagnosticRepository.findByReceptionId(receptionId)
                .orElseThrow(() -> new RuntimeException("Aucun diagnostic trouvé pour cette réception"));
        return mapToDTO(diagnostic);
    }

    @Override
    @Transactional
    public DiagnosticDTO updateDiagnostic(Long id, DiagnosticRequest request) {
        Diagnostic diagnostic = diagnosticRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Diagnostic non trouvé"));

        if (request.getNotesDiagnostic() != null) {
            diagnostic.setNotesDiagnostic(request.getNotesDiagnostic());
        }
        if (request.getDateDiagnostic() != null) {
            diagnostic.setDateDiagnostic(request.getDateDiagnostic());
        }
        if (request.getDiagnostiqueurId() != null) {
            diagnostic.setDiagnostiqueurId(request.getDiagnostiqueurId());
        }

        if (request.getPannesDetectees() != null) {
            diagnostic.getPannesDetectees().clear();
            List<PanneDetectee> nouvellesPannes = request.getPannesDetectees().stream()
                    .map(p -> PanneDetectee.builder()
                            .diagnostic(diagnostic)
                            .description(p.getDescription())
                            .urgence(p.getUrgence())
                            .tempsEstime(p.getTempsEstime())
                            .piecesNecessaires(p.getPiecesNecessaires())
                            .codePanne(p.getCodePanne())
                            .build())
                    .collect(Collectors.toList());
            diagnostic.getPannesDetectees().addAll(nouvellesPannes);
        }

        Diagnostic updated = diagnosticRepository.save(diagnostic);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public DevisDTO accepterDevis(Long diagnosticId, DevisRequest request) {
        Diagnostic diagnostic = diagnosticRepository.findById(diagnosticId)
                .orElseThrow(() -> new RuntimeException("Diagnostic non trouvé"));

        if (diagnostic.getDevis() != null) {
            throw new IllegalStateException("Un devis existe déjà pour ce diagnostic");
        }

        BigDecimal montantTotal = request.getMontantMainOeuvre().add(request.getMontantPieces());

        Devis devis = Devis.builder()
                .diagnostic(diagnostic)
                .montantMainOeuvre(request.getMontantMainOeuvre())
                .montantPieces(request.getMontantPieces())
                .montantTotal(montantTotal)
                .statut(DevisStatut.accepte)
                .dateValidation(LocalDateTime.now())
                .notes(request.getNotes())
                .build();

        Devis saved = devisRepository.save(devis);
        diagnostic.setDevis(saved);
        diagnosticRepository.save(diagnostic);

        Reception reception = diagnostic.getReception();
        reception.setStatut(StatutReception.en_reparation);
        reception.setAccordClient(true);
        reception.setAccordDevisId(saved.getId());
        receptionRepository.save(reception);

        return mapDevisToDTO(saved);
    }

    @Override
    public DevisDTO getDevis(Long diagnosticId) {
        Devis devis = devisRepository.findByDiagnosticId(diagnosticId);
//                .orElseThrow(() -> new RuntimeException("Aucun devis trouvé pour ce diagnostic"));
        return mapDevisToDTO(devis);
    }

    // Méthodes de mapping
    private DiagnosticDTO mapToDTO(Diagnostic diagnostic) {
        DiagnosticDTO dto = DiagnosticDTO.builder()
                .id(diagnostic.getId())
                .receptionId(diagnostic.getReception().getId())
                .dateDiagnostic(diagnostic.getDateDiagnostic())
                .diagnostiqueurId(diagnostic.getDiagnostiqueurId())
                .notesDiagnostic(diagnostic.getNotesDiagnostic())
                .dateCreation(diagnostic.getDateCreation())
                .build();

        if (diagnostic.getPannesDetectees() != null) {
            dto.setPannesDetectees(diagnostic.getPannesDetectees().stream()
                    .map(this::mapPanneToDTO)
                    .collect(Collectors.toList()));
        }
        if (diagnostic.getDevis() != null) {
            dto.setDevis(mapDevisToDTO(diagnostic.getDevis()));
        }
        return dto;
    }

    private PanneDetecteeDTO mapPanneToDTO(PanneDetectee panne) {
        return PanneDetecteeDTO.builder()
                .id(panne.getId())
                .diagnosticId(panne.getDiagnostic().getId())
                .description(panne.getDescription())
                .urgence(panne.getUrgence())
                .tempsEstime(panne.getTempsEstime())
                .piecesNecessaires(panne.getPiecesNecessaires())
                .codePanne(panne.getCodePanne())
                .build();
    }

    private DevisDTO mapDevisToDTO(Devis devis) {
        return DevisDTO.builder()
                .id(devis.getId())
                .diagnosticId(devis.getDiagnostic().getId())
                .montantMainOeuvre(devis.getMontantMainOeuvre())
                .montantPieces(devis.getMontantPieces())
                .montantTotal(devis.getMontantTotal())
                .status(devis.getStatut())
                .dateValidation(devis.getDateValidation())
                .notes(devis.getNotes())
                .dateCreation(devis.getDateCreation())
                .build();
    }
}