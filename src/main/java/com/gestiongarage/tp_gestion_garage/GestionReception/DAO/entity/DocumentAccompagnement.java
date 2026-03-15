package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeDocument;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "documents_accompagnement")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DocumentAccompagnement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reception_id", nullable = false)
    private Reception reception;

    @Enumerated(EnumType.STRING)
    private TypeDocument typeDocument;

    private String numeroDocument;

    private String observations;
}