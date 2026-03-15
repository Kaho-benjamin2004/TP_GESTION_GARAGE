package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Devis;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Diagnostic;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DiagnosticRepository extends JpaRepository<Diagnostic, Long> {

    Optional<Diagnostic> findByReceptionId(Long receptionId);
    boolean existsByReceptionId(Long receptionId);
}

