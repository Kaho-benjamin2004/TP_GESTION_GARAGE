package com.gestiongarage.tp_gestion_garage.GestionReparation.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository.MecanicienRepository;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository.*;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.*;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.*;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.StatutReparation;
import com.gestiongarage.tp_gestion_garage.GestionReparation.SERVICE.ReparationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReparationServiceImpl implements ReparationService {

    private final ReparationRepository reparationRepository;
    private final OperationReparationRepository operationRepository;
    private final BesoinPieceRepository besoinPieceRepository;
    private final BesoinOutillageRepository besoinOutillageRepository;
    private final ReceptionRepository receptionRepository;
    private PanneDetecteeRepository panneRepository;
    private final MecanicienRepository mecanicienRepository; // à créer
    private final PieceRechangeRepository pieceRepository; // à créer (module 5)
    private final OutillageRepository outillageRepository; // à créer (module 3)

    @Override
    @Transactional
    public ReparationDTO demarrerReparation(ReparationRequest request) {
        Reception reception = receptionRepository.findById(request.getReceptionId())
                .orElseThrow(() -> new RuntimeException("Réception non trouvée"));

        // Vérifier que la réception a un devis accepté et est en attente ou en diagnostic
        if (reception.getStatut() != StatutReception.en_reparation &&
                reception.getStatut() != StatutReception.en_attente &&
                reception.getStatut() != StatutReception.diagnostic) {
            throw new IllegalStateException("La réception n'est pas dans un état permettant de démarrer une réparation");
        }

        // Vérifier qu'une réparation n'existe pas déjà
        if (reparationRepository.findByReceptionId(request.getReceptionId()).isPresent()) {
            throw new IllegalStateException("Une réparation existe déjà pour cette réception");
        }

        Mecanicien chef = null;
        if (request.getChefAtelierId() != null) {
            chef = mecanicienRepository.findById(request.getChefAtelierId())
                    .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        }

        Reparation reparation = Reparation.builder()
                .reception(reception)
                .dateDebut(request.getDateDebut() != null ? request.getDateDebut() : LocalDateTime.now())
                .dateFinPrevue(request.getDateFinPrevue())
                .chefAtelier(chef)
                .notes(request.getNotes())
                .statut(StatutReparation.EN_COURS)
                .build();

        // Mettre à jour le statut de la réception
        reception.setStatut(StatutReception.en_reparation);

        Reparation saved = reparationRepository.save(reparation);
        return mapToDTO(saved);
    }

    @Override
    public ReparationDTO getReparation(Long id) {
        Reparation reparation = reparationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
        return mapToDTO(reparation);
    }

    @Override
    public ReparationDTO getReparationByReception(Long receptionId) {
        Reparation reparation = reparationRepository.findByReceptionId(receptionId)
                .orElseThrow(() -> new RuntimeException("Aucune réparation trouvée pour cette réception"));
        return mapToDTO(reparation);
    }

    @Override
    @Transactional
    public ReparationDTO ajouterOperation(Long reparationId, com.garage.reception.dto.OperationReparationRequest request) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));

        Mecanicien mecanicien = mecanicienRepository.findById(request.getMecanicienId())
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));

        PanneDetectee panne = null;
        if (request.getPanneId() != null) {
            panne = panneRepository.findById(request.getPanneId()).orElse(null); // à injecter si besoin
        }

        OperationReparation operation = OperationReparation.builder()
                .reparation(reparation)
                .panne(panne)
                .description(request.getDescription())
                .dateDebut(request.getDateDebut())
                .dateFin(request.getDateFin())
                .tempsPasse(request.getTempsPasse())
                .mecanicien(mecanicien)
                .commentaire(request.getCommentaire())
                .build();

        reparation.getOperations().add(operation);
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReparationDTO ajouterBesoinPiece(Long reparationId, BesoinPieceRequest request) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));

        PieceRechange piece = null;
        if (request.getPieceId() != null) {
            piece = pieceRepository.findById(request.getPieceId()).orElse(null);
        }

        BesoinPiece besoin = BesoinPiece.builder()
                .reparation(reparation)
                .piece(piece)
                .designation(request.getDesignation())
                .quantiteDemandee(request.getQuantiteDemandee())
                .quantiteUtilisee(request.getQuantiteUtilisee())
                .statut(request.getStatut() != null ? request.getStatut() : StatutBesoin.A_DEMANDER)
                .dateDemande(LocalDateTime.now())
                .remarques(request.getRemarques())
                .build();

        reparation.getBesoinsPieces().add(besoin);
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReparationDTO ajouterBesoinOutillage(Long reparationId, BesoinOutillageRequest request) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));

        Outillage outil = null;
        if (request.getOutillageId() != null) {
            outil = outillageRepository.findById(request.getOutillageId()).orElse(null);
            if (outil != null && !outil.getDisponible()) {
                throw new IllegalStateException("L'outil n'est pas disponible");
            }
            if (outil != null) {
                outil.setDisponible(false); // marquer comme utilisé
                outillageRepository.save(outil);
            }
        }

        BesoinOutillage besoin = BesoinOutillage.builder()
                .reparation(reparation)
                .outillage(outil)
                .designation(request.getDesignation())
                .dateReservation(request.getDateReservation() != null ? request.getDateReservation() : LocalDateTime.now())
                .dateRetour(request.getDateRetour())
                .statut(request.getStatut() != null ? request.getStatut() : StatutBesoinOutillage.RESERVE)
                .build();

        reparation.getBesoinsOutillage().add(besoin);
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReparationDTO terminerReparation(Long reparationId) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
        reparation.setStatut(StatutReparation.TERMINEE);
        reparation.setDateFinReelle(LocalDateTime.now());
        // Mettre à jour la réception
        Reception reception = reparation.getReception();
        reception.setStatut(StatutReception.controle); // passage au contrôle qualité
        receptionRepository.save(reception);
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReparationDTO suspendreReparation(Long reparationId) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
        reparation.setStatut(StatutReparation.SUSPENDUE);
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReparationDTO mettreAJourStatut(Long reparationId, String statut) {
        Reparation reparation = reparationRepository.findById(reparationId)
                .orElseThrow(() -> new RuntimeException("Réparation non trouvée"));
        reparation.setStatut(StatutReparation.valueOf(statut));
        Reparation updated = reparationRepository.save(reparation);
        return mapToDTO(updated);
    }

    @Override
    public List<ReparationDTO> listAll() {
        return reparationRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // Mapping methods
    private ReparationDTO mapToDTO(Reparation reparation) {
        ReparationDTO dto =ReparationDTO.builder()
                .id(reparation.getId())
                .receptionId(reparation.getReception().getId())
                .dateDebut(reparation.getDateDebut())
                .dateFinPrevue(reparation.getDateFinPrevue())
                .dateFinReelle(reparation.getDateFinReelle())
                .statut(com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.StatutReparation.valueOf(reparation.getStatut().name()))
                .notes(reparation.getNotes())
                .chefAtelierId(reparation.getChefAtelier() != null ? reparation.getChefAtelier().getId() : null)
                .dateCreation(reparation.getDateCreation())
                .dateModification(reparation.getDateModification())
                .build();

        if (reparation.getOperations() != null) {
            dto.setOperations(reparation.getOperations().stream()
                    .map(this::mapOperationToDTO)
                    .collect(Collectors.toList()));
        }
        if (reparation.getBesoinsPieces() != null) {
            dto.setBesoinsPieces(reparation.getBesoinsPieces().stream()
                    .map(this::mapBesoinPieceToDTO)
                    .collect(Collectors.toList()));
        }
        if (reparation.getBesoinsOutillage() != null) {
            dto.setBesoinsOutillage(reparation.getBesoinsOutillage().stream()
                    .map(this::mapBesoinOutillageToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private OperationReparationDTO mapOperationToDTO(OperationReparation op) {
        return OperationReparationDTO.builder()
                .id(op.getId())
                .reparationId(op.getReparation().getId())
                .panneId(op.getPanne() != null ? op.getPanne().getId() : null)
                .description(op.getDescription())
                .dateDebut(op.getDateDebut())
                .dateFin(op.getDateFin())
                .tempsPasse(op.getTempsPasse())
                .mecanicienId(op.getMecanicien() != null ? op.getMecanicien().getId() : null)
                .commentaire(op.getCommentaire())
                .build();
    }

    private BesoinPieceDTO mapBesoinPieceToDTO(BesoinPiece bp) {
        return BesoinPieceDTO.builder()
                .id(bp.getId())
                .reparationId(bp.getReparation().getId())
                .pieceId(bp.getPiece() != null ? bp.getPiece().getId() : null)
                .designation(bp.getDesignation())
                .quantiteDemandee(bp.getQuantiteDemandee())
                .quantiteUtilisee(bp.getQuantiteUtilisee())
                .statut(bp.getStatut())
                .dateDemande(bp.getDateDemande())
                .dateReception(bp.getDateReception())
                .remarques(bp.getRemarques())
                .build();
    }

    private BesoinOutillageDTO mapBesoinOutillageToDTO(BesoinOutillage bo) {
        return BesoinOutillageDTO.builder()
                .id(bo.getId())
                .reparationId(bo.getReparation().getId())
                .outillageId(bo.getOutillage() != null ? bo.getOutillage().getId() : null)
                .designation(bo.getDesignation())
                .dateReservation(bo.getDateReservation())
                .dateRetour(bo.getDateRetour())
                .statut(bo.getStatut())
                .build();
    }
}