package pl.distributed.library.mapper;

import pl.distributed.library.dto.ClientDto;
import pl.distributed.library.entity.Client;

import java.util.Set;
import java.util.stream.Collectors;

public class ClientMapper {
    public static Set<ClientDto> clientSetToClientDtoSet(Set<Client> clients) {
        return clients.stream()
                .map(ClientMapper::clientToClientDto)
                .collect(Collectors.toSet());
    }

    public static ClientDto clientToClientDto(Client client) {
        ClientDto clientDto = new ClientDto();
        clientDto.setClientId(client.getClientId());
        clientDto.setDebt(client.getDebt());
        clientDto.setEmail(client.getEmail());
        clientDto.setForename(client.getForename());
        clientDto.setSurname(client.getSurname());
        return clientDto;
    }
}
