package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.UrgencePanne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

//@Data
//public class PanneDetecteeRequestDTO {
//    @NotBlank
//    private String description;
//
//    private UrgencePanne urgence;
//
//    @Positive
//    private Integer tempsEstime;
//
//    private String piecesNecessaires;
//
//    private String codePanne;
//}


@Data
public class PanneDetecteeRequestDTO {
    @NotBlank
    private String description;

    private UrgencePanne urgence;

    @Positive
    private Integer tempsEstime;

    private String piecesNecessaires;

    private String codePanne;
}