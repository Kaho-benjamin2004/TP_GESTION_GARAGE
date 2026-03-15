package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.AffectationOutil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffectationOutilRepository extends JpaRepository<AffectationOutil, Long> {
    List<AffectationOutil> findByReceptionId(Long receptionId);
    List<AffectationOutil> findByOutilIdAndDateFinIsNull(Long outilId);
}
