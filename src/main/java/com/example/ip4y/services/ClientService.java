package com.example.ip4y.services;

import com.example.ip4y.controllers.ClientController;
import com.example.ip4y.dtos.ClientDTO;
import com.example.ip4y.exceptions.ClientNotExist;
import com.example.ip4y.exceptions.CpfAlreadyExists;
import com.example.ip4y.exceptions.FailedToAction;
import com.example.ip4y.models.Client;
import com.example.ip4y.repositories.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    public Client saveClient(ClientDTO clientDTO) {
        if (clientRepository.existsByCpf(clientDTO.cpf())) {
            throw new CpfAlreadyExists();
        }

        var client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        List<Client> clientList = clientRepository.findAll();

        if(!clientList.isEmpty()) {
            for (Client client : clientList) {
                UUID id = client.getIdCliente();
                client.add(linkTo(methodOn(ClientController.class).getOneClient(id)).withSelfRel());
            }
        }

        return clientList;
    }

    public ResponseEntity<Object> getOneClient(UUID id) {
        Optional<Client> client0 = clientRepository.findById(id);

        if (client0.isEmpty()) {
            throw new ClientNotExist();
        }

        client0.get().add(linkTo(methodOn(ClientController.class).getAllClients()).withSelfRel());
        return ResponseEntity.status(HttpStatus.OK).body(client0.get());
    }

    public ResponseEntity<Object> updateClient(UUID id, ClientDTO clientDTO) {
        Optional<Client> client0 = clientRepository.findById(id);

        if (client0.isEmpty()){
            throw new ClientNotExist();
        }

        var clientGet = client0.get();

        BeanUtils.copyProperties(clientDTO, clientGet);
        return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(clientGet));
    }

        public ResponseEntity<Object> deleteClient(UUID id) {
        Optional<Client> clientOptional = clientRepository.findById(id);

        if (clientOptional.isEmpty()) {
            throw new ClientNotExist();
        }

        Client client = clientOptional.get();
        clientRepository.delete(client);

        return ResponseEntity.status(HttpStatus.OK).body("Client deleted successfully.");
    }

    public ResponseEntity<String> deleteAllClient() {
        try {
            clientRepository.deleteAll();
            return new ResponseEntity<>("All clients have been deleted", HttpStatus.OK);
        } catch (Exception e) {
            throw new FailedToAction();
        }
    }

}
