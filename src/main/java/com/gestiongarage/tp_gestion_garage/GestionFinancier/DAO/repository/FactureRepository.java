package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface FactureRepository extends JpaRepository<Facture, Long> {
    Optional<Facture> findByNumeroFacture(String numero);
    List<Facture> findByClientId(Long clientId);
    List<Facture> findByStatut(StatutFacture statut);
    List<Facture> findByDateEcheanceBeforeAndStatut(LocalDate date, StatutFacture statut);
    @Query("SELECT f.dateEmission, SUM(f.montantTTC), COUNT(f) FROM Facture f WHERE f.dateEmission BETWEEN :debut AND :fin GROUP BY f.dateEmission ORDER BY f.dateEmission")
    List<Object[]> sumMontantParJour(@Param("debut") LocalDate debut, @Param("fin") LocalDate fin);

    @Query("SELECT MONTH(f.dateEmission), SUM(f.montantTTC), COUNT(f) FROM Facture f WHERE YEAR(f.dateEmission) = :annee GROUP BY MONTH(f.dateEmission) ORDER BY MONTH(f.dateEmission)")
    List<Object[]> sumMontantParMois(@Param("annee") int annee);

    @Query("SELECT f.client.id, f.client.nom, f.client.prenom, COUNT(f), SUM(f.montantTTC) FROM Facture f GROUP BY f.client.id, f.client.nom, f.client.prenom ORDER BY SUM(f.montantTTC) DESC")
    List<Object[]> sumMontantParClient();

    long countByStatutAndMontantRestantGreaterThan(StatutFacture statut, BigDecimal montant);

    long countByNumeroFactureStartingWith(String s);
    List<Facture> findByStatutAndMontantRestantGreaterThan(StatutFacture statut, BigDecimal montant);
}

