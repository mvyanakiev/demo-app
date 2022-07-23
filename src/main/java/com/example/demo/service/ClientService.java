package com.example.demo.service;

import com.example.demo.model.dto.IbanDto;
import com.example.demo.model.entity.Iban;
import com.example.demo.repository.ClientRepository;
import com.example.demo.model.dto.ClientDto;
import com.example.demo.model.entity.Client;
import com.example.demo.repository.IbanRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;
    private final IbanRepository ibanRepository;
    private final ModelMapper modelMapper;

    public ClientDto saveClient(ClientDto clientDto) {
        Client client = modelMapper.map(clientDto, Client.class);
        client = clientRepository.saveAndFlush(client);
        return modelMapper.map(client, ClientDto.class);
    }

    public ClientDto addIban(Long id, String ibanNumber) {

        Optional<ClientDto> clientDto = findById(id);
        if (clientDto.isEmpty()) {
            return null;
        }

        IbanDto ibanDto = new IbanDto();
        ibanDto.setIban(ibanNumber);
        Iban iban = ibanRepository.saveAndFlush(modelMapper.map(ibanDto, Iban.class));
        clientDto.get().getIbanList().add(modelMapper.map(iban, IbanDto.class));

        return saveClient(clientDto.get());
    }

    public Optional<ClientDto> findById(long id) {
        Optional<Client> client = clientRepository.findById(id);
        return client.map(value -> modelMapper.map(value, ClientDto.class));
    }

    public List<ClientDto> findAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(c -> modelMapper.map(c, ClientDto.class))
                .collect(Collectors.toList());
    }
}
