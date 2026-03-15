package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.AffectationMecano;
import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.AffectationOutil;
import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO.Diagnostic;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.FrequencePanne;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.NiveauCarburant;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.StatutReception;
import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeReception;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "receptions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reception {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, length = 30)
    private String numeroDossier;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicule_id", nullable = false)
    private Vehicule vehicule;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @Column(nullable = false)
    private LocalDateTime dateReception;

    private LocalDate datePrevueFin;

    @Enumerated(EnumType.STRING)
    private TypeReception typeReception;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String descriptionPanneClient;

    @Column(columnDefinition = "TEXT")
    private String circonstancesPanne;

    @Enumerated(EnumType.STRING)
    private FrequencePanne frequencePanne;

    @Column(columnDefinition = "TEXT")
    private String conditionsApparition;

    @Column(columnDefinition = "TEXT")
    private String temoinsTableauBord;

    private Integer kilometrageArrivee;

    @Enumerated(EnumType.STRING)
    private NiveauCarburant niveauCarburant;

    @Column(columnDefinition = "TEXT")
    private String etatExterieur;

    @Column(columnDefinition = "TEXT")
    private String etatInterieur;

    @Column(columnDefinition = "TEXT")
    private String accessoiresPresents;

    @Enumerated(EnumType.STRING)
    private StatutReception statut;

    private Boolean accordClient;

    private Long accordDevisId;

    @Column(nullable = false)
    private Long receptionnisteId; // ID de l'utilisateur connecté

    @Column(columnDefinition = "TEXT")
    private String notesInternes;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "reception", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PhotoVehicule> photos = new ArrayList<>();

    @OneToMany(mappedBy = "reception", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DocumentAccompagnement> documents = new ArrayList<>();
    // Dans Reception.java, ajouter :
    @OneToOne(mappedBy = "reception", cascade = CascadeType.ALL, orphanRemoval = true)
    private Diagnostic diagnostic;
    @OneToMany(mappedBy = "reception")
    private List<AffectationMecano> affectationsMecano = new ArrayList<>();

    @OneToMany(mappedBy = "reception")
    private List<AffectationOutil> affectationsOutil = new ArrayList<>();
}