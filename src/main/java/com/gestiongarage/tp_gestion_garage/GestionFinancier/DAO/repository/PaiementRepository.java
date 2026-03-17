package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.Paiement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {
    List<Paiement> findByFactureId(Long factureId);
    List<Paiement> findByDatePaiementBetween(LocalDate debut, LocalDate fin);
    @Query("SELECT SUM(p.montant) FROM Paiement p WHERE p.datePaiement BETWEEN :debut AND :fin")
    BigDecimal sumMontantByDateBetween(@Param("debut") LocalDateTime debut, @Param("fin") LocalDateTime fin);
}
