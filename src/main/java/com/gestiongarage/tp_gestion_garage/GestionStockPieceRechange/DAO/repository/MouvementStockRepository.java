package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MouvementStockRepository extends JpaRepository<MouvementStock, Long> {
    List<MouvementStock> findByPieceId(Long pieceId);
    List<MouvementStock> findByType(String type);
}