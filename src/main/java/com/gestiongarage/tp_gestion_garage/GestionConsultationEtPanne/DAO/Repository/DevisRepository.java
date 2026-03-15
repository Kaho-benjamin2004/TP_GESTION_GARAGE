package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Devis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DevisRepository extends JpaRepository<Devis, Long> {
    Devis findByDiagnosticId(Long diagnosticId);
}
