package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class LibraryDto {
    @NotNull
    private String id;

    @NotNull
    private AddressDto address;
}
