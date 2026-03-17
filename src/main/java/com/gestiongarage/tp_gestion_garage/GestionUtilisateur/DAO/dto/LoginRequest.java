package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;
}