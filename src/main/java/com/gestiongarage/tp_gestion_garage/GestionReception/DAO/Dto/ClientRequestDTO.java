package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeClient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ClientRequestDTO {
    @NotBlank(message = "Le nom est obligatoire")
    @Size(max = 100)
    private String nom;

    @Size(max = 100)
    private String prenom;

    private TypeClient typeClient;

    @NotBlank(message = "Le téléphone est obligatoire")
    @Pattern(regexp = "^[0-9+\\s-]{8,20}$", message = "Format de téléphone invalide")
    private String telephone;

    @Size(max = 20)
    private String telephoneSecondary;

    @Email(message = "Email invalide")
    @Size(max = 100)
    private String email;

    private String adresse;
    private String ville;
    private String codePostal;
    private String pieceIdentite;
    private String numeroPiece;
    private String notes;
}