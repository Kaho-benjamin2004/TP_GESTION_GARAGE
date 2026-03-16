package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.CommandeFournisseurRequestDTO;

import java.util.List;

public interface CommandeFournisseurService {
    CommandeFournisseurDTO createCommande(CommandeFournisseurRequestDTO request);
    CommandeFournisseurDTO getCommande(Long id);
    List<CommandeFournisseurDTO> getAllCommandes();
    CommandeFournisseurDTO validerCommande(Long id);
    CommandeFournisseurDTO recevoirCommande(Long id);
    void deleteCommande(Long id);
}