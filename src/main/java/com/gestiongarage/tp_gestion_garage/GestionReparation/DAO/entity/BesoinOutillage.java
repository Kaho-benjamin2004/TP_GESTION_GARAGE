package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "besoins_outillage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BesoinOutillage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reparation_id", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "outillage_id")
    private Outillage outillage; // outil spécifique

    private String designation; // description

    private LocalDateTime dateReservation;
    private LocalDateTime dateRetour;

    @Enumerated(EnumType.STRING)
    private StatutBesoinOutillage statut; // RESERVE, UTILISE, RENDU
}