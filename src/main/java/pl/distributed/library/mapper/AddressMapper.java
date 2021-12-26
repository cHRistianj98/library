package pl.distributed.library.mapper;

import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.entity.Address;


public class AddressMapper {

    public static AddressDto addressToAddressDto(Address address) {
        AddressDto addressDto = new AddressDto();
        addressDto.setCity(address.getCity());
        addressDto.setStreet(address.getStreet());
        addressDto.setNumber(address.getNumber());
        address.setPostalCode(address.getPostalCode());
        return addressDto;
    }
}
