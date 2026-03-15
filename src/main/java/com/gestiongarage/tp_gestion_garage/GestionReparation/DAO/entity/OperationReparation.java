package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.PanneDetectee;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "operations_reparation")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OperationReparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reparation_id", nullable = false)
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "panne_id") // optionnel, si lié à une panne détectée
    private PanneDetectee panne;

    private String description;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private Integer tempsPasse; // en minutes

    @ManyToOne
    @JoinColumn(name = "mecanicien_id")
    private Mecanicien mecanicien; // mécanicien ayant réalisé l'opération

    @Column(columnDefinition = "TEXT")
    private String commentaire;
}