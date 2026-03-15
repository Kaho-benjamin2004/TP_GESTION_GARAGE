package com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto;

import com.gestiongarage.tp_gestion_garage.GestionReception.enums.TypeDocument;
import lombok.Data;

@Data
class DocumentRequest {
    private TypeDocument typeDocument;
    private String numeroDocument;
    private String observations;
}
