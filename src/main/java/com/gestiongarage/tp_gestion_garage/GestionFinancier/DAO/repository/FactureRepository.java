package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Optional<Facture> findByNumeroFacture(String numero);
    List<Facture> findByClientId(Long clientId);
    List<Facture> findByStatut(StatutFacture statut);
    List<Facture> findByDateEcheanceBeforeAndStatut(LocalDate date, StatutFacture statut);

    long countByNumeroFactureStartingWith(String s);
    List<Facture> findByStatutAndMontantRestantGreaterThan(StatutFacture statut, BigDecimal montant);
}

