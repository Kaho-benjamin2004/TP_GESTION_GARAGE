package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.UrgencePanne;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;
//
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.NiveauUrgence;
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name = "pannes_detectees")
//@Getter @Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class PanneDetectee {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @ManyToOne(fetch = FetchType.LAZY)
//    @JoinColumn(name = "diagnostic_id", nullable = false)
//    private Diagnostic diagnostic;
//
//    @Column(nullable = false, columnDefinition = "TEXT")
//    private String description; // description de la panne
//
//    @Enumerated(EnumType.STRING)
//    private NiveauUrgence urgence;
//
//    private Integer tempsReparationEstime; // en minutes
//
//    // On pourrait stocker les pièces nécessaires sous forme de texte ou JSON, mais pour simplifier :
//    @Column(columnDefinition = "TEXT")
//    private String piecesNecessaires; // description textuelle des pièces
//
//    // Optionnel : lien vers table pieces_rechange plus tard
//    private Boolean besoinPiece; // true si des pièces sont nécessaires
//
//    // Pour l'historique : on peut aussi stocker un code panne standardisé plus tard
//    private String codePanne; // optionnel
//
//    @Column(columnDefinition = "TEXT")
//    private String commentaire;
//}
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PanneDetectee {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Diagnostic diagnostic;

    private String description;
    private UrgencePanne urgence;
    private Integer tempsEstime;
    private String piecesNecessaires;
    private String codePanne;
}