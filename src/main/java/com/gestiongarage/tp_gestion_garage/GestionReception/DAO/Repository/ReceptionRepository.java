package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReceptionRepository extends JpaRepository<Reception, Long> {
    List<Reception> findByStatut(StatutReception statut);
    List<Reception> findByClientId(Long clientId);
    List<Reception> findByVehiculeId(Long vehiculeId);
    boolean existsByVehiculeIdAndStatutNot(Long vehiculeId, StatutReception statutExclu); // pour vérifier dossier actif
    Reception findByNumeroDossier(String numeroDossier);
    long countByNumeroDossierStartingWith(String prefix);
}
