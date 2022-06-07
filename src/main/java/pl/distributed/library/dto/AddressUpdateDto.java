package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddressUpdateDto {
    @NotNull
    private Long id;

    private String city;

    private String street;

    private String number;

    private String postalCode;
}
