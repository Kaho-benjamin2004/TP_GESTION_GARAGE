package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PanneDetecteeRepository extends JpaRepository<PanneDetectee, Long> {
    List<PanneDetectee> findByDiagnosticId(Long diagnosticId);
    @Query("SELECT p.description, COUNT(p), r.vehicule.modele FROM PanneDetectee p " +
            "JOIN p.diagnostic d " +
            "JOIN d.reception r " +
            "GROUP BY p.description, r.vehicule.modele ORDER BY COUNT(p) DESC")
    List<Object[]> compterPannesParDescription();
}
