package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.CompetenceMecanicien;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.List;

@Data
public class MecanicienRequestDTO {
    @NotBlank
    @Size(max = 100)
    private String nom;

    @Size(max = 100)
    private String prenom;

    private String matricule;

    @Size(max = 20)
    private String telephone;

    @Email
    private String email;

    private List<CompetenceMecanicien> competences;
}