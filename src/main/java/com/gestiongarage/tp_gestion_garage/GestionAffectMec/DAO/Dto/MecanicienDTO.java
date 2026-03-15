package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.CompetenceMecanicien;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MecanicienDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String matricule;
    private String telephone;
    private String email;
    private List<CompetenceMecanicien> competences;
    private Boolean disponible;
    private LocalDateTime dateCreation;
}