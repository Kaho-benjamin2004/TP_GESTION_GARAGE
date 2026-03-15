package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Vehicule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehiculeRepository extends JpaRepository<Vehicule, Long> {
    List<Vehicule> findByImmatriculationContainingIgnoreCase(String immatriculation);
    List<Vehicule> findByClientId(Long clientId);
    boolean existsByImmatriculation(String immatriculation);
}
