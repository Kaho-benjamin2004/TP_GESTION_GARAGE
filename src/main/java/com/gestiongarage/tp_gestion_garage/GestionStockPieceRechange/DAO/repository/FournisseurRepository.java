package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.Fournisseur;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface FournisseurRepository extends JpaRepository<Fournisseur, Long> {
    Optional<Fournisseur> findByEmail(String email);
    List<Fournisseur> findByNomContainingIgnoreCase(String nom);
    List<Fournisseur> findByTelephone(String telephone);
    boolean existsByEmail(String email);
}