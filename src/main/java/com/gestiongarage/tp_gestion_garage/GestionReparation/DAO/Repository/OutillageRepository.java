package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.Outillage;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface OutillageRepository extends JpaRepository<Outillage, Long> {
    Optional<Outillage> findByCode(String code);
    List<Outillage> findByDisponibleTrue();
    List<Outillage> findByEtat(EtatOutil etat);
    List<Outillage> findByProchaineMaintenanceBefore(LocalDate date);
}