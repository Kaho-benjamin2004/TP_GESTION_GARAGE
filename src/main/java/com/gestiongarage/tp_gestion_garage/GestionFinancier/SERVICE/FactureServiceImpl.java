package com.gestiongarage.tp_gestion_garage.GestionFinancier.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.*;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.*;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository.CaisseRepository;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository.FactureRepository;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository.PaiementRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ClientRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Client;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FactureServiceImpl implements FactureService {

    private final FactureRepository factureRepository;
    private final ReceptionRepository receptionRepository;
    private final ClientRepository clientRepository;
    private final PaiementRepository paiementRepository;
    private final CaisseRepository caisseRepository;

    @Override
    @Transactional
    public FactureDTO creerFacture(FactureRequestDTO request) {
        Reception reception = receptionRepository.findById(request.getReceptionId())
                .orElseThrow(() -> new RuntimeException("Réception non trouvée avec id: " + request.getReceptionId()));

        Client client = reception.getClient();

        Facture facture = Facture.builder()
                .numeroFacture(genererNumeroFacture())
                .reception(reception)
                .client(client)
                .dateEmission(request.getDateEmission() != null ? request.getDateEmission() : LocalDate.now())
                .dateEcheance(request.getDateEcheance() != null ? request.getDateEcheance() : LocalDate.now().plusDays(30))
                .statut(StatutFacture.BROUILLON)
                .montantRegle(BigDecimal.ZERO)
                .notes(request.getNotes())
                .build();

        BigDecimal totalHT = BigDecimal.ZERO;
        List<LigneFacture> lignes = new ArrayList<>();

        for (LigneFactureRequestDTO lReq : request.getLignes()) {
            BigDecimal montantLigne = lReq.getPrixUnitaire().multiply(BigDecimal.valueOf(lReq.getQuantite()));
            LigneFacture ligne = LigneFacture.builder()
                    .facture(facture)
                    .description(lReq.getDescription())
                    .quantite(lReq.getQuantite())
                    .prixUnitaire(lReq.getPrixUnitaire())
                    .montantTotal(montantLigne)
                    .tauxTVA(lReq.getTauxTVA() != null ? lReq.getTauxTVA() : BigDecimal.valueOf(20.0))
                    .operationId(lReq.getOperationId())
                    .pieceId(lReq.getPieceId())
                    .build();
            lignes.add(ligne);
            totalHT = totalHT.add(montantLigne);
        }

        facture.setLignes(lignes);
        facture.setMontantHT(totalHT);
        // Calcul TVA simplifié (20%)
        BigDecimal tva = totalHT.multiply(BigDecimal.valueOf(0.20));
        facture.setMontantTVA(tva);
        facture.setMontantTTC(totalHT.add(tva));
        facture.setMontantRestant(facture.getMontantTTC());

        Facture saved = factureRepository.save(facture);
        return mapToDTO(saved);
    }

    @Override
    public FactureDTO getFacture(Long id) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec id: " + id));
        return mapToDTO(facture);
    }

    @Override
    public FactureDTO getFactureByNumero(String numero) {
        Facture facture = factureRepository.findByNumeroFacture(numero)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec numéro: " + numero));
        return mapToDTO(facture);
    }

    @Override
    public List<FactureDTO> getFacturesByClient(Long clientId) {
        return factureRepository.findByClientId(clientId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FactureDTO> getFacturesByStatut(StatutFacture statut) {
        return factureRepository.findByStatut(statut).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public FactureDTO annulerFacture(Long id) {
        Facture facture = factureRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec id: " + id));

        if (facture.getStatut() == StatutFacture.PAYEE) {
            throw new IllegalStateException("Impossible d'annuler une facture déjà payée");
        }

        facture.setStatut(StatutFacture.ANNULEE);
        Facture saved = factureRepository.save(facture);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public PaiementDTO enregistrerPaiement(PaiementRequestDTO request) {
        Facture facture = factureRepository.findById(request.getFactureId())
                .orElseThrow(() -> new RuntimeException("Facture non trouvée avec id: " + request.getFactureId()));

        Paiement paiement = Paiement.builder()
                .facture(facture)
                .montant(request.getMontant())
                .datePaiement(request.getDatePaiement() != null ? request.getDatePaiement() : LocalDate.now())
                .modePaiement(request.getModePaiement())
                .reference(request.getReference())
                .build();

        // Associer à une caisse si fournie
        if (request.getCaisseId() != null) {
            Caisse caisse = caisseRepository.findById(request.getCaisseId())
                    .orElseThrow(() -> new RuntimeException("Caisse non trouvée avec id: " + request.getCaisseId()));
            paiement.setCaisse(caisse);
        }

        Paiement saved = paiementRepository.save(paiement);
        facture.getPaiements().add(saved);

        // Mise à jour des montants réglés
        BigDecimal totalRegle = facture.getPaiements().stream()
                .map(Paiement::getMontant)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        facture.setMontantRegle(totalRegle);
        facture.setMontantRestant(facture.getMontantTTC().subtract(totalRegle));

        // Mise à jour du statut
        if (facture.getMontantRestant().compareTo(BigDecimal.ZERO) <= 0) {
            facture.setStatut(StatutFacture.PAYEE);
        } else if (facture.getStatut() == StatutFacture.BROUILLON) {
            facture.setStatut(StatutFacture.EMISE);
        }

        factureRepository.save(facture);
        return mapPaiementToDTO(saved);
    }

    @Override
    public byte[] genererPDF(Long id) {
        // Implémentation réelle avec JasperReports ou autre bibliothèque
        // Pour l'instant, on retourne un tableau vide ou on lance une exception
        throw new UnsupportedOperationException("Génération PDF non implémentée");
    }

    // Méthodes privées utilitaires
    private String genererNumeroFacture() {
        String prefix = "FACT";
        String date = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyyMM"));
        long count = factureRepository.countByNumeroFactureStartingWith(prefix + "-" + date);
        return prefix + "-" + date + "-" + String.format("%04d", count + 1);
    }

    private FactureDTO mapToDTO(Facture facture) {
        List<LigneFactureDTO> lignesDTO = facture.getLignes().stream()
                .map(l -> LigneFactureDTO.builder()
                        .id(l.getId())
                        .description(l.getDescription())
                        .quantite(l.getQuantite())
                        .prixUnitaire(l.getPrixUnitaire())
                        .montantTotal(l.getMontantTotal())
                        .tauxTVA(l.getTauxTVA())
                        .operationId(l.getOperationId())
                        .pieceId(l.getPieceId())
                        .build())
                .collect(Collectors.toList());

        List<PaiementDTO> paiementsDTO = facture.getPaiements().stream()
                .map(this::mapPaiementToDTO)
                .collect(Collectors.toList());

        return FactureDTO.builder()
                .id(facture.getId())
                .numeroFacture(facture.getNumeroFacture())
                .receptionId(facture.getReception().getId())
                .clientId(facture.getClient().getId())
                .clientNom(facture.getClient().getNom() + " " + (facture.getClient().getPrenom() != null ? facture.getClient().getPrenom() : ""))
                .dateEmission(facture.getDateEmission())
                .dateEcheance(facture.getDateEcheance())
                .statut(facture.getStatut())
                .montantHT(facture.getMontantHT())
                .montantTVA(facture.getMontantTVA())
                .montantTTC(facture.getMontantTTC())
                .montantRegle(facture.getMontantRegle())
                .montantRestant(facture.getMontantRestant())
                .notes(facture.getNotes())
                .lignes(lignesDTO)
                .paiements(paiementsDTO)
                .dateCreation(facture.getDateCreation())
                .build();
    }

    private PaiementDTO mapPaiementToDTO(Paiement paiement) {
        return PaiementDTO.builder()
                .id(paiement.getId())
                .factureId(paiement.getFacture().getId())
                .montant(paiement.getMontant())
                .datePaiement(paiement.getDatePaiement())
                .modePaiement(paiement.getModePaiement())
                .reference(paiement.getReference())
                .caisseId(paiement.getCaisse() != null ? paiement.getCaisse().getId() : null)
                .build();
    }
}