package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class AddressDto {
    @NotNull
    private Long addressId;

    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String postalCode;

    @NotNull
    private Set<ClientDto> clients;

    @NotNull
    private LibraryDto library;
}
