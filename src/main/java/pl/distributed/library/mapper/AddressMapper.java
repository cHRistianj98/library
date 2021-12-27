package pl.distributed.library.mapper;

import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.BookDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Book;

import java.util.Set;
import java.util.stream.Collectors;


public class AddressMapper {
    public static Set<AddressDto> addressSetToAddressDtoSet(Set<Address> addresses) {
        return addresses.stream()
                .map(AddressMapper::addressToAddressDto)
                .collect(Collectors.toSet());
    }

    public static AddressDto addressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setAddressId(address.getAddressId());
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        addressDto.setPostalCode(address.getPostalCode());
        addressDto.setLibrary(LibraryMapper.libraryToLibraryDto(address.getLibrary()));
        addressDto.setClients(ClientMapper.clientSetToClientDtoSet(address.getClients()));
        return addressDto;
    }
}
