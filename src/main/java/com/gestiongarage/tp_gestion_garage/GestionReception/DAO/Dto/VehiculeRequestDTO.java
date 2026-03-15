package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeCarburant;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDate;

@Data
public class VehiculeRequestDTO {
    @NotBlank(message = "L'immatriculation est obligatoire")
    @Size(max = 20)
    private String immatriculation;

    @NotBlank(message = "La marque est obligatoire")
    @Size(max = 50)
    private String marque;

    @NotBlank(message = "Le modèle est obligatoire")
    @Size(max = 50)
    private String modele;

    @Min(value = 1900, message = "Année invalide")
    @Max(value = 2100)
    private Integer annee;

    @Size(max = 30)
    private String couleur;

    private TypeCarburant typeCarburant;

    private String genre;

    @Size(max = 50)
    private String numeroChassis;

    private Integer puissanceFiscale;

    private LocalDate datePremiereMiseCirculation;

    private LocalDate dateDerniereRevision;

    @PositiveOrZero
    private Integer kilometrageActuel;

    private String observations;
}