package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class NouvelleReceptionRequest {
    // Si client existe déjà
    private Long clientId;
    // Sinon, informations nouveau client
    private ClientRequestDTO nouveauClient;

    // Véhicule : soit existant, soit nouveau
    private Long vehiculeId;
    private VehiculeRequestDTO nouveauVehicule;

    @NotNull(message = "La date de réception est obligatoire")
    private LocalDateTime dateReception;

    private LocalDate datePrevueFin;

    private TypeReception typeReception;

    @NotBlank(message = "La description de la panne est obligatoire")
    private String descriptionPanneClient;

    private String circonstancesPanne;
    private FrequencePanne frequencePanne;
    private String conditionsApparition;
    private String temoinsTableauBord;

    @NotNull(message = "Le kilométrage à l'arrivée est obligatoire")
    @PositiveOrZero
    private Integer kilometrageArrivee;

    private NiveauCarburant niveauCarburant;
    private String etatExterieur;
    private String etatInterieur;
    private String accessoiresPresents;

    private Boolean accordClient;
    private Long accordDevisId;

    @NotNull(message = "L'ID du réceptionniste est obligatoire")
    private Long receptionnisteId;

    private String notesInternes;

    // Listes de photos et documents (facultatifs)


    private List<@Valid PhotoRequest> photos;
    private List<@Valid DocumentRequest> documents;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class PhotoRequest {
        private TypePhoto typePhoto;
        @NotBlank
        private String cheminFichier;
        private String description;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class DocumentRequest {
        private TypeDocument typeDocument;
        private String numeroDocument;
        private String observations;
    }
}

