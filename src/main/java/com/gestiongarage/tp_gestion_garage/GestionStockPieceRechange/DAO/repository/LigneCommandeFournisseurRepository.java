package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.LigneCommandeFournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LigneCommandeFournisseurRepository extends JpaRepository<LigneCommandeFournisseur, Long> {
    List<LigneCommandeFournisseur> findByCommandeId(Long commandeId);
}