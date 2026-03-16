package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "mouvements_caisse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MouvementCaisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "caisse_id")
    private Caisse caisse;

    private LocalDateTime dateMouvement;
    private String libelle;
    private BigDecimal montant;

    @Enumerated(EnumType.STRING)
    private TypeMouvementCaisse type; // ENCAISSEMENT ou DECAISSEMENT

    private Long paiementId; // référence au paiement si applicable
}