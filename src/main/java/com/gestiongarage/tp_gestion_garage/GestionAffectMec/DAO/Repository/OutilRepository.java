package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Outil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutilRepository extends JpaRepository<Outil, Long> {
    List<Outil> findByDisponibleTrue();
    List<Outil> findByEtat(EtatOutil etat);
}
