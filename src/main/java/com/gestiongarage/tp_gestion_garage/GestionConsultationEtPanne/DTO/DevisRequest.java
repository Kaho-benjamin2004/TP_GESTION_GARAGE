package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO;

import lombok.Data;

import java.math.BigDecimal;

//@Data
//public class DevisRequest {
//    private BigDecimal montantMainOeuvre;
//    private BigDecimal montantPieces;
//    private String notes;
//}


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Data
public class DevisRequest {
    @NotNull
    @Positive
    private BigDecimal montantMainOeuvre;

    @NotNull
    @Positive
    private BigDecimal montantPieces;

    private String notes;
}