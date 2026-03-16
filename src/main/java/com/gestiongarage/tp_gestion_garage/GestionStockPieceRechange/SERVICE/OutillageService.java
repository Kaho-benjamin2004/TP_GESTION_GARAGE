package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.OutillageRequestDTO;

import java.util.List;

public interface OutillageService {
    OutillageDTO createOutillage(OutillageRequestDTO request);
    OutillageDTO updateOutillage(Long id, OutillageRequestDTO request);
    OutillageDTO getOutillage(Long id);
    List<OutillageDTO> getAllOutillages();
    List<OutillageDTO> getOutillagesDisponibles();
    void deleteOutillage(Long id);
}