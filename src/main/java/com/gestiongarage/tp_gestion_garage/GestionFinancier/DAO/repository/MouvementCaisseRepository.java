package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.MouvementCaisse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MouvementCaisseRepository extends JpaRepository<MouvementCaisse, Long> {
    List<MouvementCaisse> findByCaisseIdOrderByDateMouvementDesc(Long caisseId);
}
