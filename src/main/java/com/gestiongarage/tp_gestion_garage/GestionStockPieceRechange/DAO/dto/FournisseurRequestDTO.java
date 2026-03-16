package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FournisseurRequestDTO {
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @Size(max = 200)
    private String adresse;

    private String telephone;

    @Email(message = "Email invalide")
    private String email;

    private String contactPersonne;
}