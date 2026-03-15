package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

//package com.gestiongarage.tp_gestion_garage.GestionConsultationEtPanne.DAO;
//
//import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Reception;
//import jakarta.persistence.*;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import org.hibernate.annotations.CreationTimestamp;
//import org.hibernate.annotations.UpdateTimestamp;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.List;
//
//@Entity
//@Table(name = "diagnostics")
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//public class Diagnostic {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//
//    @OneToOne
//    @JoinColumn(name = "reception_id", unique = true, nullable = false)
//    private Reception reception;
//
//    @Column(nullable = false)
//    private Long mechanicienId; // ID du mécanicien qui a réalisé le diagnostic
//
//    private LocalDateTime dateDiagnostic;
//    private Long diagnostiqueurId;
//    private String notesDiagnostic;
//
//    @Column(columnDefinition = "TEXT")
//    private String observations; // observations générales
//
//    @Column(columnDefinition = "TEXT")
//    private String recommandations; // recommandations au client
//
//    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true)
//    @Builder.Default
//    private List<PanneDetectee> pannesDetectees = new ArrayList<>();
//
//    @OneToOne(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true)
//    private Devis devis;
//
//    @CreationTimestamp
//    private LocalDateTime dateCreation;
//
//    @UpdateTimestamp
//    private LocalDateTime dateModification;
//}
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Diagnostic {
    @Id
    @GeneratedValue
    private Long id;

    @OneToOne
    private Reception reception;

    private LocalDateTime dateDiagnostic;
    private Long diagnostiqueurId;
    private String notesDiagnostic;

    @OneToMany(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private List<PanneDetectee> pannesDetectees = new ArrayList<>();

    @OneToOne(mappedBy = "diagnostic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Devis devis;

    @CreationTimestamp
    private LocalDateTime dateCreation;
}