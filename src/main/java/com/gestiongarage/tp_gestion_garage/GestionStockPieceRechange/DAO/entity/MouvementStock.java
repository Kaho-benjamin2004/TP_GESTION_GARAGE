package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "mouvements_stock")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "piece_id", nullable = false)
    private PieceRechange piece;

    @Enumerated(EnumType.STRING)
    private TypeMouvement type; // ENTREE, SORTIE, AJUSTEMENT

    private Integer quantite;

    private String motif; // ex: "Commande fournisseur", "Utilisation réparation", "Inventaire"

    private Long documentAssocieId; // peut être l'ID d'une réparation, d'une commande, etc.

    @CreationTimestamp
    private LocalDateTime dateMouvement;
}