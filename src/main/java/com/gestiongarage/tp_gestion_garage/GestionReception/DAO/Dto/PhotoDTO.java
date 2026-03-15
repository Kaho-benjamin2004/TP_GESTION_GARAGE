package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypePhoto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoDTO {
    private Long id;
    private Long receptionId;
    private TypePhoto typePhoto;
    private String cheminFichier;
    private String description;
    private LocalDateTime datePrise;
}