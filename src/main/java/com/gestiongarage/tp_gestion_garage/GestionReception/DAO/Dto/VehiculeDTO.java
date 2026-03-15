package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeCarburant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class VehiculeDTO {
    private Long id;
    private Long clientId;
    private String immatriculation;
    private String marque;
    private String modele;
    private Integer annee;
    private String couleur;
    private TypeCarburant typeCarburant;
    private String genre;
    private String numeroChassis;
    private Integer puissanceFiscale;
    private LocalDate datePremiereMiseCirculation;
    private LocalDate dateDerniereRevision;
    private Integer kilometrageActuel;
    private String observations;
    private LocalDateTime dateCreation;
}