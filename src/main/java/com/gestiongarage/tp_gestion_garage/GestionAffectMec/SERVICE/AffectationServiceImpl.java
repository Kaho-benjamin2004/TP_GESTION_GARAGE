package com.gestiongarage.tp_gestion_garage.GestionAffectMec.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto.*;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository.AffectationMecanoRepository;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository.AffectationOutilRepository;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository.MecanicienRepository;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository.OutilRepository;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.*;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AffectationServiceImpl implements AffectationService {

    private final MecanicienRepository mecanicienRepository;
    private final OutilRepository outilRepository;
    private final AffectationMecanoRepository affectationMecanoRepository;
    private final AffectationOutilRepository affectationOutilRepository;
    private final ReceptionRepository receptionRepository;

    // ========== MECANICIENS ==========
    @Override
    @Transactional
    public MecanicienDTO createMecanicien(MecanicienRequestDTO request) {
        Mecanicien mecanicien = Mecanicien.builder()
                .nom(request.getNom())
                .prenom(request.getPrenom())
                .matricule(request.getMatricule())
                .telephone(request.getTelephone())
                .email(request.getEmail())
                .competences(request.getCompetences())
                .disponible(true)
                .build();
        return mapToDTO(mecanicienRepository.save(mecanicien));
    }

    @Override
    @Transactional
    public MecanicienDTO updateMecanicien(Long id, MecanicienRequestDTO request) {
        Mecanicien mecanicien = mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        mecanicien.setNom(request.getNom());
        mecanicien.setPrenom(request.getPrenom());
        mecanicien.setMatricule(request.getMatricule());
        mecanicien.setTelephone(request.getTelephone());
        mecanicien.setEmail(request.getEmail());
        mecanicien.setCompetences(request.getCompetences());
        return mapToDTO(mecanicienRepository.save(mecanicien));
    }

    @Override
    public MecanicienDTO getMecanicien(Long id) {
        return mapToDTO(mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé")));
    }

    @Override
    public List<MecanicienDTO> getAllMecaniciens() {
        return mecanicienRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<MecanicienDTO> getMecaniciensDisponibles() {
        return mecanicienRepository.findByDisponibleTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteMecanicien(Long id) {
        Mecanicien mecanicien = mecanicienRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));
        // Vérifier s'il a des affectations en cours ?
        mecanicienRepository.delete(mecanicien);
    }

    // ========== OUTILS ==========
    @Override
    @Transactional
    public OutilDTO createOutil(OutilRequestDTO request) {
        Outil outil = Outil.builder()
                .nom(request.getNom())
                .reference(request.getReference())
                .marque(request.getMarque())
                .description(request.getDescription())
                .etat(request.getEtat() != null ? request.getEtat() : EtatOutil.NEUF)
                .disponible(true)
                .emplacement(request.getEmplacement())
                .dateProchaineMaintenance(request.getDateProchaineMaintenance())
                .build();
        return mapToDTO(outilRepository.save(outil));
    }

    @Override
    @Transactional
    public OutilDTO updateOutil(Long id, OutilRequestDTO request) {
        Outil outil = outilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outil non trouvé"));
        outil.setNom(request.getNom());
        outil.setReference(request.getReference());
        outil.setMarque(request.getMarque());
        outil.setDescription(request.getDescription());
        outil.setEtat(request.getEtat());
        outil.setEmplacement(request.getEmplacement());
        outil.setDateProchaineMaintenance(request.getDateProchaineMaintenance());
        return mapToDTO(outilRepository.save(outil));
    }

    @Override
    public OutilDTO getOutil(Long id) {
        return mapToDTO(outilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outil non trouvé")));
    }

    @Override
    public List<OutilDTO> getAllOutils() {
        return outilRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OutilDTO> getOutilsDisponibles() {
        return outilRepository.findByDisponibleTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOutil(Long id) {
        Outil outil = outilRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outil non trouvé"));
        outilRepository.delete(outil);
    }

    // ========== AFFECTATIONS ==========
    @Override
    @Transactional
    public AffectationMecanoDTO affecterMecanicien(AffecterMecanoRequest request) {
        Reception reception = receptionRepository.findById(request.getReceptionId())
                .orElseThrow(() -> new RuntimeException("Réception non trouvée"));
        Mecanicien mecanicien = mecanicienRepository.findById(request.getMecanicienId())
                .orElseThrow(() -> new RuntimeException("Mécanicien non trouvé"));

        if (!mecanicien.getDisponible()) {
            throw new IllegalStateException("Le mécanicien n'est pas disponible");
        }

        AffectationMecano affectation = AffectationMecano.builder()
                .reception(reception)
                .mecanicien(mecanicien)
                .dateDebut(request.getDateDebut() != null ? request.getDateDebut() : LocalDateTime.now())
                .description(request.getDescription())
                .build();

        // Marquer le mécanicien comme non disponible
        mecanicien.setDisponible(false);
        mecanicienRepository.save(mecanicien);

        return mapToDTO(affectationMecanoRepository.save(affectation));
    }

    @Override
    @Transactional
    public AffectationMecanoDTO terminerAffectationMecano(Long affectationId, Integer tempsPasse) {
        AffectationMecano affectation = affectationMecanoRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation non trouvée"));

        affectation.setDateFin(LocalDateTime.now());
        affectation.setTempsPasse(tempsPasse);

        // Rendre le mécanicien disponible
        Mecanicien mecanicien = affectation.getMecanicien();
        mecanicien.setDisponible(true);
        mecanicienRepository.save(mecanicien);

        return mapToDTO(affectationMecanoRepository.save(affectation));
    }

    @Override
    public List<AffectationMecanoDTO> getAffectationsMecanoByReception(Long receptionId) {
        return affectationMecanoRepository.findByReceptionId(receptionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public AffectationOutilDTO affecterOutil(AffecterOutilRequest request) {
        Reception reception = receptionRepository.findById(request.getReceptionId())
                .orElseThrow(() -> new RuntimeException("Réception non trouvée"));
        Outil outil = outilRepository.findById(request.getOutilId())
                .orElseThrow(() -> new RuntimeException("Outil non trouvé"));

        if (!outil.getDisponible()) {
            throw new IllegalStateException("L'outil n'est pas disponible");
        }

        AffectationOutil affectation = AffectationOutil.builder()
                .reception(reception)
                .outil(outil)
                .dateDebut(request.getDateDebut() != null ? request.getDateDebut() : LocalDateTime.now())
                .observations(request.getObservations())
                .build();

        outil.setDisponible(false);
        outilRepository.save(outil);

        return mapToDTO(affectationOutilRepository.save(affectation));
    }

    @Override
    @Transactional
    public AffectationOutilDTO terminerAffectationOutil(Long affectationId) {
        AffectationOutil affectation = affectationOutilRepository.findById(affectationId)
                .orElseThrow(() -> new RuntimeException("Affectation non trouvée"));

        affectation.setDateFin(LocalDateTime.now());

        Outil outil = affectation.getOutil();
        outil.setDisponible(true);
        outilRepository.save(outil);

        return mapToDTO(affectationOutilRepository.save(affectation));
    }

    @Override
    public List<AffectationOutilDTO> getAffectationsOutilByReception(Long receptionId) {
        return affectationOutilRepository.findByReceptionId(receptionId).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    // ========== MAPPING ==========
    private MecanicienDTO mapToDTO(Mecanicien m) {
        return MecanicienDTO.builder()
                .id(m.getId())
                .nom(m.getNom())
                .prenom(m.getPrenom())
                .matricule(m.getMatricule())
                .telephone(m.getTelephone())
                .email(m.getEmail())
                .competences(m.getCompetences())
                .disponible(m.getDisponible())
                .dateCreation(m.getDateCreation())
                .build();
    }

    private OutilDTO mapToDTO(Outil o) {
        return OutilDTO.builder()
                .id(o.getId())
                .nom(o.getNom())
                .reference(o.getReference())
                .marque(o.getMarque())
                .description(o.getDescription())
                .etat(o.getEtat())
                .disponible(o.getDisponible())
                .emplacement(o.getEmplacement())
                .dateProchaineMaintenance(o.getDateProchaineMaintenance())
                .dateCreation(o.getDateCreation())
                .build();
    }

    private AffectationMecanoDTO mapToDTO(AffectationMecano a) {
        return AffectationMecanoDTO.builder()
                .id(a.getId())
                .receptionId(a.getReception().getId())
                .mecanicienId(a.getMecanicien().getId())
                .mecanicienNom(a.getMecanicien().getNom() + " " + a.getMecanicien().getPrenom())
                .dateDebut(a.getDateDebut())
                .dateFin(a.getDateFin())
                .tempsPasse(a.getTempsPasse())
                .description(a.getDescription())
                .build();
    }

    private AffectationOutilDTO mapToDTO(AffectationOutil a) {
        return AffectationOutilDTO.builder()
                .id(a.getId())
                .receptionId(a.getReception().getId())
                .outilId(a.getOutil().getId())
                .outilNom(a.getOutil().getNom())
                .dateDebut(a.getDateDebut())
                .dateFin(a.getDateFin())
                .observations(a.getObservations())
                .build();
    }
}