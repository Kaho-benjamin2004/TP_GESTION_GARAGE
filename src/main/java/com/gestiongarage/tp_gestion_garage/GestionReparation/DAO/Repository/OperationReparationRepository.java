package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.OperationReparation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OperationReparationRepository extends JpaRepository<OperationReparation, Long> {
    List<OperationReparation> findByReparationId(Long reparationId);
}
