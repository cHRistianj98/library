package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class CustomerBorrowingDto {
    @NotNull
    private Long id;

    @NotNull
    private String forename;

    @NotNull
    private String surname;

    @NotNull
    @Email
    private String email;

    @NotNull
    private AddressCreateDto address;
}
