package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LibraryDto {
    private Long id;

    private AddressDto addressDto;
}
