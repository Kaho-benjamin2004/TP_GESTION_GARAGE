package com.gestiongarage.tp_gestion_garage.ProductionRapport.SERVICE;

import com.gestiongarage.tp_gestion_garage.ProductionRapport.DAO.dto.*;

import java.time.LocalDate;
import java.util.List;

public interface ReportingService {
    // Opérationnels
    List<VehiculeEnAttenteDTO> getVehiculesEnAttente();

    // Financiers
    List<ChiffreAffairesDTO> getChiffreAffairesParPeriode(LocalDate debut, LocalDate fin);
    List<FactureImpayeeDTO> getFacturesImpayees();

    // Stock
    List<EtatStockDTO> getEtatStock();

    // Clients
    List<HistoriqueClientDTO> getHistoriqueClient(Long clientId);

    // Techniques
    List<PanneRecurrenteDTO> getPannesRecurrentesParModele();
}