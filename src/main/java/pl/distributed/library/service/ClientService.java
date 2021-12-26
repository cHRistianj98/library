package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.dto.NewClientDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Client;
import pl.distributed.library.mapper.ClientMapper;
import pl.distributed.library.repository.AddressRepository;
import pl.distributed.library.repository.ClientRepository;

import java.util.Optional;

@Service
public class ClientService {
    private ClientRepository clientRepository;
    private AddressRepository addressRepository;

    @Autowired
    public ClientService(ClientRepository clientRepository, AddressRepository addressRepository) {
        this.clientRepository = clientRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public ClientDto addClient(NewClientDto newClientDto) {
        Optional<Address> address = addressRepository.findByCityAndStreetAndNumberAndPostalCode(
                newClientDto.getAddressDto().getCity(),
                newClientDto.getAddressDto().getStreet(),
                newClientDto.getAddressDto().getNumber(),
                newClientDto.getAddressDto().getPostalCode());

        Client client = new Client();

        if (address.isEmpty()) {
            Address newAddress = new Address();
            newAddress.setCity(newClientDto.getAddressDto().getCity());
            newAddress.setStreet(newClientDto.getAddressDto().getStreet());
            newAddress.setNumber(newClientDto.getAddressDto().getNumber());
            newAddress.setPostalCode(newClientDto.getAddressDto().getPostalCode());
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
}
