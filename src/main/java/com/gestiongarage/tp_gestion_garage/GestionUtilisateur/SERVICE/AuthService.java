package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.dto.LoginRequest;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.JwtResponse;
import com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity.RegisterRequest;

public interface AuthService {
    JwtResponse register(RegisterRequest request);
    JwtResponse login(LoginRequest request);
}