package com.gestiongarage.tp_gestion_garage.GestionUtilisateur.DAO.entity;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterRequest {
    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @Email
    @NotBlank
    private String email;

    private String nom;
    private String prenom;

    @NotNull
    private Role role;
}