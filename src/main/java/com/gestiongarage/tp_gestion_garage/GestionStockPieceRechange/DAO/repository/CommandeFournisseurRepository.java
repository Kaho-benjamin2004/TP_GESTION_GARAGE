package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.CommandeFournisseur;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.StatutCommande;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CommandeFournisseurRepository extends JpaRepository<CommandeFournisseur, Long> {
    List<CommandeFournisseur> findByFournisseurId(Long fournisseurId);
    List<CommandeFournisseur> findByStatut(StatutCommande statut);
}