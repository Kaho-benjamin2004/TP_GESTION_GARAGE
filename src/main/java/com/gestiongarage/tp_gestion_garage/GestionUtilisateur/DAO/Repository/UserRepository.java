package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.Repository;

import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
}