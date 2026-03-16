package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository.PieceRechangeRepository;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.LigneCommandeDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.CommandeFournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.Fournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.LigneCommandeFournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.StatutCommande;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.CommandeFournisseurRepository;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.FournisseurRepository;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.LigneCommandeFournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {

    private final CommandeFournisseurRepository commandeRepository;
    private final FournisseurRepository fournisseurRepository;
    private final PieceRechangeRepository pieceRepository;
    private final LigneCommandeFournisseurRepository ligneCommandeRepository; // optionnel

    @Override
    @Transactional
    public CommandeFournisseurDTO createCommande(CommandeFournisseurRequestDTO request) {
        Fournisseur fournisseur = fournisseurRepository.findById(request.getFournisseurId())
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé"));

        CommandeFournisseur commande = CommandeFournisseur.builder()
                .fournisseur(fournisseur)
                .dateCommande(request.getDateCommande() != null ? request.getDateCommande() : LocalDate.now())
                .statut(StatutCommande.EN_ATTENTE)
                .observations(request.getObservations())
                .build();

        // Ajouter les lignes de commande
        if (request.getLignesCommande() != null) {
            List<LigneCommandeFournisseur> lignes = request.getLignesCommande().stream()
                    .map(l -> {
                        PieceRechange piece = pieceRepository.findById(l.getPieceId())
                                .orElseThrow(() -> new RuntimeException("Pièce non trouvée"));
                        // Si prix unitaire non fourni, on peut prendre un prix par défaut (ex: prixAchat)
                        BigDecimal prix = l.getPrixUnitaire() != null ?
                                BigDecimal.valueOf(l.getPrixUnitaire()) :
                                piece.getPrixAchat(); // supposons que PieceRechange a un prixAchat
                        return LigneCommandeFournisseur.builder()
                                .commande(commande)
                                .piece(piece)
                                .quantite(l.getQuantite())
                                .prixUnitaire(prix)
                                .quantiteRecue(0)
                                .build();
                    })
                    .collect(Collectors.toList());
            commande.setLignesCommande(lignes);
        }

        CommandeFournisseur saved = commandeRepository.save(commande);
        return mapToDTO(saved);
    }

    @Override
    public CommandeFournisseurDTO getCommande(Long id) {
        CommandeFournisseur commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        return mapToDTO(commande);
    }

    @Override
    public List<CommandeFournisseurDTO> getAllCommandes() {
        return commandeRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommandeFournisseurDTO validerCommande(Long id) {
        CommandeFournisseur commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        if (commande.getStatut() != StatutCommande.EN_ATTENTE) {
            throw new IllegalStateException("Seules les commandes en attente peuvent être validées");
        }

        commande.setStatut(StatutCommande.VALIDEE);
        // Optionnel : fixer une date de réception prévue
        commande.setDateReceptionPrevue(LocalDate.now().plusDays(7)); // exemple

        CommandeFournisseur saved = commandeRepository.save(commande);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public CommandeFournisseurDTO recevoirCommande(Long id) {
        CommandeFournisseur commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));

        if (commande.getStatut() != StatutCommande.VALIDEE && commande.getStatut() != StatutCommande.EN_ATTENTE) {
            throw new IllegalStateException("La commande ne peut pas être reçue dans son état actuel");
        }

        // Mettre à jour le stock pour chaque ligne
        for (LigneCommandeFournisseur ligne : commande.getLignesCommande()) {
            PieceRechange piece = ligne.getPiece();
            int qte = ligne.getQuantite();
            // Si gestion de réception partielle, on pourrait utiliser quantiteRecue
            piece.setStock(piece.getStock() + qte);
            pieceRepository.save(piece);
            // Marquer la ligne comme reçue
            ligne.setQuantiteRecue(qte);
        }

        commande.setStatut(StatutCommande.RECUE);
        commande.setDateReceptionReelle(LocalDate.now());

        CommandeFournisseur saved = commandeRepository.save(commande);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public void deleteCommande(Long id) {
        CommandeFournisseur commande = commandeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commande non trouvée"));
        // On peut vérifier que la commande n'est pas déjà reçue avant suppression
        if (commande.getStatut() == StatutCommande.RECUE) {
            throw new IllegalStateException("Impossible de supprimer une commande déjà reçue");
        }
        commandeRepository.delete(commande);
    }

    private CommandeFournisseurDTO mapToDTO(CommandeFournisseur commande) {
        List<LigneCommandeDTO> lignesDTO = commande.getLignesCommande().stream()
                .map(l -> LigneCommandeDTO.builder()
                        .id(l.getId())
                        .pieceId(l.getPiece().getId())
                        .pieceReference(l.getPiece().getReference())
                        .pieceNom(l.getPiece().getNom())
                        .quantite(l.getQuantite())
                        .prixUnitaire(l.getPrixUnitaire())
                        .quantiteRecue(l.getQuantiteRecue())
                        .build())
                .collect(Collectors.toList());

        return CommandeFournisseurDTO.builder()
                .id(commande.getId())
                .fournisseurId(commande.getFournisseur().getId())
                .fournisseurNom(commande.getFournisseur().getNom())
                .dateCommande(commande.getDateCommande())
                .statut(commande.getStatut())
                .lignesCommande(lignesDTO)
                .dateReceptionPrevue(commande.getDateReceptionPrevue())
                .dateReceptionReelle(commande.getDateReceptionReelle())
                .observations(commande.getObservations())
                .dateCreation(commande.getDateCreation())
                .build();
    }
}