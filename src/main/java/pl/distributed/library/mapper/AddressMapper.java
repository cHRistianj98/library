//package pl.distributed.library.mapper;
//
//import pl.distributed.library.dto.AddressCreateDto;
//import pl.distributed.library.dto.AddressDto;
//import pl.distributed.library.entity.Address;
//
//import java.util.Objects;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//
//public class AddressMapper {
//    public static Set<AddressDto> addressSetToAddressDtoSet(Set<Address> addresses) {
//        return addresses.stream()
//                .map(AddressMapper::addressToAddressDto)
//                .collect(Collectors.toSet());
//    }
//
//    public static AddressDto addressToAddressDto(Address address) {
//        AddressDto addressDto = new AddressDto();
//        addressDto.setAddressId(address.getId());
//        addressDto.setCity(address.getCity());
//        addressDto.setStreet(address.getStreet());
//        addressDto.setNumber(address.getNumber());
//        addressDto.setPostalCode(address.getPostalCode());
//        if (Objects.nonNull(address.getLibrary())) {
//            addressDto.setLibraryId(address.getLibrary().getId());
//        }
//        if (Objects.nonNull(address.getCustomers())) {
//            addressDto.setCustomers(CustomerMapper.customerSetToCustomerDtoSet(address.getCustomers()));
//        }
//
//        return addressDto;
//    }
//
//    public static AddressCreateDto addressToAddressCreateDto(Address address) {
//        AddressCreateDto addressCreateDto = new AddressCreateDto();
//        addressCreateDto.setCity(address.getCity());
//        addressCreateDto.setStreet(address.getStreet());
//        addressCreateDto.setNumber(address.getNumber());
//        addressCreateDto.setPostalCode(address.getPostalCode());
//        return addressCreateDto;
//    }
//}
