package com.allangabr.crud.services;

import com.allangabr.crud.model.entities.Client;
import com.allangabr.crud.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Client findById(Long id){
        return clientRepository.findById(id).get();
    }

}
