package com.gestiongarage.tp_gestion_garage.GestionReception.SERVICE;

import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ClientDTO;
import com.gestiongarage.tp_gestion_garage.GestionReception.DAO.Dto.ClientRequestDTO;

import java.util.List;

public interface ClientService {
    ClientDTO createClient(ClientRequestDTO dto);
    ClientDTO updateClient(Long id, ClientRequestDTO dto);
    ClientDTO getClient(Long id);
    List<ClientDTO> searchClients(String nom, String telephone, String email);
    void deleteClient(Long id);
}
