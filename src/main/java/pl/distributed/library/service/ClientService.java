package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.dto.NewClientDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Client;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.ClientMapper;
import pl.distributed.library.repository.AddressRepository;
import pl.distributed.library.repository.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClientService {
    private final ClientRepository clientRepository;
    private final AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public ClientDto addClient(NewClientDto newClientDto) {
        Optional<Address> address = addressRepository.findByCityAndStreetAndNumberAndPostalCode(
                newClientDto.getAddressCreateDto().getCity(),
                newClientDto.getAddressCreateDto().getStreet(),
                newClientDto.getAddressCreateDto().getNumber(),
                newClientDto.getAddressCreateDto().getPostalCode());

        Client client = new Client();

        if (address.isEmpty()) {
            Address newAddress = new Address();
            newAddress.setCity(newClientDto.getAddressCreateDto().getCity());
            newAddress.setStreet(newClientDto.getAddressCreateDto().getStreet());
            newAddress.setNumber(newClientDto.getAddressCreateDto().getNumber());
            newAddress.setPostalCode(newClientDto.getAddressCreateDto().getPostalCode());
            addressRepository.save(newAddress);
            client.setAddress(newAddress);
        } else {
            client.setAddress(address.get());
        }
        client.setForename(newClientDto.getForename());
        client.setSurname(newClientDto.getSurname());
        client.setEmail(newClientDto.getEmail());
        client.setDebt(0);
        clientRepository.save(client);
        return ClientMapper.clientToClientDto(client);
    }

    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }

    public List<ClientDto> findAll() {
        List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(ClientMapper::clientToClientDto)
                .collect(Collectors.toList());
    }

    public Long deleteClient(Long id) {
        try {
            clientRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return id;
    }
}
