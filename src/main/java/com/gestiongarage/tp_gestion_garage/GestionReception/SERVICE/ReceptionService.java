package com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.NouvelleReceptionRequest;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ReceptionDTO;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;

import java.util.List;

public interface ReceptionService {
    ReceptionDTO createReception(NouvelleReceptionRequest request);
    ReceptionDTO getReception(Long id);
    ReceptionDTO getReceptionByNumeroDossier(String numeroDossier);
    List<ReceptionDTO> getAllReceptions();
    List<ReceptionDTO> getReceptionsByStatut(StatutReception statut);
    ReceptionDTO updateStatut(Long id, StatutReception statut);
    ReceptionDTO updateReception(Long id, NouvelleReceptionRequest request);
    void deleteReception(Long id);
}