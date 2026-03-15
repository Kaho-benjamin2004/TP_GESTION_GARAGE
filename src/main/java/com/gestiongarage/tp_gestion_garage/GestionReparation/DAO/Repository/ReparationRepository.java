package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReparationRepository extends JpaRepository<Reparation, Long> {
    Optional<Reparation> findByReceptionId(Long receptionId);
    List<Reparation> findByStatut(StatutReparation statut);
}

