package com.gestiongarage.tp_gestion_garage.GestionFinancier.DAO.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "caisse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Caisse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom; // "Caisse principale", "Caisse secondaire", etc.

    private BigDecimal soldeInitial;
    private BigDecimal soldeActuel;

    @CreationTimestamp
    private LocalDateTime dateCreation;

    @OneToMany(mappedBy = "caisse")
    private List<Paiement> paiements;
}