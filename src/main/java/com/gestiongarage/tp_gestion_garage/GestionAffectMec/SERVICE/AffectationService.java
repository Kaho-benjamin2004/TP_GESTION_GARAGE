package com.gestiongarage.tp_gestion_garage.GestionAffectMec.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionAffectMec.DAO.Dto.*;

import java.util.List;

public interface AffectationService {
    // Mécaniciens
    MecanicienDTO createMecanicien(MecanicienRequestDTO request);
    MecanicienDTO updateMecanicien(Long id, MecanicienRequestDTO request);
    MecanicienDTO getMecanicien(Long id);
    List<MecanicienDTO> getAllMecaniciens();
    List<MecanicienDTO> getMecaniciensDisponibles();
    void deleteMecanicien(Long id);

    // Outils
    OutilDTO createOutil(OutilRequestDTO request);
    OutilDTO updateOutil(Long id, OutilRequestDTO request);
    OutilDTO getOutil(Long id);
    List<OutilDTO> getAllOutils();
    List<OutilDTO> getOutilsDisponibles();
    void deleteOutil(Long id);

    // Affectations
    AffectationMecanoDTO affecterMecanicien(AffecterMecanoRequest request);
    AffectationMecanoDTO terminerAffectationMecano(Long affectationId, Integer tempsPasse);
    List<AffectationMecanoDTO> getAffectationsMecanoByReception(Long receptionId);

    AffectationOutilDTO affecterOutil(AffecterOutilRequest request);
    AffectationOutilDTO terminerAffectationOutil(Long affectationId);
    List<AffectationOutilDTO> getAffectationsOutilByReception(Long receptionId);
}