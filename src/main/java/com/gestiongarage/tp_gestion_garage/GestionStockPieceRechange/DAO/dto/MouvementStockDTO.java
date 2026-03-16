package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity.TypeMouvement;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementStockDTO {
    private Long id;
    private Long pieceId;
    private String pieceReference;
    private TypeMouvement type;
    private Integer quantite;
    private String motif;
    private Long documentAssocieId;
    private LocalDateTime dateMouvement;
}