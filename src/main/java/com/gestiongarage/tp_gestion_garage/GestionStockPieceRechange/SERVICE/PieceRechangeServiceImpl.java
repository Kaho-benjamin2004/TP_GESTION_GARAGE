package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository.PieceRechangeRepository;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.MouvementStockDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.Fournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.MouvementStock;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.TypeMouvement;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.FournisseurRepository;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.MouvementStockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PieceRechangeServiceImpl implements PieceRechangeService {
    private final PieceRechangeRepository pieceRepository;
    private final FournisseurRepository fournisseurRepository;
    private final MouvementStockRepository mouvementRepository;

    @Override
    @Transactional
    public PieceRechangeDTO createPiece(PieceRechangeRequestDTO request) {
        Fournisseur fournisseur = null;
        if (request.getFournisseurId() != null) {
            fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                    .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
        }

        PieceRechange piece = PieceRechange.builder()
                .reference(request.getReference())
                .nom(request.getNom())
                .description(request.getDescription())
                .marque(request.getMarque())
                .modele(request.getModele())
                .stock(request.getStock() != null ? request.getStock() : 0)
                .seuilAlerte(request.getSeuilAlerte() != null ? request.getSeuilAlerte() : 5)
                .prixAchat(request.getPrixAchat())
                .prixVente(request.getPrixVente())
                .emplacement(request.getEmplacement())
                .fournisseur(fournisseur)
                .build();

        PieceRechange saved = pieceRepository.save(piece);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public PieceRechangeDTO updatePiece(Long id, PieceRechangeRequestDTO request) {
        PieceRechange piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));

        piece.setNom(request.getNom());
        piece.setDescription(request.getDescription());
        piece.setMarque(request.getMarque());
        piece.setModele(request.getModele());
        piece.setSeuilAlerte(request.getSeuilAlerte());
        piece.setPrixAchat(request.getPrixAchat());
        piece.setPrixVente(request.getPrixVente());
        piece.setEmplacement(request.getEmplacement());

        if (request.getFournisseurId() != null) {
            Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                    .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));
            piece.setFournisseur(fournisseur);
        } else {
            piece.setFournisseur(null);
        }

        // La référence et le stock ne sont pas modifiés ici (le stock se fait par mouvements)
        PieceRechange updated = pieceRepository.save(piece);
        return mapToDTO(updated);
    }

    @Override
    public PieceRechangeDTO getPiece(Long id) {
        PieceRechange piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));
        return mapToDTO(piece);
    }

    @Override
    public PieceRechangeDTO getPieceByReference(String reference) {
        PieceRechange piece = pieceRepository.findByReference(reference)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));
        return mapToDTO(piece);
    }

    @Override
    public List<PieceRechangeDTO> getAllPieces() {
        return pieceRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PieceRechangeDTO> getPiecesEnAlerte() {
        return pieceRepository.findEnAlerte().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deletePiece(Long id) {
        PieceRechange piece = pieceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));
        pieceRepository.delete(piece);
    }

    @Override
    @Transactional
    public MouvementStockDTO entreeStock(Long pieceId, Integer quantite, String motif, Long documentId) {
        PieceRechange piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));

        piece.setStock(piece.getStock() + quantite);
        pieceRepository.save(piece);

        MouvementStock mouvement = MouvementStock.builder()
                .piece(piece)
                .type(TypeMouvement.ENTREE)
                .quantite(quantite)
                .motif(motif)
                .documentAssocieId(documentId)
                .build();

        MouvementStock saved = mouvementRepository.save(mouvement);
        return mapMouvementToDTO(saved);
    }

    @Override
    @Transactional
    public MouvementStockDTO sortieStock(Long pieceId, Integer quantite, String motif, Long documentId) {
        PieceRechange piece = pieceRepository.findById(pieceId)
                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));

        if (piece.getStock() < quantite) {
            throw new IllegalStateException("Stock insuffisant");
        }

        piece.setStock(piece.getStock() - quantite);
        pieceRepository.save(piece);

        MouvementStock mouvement = MouvementStock.builder()
                .piece(piece)
                .type(TypeMouvement.SORTIE)
                .quantite(quantite)
                .motif(motif)
                .documentAssocieId(documentId)
                .build();

        MouvementStock saved = mouvementRepository.save(mouvement);
        return mapMouvementToDTO(saved);
    }

    @Override
    public List<MouvementStockDTO> getMouvements(Long pieceId) {
        return mouvementRepository.findByPieceId(pieceId).stream()
                .map(this::mapMouvementToDTO)
                .collect(Collectors.toList());
    }

    private PieceRechangeDTO mapToDTO(PieceRechange piece) {
        return PieceRechangeDTO.builder()
                .id(piece.getId())
                .reference(piece.getReference())
                .nom(piece.getNom())
                .description(piece.getDescription())
                .marque(piece.getMarque())
                .modele(piece.getModele())
                .stock(piece.getStock())
                .seuilAlerte(piece.getSeuilAlerte())
                .prixAchat(piece.getPrixAchat())
                .prixVente(piece.getPrixVente())
                .emplacement(piece.getEmplacement())
                .fournisseurId(piece.getFournisseur() != null ? piece.getFournisseur().getId() : null)
                .fournisseurNom(piece.getFournisseur() != null ? piece.getFournisseur().getNom() : null)
                .build();
    }

    private MouvementStockDTO mapMouvementToDTO(MouvementStock mouvement) {
        return MouvementStockDTO.builder()
                .id(mouvement.getId())
                .pieceId(mouvement.getPiece().getId())
                .pieceReference(mouvement.getPiece().getReference())
                .type(mouvement.getType())
                .quantite(mouvement.getQuantite())
                .motif(mouvement.getMotif())
                .documentAssocieId(mouvement.getDocumentAssocieId())
                .dateMouvement(mouvement.getDateMouvement())
                .build();
    }
}