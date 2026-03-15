package com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ClientDTO;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ClientRequestDTO;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Repository.ClientRepository;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.entity.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    @Override
    @Transactional
    public ClientDTO createClient(ClientRequestDTO dto) {
        Client client = mapToEntity(dto);
        client.setCodeClient(generateCodeClient());
        Client saved = clientRepository.save(client);
        return mapToDTO(saved);
    }

    @Override
    @Transactional
    public ClientDTO updateClient(Long id, ClientRequestDTO dto) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + id));
        updateEntity(client, dto);
        Client updated = clientRepository.save(client);
        return mapToDTO(updated);
    }



    @Override
    public ClientDTO getClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + id));
        return mapToDTO(client);
    }

    @Override
    public List<ClientDTO> searchClients(String nom, String telephone, String email) {
        // Implémentation simple : si un critère est fourni, on appelle la méthode correspondante
        // Sinon, on retourne tout (à éviter en production)
        if (nom != null && !nom.isEmpty()) {
            return clientRepository.findByNomContainingIgnoreCase(nom).stream().map(this::mapToDTO).collect(Collectors.toList());
        } else if (telephone != null && !telephone.isEmpty()) {
            return clientRepository.findByTelephoneContaining(telephone).stream().map(this::mapToDTO).collect(Collectors.toList());
        } else if (email != null && !email.isEmpty()) {
            return clientRepository.findByEmailContainingIgnoreCase(email).stream().map(this::mapToDTO).collect(Collectors.toList());
        } else {
            return clientRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
        }
    }

    @Override
    @Transactional
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Client non trouvé avec id: " + id));
        clientRepository.delete(client);
    }

    private String generateCodeClient() {
        return "CLI-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private Client mapToEntity(ClientRequestDTO dto) {
        return Client.builder()
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .typeClient(dto.getTypeClient())
                .telephone(dto.getTelephone())
                .telephoneSecondary(dto.getTelephoneSecondary())
                .email(dto.getEmail())
                .adresse(dto.getAdresse())
                .ville(dto.getVille())
                .codePostal(dto.getCodePostal())
                .pieceIdentite(dto.getPieceIdentite())
                .numeroPiece(dto.getNumeroPiece())
                .notes(dto.getNotes())
                .build();
    }

    private void updateEntity(Client client, ClientRequestDTO dto) {
        client.setNom(dto.getNom());
        client.setPrenom(dto.getPrenom());
        client.setTypeClient(dto.getTypeClient());
        client.setTelephone(dto.getTelephone());
        client.setTelephoneSecondary(dto.getTelephoneSecondary());
        client.setEmail(dto.getEmail());
        client.setAdresse(dto.getAdresse());
        client.setVille(dto.getVille());
        client.setCodePostal(dto.getCodePostal());
        client.setPieceIdentite(dto.getPieceIdentite());
        client.setNumeroPiece(dto.getNumeroPiece());
        client.setNotes(dto.getNotes());
    }

    private ClientDTO mapToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .codeClient(client.getCodeClient())
                .nom(client.getNom())
                .prenom(client.getPrenom())
                .typeClient(client.getTypeClient())
                .telephone(client.getTelephone())
                .telephoneSecondary(client.getTelephoneSecondary())
                .email(client.getEmail())
                .adresse(client.getAdresse())
                .ville(client.getVille())
                .codePostal(client.getCodePostal())
                .pieceIdentite(client.getPieceIdentite())
                .numeroPiece(client.getNumeroPiece())
                .notes(client.getNotes())
                .dateCreation(client.getDateCreation())
                .dateModification(client.getDateModification())
                .build();
    }
}
