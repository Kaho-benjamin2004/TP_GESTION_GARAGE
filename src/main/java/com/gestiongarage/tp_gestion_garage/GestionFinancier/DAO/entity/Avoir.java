package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Client;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "avoirs")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Avoir {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, length = 30)
    private String numeroAvoir;

    @ManyToOne
    @JoinColumn(name = "facture_source_id")
    private Facture factureSource; // facture d'origine

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    private LocalDate dateEmission;
    private BigDecimal montant;
    private String raison;

    private Boolean utilise; // si l'avoir a déjà été utilisé

    @CreationTimestamp
    private LocalDateTime dateCreation;
}