package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeClient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long id;
    private String codeClient;
    private String nom;
    private String prenom;
    private TypeClient typeClient;
    private String telephone;
    private String telephoneSecondary;
    private String email;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pieceIdentite;
    private String numeroPiece;
    private String notes;
    private LocalDateTime dateCreation;
    private LocalDateTime dateModification;
}