package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeClient;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 20)
    private String codeClient;

    @Column(nullable = false, length = 100)
    private String nom;

    private String prenom;

    @Enumerated(EnumType.STRING)
    private TypeClient typeClient;

    @Column(nullable = false, length = 20)
    private String telephone;

    private String telephoneSecondary;

    @Column(length = 100)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String adresse;

    private String ville;

    private String codePostal;

    private String pieceIdentite;

    private String numeroPiece;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @UpdateTimestamp
    private LocalDateTime dateModification;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Vehicule> vehicules;
}