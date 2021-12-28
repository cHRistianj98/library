package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddressCreateDto {
    @NotNull
    private String city;

    @NotNull
    private String street;

    @NotNull
    private String number;

    @NotNull
    private String postalCode;
}
