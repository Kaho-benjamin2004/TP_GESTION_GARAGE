package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeCarburant;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "vehicules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(unique = true, nullable = false, length = 20)
    private String immatriculation;

    @Column(nullable = false, length = 50)
    private String marque;

    @Column(nullable = false, length = 50)
    private String modele;

    private Integer annee;

    private String couleur;

    @Enumerated(EnumType.STRING)
    private TypeCarburant typeCarburant;

    private String genre;

    @Column(length = 50)
    private String numeroChassis;

    private Integer puissanceFiscale;

    private LocalDate datePremiereMiseCirculation;

    private LocalDate dateDerniereRevision;

    private Integer kilometrageActuel;

    @Column(columnDefinition = "TEXT")
    private String observations;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reception> receptions;
}
