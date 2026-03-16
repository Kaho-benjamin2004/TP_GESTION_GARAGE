package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.Fournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository.FournisseurRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FournisseurServiceImpl implements FournisseurService {

    private final FournisseurRepository fournisseurRepository;

    @Override
    @Transactional
    public FournisseurDTO createFournisseur(FournisseurRequestDTO request) {
        if (request.getEmail() != null && fournisseurRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("Un fournisseur avec cet email existe déjà");
        }

        Fournisseur fournisseur = Fournisseur.builder()
                .nom(request.getNom())
                .adresse(request.getAdresse())
                .telephone(request.getTelephone())
                .email(request.getEmail())
                .contact(request.getContactPersonne())

                .build();

        Fournisseur saved = fournisseurRepository.save(fournisseur);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public FournisseurDTO updateFournisseur(Long id, FournisseurRequestDTO request) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id: " + id));

        fournisseur.setNom(request.getNom());
        fournisseur.setAdresse(request.getAdresse());
        fournisseur.setTelephone(request.getTelephone());
        fournisseur.setEmail(request.getEmail());
        fournisseur.setContact(request.getContactPersonne());

        Fournisseur updated = fournisseurRepository.save(fournisseur);
        return mapToDTO(updated);
    }

    @Override
    public FournisseurDTO getFournisseur(Long id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id: " + id));
        return mapToDTO(fournisseur);
    }

    @Override
    public List<FournisseurDTO> getAllFournisseurs() {
        return fournisseurRepository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void deleteFournisseur(Long id) {
        Fournisseur fournisseur = fournisseurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Fournisseur non trouvé avec id: " + id));
        fournisseurRepository.delete(fournisseur);
    }

    private FournisseurDTO mapToDTO(Fournisseur fournisseur) {
        return FournisseurDTO.builder()
                .id(fournisseur.getId())
                .nom(fournisseur.getNom())
                .adresse(fournisseur.getAdresse())
                .telephone(fournisseur.getTelephone())
                .email(fournisseur.getEmail())
                .contact(fournisseur.getContact())
                .dateCreation(fournisseur.getDateCreation())
                .dateModification(fournisseur.getDateModification())
                .build();
    }
}