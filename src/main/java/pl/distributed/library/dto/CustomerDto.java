package pl.distributed.library.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import pl.distributed.library.entity.Borrowing;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
public class CustomerDto {
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

    // TODO: borrowings
//    private Set<Borrowing> borrowings;
}
