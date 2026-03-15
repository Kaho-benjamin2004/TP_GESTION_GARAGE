package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AffectationMecanoRepository extends JpaRepository<AffectationMecano, Long> {
    List<AffectationMecano> findByReceptionId(Long receptionId);
    List<AffectationMecano> findByMecanicienIdAndDateFinIsNull(Long mecanicienId);
}

