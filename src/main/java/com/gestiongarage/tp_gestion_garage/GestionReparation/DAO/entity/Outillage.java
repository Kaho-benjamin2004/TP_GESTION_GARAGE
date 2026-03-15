package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.EtatOutil;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "outillage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outillage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 50)
    private String code;

    @Column(nullable = false)
    private String nom;

    private String marque;
    private String modele;
    private String numeroSerie;

    private String emplacement;

    @Enumerated(EnumType.STRING)
    private EtatOutil etat;

    private LocalDate dateAchat;
    private LocalDate prochaineMaintenance;

    private Boolean disponible = true;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}