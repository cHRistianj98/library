package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.*;
import pl.distributed.library.entity.Address;
import pl.distributed.library.exception.ResourceNotFoundException;
import pl.distributed.library.mapper.AddressMapper;
import pl.distributed.library.repository.AddressRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }

    public List<AddressDto> findAll() {
        List<Address> addresses = addressRepository.findAll();
        return addresses.stream()
                .map(AddressMapper::addressToAddressDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public AddressDto addAddress(AddressCreateDto addressCreateDto) {
        Address address = new Address();
        address.setPostalCode(addressCreateDto.getPostalCode());
        address.setNumber(addressCreateDto.getNumber());
        address.setStreet(addressCreateDto.getStreet());
        address.setCity(addressCreateDto.getCity());
        Address addressFromRepo = addressRepository.save(address);
        return AddressMapper.addressToAddressDto(addressFromRepo);
    }

    @Transactional
    public Long deleteAddress(Long id) {
        try {
            addressRepository.deleteById(id);
        } catch (EmptyResultDataAccessException ex) {
            throw new ResourceNotFoundException();
        }
        return id;
    }
}
