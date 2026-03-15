package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "pieces_rechange")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PieceRechange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 50)
    private String reference;

    @Column(nullable = false)
    private String nom;

    private String description;

    private String marque;
    private String modele;

    private Integer stock;
    private Integer seuilAlerte;

    private BigDecimal prixAchat;
    private BigDecimal prixVente;

    private String emplacement;

    private Long fournisseurId;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;
}