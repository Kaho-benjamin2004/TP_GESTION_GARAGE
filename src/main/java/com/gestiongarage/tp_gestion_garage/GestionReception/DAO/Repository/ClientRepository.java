package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.*;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    List<Client> findByNomContainingIgnoreCase(String nom);
    List<Client> findByTelephoneContaining(String telephone);
    List<Client> findByEmailContainingIgnoreCase(String email);
}

