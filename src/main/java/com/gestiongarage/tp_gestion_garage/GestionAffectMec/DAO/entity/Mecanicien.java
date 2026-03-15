package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "mecaniciens")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Mecanicien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nom;

    private String prenom;

    @Column(unique = true)
    private String matricule;

    private String telephone;
    private String email;

    @ElementCollection(targetClass = CompetenceMecanicien.class)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "mecanicien_competences", joinColumns = @JoinColumn(name = "mecanicien_id"))
    @Column(name = "competence")
    private List<CompetenceMecanicien> competences;

    private Boolean disponible = true;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "mecanicien")
    private List<AffectationMecano> affectations;
}