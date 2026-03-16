package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PieceRechangeRepository extends JpaRepository<PieceRechange, Long> {
    Optional<PieceRechange> findByReference(String reference);
    List<PieceRechange> findByStockLessThanEqual(Integer seuil);
    List<PieceRechange> findByFournisseurId(Long fournisseurId);

    @Query("SELECT p FROM PieceRechange p WHERE p.stock <= p.seuilAlerte")
    List<PieceRechange> findEnAlerte();
}