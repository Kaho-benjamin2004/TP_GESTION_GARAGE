package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.FrequencePanne;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.NiveauCarburant;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeReception;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReceptionDTO {
    private Long id;
    private String numeroDossier;
    private VehiculeDTO vehicule;
    private ClientDTO client;
    private LocalDateTime dateReception;
    private LocalDate datePrevueFin;
    private TypeReception typeReception;
    private String descriptionPanneClient;
    private String circonstancesPanne;
    private FrequencePanne frequencePanne;
    private String conditionsApparition;
    private String temoinsTableauBord;
    private Integer kilometrageArrivee;
    private NiveauCarburant niveauCarburant;
    private String etatExterieur;
    private String etatInterieur;
    private String accessoiresPresents;
    private StatutReception statut;
    private Boolean accordClient;
    private Long accordDevisId;
    private Long receptionnisteId;
    private String notesInternes;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
    private List<PhotoDTO> photos;
    private List<DocumentDTO> documents;
}