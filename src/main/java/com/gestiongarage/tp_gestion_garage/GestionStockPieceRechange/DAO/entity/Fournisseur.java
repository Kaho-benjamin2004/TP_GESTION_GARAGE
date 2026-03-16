package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity.PieceRechange;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "fournisseurs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Fournisseur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String contact;
    private String telephone;
    private String email;
    private String adresse;

    private String siteWeb;
    private String numeroSiret;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @OneToMany(mappedBy = "fournisseur")
    @Builder.Default
    private List<PieceRechange> pieces = new ArrayList<>();

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;
}