package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.CompetenceMecanicien;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MecanicienRepository extends JpaRepository<Mecanicien, Long> {
    List<Mecanicien> findByDisponibleTrue();
    List<Mecanicien> findByCompetencesContaining(CompetenceMecanicien competence);
}
