package pl.distributed.library.mapper;

import pl.distributed.library.dto.LibraryCreateDto;
import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Library;

import java.util.Set;
import java.util.stream.Collectors;

public class LibraryMapper {
    public static Set<LibraryDto> librarySetToLibraryDtoSet(Set<Library> libraries) {
        return libraries.stream()
                .map(LibraryMapper::libraryToLibraryDto)
                .collect(Collectors.toSet());
    }

    public static LibraryDto libraryToLibraryDto(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getId());
        libraryDto.setAddress(AddressMapper.addressToAddressDto(library.getAddress()));
        return libraryDto;
    }

    public static LibraryCreateDto libraryToLibraryCreateDto(Library library) {
        LibraryCreateDto libraryCreateDto = new LibraryCreateDto();
        libraryCreateDto.setAddressCreateDto(AddressMapper.addressToAddressCreateDto(library.getAddress()));
        return libraryCreateDto;
    }
}
