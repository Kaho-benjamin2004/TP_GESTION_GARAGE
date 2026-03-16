package com.gestiongarage.tp_gestion_garage.ProductionRapport.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository.PanneDetecteeRepository;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.StatutFacture;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository.FactureRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ClientRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository.PieceRechangeRepository;
import com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto.*;
import com.gestiongarage.tp_gestion_garage.ProductionRapport.SERVICE.ReportingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReportingServiceImpl implements ReportingService {

    private final ReceptionRepository receptionRepository;
    private final FactureRepository factureRepository;
    private final PieceRechangeRepository pieceRepository;
    private final ClientRepository clientRepository;
    private final PanneDetecteeRepository panneDetecteeRepository; // pour les pannes récurrentes

    @Override
    public List<VehiculeEnAttenteDTO> getVehiculesEnAttente() {
        return receptionRepository.findByStatut(StatutReception.en_attente).stream()
                .map(r -> VehiculeEnAttenteDTO.builder()
                        .receptionId(r.getId())
                        .numeroDossier(r.getNumeroDossier())
                        .clientNom(r.getClient().getNom() + " " + (r.getClient().getPrenom() != null ? r.getClient().getPrenom() : ""))
                        .vehiculeImmatriculation(r.getVehicule().getImmatriculation())
                        .vehiculeModele(r.getVehicule().getMarque() + " " + r.getVehicule().getModele())
                        .dateReception(r.getDateReception())
                        .descriptionPanne(r.getDescriptionPanneClient())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<ChiffreAffairesDTO> getChiffreAffairesParPeriode(LocalDate debut, LocalDate fin) {
        // Requête personnalisée à écrire dans FactureRepository
        // Pour l'exemple, on simule un résultat vide
        return List.of();
    }

    @Override
    public List<FactureImpayeeDTO> getFacturesImpayees() {
        return factureRepository.findByStatutAndMontantRestantGreaterThan(StatutFacture.EMISE, BigDecimal.ZERO)
                .stream()
                .map(f -> FactureImpayeeDTO.builder()
                        .factureId(f.getId())
                        .numeroFacture(f.getNumeroFacture())
                        .clientNom(f.getClient().getNom())
                        .dateEmission(f.getDateEmission())
                        .dateEcheance(f.getDateEcheance())
                        .montantRestant(f.getMontantRestant())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<EtatStockDTO> getEtatStock() {
        return pieceRepository.findAll().stream()
                .map(p -> EtatStockDTO.builder()
                        .pieceId(p.getId())
                        .reference(p.getReference())
                        .nom(p.getNom())
                        .stock(p.getStock())
                        .seuilAlerte(p.getSeuilAlerte())
                        .emplacement(p.getEmplacement())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<HistoriqueClientDTO> getHistoriqueClient(Long clientId) {
        return receptionRepository.findByClientId(clientId).stream()
                .map(r -> HistoriqueClientDTO.builder()
                        .receptionId(r.getId())
                        .numeroDossier(r.getNumeroDossier())
                        .dateReception(r.getDateReception())
                        .vehiculeImmatriculation(r.getVehicule().getImmatriculation())
                        .descriptionPanne(r.getDescriptionPanneClient())
                        .statut(r.getStatut().name())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public List<PanneRecurrenteDTO> getPannesRecurrentesParModele() {
        // Requête personnalisée à écrire dans PanneDetecteeRepository
        // Exemple : compter par modèle et description
        return List.of();
    }
}