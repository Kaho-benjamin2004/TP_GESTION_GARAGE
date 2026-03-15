package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypePhoto;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
class PhotoRequest {
    private TypePhoto typePhoto;
    @NotBlank
    private String cheminFichier;
    private String description;
}
