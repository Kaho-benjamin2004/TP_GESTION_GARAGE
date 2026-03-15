package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.BesoinPiece;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BesoinPieceRepository extends JpaRepository<BesoinPiece, Long> {
    List<BesoinPiece> findByReparationId(Long reparationId);
}
