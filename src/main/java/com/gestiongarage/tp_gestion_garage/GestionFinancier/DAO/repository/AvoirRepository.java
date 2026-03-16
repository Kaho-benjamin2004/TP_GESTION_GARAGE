package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.Avoir;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AvoirRepository extends JpaRepository<Avoir, Long> {
    Optional<Avoir> findByNumeroAvoir(String numero);
    List<Avoir> findByClientId(Long clientId);
}
