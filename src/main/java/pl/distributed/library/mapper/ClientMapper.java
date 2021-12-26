package pl.distributed.library.mapper;

import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.entity.Client;

public class ClientMapper {
    public static ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setAddressDto(AddressMapper.addressToAddressDto(client.getAddress()));
        clientDto.setClientId(client.getClientId());
        clientDto.setDebt(client.getDebt());
        clientDto.setEmail(client.getEmail());
        clientDto.setForename(client.getForename());
        clientDto.setSurname(client.getSurname());
        return clientDto;
    }
}
