package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypePhoto;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "photos_vehicule")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PhotoVehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id", nullable = false)
    private Reception reception;

    @Enumerated(EnumType.STRING)
    private TypePhoto typePhoto;

    @Column(nullable = false)
    private String cheminFichier;

    private String description;

    @CreationTimestamp
    private LocalDateTime datePrise;
}