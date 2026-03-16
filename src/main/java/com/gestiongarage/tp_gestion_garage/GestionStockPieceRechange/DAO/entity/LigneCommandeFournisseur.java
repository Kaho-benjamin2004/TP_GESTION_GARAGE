package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "lignes_commande_fournisseur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LigneCommandeFournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "commande_id", nullable = false)
    private CommandeFournisseur commande;

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private PieceRechange piece;

    private Integer quantite;
    private BigDecimal prixUnitaire; // prix négocié ou par défaut

    private Integer quantiteRecue; // pour gérer les réceptions partielles
}