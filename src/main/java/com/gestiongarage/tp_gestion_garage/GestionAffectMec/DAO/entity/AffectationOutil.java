package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "affectations_outillage")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffectationOutil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reception_id", nullable = false)
    private Reception reception;

    @ManyToOne
    @JoinColumn(name = "outil_id", nullable = false)
    private Outil outil;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private String observations;
}