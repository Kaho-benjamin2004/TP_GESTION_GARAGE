package com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import java.time.LocalDateTime;

@Entity

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AffectationMecano {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "reception_id", nullable = false)
    private Reception reception;

    @ManyToOne
    @JoinColumn(name = "mecanicien_id", nullable = false)
    private Mecanicien mecanicien;

    private LocalDateTime dateDebut;
    private LocalDateTime dateFin;

    private Integer tempsPasse; // en minutes
//     private statuss statutEnCours;
    private String description;
}