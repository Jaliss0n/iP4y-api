package com.example.ip4y.controllers;

import com.example.ip4y.dtos.ClientDTO;
import com.example.ip4y.models.Client;
import com.example.ip4y.services.ClientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
@RequestMapping("client")
public class ClientController {

    private final ClientService clientService;

    @PostMapping("/new")
    public ResponseEntity<Client> saveClient(@RequestBody @Valid ClientDTO clientDTO) {
        Client savedClient = clientService.saveClient(clientDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedClient);
    }

    @GetMapping("/list")
    public ResponseEntity<List<Client>> getAllClients(){
        List<Client> clients = clientService.getAllClients();
        return ResponseEntity.status(HttpStatus.OK).body(clients);
    }

    @GetMapping("/list/{id}")
    public ResponseEntity<Object> getOneClient(@PathVariable(value = "id") UUID id) {
        return clientService.getOneClient(id);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(value = "id") UUID id,
                                                  @RequestBody @Valid ClientDTO clientDTO) {
        return clientService.updateClient(id, clientDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(value = "id") UUID id) {
        return clientService.deleteClient(id);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteAllClients() {
        return clientService.deleteAllClient();
    }

}
