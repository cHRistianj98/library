package pl.distributed.library.mapper;

import pl.distributed.library.dto.LibraryDto;
import pl.distributed.library.entity.Library;

public class LibraryMapper {
    public static LibraryDto libraryToLibraryDto(Library library) {
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setId(library.getLibraryId());
        libraryDto.setAddressDto(AddressMapper.addressToAddressDto(library.getAddress()));
        return libraryDto;
    }
}
