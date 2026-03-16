package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository.OutillageRepository;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.Outillage;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OutillageServiceImpl implements OutillageService {

    private final OutillageRepository outillageRepository;

    @Override
    @Transactional
    public OutillageDTO createOutillage(OutillageRequestDTO request) {
        Outillage outillage = Outillage.builder()
                .code(request.getCode())
                .nom(request.getNom())
                .marque(request.getMarque())
                .modele(request.getModele())
                .numeroSerie(request.getNumeroSerie())
                .emplacement(request.getEmplacement())
                .etat(request.getEtat() != null ? request.getEtat() : EtatOutil.BON)
                .dateAchat(request.getDateAchat())
                .prochaineMaintenance(request.getProchaineMaintenance())
                .disponible(request.getDisponible() != null ? request.getDisponible() : true)
                .notes(request.getNotes())
                .build();

        Outillage saved = outillageRepository.save(outillage);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public OutillageDTO updateOutillage(Long id, OutillageRequestDTO request) {
        Outillage outillage = outillageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outillage non trouvé avec id: " + id));

        outillage.setCode(request.getCode());
        outillage.setNom(request.getNom());
        outillage.setMarque(request.getMarque());
        outillage.setModele(request.getModele());
        outillage.setNumeroSerie(request.getNumeroSerie());
        outillage.setEmplacement(request.getEmplacement());
        outillage.setEtat(request.getEtat());
        outillage.setDateAchat(request.getDateAchat());
        outillage.setProchaineMaintenance(request.getProchaineMaintenance());
        outillage.setDisponible(request.getDisponible());
        outillage.setNotes(request.getNotes());

        Outillage updated = outillageRepository.save(outillage);
        return mapToDTO(updated);
    }

    @Override
    public OutillageDTO getOutillage(Long id) {
        Outillage outillage = outillageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outillage non trouvé avec id: " + id));
        return mapToDTO(outillage);
    }

    @Override
    public List<OutillageDTO> getAllOutillages() {
        return outillageRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<OutillageDTO> getOutillagesDisponibles() {
        return outillageRepository.findByDisponibleTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteOutillage(Long id) {
        Outillage outillage = outillageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Outillage non trouvé avec id: " + id));
        outillageRepository.delete(outillage);
    }

    private OutillageDTO mapToDTO(Outillage outillage) {
        return OutillageDTO.builder()
                .id(outillage.getId())
                .code(outillage.getCode())
                .nom(outillage.getNom())
                .marque(outillage.getMarque())
                .modele(outillage.getModele())
                .numeroSerie(outillage.getNumeroSerie())
                .emplacement(outillage.getEmplacement())
                .etat(outillage.getEtat())
                .dateAchat(outillage.getDateAchat())
                .prochaineMaintenance(outillage.getProchaineMaintenance())
                .disponible(outillage.getDisponible())
                .notes(outillage.getNotes())
//                .dateAchat(outillage.getDateAchat())
                .build();
    }
}