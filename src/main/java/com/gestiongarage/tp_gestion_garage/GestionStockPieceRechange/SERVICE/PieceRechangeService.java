package com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.MouvementStockDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeDTO;
import com.gestiongarage.tp_gestion_garage.GestionStockPieceRechange.DAO.dto.PieceRechangeRequestDTO;

import java.util.List;

public interface PieceRechangeService {
    PieceRechangeDTO createPiece(PieceRechangeRequestDTO request);
    PieceRechangeDTO updatePiece(Long id, PieceRechangeRequestDTO request);
    PieceRechangeDTO getPiece(Long id);
    PieceRechangeDTO getPieceByReference(String reference);
    List<PieceRechangeDTO> getAllPieces();
    List<PieceRechangeDTO> getPiecesEnAlerte();
    void deletePiece(Long id);
    MouvementStockDTO entreeStock(Long pieceId, Integer quantite, String motif, Long documentId);
    MouvementStockDTO sortieStock(Long pieceId, Integer quantite, String motif, Long documentId);
    List<MouvementStockDTO> getMouvements(Long pieceId);
}