package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "besoins_pieces")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BesoinPiece {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reparation_id", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "piece_id") // référence à la pièce en stock (module 5)
    private PieceRechange piece; // optionnel, si la pièce existe dans le catalogue

    private String designation; // description de la pièce nécessaire

    private Integer quantiteDemandee;
    private Integer quantiteUtilisee;

    @Enumerated(EnumType.STRING)
    private StatutBesoin statut; // A_DEMANDER, COMMANDE, RECUE, ANNULE

    private LocalDateTime dateDemande;
    private LocalDateTime dateReception;

    @Column(columnDefinition = "TEXT")
    private String remarques;
}