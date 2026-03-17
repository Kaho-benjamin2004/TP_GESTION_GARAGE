package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.CompetenceMecanicien;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface MecanicienRepository extends JpaRepository<Mecanicien, Long> {
    List<Mecanicien> findByDisponibleTrue();

    List<Mecanicien> findByCompetencesContaining(CompetenceMecanicien competence);
//    @Query("SELECT COUNT(DISTINCT a.mecanicien.id) FROM AffectationMecano a WHERE a.statut = 'EN_COURS'")
//    long countDistinctMecanicienByStatutEnCours();
//
//    // Statistiques par mécanicien sur une période donnée (suppose que vous avez des relations)
//    @Query("SELECT a.mecanicien.id, a.mecanicien.nom, COUNT(a), SUM(a.tempsPasse), SUM(f.montantTTC) " +
//            "FROM AffectationMecano " +
//            "LEFT JOIN a.reception r " +
//            "LEFT JOIN r.facture f " +
//            "WHERE a.dateAffectation BETWEEN :debut AND :fin " +
//            "GROUP BY a.mecanicien.id, a.mecanicien.nom")
//    List<Object[]> statistiquesMecaniciens(@Param("debut") LocalDate debut, @Param("fin") LocalDate fin);
//}
}