package com.allangabr.crud.services;

import com.allangabr.crud.dto.ClientDTO;
import com.allangabr.crud.model.entities.Client;
import com.allangabr.crud.repositories.ClientRepository;
import com.allangabr.crud.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id){
       Client client = clientRepository.findById(id).orElseThrow(
               () -> new ResourceNotFoundException("Cliente não encontrado"));
       return new ClientDTO(client);
    }

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable){
        Page<Client> list = clientRepository.findAll(pageable);
        return list.map(p -> new ClientDTO(p));
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO){
        Client client = new Client();
        copyDtoToEntity(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    private void copyDtoToEntity(ClientDTO clientDTO, Client client){
        client.setName(clientDTO.getName());
        client.setCpf(clientDTO.getCpf());
        client.setIncome(clientDTO.getIncome());
        client.setBirthDate(clientDTO.getBirthDate());
        client.setChildren(clientDTO.getChildren());
    }

}
