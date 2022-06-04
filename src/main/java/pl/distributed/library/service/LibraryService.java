package pl.distributed.library.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.distributed.library.dto.AddressCreateDto;
import pl.distributed.library.dto.AddressDto;
import pl.distributed.library.dto.LibraryCreateDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Address;
import pl.distributed.library.entity.Library;
import pl.distributed.library.exception.LibraryAlreadyExistsException;
import pl.distributed.library.mapper.LibraryMapper;
import pl.distributed.library.repository.AddressRepository;
import pl.distributed.library.repository.LibraryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final AddressRepository addressRepository;
    private final LibraryRepository libraryRepository;

    @Autowired
    public LibraryService(AddressRepository addressRepository, LibraryRepository libraryRepository) {
        this.addressRepository = addressRepository;
        this.libraryRepository = libraryRepository;
    }

    @Transactional
    public LibraryCreateDto addLibrary(AddressCreateDto addressCreateDto) {
        Optional<Address> address = addressRepository.findByCityAndStreetAndNumberAndPostalCode(
                addressCreateDto.getCity(),
                addressCreateDto.getStreet(),
                addressCreateDto.getNumber(),
                addressCreateDto.getPostalCode());

        if (address.isEmpty()) {
            Address newAddress = new Address();
            newAddress.setCity(addressCreateDto.getCity());
            newAddress.setStreet(addressCreateDto.getStreet());
            newAddress.setNumber(addressCreateDto.getNumber());
            newAddress.setPostalCode(addressCreateDto.getPostalCode());
            addressRepository.save(newAddress);

            Library library = new Library();
            library.setAddress(newAddress);
            libraryRepository.save(library);

            return LibraryMapper.libraryToLibraryCreateDto(library);
        }

        throw new LibraryAlreadyExistsException();
    }

    public String deleteLibrary(String id) {
        libraryRepository.deleteById(id);
        return id;
    }

    public List<LibraryDto> findAll() {
        List<Library> libraries = libraryRepository.findAll();
        return libraries.stream()
                .map(LibraryMapper::libraryToLibraryDto)
                .collect(Collectors.toList());
    }

    public Optional<Library> findById(String id) {
        return libraryRepository.findById(id);
    }
}
