package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "commandes_fournisseur")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommandeFournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "fournisseur_id", nullable = false)
    private Fournisseur fournisseur;

    private LocalDate dateCommande;

    @Enumerated(EnumType.STRING)
    private StatutCommande statut;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<LigneCommandeFournisseur> lignesCommande = new ArrayList<>();

    private LocalDate dateReceptionPrevue;
    private LocalDate dateReceptionReelle;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}