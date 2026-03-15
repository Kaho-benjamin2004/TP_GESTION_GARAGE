package com.gestiongarage.tp_gestion_garage.GestionReparation.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinOutillageRequest;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.BesoinPieceRequest;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.ReparationDTO;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.dto.ReparationRequest;

import java.util.List;

public interface ReparationService {
 ReparationDTO demarrerReparation(ReparationRequest request);
   ReparationDTO getReparation(Long id);
    ReparationDTO getReparationByReception(Long receptionId);
   ReparationDTO ajouterOperation(Long reparationId, com.garage.reception.dto.OperationReparationRequest request);
    ReparationDTO ajouterBesoinPiece(Long reparationId, BesoinPieceRequest request);
    ReparationDTO ajouterBesoinOutillage(Long reparationId, BesoinOutillageRequest request);
    ReparationDTO terminerReparation(Long reparationId);
    ReparationDTO suspendreReparation(Long reparationId);
   ReparationDTO mettreAJourStatut(Long reparationId, String statut); // ou enum
    List<ReparationDTO> listAll();
}
