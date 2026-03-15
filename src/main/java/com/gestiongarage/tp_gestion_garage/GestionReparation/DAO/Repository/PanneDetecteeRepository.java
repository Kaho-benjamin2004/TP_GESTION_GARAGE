package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PanneDetecteeRepository extends JpaRepository<PanneDetectee, Long> {
    List<PanneDetectee> findByDiagnosticId(Long diagnosticId);
}