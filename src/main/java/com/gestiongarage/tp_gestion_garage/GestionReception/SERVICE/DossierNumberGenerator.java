package com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ReceptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Component
@RequiredArgsConstructor
public class DossierNumberGenerator {
    private final ReceptionRepository receptionRepository;

    public String generateNextNumber() {
        YearMonth currentMonth = YearMonth.now();
        String prefix = currentMonth.format(DateTimeFormatter.ofPattern("yyyy-MM"));
        // Compter les dossiers du mois en cours
        long count = receptionRepository.countByNumeroDossierStartingWith(prefix); // à ajouter dans repository
        long next = count + 1;
        return prefix + "-" + String.format("%04d", next);
    }
}