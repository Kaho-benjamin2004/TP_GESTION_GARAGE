package com.gestiongarage.tp_gestion_garage.GestionReparation.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity.Mecanicien;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "temps_travail")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TempsTravail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reparation_id")
    private Reparation reparation;

    @ManyToOne
    @JoinColumn(name = "mecanicien_id")
    private Mecanicien mecanicien;

    private LocalDateTime debut;
    private LocalDateTime fin;

    private Integer duree; // en minutes

    private String tache;
}
