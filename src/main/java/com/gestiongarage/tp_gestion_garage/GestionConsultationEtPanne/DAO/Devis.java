package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;

import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.DevisStatut;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

//package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;
//
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DTO.DevisDTO;
//import com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.Enum.StatutDevis;
//import jakarta.persistence.*;
//import lombok.*;
//import org.hibernate.annotations.CreationTimestamp;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//
//@Setter
//@Getter
//@Entity
//@Table(name = "devis")
//
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Devis {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "diagnostic_id", unique = true, nullable = false)
//    private Diagnostic diagnostic;
//
//    private BigDecimal totalMainOeuvre; // calculé depuis les pannes (temps * taux horaire)
//    private BigDecimal totalPieces; // somme des pièces
//    private BigDecimal totalToutesTaxes; // total général
//    private BigDecimal tauxHoraire; // taux appliqué au moment du devis
//
//    @Enumerated(EnumType.STRING)
//    private StatutDevis statut;
//
//    private LocalDateTime dateValidationClient; // quand le client a validé
//
//    @Column(columnDefinition = "TEXT")
//    private String remarques;
//
//    @CreationTimestamp
//    private LocalDateTime dateCreation;
//
//}
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Devis {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Diagnostic diagnostic;

    private BigDecimal montantMainOeuvre;
    private BigDecimal montantPieces;
    private BigDecimal montantTotal;
    private DevisStatut statut;
    private LocalDateTime dateValidation;
    private String notes;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}