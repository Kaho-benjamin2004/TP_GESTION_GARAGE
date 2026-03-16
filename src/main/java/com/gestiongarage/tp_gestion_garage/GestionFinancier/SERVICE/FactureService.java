package com.gestiongarage.tp_gestion_garage.GestionFinancier.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.FactureDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.FactureRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.PaiementDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.dto.PaiementRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.StatutFacture;

import java.util.List;

public interface FactureService {
    FactureDTO creerFacture(FactureRequestDTO request);
    FactureDTO getFacture(Long id);
    FactureDTO getFactureByNumero(String numero);
    List<FactureDTO> getFacturesByClient(Long clientId);
    List<FactureDTO> getFacturesByStatut(StatutFacture statut);
    FactureDTO annulerFacture(Long id);
    PaiementDTO enregistrerPaiement(PaiementRequestDTO request);
    byte[] genererPDF(Long id); // pour impression
}