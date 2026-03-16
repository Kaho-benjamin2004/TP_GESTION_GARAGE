package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.FournisseurRequestDTO;

import java.util.List;

public interface FournisseurService {
    FournisseurDTO createFournisseur(FournisseurRequestDTO request);
    FournisseurDTO updateFournisseur(Long id, FournisseurRequestDTO request);
    FournisseurDTO getFournisseur(Long id);
    List<FournisseurDTO> getAllFournisseurs();
    void deleteFournisseur(Long id);
}