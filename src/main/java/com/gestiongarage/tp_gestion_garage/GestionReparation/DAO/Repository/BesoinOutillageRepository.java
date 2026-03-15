package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.BesoinOutillage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BesoinOutillageRepository extends JpaRepository<BesoinOutillage, Long> {
    List<BesoinOutillage> findByReparationId(Long reparationId);
}
