package com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.*;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.*;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.*;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReceptionServiceImpl implements ReceptionService {
    private final ReceptionRepository receptionRepository;
    private final ClientRepository clientRepository;
    private final VehiculeRepository vehiculeRepository;
    private final PhotoVehiculeRepository photoRepository;
    private final DocumentAccompagnementRepository documentRepository;
    private final DossierNumberGenerator dossierNumberGenerator;

    @Override
    @Transactional
    public ReceptionDTO createReception(NouvelleReceptionRequest request) {
        // 1. Gérer le client
        Client client;
        if (request.getClientId() != null) {
            client = clientRepository.findById(request.getClientId())
                    .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + request.getClientId()));
        } else if (request.getNouveauClient() != null) {
            // Créer le nouveau client
            Client newClient = mapClientRequestToEntity(request.getNouveauClient());
            newClient.setCodeClient(generateClientCode()); // méthode utilitaire
            client = clientRepository.save(newClient);
        } else {
            throw new IllegalArgumentException("Un client existant ou nouveau doit être fourni");
        }

        // 2. Gérer le véhicule
        Vehicule vehicule;
        if (request.getVehiculeId() != null) {
            vehicule = vehiculeRepository.findById(request.getVehiculeId())
                    .orElseThrow(() -> new RuntimeException("Véhicule non trouvé avec id: " + request.getVehiculeId()));
            // Vérifier que le véhicule appartient bien au client (optionnel mais recommandé)
            if (!vehicule.getClient().getId().equals(client.getId())) {
                throw new IllegalArgumentException("Le véhicule n'appartient pas au client spécifié");
            }
        } else if (request.getNouveauVehicule() != null) {
            // Vérifier que l'immatriculation n'existe pas déjà
            if (vehiculeRepository.existsByImmatriculation(request.getNouveauVehicule().getImmatriculation())) {
                throw new IllegalArgumentException("Un véhicule avec cette immatriculation existe déjà");
            }
            Vehicule newVehicule = mapVehiculeRequestToEntity(request.getNouveauVehicule());
            newVehicule.setClient(client);
            vehicule = vehiculeRepository.save(newVehicule);
        } else {
            throw new IllegalArgumentException("Un véhicule existant ou nouveau doit être fourni");
        }

        // 3. Vérifier qu'il n'y a pas déjà un dossier actif pour ce véhicule
        if (receptionRepository.existsByVehiculeIdAndStatutNot(vehicule.getId(), StatutReception.livre)) {
            throw new IllegalStateException("Un dossier est déjà en cours pour ce véhicule");
        }

        Reception reception = Reception.builder()
                .numeroDossier(dossierNumberGenerator.generateNextNumber())
                .vehicule(vehicule)
                .client(client)
                .dateReception(request.getDateReception())
                .datePrevueFin(request.getDatePrevueFin())
                .typeReception(request.getTypeReception())
                .descriptionPanneClient(request.getDescriptionPanneClient())
                .circonstancesPanne(request.getCirconstancesPanne())
                .frequencePanne(request.getFrequencePanne())
                .conditionsApparition(request.getConditionsApparition())
                .temoinsTableauBord(request.getTemoinsTableauBord())
                .kilometrageArrivee(request.getKilometrageArrivee())
                .niveauCarburant(request.getNiveauCarburant())
                .etatExterieur(request.getEtatExterieur())
                .etatInterieur(request.getEtatInterieur())
                .accessoiresPresents(request.getAccessoiresPresents())
                .statut(StatutReception.en_attente)
                .accordClient(request.getAccordClient())
                .accordDevisId(request.getAccordDevisId())
                .receptionnisteId(request.getReceptionnisteId())
                .notesInternes(request.getNotesInternes())
                .build();

        // Ajouter les photos avant la sauvegarde pour bénéficier de la cascade
        if (request.getPhotos() != null && !request.getPhotos().isEmpty()) {
            List<PhotoVehicule> photos = request.getPhotos().stream()
                    .map(p -> PhotoVehicule.builder()
                            .reception(reception) // liaison avec l'entité non encore persistée
                            .typePhoto(p.getTypePhoto())
                            .cheminFichier(p.getCheminFichier())
                            .description(p.getDescription())
                            .build())
                    .collect(Collectors.toList());
            reception.getPhotos().addAll(photos);
        }

        // Ajouter les documents
        if (request.getDocuments() != null && !request.getDocuments().isEmpty()) {
            List<DocumentAccompagnement> docs = request.getDocuments().stream()
                    .map(d -> DocumentAccompagnement.builder()
                            .reception(reception)
                            .typeDocument(d.getTypeDocument())
                            .numeroDocument(d.getNumeroDocument())
                            .observations(d.getObservations())
                            .build())
                    .collect(Collectors.toList());
            reception.getDocuments().addAll(docs);
        }

        // Sauvegarder la réception (les photos/documents seront persistés automatiquement grâce à CascadeType.ALL)
        Reception savedReception = receptionRepository.save(reception);

        return mapToDTO(savedReception);
    }

    @Override
    public ReceptionDTO getReception(Long id) {
        Reception reception = receptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réception non trouvée avec id: " + id));
        return mapToDTO(reception);
    }

    @Override
    public ReceptionDTO getReceptionByNumeroDossier(String numeroDossier) {
        Reception reception = receptionRepository.findByNumeroDossier(numeroDossier);
        if (reception == null) {
            throw new RuntimeException("Réception non trouvée avec numéro: " + numeroDossier);
        }
        return mapToDTO(reception);
    }

    @Override
    public List<ReceptionDTO> getAllReceptions() {
        return receptionRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ReceptionDTO> getReceptionsByStatut(StatutReception statut) {
        return receptionRepository.findByStatut(statut).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ReceptionDTO updateStatut(Long id, StatutReception statut) {
        Reception reception = receptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réception non trouvée avec id: " + id));
        reception.setStatut(statut);
        Reception updated = receptionRepository.save(reception);
        return mapToDTO(updated);
    }

    @Override
    @Transactional
    public ReceptionDTO updateReception(Long id, NouvelleReceptionRequest request) {
        // Implémentation de mise à jour partielle selon les besoins
        // (similaire à create mais en modifiant l'existant)
        // À développer si nécessaire
        throw new UnsupportedOperationException("Méthode non implémentée");
    }

    @Override
    @Transactional
    public void deleteReception(Long id) {
        Reception reception = receptionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Réception non trouvée avec id: " + id));
        receptionRepository.delete(reception);
    }

    // Méthodes de mapping
    private Client mapClientRequestToEntity(ClientRequestDTO dto) {
        return Client.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .typeClient(dto.getTypeClient())
                .telephone(dto.getTelephone())
                .telephoneSecondary(dto.getTelephoneSecondary())
                .email(dto.getEmail())
                .adresse(dto.getAdresse())
                .ville(dto.getVille())
                .codePostal(dto.getCodePostal())
                .pieceIdentite(dto.getPieceIdentite())
                .numeroPiece(dto.getNumeroPiece())
                .notes(dto.getNotes())
                .build();
    }

    private Vehicule mapVehiculeRequestToEntity(VehiculeRequestDTO dto) {
        return Vehicule.builder()
                .immatriculation(dto.getImmatriculation())
                .marque(dto.getMarque())
                .modele(dto.getModele())
                .annee(dto.getAnnee())
                .couleur(dto.getCouleur())
                .typeCarburant(dto.getTypeCarburant())
                .genre(dto.getGenre())
                .numeroChassis(dto.getNumeroChassis())
                .puissanceFiscale(dto.getPuissanceFiscale())
                .datePremiereMiseCirculation(dto.getDatePremiereMiseCirculation())
                .dateDerniereRevision(dto.getDateDerniereRevision())
                .kilometrageActuel(dto.getKilometrageActuel())
                .observations(dto.getObservations())
                .build();
    }

    private ReceptionDTO mapToDTO(Reception reception) {
        ReceptionDTO dto = ReceptionDTO.builder()
                .id(reception.getId())
                .numeroDossier(reception.getNumeroDossier())
                .vehicule(mapVehiculeToDTO(reception.getVehicule()))
                .client(mapClientToDTO(reception.getClient()))
                .dateReception(reception.getDateReception())
                .datePrevueFin(reception.getDatePrevueFin())
                .typeReception(reception.getTypeReception())
                .descriptionPanneClient(reception.getDescriptionPanneClient())
                .circonstancesPanne(reception.getCirconstancesPanne())
                .frequencePanne(reception.getFrequencePanne())
                .conditionsApparition(reception.getConditionsApparition())
                .temoinsTableauBord(reception.getTemoinsTableauBord())
                .kilometrageArrivee(reception.getKilometrageArrivee())
                .niveauCarburant(reception.getNiveauCarburant())
                .etatExterieur(reception.getEtatExterieur())
                .etatInterieur(reception.getEtatInterieur())
                .accessoiresPresents(reception.getAccessoiresPresents())
                .statut(reception.getStatut())
                .accordClient(reception.getAccordClient())
                .accordDevisId(reception.getAccordDevisId())
                .receptionnisteId(reception.getReceptionnisteId())
                .notesInternes(reception.getNotesInternes())
                .dateCreation(reception.getDateCreation())
                .dateModification(reception.getDateModification())
                .build();

        if (reception.getPhotos() != null) {
            dto.setPhotos(reception.getPhotos().stream()
                    .map(this::mapPhotoToDTO)
                    .collect(Collectors.toList()));
        }
        if (reception.getDocuments() != null) {
            dto.setDocuments(reception.getDocuments().stream()
                    .map(this::mapDocumentToDTO)
                    .collect(Collectors.toList()));
        }
        return dto;
    }

    private VehiculeDTO mapVehiculeToDTO(Vehicule v) {
        return VehiculeDTO.builder()
                .id(v.getId())
                .clientId(v.getClient().getId())
                .immatriculation(v.getImmatriculation())
                .marque(v.getMarque())
                .modele(v.getModele())
                .annee(v.getAnnee())
                .couleur(v.getCouleur())
                .typeCarburant(v.getTypeCarburant())
                .genre(v.getGenre())
                .numeroChassis(v.getNumeroChassis())
                .puissanceFiscale(v.getPuissanceFiscale())
                .datePremiereMiseCirculation(v.getDatePremiereMiseCirculation())
                .dateDerniereRevision(v.getDateDerniereRevision())
                .kilometrageActuel(v.getKilometrageActuel())
                .observations(v.getObservations())
                .dateCreation(v.getDateCreation())
                .build();
    }

    private ClientDTO mapClientToDTO(Client c) {
        return ClientDTO.builder()
                .id(c.getId())
                .codeClient(c.getCodeClient())
                .nom(c.getNom())
                .prenom(c.getPrenom())
                .typeClient(c.getTypeClient())
                .telephone(c.getTelephone())
                .telephoneSecondary(c.getTelephoneSecondary())
                .email(c.getEmail())
                .adresse(c.getAdresse())
                .ville(c.getVille())
                .codePostal(c.getCodePostal())
                .pieceIdentite(c.getPieceIdentite())
                .numeroPiece(c.getNumeroPiece())
                .notes(c.getNotes())
                .dateCreation(c.getDateCreation())
                .dateModification(c.getDateModification())
                .build();
    }

    private PhotoDTO mapPhotoToDTO(PhotoVehicule p) {
        return PhotoDTO.builder()
                .id(p.getId())
                .receptionId(p.getReception().getId())
                .typePhoto(p.getTypePhoto())
                .cheminFichier(p.getCheminFichier())
                .description(p.getDescription())
                .datePrise(p.getDatePrise())
                .build();
    }

    private DocumentDTO mapDocumentToDTO(DocumentAccompagnement d) {
        return DocumentDTO.builder()
                .id(d.getId())
                .receptionId(d.getReception().getId())
                .typeDocument(d.getTypeDocument())
                .numeroDocument(d.getNumeroDocument())
                .observations(d.getObservations())
                .build();
    }

    private String generateClientCode() {
        return "CLI-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}