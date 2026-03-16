package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.repository;

import com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity.Caisse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CaisseRepository extends JpaRepository<Caisse, Long> {
    // ...
}
