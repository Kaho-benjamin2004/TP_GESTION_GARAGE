package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "outillage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Outil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String reference;

    private String marque;

    private String description;

    @Enumerated(EnumType.STRING)
    private EtatOutil etat = EtatOutil.NEUF;

    private Boolean disponible = true;

    private String emplacement;

    private LocalDateTime dateProchaineMaintenance;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "outil")
    private List<AffectationOutil> affectations;
}