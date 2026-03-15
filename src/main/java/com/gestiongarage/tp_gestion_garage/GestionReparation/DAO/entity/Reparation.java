package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "reparations")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reparation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "reception_id", nullable = false, unique = true)
    private Reception reception; // la réception associée (après diagnostic et devis accepté)

    private LocalDateTime dateDebut;
    private LocalDateTime dateFinPrevue;
    private LocalDateTime dateFinReelle;

    @Enumerated(EnumType.STRING)
    private StatutReparation statut; // EN_COURS, SUSPENDUE, TERMINEE, CONTROLE

    @Column(columnDefinition = "TEXT")
    private String notes;

    @ManyToOne
    @JoinColumn(name = "chef_atelier_id")
    private Mecanicien chefAtelier; // responsable du suivi

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<OperationReparation> operations = new ArrayList<>();

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BesoinPiece> besoinsPieces = new ArrayList<>();

    @OneToMany(mappedBy = "reparation", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<BesoinOutillage> besoinsOutillage = new ArrayList<>();
}