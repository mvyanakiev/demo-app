package com.example.demo.controler;

import com.example.demo.model.dto.ClientDto;
import com.example.demo.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
public class ClientController {

    private ClientService clientService;

    @RequestMapping(value = "/client/add/{name}", produces = "application/json")
    public ClientDto addClient(@PathVariable("name") String name) {
        ClientDto clientDto = new ClientDto();

        if (!"".equalsIgnoreCase(name)) {
            clientDto.setName(name);
            clientDto = clientService.saveClient(clientDto);
        }

        return clientDto;
    }

    @RequestMapping(value = "/client/{id}/iban/{iban}", produces = "application/json")
    public ResponseEntity<ClientDto> addIban(@PathVariable("id") Long id, @PathVariable("iban") String iban) {
        ClientDto client = null;

        if (!"".equalsIgnoreCase(iban) && id != null) {
            client = clientService.addIban(id, iban);
        }

        if (client != null) {
            return ResponseEntity.ok(client);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/client/{id}", produces = "application/json")
    public ResponseEntity<ClientDto> getClient(@PathVariable("id") long id) {

        Optional<ClientDto> client = clientService.findById(id);

        if (client.isPresent()) {
            return ResponseEntity.ok(client.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @RequestMapping(value = "/clients", produces = "application/json")
    public ResponseEntity<List<ClientDto>> getAllClients() {

        List<ClientDto> allClients = clientService.findAll();

        if (!allClients.isEmpty()) {
            return ResponseEntity.ok(allClients);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
